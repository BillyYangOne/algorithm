package com.billy.leetcode;

import java.util.*;

/**
 * @author Billy
 * @description: BFS 算法
 *  问题的本质就是让你在一幅[图]中找到从起点 start 到终点 target 的最近距离。
 *      BFS 和 DFS 的区别： DFS 是线， BFS 是面
 * @date 2020/5/22 14:22
 */
public class BFS {

    public static void main(String[] args) {

    }

    /**
     * 基本框架 — 伪代码
     *  计算从起点 start 到终点 target 的最近距离
     */
    /*int BFS(Node start, Node target) {
        Queue<Node> q; //核心数据结构
        Set<Node> visited; //避免走回头路

        q.offer(start); // 将起点加入队列
        visited.add(start);
        int step = 0; // 记录扩散的步数

        while (q not empty) {
            int size = q.size();
            // 将当前队列中的所有结点向四周扩散
            for (int i = 0; i < size; i++) {
                Node current = q.poll();
                // 判断是否到达终点
                if (current is target) {
                    return step;
                }
                // 将 current 的相邻节点加入队列
                for (Node x : current.adj()) {
                    if (x not in visited) {
                        q.offer(x);
                        visited.add(x);
                    }
                }
            }
        }
        // 更新步数
        step ++;
    }*/

    /**
     * 树节点
     */
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

        if (root == null) return 0;
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
            deepth++;
        }
        return deepth;
    }

    /**
     * 打开密码锁的最少次数
     *  题目：
     *      你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
     *      每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     *      锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     *      列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     *      字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
     *
     *  示例 1:
     *      输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     *      输出：6
     * 解释：
     *      可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     *      注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
     *      因为当拨动到 "0102" 时这个锁就会被锁定。
     * 示例 2:
     *      输入: deadends = ["8888"], target = "0009"
     *      输出：1
     * 解释：
     *      把最后一位反向旋转一次即可 "0000" -> "0009"。
     * 示例 3:
     *      输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     *      输出：-1
     * 解释：
     *      无法旋转到目标数字且不被锁定。
     * 示例 4:
     *      输入: deadends = ["0000"], target = "8888"
     *      输出：-1
     *  
     * 提示：
     *
     * 死亡列表 deadends 的长度范围为 [1, 500]。
     * 目标数字 target 不会在 deadends 之中。
     * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/open-the-lock
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {

        // 记录需要跳过的死亡密码
        Set<String> deeds = new HashSet<>();
        deeds.addAll(Arrays.asList(deadends));

        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        // 从起点开始启动 BFS(广度优先搜索)
        int step = 0;
        queue.offer("0000");
        visited.add("0000");

        while (!queue.isEmpty()) {
            int size = queue.size();
            // 将当前队列中的所有节点向周围扩散
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // 排除死亡密码
                if (deeds.contains(current))
                    continue;
                // 判断是否到达终点
                if (current.equals(target))
                    return step;

                // 将一个节点的未便利节点计入队列
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(current, j);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(current, j);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            step ++;
        }
        return -1;
    }

    /**
     * 双向 BFS
     */
    int openLock2(String[] deedends, String target) {
        Set<String> deeds = new HashSet<>();
        deeds.addAll(Arrays.asList(deedends));
        // 用集合可以快速判断
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        q1.add("0000");
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> temp = new HashSet<>();

            // 将 q1 中的所有节点向周围扩散
            for (String current : q1) {

                // 判断是否到达终点
                if (deeds.contains(current))
                    continue;
                if (q2.contains(current))
                    return step;
                visited.add(current);

                // 将一个节点的未遍历相邻节点加入集合
                for (int i = 0; i < 4; i++) {
                    String up = plusOne(current, i);
                    if (!visited.contains(up)) {
                        temp.add(up);
                    }
                    String down = minusOne(current, i);
                    if(!visited.contains(down))
                        temp.add(down);
                }
            }
            step ++;
            // temp 相当于 q1
            // 在这里交换 q1 q2
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }


    /**
     * 以下 密码解锁的前奏
     */

    // 将 s[j] 向上拨动一次
    String plusOne(String s, int j) {
        char[] chars = s.toCharArray();
        if(chars[j] == 9)
            chars[j] = 0;
        else
            chars[j] += 1;
        return new String(chars);
    }

    // 将 s[j] 向下拨动一次
    String minusOne(String s, int j) {
        char[] chars = s.toCharArray();
        if(chars[j] == 1)
            chars[j] = 9;
        else 
            chars[j] -= 1;
        return new String(chars);
    }

    // BFS 框架，打印出所有可能的密码
    void BFS(String target) {
        Queue<String> q = new LinkedList<>();
        q.offer("0000");

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String current = q.poll();
                // 判断是否达到终点
                System.out.println(current);
                
                // 将一个节点的相邻节点加入队列
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(current, j);
                    String down = minusOne(current, j);
                    q.offer(up);
                    q.offer(down);
                }
            }
            // 增加步数
        }
    }
    
}
