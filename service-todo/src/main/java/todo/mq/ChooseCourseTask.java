package todo.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import todo.service.TaskService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Administrator
 * @version 1.0
 **/
//@Component
public class ChooseCourseTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChooseCourseTask.class);

    @Autowired
    private TaskService taskService;


    @Scheduled(cron="0/3 * * * * *")
    //定时发送加选课任务
    public void sendChoosecourseTask(){
        //得到1分钟之前的时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(GregorianCalendar.MINUTE,-1);
        Date time = calendar.getTime();


       /* XcTask xcTask = new XcTask();
        xcTask.setMqExchange(RabbitMQConfig.EXCHANGE_TODO);
        xcTask.setMqRoutingkey(RabbitMQConfig.KEY_TODO);
        //取任务
        String ex = xcTask.getMqExchange();//要发送的交换机
        String routingKey = xcTask.getMqRoutingkey();//发送消息要带routingKey
        taskService.publish(xcTask,ex,routingKey);
        System.out.println("发送完成");*/

    }

    //定义任务调试策略
//    @Scheduled(cron="0/3 * * * * *")//每隔3秒去执行
//       @Scheduled(fixedRate = 3000) //在任务开始后3秒执行下一次调度
//       @Scheduled(fixedDelay = 3000) //在任务结束后3秒后才开始执行
        public void task1(){
        LOGGER.info("===============测试定时任务1开始===============");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("===============测试定时任务1结束===============");

    }

    //定义任务调试策略
//    @Scheduled(cron="0/3 * * * * *")//每隔3秒去执行
//    @Scheduled(fixedRate = 3000) //在任务开始后3秒执行下一次调度
//       @Scheduled(fixedDelay = 3000) //在任务结束后3秒后才开始执行
    public void task2(){
        LOGGER.info("===============测试定时任务2开始===============");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("===============测试定时任务2结束===============");

    }
}
