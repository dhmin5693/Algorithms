import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || k <= 0) {
            return new int[0];
        }

        if (k == 1) {
            return nums;
        }

        int[] answer = new int[nums.length - k + 1];
        int index = 0;

        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {

            while (!dq.isEmpty() && dq.peek() < i - k + 1) {
                dq.poll();
            }

            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }

            dq.offer(i);
            if (i >= k - 1) {
                answer[index++] = nums[dq.peek()];
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
    void test(int[] nums, int k, int[] expected) {
        var actual = solution.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(actual));
        assertArrayEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3, new int[] {3, 3, 5, 5, 6, 7}),
            Arguments.of(new int[] {1, -1}, 1, new int[] {1, -1}),
            Arguments.of(new int[] {1}, 1, new int[] {1}),
            Arguments.of(new int[] {9, 11}, 2, new int[] {11}),
            Arguments.of(new int[] {4, -2}, 2, new int[] {4})
        );
    }
}

 */