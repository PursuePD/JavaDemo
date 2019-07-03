package com.example.CollectionTest;

import com.example.java8.Stream.model.PersonModel;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:cuijialei
 * @Date: 2019/6/26
 * @Describe
 */
public class CollectionsSortTest {

    public static void main(String[] args) {
        List<PersonModel> list = new ArrayList<>();
        list.add(new PersonModel("a",18,true,178.1));
        list.add(new PersonModel("b",16,true,158.1));
        list.add(new PersonModel("c",21,true,168.1));
        list.add(new PersonModel("d",11,true,172.1));
        list.add(new PersonModel("e",15,true,153.1));
        list.add(new PersonModel("f",10,true,164.1));
        list.add(new PersonModel("g",23,true,174.1));
        list.add(new PersonModel("h",20,true,182.1));
        list.add(new PersonModel("i",18,true,154.1));


        Map<String,PersonModel> personModelMap = new HashMap<>();
        for (PersonModel personModel : list) {
            personModelMap.put(personModel.getName(),personModel);
        }
        Map<String,PersonModel> personModelMap1 = new Hashtable<>();
        Map<String,PersonModel> personModelMap2 = new LinkedHashMap<>();
        Map<String,PersonModel> personModelMap3 = new ConcurrentHashMap<>();

        Collections.sort(list,(o1, o2) -> (int)(o1.getHeight()*1000)-(int)(o2.getHeight()*1000) );
        printList(list);


//       CollectionUtils.
    }

    public static void printList(List list){
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0;i<nums.length;i++){
            for (int j = 0 ; j < nums.length ; j++){
                if(i == j){
                    continue;
                }
                if(nums[i]+nums[j] == target){
                    result[0] = nums[i];
                    result[1] = nums[j];
                    return result;
                }
            }
        }
        return result;
    }
}
