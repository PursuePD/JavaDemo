package com.example.算法.排序;

import java.util.Arrays;

/**
 * @Author:cuijialei
 * @Date: 2018/11/21
 * @Describe 选择排序
 */
public class SelectionSort {
    public static void main(String []arg){
        int[] ints = {3,21,12,99,44,23,44};

        int minIndex=0;
        for(int i=0 ; i<ints.length;i++){
            minIndex=i;//无序区的最小数据数组下标
            for(int j=i+1;j<ints.length;j++){
                if(ints[minIndex]>ints[j]){
                    minIndex = j;
                }
            }
            int temp=ints[i];
            ints[i]=ints[minIndex];
            ints[minIndex]=temp;
        }
        System.out.println(Arrays.toString(ints));
    }
}
