package com.billy.leetcode;

import java.util.Arrays;

/**
 * @author Billy
 * @description: 打家劫舍
 * @date 2020/6/15 11:30
 */
public class HouseRobber {


    // ++++++++++++ 1、基础方法 ++++++++++++++++
   /* public int rob(int[] nums) {
        return dp(nums, 0);
    }

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
            return nums[start];
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
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0 ; i --) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }
    // ++++++++++++++++++++++++++++

}
