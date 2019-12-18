package todo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.pojo.TaskHis;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-08-04 13:26
 **/
public interface TaskHisRepository extends JpaRepository<TaskHis,String> {
}
