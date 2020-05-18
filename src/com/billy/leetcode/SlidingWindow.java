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

        String target = "abc";
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        char[] chars = target.toCharArray();
        for (char c : chars) {
            System.out.println(c);
        }

    }

    /**
     * 最小覆盖子串
     *  给一个字符串S、一个字符串 T，请在字符串 S 里面找出包含 T 所有字母的最小字符串
     *      示例：输入： s = "ADOBECODEBANC", T = "ABC"
     *            输出： "BANC"
     *
     * @return
     */
    public String minWindowSubStr(String source, String target) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        char[] s = source.toCharArray();

        char[] chars = target.toCharArray();
        for (char c : chars) {
            need.put(c, 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < source.length()) {
            // c 是将移入窗口的字符
            char c = s[right];
            // 右移窗口
            right ++;
            // 进行窗口内数据的更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
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
                    if (window.get(d).equals(need.get(d))) {
                        valid --;
                        window.put(d, window.getOrDefault(d, 0) - 1);
                    }
                }

            }

        }

        return len == Integer.MAX_VALUE ? "": source.substring(start, len);

    }



}
