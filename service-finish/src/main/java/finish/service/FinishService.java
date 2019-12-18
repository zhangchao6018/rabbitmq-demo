package finish.service;

import finish.dao.FinishRepository;
import finish.dao.TaskHisRepository;
import finish.pojo.LearningCourse;
import finish.pojo.Task;
import finish.pojo.TaskHis;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-08-04 13:18
 **/
@Service
public class FinishService {
    @Autowired
    private FinishRepository finishRepository;
    @Autowired
    private TaskHisRepository taskHisRepository;

    /**
     * 完成选课
     * @param userId
     * @param courseId
     * @param valid
     * @param startTime
     * @param endTime
     * @param task
     * @return -1：失败，0：成功
     */
    @Transactional
    public String addcourse(String userId, String courseId, String valid, Date startTime, Date endTime, Task task) {
        //参数校验。。。
        if (StringUtils.isEmpty(courseId)) {
            return "-1";
        }
        //根据userId，courseId查询课程记录
        LearningCourse learningCourse = finishRepository.findByUserIdAndCourseId(userId, courseId);
        if (Objects.nonNull(learningCourse)){
            //更新
            learningCourse.setStartTime(startTime);
            learningCourse.setEndTime(endTime);
            learningCourse.setStatus("501001");
            finishRepository.save(learningCourse);

        }else {
            //新增
            learningCourse = new LearningCourse();
            learningCourse.setUserId(userId);
            learningCourse.setCourseId(courseId);
            learningCourse.setStartTime(startTime);
            learningCourse.setEndTime(endTime);
            learningCourse.setStatus("501001");
            finishRepository.save(learningCourse);
        }
        //根据task_id查询该任务是否存在
        Optional<TaskHis> byId = taskHisRepository.findById(task.getId());
        if (!byId.isPresent()) {
            //新增记录
            TaskHis taskHis = new TaskHis();
            BeanUtils.copyProperties(task, taskHis);
            taskHisRepository.save(taskHis);
        }
        return "0";
    }
}
