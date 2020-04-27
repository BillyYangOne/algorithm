package com.billy.leetcode;

/**
 * 寻找两个有序数组的中位数
 * @description
 *  给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Median {


    /**
     * 寻找中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public int getMedian(int[] nums1, int[] nums2) {

        int middelIndex = (nums1.length + nums2.length) / 2;

        //两个数组无交集

        //有交集

        int tempMin = 0;
        int tempMax = 0;



        for (int i = 0; i < nums1.length; i++) {

            int a = nums1[i];
            for (int j = 0; j < nums2.length; j++) {
                int b = nums2[j];

            }
        }


        return 0;
    }
}
