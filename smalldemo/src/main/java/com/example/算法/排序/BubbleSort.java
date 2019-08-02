package com.example.算法.排序;

import java.util.Arrays;

/**
 * @Author:cuijialei
 * @Date: 2018/11/21
 * @Describe 冒泡排序
 */
public class BubbleSort {

    public static void main(String []arg){
        int[] ints = {3,21,12,99,44,23,44};
        for(int i=0 ; i<ints.length;i++){
            for(int j=0;j<ints.length-i-1;j++){
                if(ints[j]>ints[j+1]){
                    int temp = ints[j];
                    ints[j]=ints[j+1];
                    ints[j+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(ints));
    }



}
