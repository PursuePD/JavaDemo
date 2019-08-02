package com.example.算法.日常;

/**
 * @Author:cuijialei
 * @Date: 2019/7/8
 * @Describe
 */
public class Test20190708 {
    public static void main(String[] args) {
        /**
         * 给定一个二叉树，找出其最大深度。
         * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
         * 说明: 叶子节点是指没有子节点的节点。
         * 示例：
         * 给定二叉树 [3,9,20,null,null,15,7]，
         *
         *     3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         * 返回它的最大深度 3 。
         */
    }

    /**
     * 递归
     * 执行用时 :1 ms, 在所有 Java 提交中击败了99.17%的用户
     * 内存消耗 :37.2 MB, 在所有 Java 提交中击败了51.10%的用户
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return java.lang.Math.max(left_height, right_height) + 1;
        }
    }

}
