package com.example.算法.日常;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * @Author:cuijialei
 * @Date: 2019/7/2
 * @Describe
 *
 *      * 二进制求和
 *
 *      * 加1
 *      * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *      *
 *      * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 *      *
 *      * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *      * @param digits
 *      * @return
 *
 */
public class Test20190702 {

    /**
     * 二进制求和
     * 给定两个二进制字符串，返回它们的和（用二进制表示）。
     * 输入为非空字符串且只包含数字 1 和 0。
     * 示例 1:
     * 输入: a = "11", b = "1"
     * 输出: "100"
     * 示例 2:
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        Long numa = Long.valueOf(a,2);
        Long numb = Long.valueOf(b,2);

        return Long.toBinaryString(numa+numb);
    }
    public String addBinary1(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int ca = 0;
        for(int i = a.length() - 1, j = b.length() - 1;i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");
        return ans.reverse().toString();

    }
    /**
     * 加1
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        return plusOneF(digits,digits.length-1);
    }

    public static int[] plusOneF(int[] digits , int index){
        int num =  digits[index];
        if(index == 0){
            if(num == 9){
                int[] newDigits = new int[digits.length+1];
                newDigits[0] = 0;
                for(int i = 0;i<digits.length;i++){
                    newDigits[i+1] =  digits[i];
                }
                digits[index] = 0;
                return plusOneF(newDigits , index+1);//递归
            }
        }
        if(num == 9){
            digits[index] = 0;
            return plusOneF(digits , index-1);
        }
        digits[index] = digits[index]+1;

        return digits;
    }


    public static void main(String[] args) {
        int[] ints = new int[]{9,9,9,9};
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(plusOne(ints)));
        Vector vector;

        List list = Collections.synchronizedList(new ArrayList<>());
        Sets.newConcurrentHashSet();
        Map map = new HashMap();
        Map map1 = new Hashtable();

    }
}
