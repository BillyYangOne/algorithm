package com.billy.leetcode;

import java.util.Arrays;

/**
 * @author Billy
 * @description: 打家劫舍
 *
 * @date 2020/6/15 11:30
 */
public class HouseRobber {

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 1};
        int[] nums = {2, 7, 9, 3, 1};
        int rob = new HouseRobber().rob(nums);
        System.out.println(rob);


    }


    /**
     * 问题分析：
     *  从左到右，走过这一排房子，对于每个房子右两个选择：抢/不抢
     *      动态规划 两个要素 -》[状态]（房间索引） [选择]（抢或者不抢）
     * @param nums
     * @return
     */
    // ++++++++++++ 1、基础方法 ++++++++++++++++
   /*
   // 主函数
   public int rob(int[] nums) {
        return dp(nums, 0);
    }

    // 返回 dp[start...] 能抢到的最大值
    private int dp(int[] nums, int start) {

        if (start >= nums.length) {
            return 0;
        }
        return Math.max(
                // 抢，去下下家
                nums[start] + dp(nums, start + 2),
                // 不抢，去下家
                dp(nums, start + 1)
        );
    }*/
    // ++++++++++++++++++++++++++++

    // ++++++++++++++ 2、增加备忘录 ++++++++++++++
    /*private int[] memo; // 备忘录
    public int rob(int[] nums) {

        // 初始化备忘录
        memo = new int[nums.length];
        Arrays.fill(nums, -1);
        return dp(nums, 0);
    }

    private int dp(int[] nums, int start) {

        if (start >= nums.length) {
            return 0;
        }
        // 避免重复计算
        if (memo[start] != -1) {
            return memo[start];
        }
        int result = Math.max(
                nums[start] + dp(nums, start + 2),
                dp(nums, start + 1)
        );
        // 放入备忘录
        memo[start] = result;
        return result;
    }*/
    // ++++++++++++++++++++++++++++

    // ++++++++++++++ 3、自底向上 ++++++++++++++
    public int rob(int[] nums) {

        int n = nums.length;
        // dp[i] = x 表示从第i间房子开始，能抢到的钱最多为 x
        // base case: dp[n] = 0
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0 ; i --) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }
    // ++++++++++++++++++++++++++++

}
