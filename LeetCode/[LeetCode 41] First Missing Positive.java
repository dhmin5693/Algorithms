import java.util.PriorityQueue;

class Solution {
    public int firstMissingPositive(int[] nums) {

        var pq = new PriorityQueue<Integer>(nums.length);

        for (int num : nums) {
            if (num > 0) {
                pq.add(num);
            }
        }

        if (pq.isEmpty()) {
            return 1;
        }

        int check = 0;

        while (!pq.isEmpty()) {
            int num = pq.poll();

            if (num == check + 1 || num == check) {
                check = num;
                continue;
            }

            return check + 1;
        }

        return check + 1;
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int [] nums, int expected) {
        var actual = solution.firstMissingPositive(nums);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {0,2,2,1,1}, 3),
            Arguments.of(new int[] {7,8,9,11,12}, 1),
            Arguments.of(new int[] {3,4,-1,1}, 2),
            Arguments.of(new int[] {1,2,0}, 3)
        );
    }
}

 */
