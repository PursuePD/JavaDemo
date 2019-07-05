package com.example.algrorithm;

import javax.xml.soap.Node;
import java.util.*;

/**
 * @Author:cuijialei
 * @Date: 2019/7/5
 * @Describe
 */
public class Test20190705 {

    public static void main(String[] args) {
        /**
         * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
         *
         * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
         * 输入: head = [4,5,1,9], node = 5
         * 输出: [4,1,9]
         * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
         *
         */
        /**
         * 给定一个二叉树，检查它是否是镜像对称的。
         *
         * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
         *
         *     1
         *    / \
         *   2   2
         *  / \ / \
         * 3  4 4  3
         *.但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
         *     1
         *    / \
         *   2   2
         *    \   \
         *    3    3
         */

//        TreeNode root = new TreeNode(1);
//        TreeNode treeNode2= new TreeNode(2);
//        TreeNode treeNode3 = new TreeNode(2);
//        TreeNode treeNode4 = new TreeNode(3);
//        TreeNode treeNode5 = new TreeNode(4);
//        TreeNode treeNode6 = new TreeNode(4);
//        TreeNode treeNode7 = new TreeNode(3);
//
//        root.left = treeNode2;
//        root.right = treeNode3;
//        treeNode2.left = treeNode4;
//        treeNode2.right = treeNode5;
//        treeNode3.left = treeNode6;
//        treeNode3.right = treeNode7;

        TreeNode root = new TreeNode(1);
        TreeNode treeNode2= new TreeNode(0);
        root.left = treeNode2;


        System.out.println(isSymmetric(root));
        //dfs(root);
    }

    /**
     * 执行用时 :2 ms, 在所有 Java 提交中击败了83.45%的用户
     * 内存消耗 :35.3 MB, 在所有 Java 提交中击败了81.69%的用户
     * 思路：求和
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if(null == root){
            return true;
        }
        return jdugeLeftAndRigth(root.left,root.right);
    }

    public static boolean jdugeLeftAndRigth(TreeNode leftNode , TreeNode rightNode){
        if(leftNode == null && rightNode != null){
            return false;
        }
        if(leftNode != null && rightNode == null){
            return false;
        }
        if(leftNode == rightNode || sumNode(leftNode).intValue() == sumNode(rightNode).intValue()){
            if(sumNode(leftNode)==null && sumNode(rightNode) == null ){
                return true;
            }
           if(jdugeLeftAndRigth(leftNode.left,rightNode.right) && jdugeLeftAndRigth(leftNode.right,rightNode.left)) {
               return true;
           }else{
               return false;
           }
        }else{
            return false;
        }
    }

    public static Integer sumNode(TreeNode treeNode){
        int sum;
        if(null!=treeNode){
            sum = treeNode.val;
        }else{
            return null;
        }
        if(treeNode.left != null){
            sum += sumNode(treeNode.left);
        }
        if(treeNode.right != null){
            sum += sumNode(treeNode.right);
        }
        return sum;
    }

    /**
     * 系统答案
     * @param root
     * @return
     */
    public boolean isSymmetric1(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }


}
