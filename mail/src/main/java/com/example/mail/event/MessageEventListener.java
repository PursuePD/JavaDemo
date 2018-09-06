package com.example.mail.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author:cuijialei
 * @Date: 2018/9/6
 * @Describe 监听器
 *
 * 监听异步需要@Async注解 启动类需要@EnableAsync注解 不然@Async无效
 */
@Component
public class MessageEventListener implements ApplicationListener<MessageEvent> {

    private final Logger logger = LoggerFactory.getLogger(MessageEventListener.class);

    @Override
    @Async
    public void onApplicationEvent(MessageEvent messageEvent) {
        for (int i=1;i<=10;i++){
            logger.info(i+"s");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("监听事件消息为：" + messageEvent.getMessage());
    }
}
