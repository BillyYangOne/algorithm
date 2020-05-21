package com.billy.leetcode;

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

        ListNode lastIndexPoint = doublePointer.findLastIndexPoint(head, 2);
        System.out.println(lastIndexPoint.getVal());

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


}
