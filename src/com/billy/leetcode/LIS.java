package com.billy.leetcode;

import java.util.Arrays;

/**
 * @author Billy
 * @description: 最长子序列
 *      Longest	Increasing	Subsequence 简写	LIS
 * @date 2020/5/13 18:25
 */
public class LIS {

    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 4, 2, 3};
        LIS lis = new LIS();
        System.out.println(lis.lengthLISofBinarySearch(nums));
    }

    /**
     * 最长子序列长度 — 动态规划解法，时间复杂度 O(n^2)
     *  dp[i] 表示以第i位结束的最长子序列数
     * @param nums
     * @return
     */
    public int lengthLIS(int[] nums) {

        int[] dp = new int[nums.length];
        // 全部初始化为 1
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 最长子序列长度 — 二分查找法，时间复杂度 O(nlogn)
     * @param nums
     * @return
     */
    public int lengthLISofBinarySearch(int[] nums) {

        int[] top = new int[nums.length];
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];

            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left == piles) {
                piles ++;
            }
        }
        return piles;
    }
}
