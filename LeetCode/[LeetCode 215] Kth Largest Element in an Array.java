import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {

        var pq = new PriorityQueue<Integer>();
        for (int num : nums) {
            pq.add(-num);
        }

        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }

        return -1 * pq.poll();
    }
}


/* Test with Junit 5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[] nums, int k, int expected) {
        var actual = solution.findKthLargest(nums, k);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {3,2,1,5,6,4}, 2, 5),
            Arguments.of(new int[] {3,2,3,1,2,4,5,5,6}, 4, 4)
        );
    }
}

 */