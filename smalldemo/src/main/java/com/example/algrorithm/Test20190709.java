package com.example.algrorithm;

import com.sun.javafx.collections.MappingChange;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:cuijialei
 * @Date: 2019/7/9
 * @Describe
 */
public class Test20190709 {

    public static void main(String[] args) {
        /**
         * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
         */
       // System.out.println(toLowerCase("aAZ"));
       // System.out.println((char)(65));


        /**
         * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/submissions/
         * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
         * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
         * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
         *
         * 示例 1:
         * 输入: [7,1,5,3,6,4]
         * 输出: 7
         * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
         *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
         * 示例 2:
         * 输入: [1,2,3,4,5]
         * 输出: 4
         * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
         *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
         *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
         * 示例 3:
         * 输入: [7,6,4,3,1]
         * 输出: 0
         * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
         */
        int[] i = new int[]{7,6,4,3,1};
        System.out.println(maxProfit(i));

    }

    /**
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :34.4 MB, 在所有 Java 提交中击败了56.30%的用户
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0;i<chars.length ; i ++) {
            int intA = (int)chars[i];
            if(intA>= 65 && intA <= 90){
                intA = intA+32;
                chars[i] = (char)intA;
            }
        }
        return String.valueOf(chars);
    }

    public static String toLowerCase1(String str) {
        return  str.toLowerCase();
    }

    /**
     * 执行用时 :3 ms, 在所有 Java 提交中击败了54.94%的用户
     * 内存消耗 :38.7 MB, 在所有 Java 提交中击败了27.36%的用户
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if(prices.length <= 1){
            return 0;
        }

        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]){
                i++;
            }
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]){
                i++;
            }
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }

    /**
     *该解决方案遵循 方法二 的本身使用的逻辑，但有一些轻微的变化。在这种情况下，
     * 我们可以简单地继续在斜坡上爬升并持续增加从连续交易中获得的利润，而不是在谷之后寻找每个峰值。
     * 最后，我们将有效地使用峰值和谷值，但我们不需要跟踪峰值和谷值对应的成本以及最大利润
     * ，但我们可以直接继续增加加数组的连续数字之间的差值，如果第二个数字大于第一个数字，我们获得的总和将是最大利润。
     * 这种方法将简化解决方案。 这个例子可以更清楚地展现上述情况：
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

}
