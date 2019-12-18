package todo.pojo;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**MQ任务历史表
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-08-02 14:47
 **/
@Data
@ToString
@Entity
@Table(name = "task_his")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class TaskHis implements Serializable {
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    @Column(length = 32)
    private String id;

    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "delete_time")
    private Date deleteTime;
    @Column(name = "task_type")
    private String taskType;
    @Column(name = "mq_exchange")
    private String mqExchange;
    @Column(name = "mq_routingkey")
    private String mqRoutingkey;
    @Column(name = "request_body")
    private String requestBody;
    private Integer version;
    private String status;
}
