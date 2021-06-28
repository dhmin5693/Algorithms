package solve;

import java.util.ArrayList;
import java.util.Stack;

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

    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        var stack = new Stack<Integer>();

        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        var answer = new ListNode();
        var current = answer;

        while (!stack.isEmpty()) {
            current.val = stack.pop();

            if (stack.isEmpty()) {
                break;
            }

            current.next = new ListNode();
            current = current.next;
        }

        return answer;
    }

    /*
    public static class ListNode {

        int val;
        ListNode next;

        ListNode() { }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    */
}

/* test with Junit5

public class SolutionTest {

    private final Solution s = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(ListNode head, ListNode expected) {
        var actual = s.reverseList(head);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(listNode(List.of(1, 2, 3, 4, 5)), listNode(List.of(5, 4, 3, 2, 1))),
            Arguments.of(listNode(List.of(2, 1)), listNode(List.of(1, 2)))
        );
    }

    private static ListNode listNode(List<Integer> list) {
        ListNode node = new ListNode();

        ListNode current = node;
        for (int i = 0; i < list.size(); i++) {
            current.val = list.get(i);

            if (i < list.size() - 1) {
                current.next = new ListNode();
                current = current.next;
            }
        }

        return node;
    }
}

 */