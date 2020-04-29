package com.billy.leetcode;

/**
 * @author Billy
 * @description: 斐波那契数列
 *      3 种算法：
 *          1）、暴力递归 （最耗时）
 *          2）、备忘录递归
 *          3）、动态规划（Dynamic Programming） DP table
 * @date 2020/4/29 17:33
 */
public class Fibonacci {

    /**
     * 1、暴力递归  时间复杂度 O(2^n)
     *      特点：效率低下
     * @param n
     * @return
     */
    private int fib_recursive(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fib_recursive(n - 1) + fib_recursive(n - 2);
        }
    }

    /**
     * 2、备忘录递归
     *  每次将子问题记入备忘录中，在后面的计算中，先从备忘录里面查询，如有则取出
     * @param n
     * @return
     */
    private long fibMemo(int n) {

        if (n < 0) {
            return 0;
        }
        long[] memo = new long[n + 1];
        return helper(memo, n);
    }
    private long helper(long[] memo, int n) {

        if (n == 1 || n == 2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }

    /**
     *  dp 数组的迭代解法
     * @param n
     * @return
     */
    private long fib_dp(int n) {

        if (n == 1 || n == 2) {
            return 1;
        }
        long pre = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            long sum = pre + curr;
            pre = curr;
            curr = sum;
        }

        return curr;
    }

}
