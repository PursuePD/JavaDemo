package com.example.java8.Stream;

import com.example.java8.Stream.model.PersonModel;

import java.util.ArrayList;
import java.util.List;
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

    public static void getAgeList(){
        List<Integer> tempIdList = list.stream().map(PersonModel::getAge).collect(Collectors.toList());
        System.out.println(tempIdList);
    }


    private void print(List<PersonModel> list){
        list.forEach(personModel -> System.out.println(personModel));
    }

    public static void main(String[] args) {
        getAgeList();
    }
}
