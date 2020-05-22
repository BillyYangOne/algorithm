package com.billy.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Billy
 * @description: BFS 算法
 * @date 2020/5/22 14:22
 */
public class BFS {

    public static void main(String[] args) {

    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    /**
     * 获取二叉树的最小深度
     */
    private int minDeepth(TreeNode root) {

        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // root 本身就是一层
        int deepth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                //判断是否到达终点（左右节点均为空）
                if (current.getLeft() == null && current.getRight() == null) {
                    return deepth;
                }
                if (current.getLeft() != null) {
                    queue.offer(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.offer(current.getRight());
                }
            }
            deepth ++;
        }
        return deepth;
    }
}
