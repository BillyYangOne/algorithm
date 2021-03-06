package com.billy.leetcode;

import java.util.*;

/**
 * @author Billy
 * @description: 滑动窗口算法
 * @date 2020/5/18 19:51
 */
public class SlidingWindow {

    public static void main(String[] args) {

        // minWindowSubStr
        String s = minWindowSubStr("ADOBECODEBANC", "ABC");
        System.out.println(s);

        // checkInclusion
        boolean b = checkInclusion("asdfebah", "ab");
        System.out.println(b);

        // findAnagrams
//        List<Integer> anagrams = findAnagrams("cbaebabacd", "abc");
        List<Integer> anagrams = findAnagrams("baa", "aa");
        System.out.println(anagrams);

        // lengthOfLengestSubstring
        int subLength = lengthOfLongestSubstring("abcabcbb");
        System.out.println(subLength);

    }

    /**
     * 最小覆盖子串  Minimum Window Substring
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
    private static String minWindowSubStr(String source, String target) {

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
                    if (window.get(d).equals(need.get(d))) {
                        valid --;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }

        }
        return len == Integer.MAX_VALUE ? "": source.substring(start, start + len);
    }


    /**
     *  字符串排列  Permutation in String
     *      问题：
     *          给定两个字符串 S1 和 S2 , 写一个函数来判断 S2 是否包含 S1 的排列（判断S1的排列之一是否是S2的子串）
     *      示例：
     *          输入： S1 = "ab"  S2 = "asdfebah"
     *          输出： true
     *          解释： S2 包含 S1 的排列之一 "ba"
     *
     *      思路：
     *          运用滑动窗口算法，给定两个字符串 s 和 t ，判断 s 中是否存在一个子串，包含 t 中的所有字符且没有其他字符
     * @return
     */
    private static boolean checkInclusion(String s, String t) {

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        char[] chars = t.toCharArray();
        // 初始化
        for (char c : chars) {
            need.put(c, 1);
            window.put(c, 0);
        }
        int left = 0, right = 0, valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right ++;
            if (need.containsKey(c)) {
                window.put(c, window.get(c) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid ++;
                }
            }
            // 判断左侧窗口是否需要收缩
            while (right - left > t.length()) {
                //是否找到合法的字符串
                if (valid == t.length()) {
                    return true;
                }
                char d = s.charAt(left);
                left ++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid --;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return false;
    }


    /**
     *  找所有字母异位词   Find All Anagrams in a String
     *
     *  给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     *      字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     * 说明：
     *      字母异位词指字母相同，但排列不同的字符串。
     *      不考虑答案输出的顺序。
     * 示例 1:
     *   输入: s: "cbaebabacd" p: "abc"
     *   输出:  [0, 6]
     *
     * 解释:
     *      起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
     *      起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
     *  
     * 示例 2:
     *    输入:  s: "abab" p: "ab"
     *    输出:  [0, 1, 2]
     *  解释:
     *  起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
     *  起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
     *  起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
     *
     * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
     *
     */
    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();
        char[] chars = p.toCharArray();
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : chars) {
            need.put(c, need.getOrDefault(c, 0) + 1);
            window.put(c, 0);
        }
        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right ++;
            if (need.containsKey(c)) {
                window.put(c, window.get(c) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid ++;
                }
            }
            while (right - left >= p.length()) {
                // 找到符合条件的
                if (valid == need.size()) {
                    result.add(left);
                }
                char d = s.charAt(left);
                left ++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid --;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return result;
    }

    /**
     *  最长无重复子串 Longest Substring Without Repeating Characters
     *
     *      给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *  输入: "abcabcbb"
     *  输出: 3
     *  解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 示例 2:
     *  输入: "bbbbb"
     *  输出: 1
     *  解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * 示例 3:
     *  输入: "pwwkew"
     *  输出: 3
     *  解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * @param s
     * @return
     */
    private static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int result = 0;
        while (right < s.length()) {

            char c = s.charAt(right);
            right ++;
            window.put(c, window.getOrDefault(c, 0) + 1);

            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left ++;
                window.put(d, window.getOrDefault(d, 0) - 1);
            }
            result = Math.max(result, right - left);
        }
        return result;
    }

}
