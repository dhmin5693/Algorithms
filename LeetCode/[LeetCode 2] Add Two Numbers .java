/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode answer = new ListNode();
        ListNode cur = answer;

        int sum = 0, carry = 0;

        while (hasNext(carry, l1, l2)) {

            sum += carry;
            carry = 0;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            if (sum >= 10) {
                carry = sum / 10;
                sum %= 10;
            }

            cur.val = sum;
            sum = 0;

            if (hasNext(carry, l1, l2)) {
                cur.next = new ListNode();
                cur = cur.next;
            }
        }

        return answer;
    }

    private boolean hasNext(int carry, ListNode l1, ListNode l2) {
        return carry != 0 || l1 != null || l2 != null;
    }
}