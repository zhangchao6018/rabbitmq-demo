package todo.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.dao.TaskHisRepository;
import todo.dao.TaskRepository;
import todo.mq.ChooseCourseTask;
import todo.pojo.Task;
import todo.pojo.TaskHis;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 任务业务实现类
 **/
@Service
public class TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChooseCourseTask.class);

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    TaskHisRepository taskHisRepository;

    public List<Task> findTaskList(Date updateTime , int size){
        //设置分页参数
        Pageable pageable = new PageRequest(0,size);
        //查询前n条任务
        Page<Task> all = taskRepository.findByUpdateTimeBefore(pageable, updateTime);
        List<Task> list = all.getContent();
        return list;
    }

    //发布消息
    public void publish(Task task, String ex, String routingKey){
        //查询这个任务，更新时间
        Optional<Task> optional = taskRepository.findById(task.getId());
        if (optional.isPresent()){
            //转化为json字符串发送
            rabbitTemplate.convertAndSend(ex,routingKey, JSON.toJSONString(task));
            LOGGER.info("消息发送成功"+task);
            //更新任务时间
            Task one = optional.get();
            one.setUpdateTime(new Date());
            taskRepository.save(one);
        }
    }

    /**
     * 乐观锁取任务
     * @param id
     * @param version
     * @return
     */
    @Transactional
    public int getTask(String id, Integer version) {
        int i = taskRepository.updateTaskVersion(id, version);
        return  i;
    }

    /**
     * 完成任务
     * @param task
     */
    public void finishTask(Task task) {
        Optional<Task> optional = taskRepository.findById(task.getId());
        if (optional.isPresent()) {
            //删除任务
            Task dbTask = optional.get();
            taskRepository.delete(dbTask);
            //新增历史任务记录
            Optional<TaskHis> dbTaskHis = taskHisRepository.findById(task.getId());
            if (!dbTaskHis.isPresent()) {
                TaskHis taskHis = new TaskHis();
                BeanUtils.copyProperties(dbTask,taskHis);
                taskHisRepository.save(taskHis);
            }
            System.out.println("finish。。。");
        }

    }
}


