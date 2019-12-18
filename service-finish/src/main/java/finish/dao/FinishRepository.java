package finish.dao;

import finish.pojo.LearningCourse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-08-04 13:21
 **/
public interface FinishRepository extends JpaRepository<LearningCourse ,String> {
    //根据用户id和课程id查询
    LearningCourse findByUserIdAndCourseId(String userId, String courseId);
}
