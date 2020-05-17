package com.billy.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试类
 * 1、stream Api  内部迭代
 */
public class Test {

    public static void main(String[] args) {

//        List<User> users = new ArrayList<>();
//        Map<String, List<User>> collect = users.stream()
//                .filter((User u) -> u.getAge() > 10) //筛选
//                .collect(groupingBy(User::getSex));  //分组
//
//        System.out.println(collect.get("123"));
//
//        List<User> collect1 = users.parallelStream().filter((User u) -> "male".equals(u.getSex()))
//                .collect(toList());

        String str = "dvdf";
        Test test = new Test();
        double medianSortedArrays = test.findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{5, 6});

        System.out.println(medianSortedArrays);

    }

    /**
     * 4、
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     *
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     *
     * You may assume nums1 and nums2 cannot be both empty.
     *
     * Example 1:
     *
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * The median is 2.0
     * Example 2:
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * The median is (2 + 3)/2 = 2.5
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int[] nums = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while (k != m + n) {
            if(i == m){
                while (j != n){
                    nums[k++] = nums2[j++];
                }
                break;
            }
            if (j == n){
                while (i != m) {
                    nums[k++] = nums1[i++];
                }
                break;
            }
            if(nums1[i] > nums2[j]){
                nums[k++] = nums2[j++];
            } else {
                nums[k++] = nums1[i++];
            }
        }

        if (k % 2 == 0) {
            return (nums[k / 2 - 1] + nums[k / 2]) / 2;
        } else {
            return nums[k / 2];
        }
    }

    /**
     * 3、
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * @param s
     * @return
     */
    // 运用滑动窗口方法求解
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {

            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstring1(String s) {
        
        int result = 0;
        if (!"".equals(s)) {
            char[] chars = s.toCharArray();
            List<Character> list = new ArrayList<>();
            int len = 0;
            for (int i = 0; i < chars.length; i++) {
                Character temp = chars[i];
                if (list.contains(temp)) {
                    list.clear();
                    if(len > result){
                        result = len;
                    }
                    s = s.substring(s.indexOf(temp) + 1);
                    chars = s.toCharArray();
                    System.out.println("s::" + s);
                    len = 0;
                    i = -1;
                }else{
                    len ++;
                    list.add(temp);
                }
            }
            result = len > result ? len : result;
        }

        return result;

    }
    

    /**
     * @param nums
     * @param target
     * @return
     */
    public int[] twosum(int[] nums, int target) {

        if (nums == null || nums.length < 1) {
            return new int[2];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[2];
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 2、
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Example:
     *
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        ListNode pre = new ListNode(0);
        ListNode temp = pre;
        int index = 0;
        while (l1 != null || l2 != null || index != 0){

            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + index;
            index = sum / 10;
            sum = sum % 10;
            temp.next = new ListNode(sum);
            temp = temp.next;
            if(l1 != null){
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return pre.next;
    }

}