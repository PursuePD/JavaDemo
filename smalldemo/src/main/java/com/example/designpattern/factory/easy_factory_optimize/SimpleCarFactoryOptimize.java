package com.example.designpattern.factory.easy_factory_optimize;

import com.example.designpattern.factory.Car;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author:cuijialei
 * @Date: 2019/7/26
 * @Describe
 */
public class SimpleCarFactoryOptimize {
    // 缓存Car子类的Class实例
    private static Map<String, Car> carMap = new HashMap<>();

    static {
        // 读取配置文件
        InputStream is = SimpleCarFactoryOptimize.class.getResourceAsStream("/car.properties");
        Properties properties = new Properties();
        try {

            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Enumeration<?> en = properties.propertyNames();
        while(en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String className = properties.getProperty(key);
            try {
                //通过反射实例化放入map
                Class<Car> clazz = (Class<Car>) Class.forName(className);
                Car car = (Car)clazz.newInstance();
                carMap.put(key, car);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    // 获取产品实例的方法
    public static Car getInstance(String carName) {
        if(carMap.containsKey(carName)) {
            return carMap.get(carName);
        }
        throw new RuntimeException("根据[" + carName+"]查找不到实例");
    }
}
