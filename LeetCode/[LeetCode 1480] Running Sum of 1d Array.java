class Solution {
    public int[] runningSum(int[] nums) {
        int[] answer = new int[nums.length];
        answer[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            answer[i] = answer[i - 1] + nums[i];
        }

        return answer;
    }
}


/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[] nums, int[] expected) {
        var actual = solution.runningSum(nums);
        assertArrayEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {1,2,3,4}, new int[] {1,3,6,10}),
            Arguments.of(new int[] {1,1,1,1,1}, new int[] {1,2,3,4,5}),
            Arguments.of(new int[] {3,1,2,10,1}, new int[] {3,4,6,16,17})
        );
    }
}

 */