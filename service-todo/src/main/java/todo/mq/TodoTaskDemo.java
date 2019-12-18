package todo.mq;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import todo.config.RabbitMQConfig;
import todo.pojo.Task;
import todo.service.TaskService;
import org.apache.commons.lang3.StringUtils;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-08-02 15:58
 **/
@Component
public class TodoTaskDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChooseCourseTask.class);

    @Autowired
    private TaskService taskService;

    @Scheduled(cron = "0/3 * * * * *")
    public void sendTsakTodo(){
        //得到1分钟之前的时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(GregorianCalendar.MINUTE,-1);
        Date time = calendar.getTime();
        //查询
        List<Task> tasks = taskService.findTaskList(time,100);
        for (Task task : tasks) {
            //用乐观锁获取当前任务
            if (taskService.getTask(task.getId() , task.getVersion())>0){
                String ex = task.getMqExchange();
                String routingKey = task.getMqRoutingkey();
                //发送消息
                taskService.publish(task,ex,routingKey);
                System.out.println("send success...");
            }
        }

    }

    /**
     * 监听任务完成的消息
     * @param taskStr
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_FINISH)
    public void receiveChoosecourseTask(String taskStr){
        Task task = JSONObject.parseObject(taskStr, Task.class);
        System.out.println("接收到完成任务消息："+task);
        //将任务设置成完成（任务列表删除，生成任务历史记录）
        if(task!=null && StringUtils.isNotEmpty(task.getId())){
            taskService.finishTask(task);
        }
        taskService.finishTask(task);
        System.out.println("task finished...");

    }

}
