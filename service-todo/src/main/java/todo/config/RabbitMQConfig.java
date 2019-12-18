package todo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置类模板
 *
 */
@Configuration
public class RabbitMQConfig {
    //待执行任务交换机
    public static final String EXCHANGE_TODO = "ex_my_task";

    //完成完成任务finish消息队列
    public static final String QUEUE_FINISH = "queue_finish_money222";
    //添加待执行任务todo队列
    public static final String QUEUE_TODO = "queue_todo_money222";

    //待执行任务todo路由key
    public static final String KEY_TODO = "key_todo_money";
    //完成任务路由key
    public static final String KEY_FINISH = "key_finish_money";

    /**
     * 交换机配置
     * @return the exchange
     */
    @Bean(EXCHANGE_TODO)
    public Exchange EX_DECLARE() {
        return ExchangeBuilder.directExchange(EXCHANGE_TODO).durable(true).build();
    }
    //声明队列 _finish
    @Bean(QUEUE_FINISH)
    public Queue QUEUE_DECLARE() {
        Queue queue = new Queue(QUEUE_FINISH,true,false,true);
        return queue;
    }

    //声明队列 _todo
    @Bean(QUEUE_TODO)
    public Queue QUEUE_DECLARE_2() {
        Queue queue = new Queue(QUEUE_TODO,true,false,true);
        return queue;
    }
    /**
     * 绑定finish队列到交换机 .
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding bindingFinishProcessTask(@Qualifier(QUEUE_FINISH) Queue queue, @Qualifier(EXCHANGE_TODO) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(KEY_FINISH).noargs();
    }
    /**
     * 绑定todo队列到交换机 .
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding bindingTodoProcessTask(@Qualifier(QUEUE_TODO) Queue queue, @Qualifier(EXCHANGE_TODO) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(KEY_TODO).noargs();
    }

}
