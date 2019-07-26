#### **简单工厂模式**

##### 概述

简单工厂模式是属于**创建型**模式，又叫做静态工厂方法（Static Factory Method）模式，但**不属于**23种GOF设计模式之一。简单工厂模式是由一个工厂对象决定创建出哪一种产品类的实例。简单工厂模式是工厂模式家族中最简单实用的模式，可以理解为是不同工厂模式的一个特殊实现
![1564108895951](\1564108895951.png)

简单工厂模式的实质是由一个工厂类根据传入的参数，动态决定应该创建哪一个产品类（这些产品类继承自一个父类或接口）的实例。
该模式中包含的角色及其**职责**：
**（1）工厂角色（Creator）：**
简单工厂模式的核心，它负责实现创建所有实例的内部逻辑。工厂类的创建产品类的方法可以被外界直接调用，创建所需的产品对象。
**（2）抽象产品角色（Product）：**
简单工厂模式所创建的所有对象的父类，它负责描述所有实例所共有的公共接口。
**（3）具体产品角色（Concrete Product）：**
是简单工厂模式的创建目标，所有创建的对象都是充当这个角色的某个具体类的实例。

工厂类是整个模式的关键。包含了必要的逻辑判断，根据外界给定的信息，决定究竟应该创建哪个具体类的对象。通过使用工厂类,外界可以从直接创建具体产品对象的尴尬局面摆脱出来，仅仅需要负责“消费”对象就可以了。而不必管这些对象究竟如何创建及如何组织的。明确了各自的职责和权利，有利于整个软件体系结构的优化。

##### 2、缺点

由于工厂类集中了所有实例的创建逻辑，这违反了**高内聚责任分配原则**，将全部创建逻辑集中到了一个工厂类中；它所能创建的类只能是事先考虑到的，**如果需要添加新的类，则就需要改变工厂类了，这违反了开闭原则。**

当系统中的具体产品类不断增多时候，可能会出现要求工厂类根据不同条件创建不同实例的需求。这种对条件的判断和对具体产品类型的判断交错在一起，很难避免模块功能的蔓延，对系统的维护和扩展非常不利。

#### 简单工厂模式优化

1.使用配置文件和反射来创建类

```java

public class SimpleFactory {
    // 缓存Car子类的实例
    private static Map<String, Car> carMap = new HashMap<>();

    static {
        InputStream is = SimpleFactory.class.getResourceAsStream("car.properties");
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
                Class<?> clazz = Class.forName(className);
                // 预先实例化
                Car car = (Car) clazz.newInstance();
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
```
对应的配置文件 /car.properties
``` 
bmw320=com.example.designpattern.factory.BMW320
bmw740=com.example.designpattern.factory.BMW740
bmw530=com.example.designpattern.factory.BMW530
```

2.单例

```java
 private static Map<String, Car> carMap = new ConcurrentHashMap<>();

 Class<Car> clazz = (Class<Car>) Class.forName(className);
 Car car = (Car)clazz.newInstance();
 carMap.put(key, car);
```
