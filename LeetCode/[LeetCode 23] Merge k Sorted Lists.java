import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

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
class Solution {

    public ListNode mergeKLists(ListNode[] nodes) {

        var pq = new PriorityQueue<Integer>();

        for (ListNode node : nodes) {
            while (node != null) {
                pq.add(node.val);
                node = node.next;
            }
        }

        if (pq.size() == 0) {
            return null;
        }

        ListNode answer = new ListNode();
        ListNode current = answer;

        while (!pq.isEmpty()) {
            current.val = pq.poll();

            if (!pq.isEmpty()) {
                current.next = new ListNode();
                current = current.next;
            }
        }

        return answer;
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(ListNode[] nodes, ListNode expected) {
        var actual = solution.mergeKLists(nodes);
//        System.out.println(actual);
//        System.out.println(expected);
//        assertEquals(expected, actual);
        assertTrue(equals(expected, actual));
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new ListNode[] { listNode(1, 4, 5), listNode(1, 3, 4), listNode(2, 6) },
                         listNode(1,1,2,3,4,4,5,6))
        );
    }

    private static ListNode listNode(int... nums) {
        ListNode node = new ListNode();

        ListNode current = node;
        for (int i = 0; i < nums.length; i++) {
            current.val = nums[i];

            if (i < nums.length - 1) {
                current.next = new ListNode();
                current = current.next;
            }
        }

        return node;
    }

    private boolean equals(ListNode n1, ListNode n2) {

        while (n1 != null && n2 != null) {
            if (n1.val != n2.val) {
                return false;
            }

            n1 = n1.next;
            n2 = n2.next;
        }

        return n1 == null && n2 == null;
    }
}

 */