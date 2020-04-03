package com.example.java8.Stream;

import com.example.java8.Stream.model.PersonModel;
import com.example.java8.Stream.model.PersonModelCopy;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:cuijialei
 * @Date: 2019/4/23
 * @Describe
 */
public class StreamTest {

    private static List<PersonModel> list = null;

    static {
        list = new ArrayList<>();
        PersonModel zhang = new PersonModel("zhangsan",18,true,170.5);
        PersonModel lisi = new PersonModel("lisi",19,true,182.1);
        PersonModel wangwu = new PersonModel("wangwu",22,true,175.0);
        PersonModel liufang = new PersonModel("liufang",18,false,166.5);
        PersonModel wangqi = new PersonModel("wangqi",19,false,172.5);
        PersonModel zhangsan = new PersonModel("zhangsan",15,false,162.5);
        list.add(zhang);
        list.add(lisi);
        list.add(wangwu);
        list.add(liufang);
        list.add(wangqi);
        list.add(zhangsan);
    }

    public static List<PersonModel> getData(){
        return list;
    }

    /**
     * filter
     * 1、遍历数据并检查其中的元素时使用。
     * 2、filter接受一个函数作为参数，该函数用Lambda表达式表示。
     */

    public static void fiterSex(){
        List<PersonModel> data = getData();


    }

    /**
     * 获取某个属性
     */
    public static void getAgeList(){
        List<Integer> tempIdList = list.stream().map(PersonModel::getAge).collect(Collectors.toList());
        System.out.println(tempIdList);
    }

    /**
     * 转为list
     */
    public static void getList(){
        List<PersonModelCopy> copy = new ArrayList<>();
        copy = list.stream().map(personModel -> {
            PersonModelCopy personModelCopy = new PersonModelCopy();
            BeanUtils.copyProperties(personModel, personModelCopy);
            personModelCopy.setHeight(111);
            return personModelCopy;
        }).collect(Collectors.toList());
        System.out.println(copy);
    }
    /**
     * 转为linkedlist
     */
    public static void getLinkedList(){
        LinkedList<PersonModelCopy> copy = list.stream().map(personModel -> {
            PersonModelCopy personModelCopy = new PersonModelCopy();
            BeanUtils.copyProperties(personModel, personModelCopy);
            personModelCopy.setHeight(111);
            return personModelCopy;
        }).collect(Collectors.toCollection(LinkedList::new));
        System.out.println(copy);
    }

    /**
     * 转为map
     */
    public static void getMap( List<PersonModel> personModels){
        Map<String,PersonModelCopy> copy  = list.stream().map(personModel -> {
            PersonModelCopy personModelCopy = new PersonModelCopy();
            BeanUtils.copyProperties(personModel, personModelCopy);
            personModelCopy.setHeight(111);
            return personModelCopy;
        }).collect(Collectors.toMap(PersonModelCopy::getName,personModelCopy->personModelCopy));

        System.out.println(copy);
    }


    private void print(List<PersonModel> list){
        list.forEach(personModel -> System.out.println(personModel));
    }

    public static void main(String[] args) {
        getList();
    }
}
