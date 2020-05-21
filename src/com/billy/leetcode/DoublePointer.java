package com.billy.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Billy
 * @description: 双指针技巧
 *  双指针技巧分为两类：
 *      1、快指针 与 慢指针（主要解决链表中的问题，比如：链表中是否包含环）
 *      2、左右指针（主要解决数组或者字符串的问题，比如：二分查找）
 * @date 2020/5/21 15:11
 */
public class DoublePointer {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode middle = new ListNode(2);
        ListNode last = new ListNode(3);
        middle.setNext(last);
//        last.setNext(middle);
        head.setNext(middle);

        DoublePointer doublePointer = new DoublePointer();
//        boolean b = doublePointer.hasCycle(head);
//        System.out.println(b);
//
//        ListNode listNode = doublePointer.detectCycle(head);
//        System.out.println(listNode.getVal());

//        ListNode midPoint = doublePointer.findMidPoint(head);
//        System.out.println(midPoint.getVal());

//        ListNode lastIndexPoint = doublePointer.findLastIndexPoint(head, 2);
//        System.out.println(lastIndexPoint.getVal());

        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        doublePointer.reverse(nums);
        List<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println(collect);

    }

    /**
     * 判定链表中是否含有环
     */
    private boolean hasCycle(ListNode head) {

        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    // 链表节点
    static class ListNode{

        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    /**
     * 已知链表中含有环，返回这个环的起始位置
     *    思路：
     *      当快慢指针相遇时，让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点位置就是环开始的位置。
     * @param head
     * @return
     */
    private ListNode detectCycle(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     *  寻找链表的中点
     *      思路： 运用双指针
     * @return
     */
    ListNode findMidPoint(ListNode head) {

        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 寻找链表的倒数第 k 个元素
     *  思路：
     *      让快指针先走 k 步，然后快慢指针开始同速前进。这样当快指针走到链表末尾 null 时，
     *      慢指针所在的位置就是倒数第 k 个链表节点（为了简化，假设 k 不会超过链表长度）
     *
     * @return
     */
    ListNode findLastIndexPoint(ListNode head, int k) {

        ListNode fast, slow;
        fast = slow = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    // 左右指针

    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    int binarySearch(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 两数之和
     *    给定一个升序排列的有序数组，找到两个数使得他们相加之和等于目标数。
     *    返回这两个数的坐标值。
     *    示例：
     *      输入： nums = [2, 7, 11, 15], target = 18
     *      输出： [1, 2]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        while (left != right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left,right};
            } else if (sum < target) {
                left ++;
            } else if (sum > target) {
                right --;
            }
        }
        return new int[] {-1, -1};
    }

    /**
     *  反转数组
     * @param nums
     */
    public void reverse(int[] nums) {

        if (nums != null && nums.length > 1) {

            int left = 0, right = nums.length - 1;
            while (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                right --;
                left ++;
            }
        }
    }


}
