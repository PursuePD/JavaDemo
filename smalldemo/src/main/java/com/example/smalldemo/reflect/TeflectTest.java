package com.example.smalldemo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author:cuijialei
 * @Date: 2019/6/24
 * @Describe
 */
public class TeflectTest {

    public static void main1(String[] args) throws Exception {

        Model model = new Model();
        Class class1 =Class.forName("com.example.smalldemo.reflect.Model");
        Class class2 =model.getClass();
        Class class3 =Model.class;

        System.out.println(class1.equals(class2));
        System.out.println(class2.equals(class3));

        Object obj = class2.newInstance();
        ((Model) obj).setAge(10);
        ((Model) obj).setName("asdasd");
        System.out.println(((Model) obj).sout(""));


        Class<Model> class4 = Model.class;

    }


    public static void main(String[] args) throws Exception {

        Model model = new Model("a",12);
        Class class1 =Class.forName("com.example.smalldemo.reflect.Model");
        Object obj = class1.newInstance();

        System.out.println("*****************获取所有public方法*************");
        Method[] methodArray = class1.getMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }
        System.out.println("*****************获取所有方法*************");
        Method[] privateMethod = class1.getDeclaredMethods();
        for (Method method : privateMethod) {
            System.out.println(method);
        }
        System.out.println("*****************获取特定方法并使用*************");

        Method method1 = class1.getDeclaredMethod("privateSout",String.class);
        if(!method1.isAccessible()){
            method1.setAccessible(true); //类中的成员变量为private,故必须进行此操作
        }
        Object invoke = method1.invoke(obj,"aa有方法");
//        privateMethod.setAccessible(true);
//        Object obj;
//        obj = class1.newInstance();
//        privateMethod.invoke(obj, "asdasdasd");
        System.out.println("*****************获取构造使用*************");
        Constructor<?>[] constructors = class1.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        Model newModel = (Model)constructors[0].newInstance("我",1);
        newModel.sout("");

        System.out.println("*****************获取属性*************");
        Field[] fields = class1.getDeclaredFields();
        String nameVlues="";
        for (int i=0;i<fields.length;i++){//遍历
            //得到属性
            Field field = fields[i];
            //打开私有访问
            field.setAccessible(true);
            //获取属性
            String name = field.getName();
            //获取属性值
            Object value = field.get(obj);
            //一个个赋值
            nameVlues += field.getName()+":"+value+",";
        }
        System.out.println(nameVlues);
    }

}
