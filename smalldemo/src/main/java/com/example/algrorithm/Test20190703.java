package com.example.algrorithm;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author:cuijialei
 * @Date: 2019/7/3
 * @Describe
 */
public class Test20190703 {


    public static void main(String[] args) {
        /**
         * 题目1
         * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
         * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
         * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
         * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
         *
         * 说明:
         * 单词是指由非空格字符组成的字符序列。
         * 每个单词的长度大于 0，小于等于 maxWidth。
         * 输入单词数组 words 至少包含一个单词。
         */
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        fullJustify(words,20);
        String[] words1 = new String[]{"What","must","be","acknowledgment","shall","be"};
        fullJustify(words1,16);
        String[] words2 = new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        fullJustify(words2,20);


    }

    /**
     * @param words
     * @param maxWidth
     * 执行用时 :7 ms, 在所有 Java 提交中击败了6.06%的用户
     * 内存消耗 :35.5 MB, 在所有 Java 提交中击败了31.17%的用户
     * @return
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new LinkedList<>();
        int youbiao = 0;
        boolean isKongge = false;
        while (true){
            int lineLength = 0;
            List<String> line = new ArrayList<>();
            for(int i = youbiao ; i< words.length ; i++){
                int thisWordLength = words[i].length();
                if(thisWordLength>maxWidth){
                    //给的maxWidth太小了
                    return null;
                }
                //第一个单词
                else if(line.size() == 0 && thisWordLength == maxWidth){
                    lineLength += thisWordLength;
                    line.add(words[i]);
                    youbiao ++;
                }
                else if(lineLength + thisWordLength + 1 > maxWidth){
                    //todo 放不下了，可以跳出了
                    break;
                }else{
                    //todo 加入该单词并判断是否加空格  加游标
                    // 第一个单词不加，其他单词都要在前面加空格
                    youbiao ++;
                    if(line.size() == 0){
                        lineLength = lineLength + thisWordLength;
                        line.add(words[i]);
                    }else{
                        lineLength = lineLength + thisWordLength + 1;
                        line.add(" ");
                        line.add(words[i]);
                    }
                }
            }
            //todo 处理该行格式加空格
            if(maxWidth > lineLength){
                int chaZhi = maxWidth - lineLength;
                //最后一行为左对齐
                if(youbiao >= words.length){
                    StringBuilder stringBuilderSpace = new StringBuilder();
                    for(int i=0 ; i<chaZhi;i++){
                        stringBuilderSpace.append(" ");
                    }
                    line.add(stringBuilderSpace.toString());
                }
                else if(line.size()>2){
                    while (true){
                        for(int j = 1; j < line.size() ; j = j +2,chaZhi--){
                            if(chaZhi == 0){
                                break;
                            }
                            line.set(j,line.get(j)+" ");
                        }
                        if(chaZhi == 0){
                            break;
                        }
                    }
                }else{
                    for(int i=0 ; i<chaZhi;i++){
                        line.add(" ");
                    }
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : line) {
                stringBuilder.append(s);
            }
            list.add(stringBuilder.toString());
            System.out.println(stringBuilder.toString());
            //跳出循环
            if(youbiao >= words.length){
                break;
            }
        }

        return list;
    }


    public static String getSpace(int size){
        StringBuilder stringBuilderSpace = new StringBuilder();
        for(int i=0 ; i<size;i++){
            stringBuilderSpace.append(" ");
        }
        return stringBuilderSpace.toString();
    }

    public List<String> fullJustify1(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        //当前行单词已经占用的长度
        int currentLen = 0;
        //保存当前行的单词
        List<String> row = new ArrayList<>();
        //遍历每个单词
        for (int i = 0; i < words.length;) {
            //判断加入该单词是否超过最长长度
            //分了两种情况，一种情况是加入第一个单词，不需要多加 1
            //已经有单词的话，再加入单词，需要多加个空格，所以多加了 1
            if (currentLen == 0 && currentLen + words[i].length() <= maxWidth
                    || currentLen > 0 && currentLen + 1 + words[i].length() <= maxWidth) {
                row.add(words[i]);
                if (currentLen == 0) {
                    currentLen = currentLen + words[i].length();
                } else {
                    currentLen = currentLen + 1 + words[i].length();
                }
                i++;
                //超过的最长长度，对 row 里边的单词进行处理
            } else {
                //计算有多少剩余，也就是总共的空格数，因为之前计算 currentLen 多算了一个空格，这里加回来
                int sub = maxWidth - currentLen + row.size() - 1;
                //如果只有一个单词，那么就直接单词加空格就可以
                if (row.size() == 1) {
                    String blank = getStringBlank(sub);
                    ans.add(row.get(0) + blank);
                } else {
                    //用来保存当前行的结果
                    StringBuilder temp = new StringBuilder();
                    //将第一个单词加进来
                    temp.append(row.get(0));
                    //计算平均空格数
                    int averageBlank = sub / (row.size() - 1);
                    //如果除不尽，计算剩余空格数
                    int missing = sub - averageBlank * (row.size() - 1);
                    //前 missing 的空格数比平均空格数多 1
                    String blank = getStringBlank(averageBlank + 1);
                    int k = 1;
                    for (int j = 0; j < missing; j++) {
                        temp.append(blank + row.get(k));
                        k++;
                    }
                    //剩下的空格数就是求得的平均空格数
                    blank = getStringBlank(averageBlank);
                    for (; k < row.size(); k++) {
                        temp.append(blank + row.get(k));
                    }
                    //将当前结果加入
                    ans.add(temp.toString());

                }
                //清空以及置零
                row = new ArrayList<>();
                currentLen = 0;

            }
        }
        //单独考虑最后一行，左对齐
        StringBuilder temp = new StringBuilder();
        temp.append(row.get(0));
        for (int i = 1; i < row.size(); i++) {
            temp.append(" " + row.get(i));
        }
        //剩余部分用空格补齐
        temp.append(getStringBlank(maxWidth - currentLen));
        //最后一行加入到结果中
        ans.add(temp.toString());
        return ans;
    }
    //得到 n 个空白
    private String getStringBlank(int n) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append(" ");
        }
        return str.toString();
    }

}
