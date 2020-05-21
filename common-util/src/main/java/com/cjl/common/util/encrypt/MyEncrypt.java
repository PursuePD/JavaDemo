package com.cjl.common.util.encrypt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 小崔
 * @create: 2020-05-15 17:49
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyEncrypt {

    /**
     * 是否是集合
     * @return
     */
    boolean isCollection() default true;

    /**
     * 需加密Bean路径
     * @return
     */
    String beanRoute();

    /**
     * 密文替换符号
     * @return
     */
    String encryptStr() default "****";

    /**
     * 密文开始序号
     * @return
     */
    int start() default 0;

    /**
     * 加密长度
     * @return
     */
    int length() default 4;

    /**
     * 返回结果最外层类型
     * @return
     */
    Class resultClass();

    /**
     * 加密字段名
     * @return
     */
    String[] fieldNames();

}
