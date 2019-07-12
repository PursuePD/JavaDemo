package com.example.designmode.dynamic.Interface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author:cuijialei
 * @Date: 2019/7/12
 * @Describe
 */
public class ProxyCompany implements InvocationHandler {

    private Object factory;


    /**
     * 通过proxy获取动态代理对象
     */
    public Object getProxyInstance(){
        return  Proxy.newProxyInstance(factory.getClass().getClassLoader(),factory.getClass().getInterfaces(),this);
    }

    @Override
    /**
     * 通过动态代理对象对方法进行增强
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        preMethod();
        Object ret = method.invoke(factory,args);
        backMethod();
        return ret;
    }

    private void preMethod(){
        System.out.println("前置服务");
    }

    private void backMethod(){
        System.out.println("后继服务");
    }

    public Object getFactory() {
        return factory;
    }

    public void setFactory(Object factory) {
        this.factory = factory;
    }
}
