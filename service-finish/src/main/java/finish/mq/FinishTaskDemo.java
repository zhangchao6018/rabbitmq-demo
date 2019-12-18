package finish.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import finish.config.RabbitMQConfig;
import finish.pojo.Task;
import finish.service.FinishService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**rabbitmq完成任务接收器
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-08-02 20:21
 **/
@Component
public class FinishTaskDemo {

    @Autowired
    private FinishService finishService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_TODO)
    public void receiveChoosecourseTask(String taskStr){
        Task task = JSONObject.parseObject(taskStr, Task.class);
        System.out.println("接收到任务消息："+task);
        //取出任务内容体
        String requestBody = task.getRequestBody();
        Map map = JSON.parseObject(requestBody, Map.class);
        String userId = (String) map.get("userId");
        String courseId = (String) map.get("courseId");
        //解析出valid, Date startTime, Date endTime...

        //添加选课
        //String userId, String courseId, String valid, Date startTime, Date endTime, XcTask xcTask
        String addcourse = finishService.addcourse(userId, courseId, null, null, null, task);
        if ("0".equals(addcourse)) {
            //要向mq发送完成任务的消息
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TODO,RabbitMQConfig.KEY_FINISH,JSON.toJSONString(task));
        }

    }
}
