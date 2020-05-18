package com.billy.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Billy
 * @description: 回溯算法
 * @date 2020/5/18 14:53
 */
public class BackTrack {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4};
        BackTrack backTrack = new BackTrack();
        backTrack.getFullArrange(nums);
        System.out.println(backTrack.result.size());

    }

    private List<List<Integer>> result = new LinkedList<>();

    // 输入一组不重复的数字，返回它们的全排列
    private List<List<Integer>> getFullArrange(int[] nums) {
        // 记录[路径]
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return result;
    }

    /**
     * // 路径：记录在 track 中
     * // 选择列表：nums 中不存在于 track 的那些元素
     * // 结束条件：nums 中的元素全都在 track 中出现
     * @param nums
     * @param track
     */
    private void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            System.out.println(track);
            result.add(new LinkedList<>(track));
            return;
        }
        for (int num : nums) {
            // 排除不合法的选择
            if (track.contains(num)) {
                continue;
            }
            // 做选择
            track.add(num);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }
}
