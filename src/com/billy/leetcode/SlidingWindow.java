package com.billy.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Billy
 * @description: 滑动窗口算法
 * @date 2020/5/18 19:51
 */
public class SlidingWindow {

    public static void main(String[] args) {

        String s = minWindowSubStr("ADOBECODEBANC", "ABC");
        System.out.println(s);

    }

    /**
     * 最小覆盖子串
     *  给一个字符串S、一个字符串 T，请在字符串 S 里面找出包含 T 所有字母的最小字符串
     *      示例：输入： s = "ADOBECODEBANC", T = "ABC"
     *            输出： "BANC"
     *      说明：
     *          如果 S 中不存在这样的子串，则返回空串 ""
     *          如果 S 中存在这样的子串，我们保证它是唯一答案
     *      滑动窗口算法的思路：
     *          1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，把索引左闭右开区间 [left, right) 称为一个「窗口」。
     *          2、我们先不断地增加 right 指针扩大窗口 [left, right)，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
     *          3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right)，直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，每次增加 left，我们都要更新一轮结果。
     *          4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。
     *              这个思路其实也不难，第 2 步相当于在寻找一个「可行解」，然后第 3 步在优化这个「可行解」，最终找到最优解，也就是最短的覆盖子串。
     *              左右指针轮流前进，窗口大小增增减减，窗口不断向右滑动，这就是「滑动窗口」这个名字的来历。
     * @return
     */
    public static String minWindowSubStr(String source, String target) {

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        char[] s = source.toCharArray();
        char[] chars = target.toCharArray();
        for (char c : chars) {
            need.put(c, 1);
            window.put(c, 0);
        }
        int left = 0, right = 0;
        int valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length) {
            // c 是将移入窗口的字符
            char c = s[right];
            // 右移窗口
            right ++;
            // 进行窗口内数据的更新
            if (need.containsKey(c)) {
                window.put(c, window.get(c) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid ++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗口的字符
                char d = s[left];
                left ++;
                //进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    window.put(d, window.get(d) - 1);
                    if (window.get(d).equals(need.get(d))) {
                        valid --;
                    }
                }
            }

        }
        return len == Integer.MAX_VALUE ? "": source.substring(start, start + len);
    }



}
