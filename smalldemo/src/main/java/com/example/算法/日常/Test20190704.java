package com.example.算法.日常;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:cuijialei
 * @Date: 2019/7/4
 * @Describe
 */
public class Test20190704 {
    public static void main(String[] args) {
        /**
         * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
         *
         * 示例 1:
         *
         * 输入: "abcabcbb"
         * 输出: 3
         * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
         */
        //System.out.println(lengthOfLongestSubstring2("pwwkewa"));

        /**
         * --------------------------------------------------------------------------
         * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
         *
         * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
         *
         */
        //int[] nums1 = new int[]{0,0,1,1,1,2,2,3,3,4};
        //System.out.println(removeDuplicates(nums1));

        /**
         * --------------------------------------------------------------------------
         * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
         * 进阶:
         * 你能不将整数转为字符串来解决这个问题吗？
         */

       // System.out.println(getNumberByRightIndex(10,1));
       // System.out.println(getNumberByLeftIndex(10,1));
        System.out.println(isPalindrome(10101+""));

    }

    /**
     * 执行用时 :140 ms, 在所有 Java 提交中击败了18.12%的用户
     * 内存消耗 :39.3 MB 在所有 Java 提交中击败了81.37%的用户
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int maxLength = 0;
            for(int j = 0;j<chars.length;j++){
                int length = 1;
                for(int k = j+1 ; k < chars.length ; k++){
                    boolean isTure = false;
                    for(int i = k-1 ; i >= j ; i --){
                        if(chars[i] == chars[k]){
                            isTure = true;
                            break;
                        }
                    }
                    if(isTure){
                        break;
                    }else{
                        length++;
                    }
                }
                maxLength = (maxLength<length?length:maxLength);
            }
        return maxLength;
    }

    /**
     * 执行用时 :77 ms, 在所有 Java 提交中击败了24.90%的用户
     * 内存消耗 :38.2 MB, 在所有 Java 提交中击败了89.41%的用户
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        char[] chars = s.toCharArray();
        int maxLength = 0;
        for(int j = 0;j<chars.length;j++){
            int length = 1;
            Map<Character , String > map = new HashMap<>(16);
            map.put(chars[j],"");
            for(int k = j+1 ; k < chars.length ; k++){
                if(map.containsKey(chars[k])){
                    break;
                }else {
                    map.put(chars[k],"");
                    length++;
                }
            }
            map.clear();
            maxLength = (maxLength<length?length:maxLength);
        }
        return maxLength;
    }

    /**
     * 执行用时 :29 ms, 在所有 Java 提交中击败了61.21%的用户
     * 内存消耗 :39.9 MB, 在所有 Java 提交中击败了75.70%的用户
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        char[] chars = s.toCharArray();
        int maxLength = 0;
        Map<Character , Integer > map = new HashMap<>(16);
        for(int end = 0,start = 0;end<chars.length;end++){
            char charVal = chars[end];
            if(map.containsKey(charVal)){
                start = (map.get(charVal)>start)?map.get(charVal):start;
            }
            maxLength =  maxLength>(end + 1 - start)?maxLength:(end + 1 - start);
            map.put(charVal,end+1);
        }
        return maxLength;
    }


    public static int removeDuplicates(int[] nums) {
        Map<Integer,Integer> map = new HashMap(16);
        for (int num : nums) {
            map.put(num,num);
        }
        return  map.size();
    }


    /**
     * 执行用时 :283 ms, 在所有 Java 提交中击败了5.11%的用户
     * 内存消耗 :37.6 MB, 在所有 Java 提交中击败了86.80%的用户
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        int length = Integer.toString(x).length();
        if(x < 0){
            return false;
        }

        int xunhuancishu = (length%2 == 0)?(length/2):(length-1)/2;
        for(int i = 1; i <= xunhuancishu ; i ++ ){
            int rigth = getNumberByRightIndex(x,i);
            int left = getNumberByLeftIndex(x,i);
            System.out.println(rigth+","+left);
            if(rigth != left){
                return false;
            }
        }
        return true;
    }
    public static int getNumberByLeftIndex(int number ,int index){
        int length = Integer.toString(number).length();
        for(int i=0;i<length-index;i++)
        {
            number = number/10;
        }
        number = number%10;
        return number;
    }
    public static int getNumberByRightIndex(int number ,int index){
        int length = Integer.toString(number).length();
        for(int i=0;i<index-1;i++)
        {
            number = number/10;
        }
        number = number%10;
        return number;
    }

    /**
     * 官方题解
     * 执行用时 :50 ms, 在所有 Java 提交中击败了60.36%的用户
     * 内存消耗 :37.8 MB, 在所有 Java 提交中击败了86.16%的用户
     */
    public static boolean isPalindrom1e(int x) {
        //思考：这里大家可以思考一下，为什么末尾为 0 就可以直接返回 false
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }


    /**
     * @return
     */
    public static boolean isPalindrome(String str) {
        char[] chars = str.toCharArray();
        int length = chars.length;
        int xunhuancishu = (length%2 == 0)?(length/2):(length-1)/2;
        String first = str.substring(0,xunhuancishu);
        StringBuilder end = new StringBuilder();
        for (int i = 0;i < xunhuancishu ; i++){
            end.append(chars[length-i-1]);
        }
        System.out.println("first:"+first +"  end:"+end);

        return first.equals(end.toString());
    }

}
