package com.example.mail.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author:cuijialei
 * @Date: 2018/9/6
 * @Describe 事件  需要继承ApplicationEvent
 */
public class MessageEvent extends ApplicationEvent {

    /**
     * 将需要的参数传递给监听器listener
     */
    private String message;

    public MessageEvent(Object source) {
        super(source);
    }

    public MessageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
