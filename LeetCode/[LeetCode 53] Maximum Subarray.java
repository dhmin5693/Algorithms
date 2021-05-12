class Solution {
    public int maxSubArray(int[] nums) {

        int answer = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], max + nums[i]);
            answer = Math.max(max, answer);
        }

        return answer;
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[] nums, int expected) {
        var actual = solution.maxSubArray(nums);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[]{-2,1,-3,4,-1,2,1,-5,4}, 6),
            Arguments.of(new int[]{1},1),
            Arguments.of(new int[]{5,4,-1,7,8},23)
        );
    }
}

 */