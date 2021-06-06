class Solution {

    private int[][] dp; // [][0]: LEFT MAX, [][1]: RIGHT MAX

    public int trap(int[] heights) {

        dp = new int[heights.length][2];
        int sum = 0;

        for (int i = 1; i < heights.length - 1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], heights[i - 1]);
            dp[heights.length - i - 1][1] = Math.max(dp[heights.length - i][1], heights[heights.length - i]);
        }

        for (int i = 1; i < heights.length - 1; i++) {
            int depth = Math.min(dp[i][0], dp[i][1]) - heights[i];
            sum += Math.max(depth, 0);
        }

        return sum;
    }
}

/* TEST CODE

class SolutionTest {

    private final Solution code = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[] heights, int output) {
        var result = code.trap(heights);
        assertThat(result, is(output));
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}, 6),
            Arguments.of(new int[] {4,2,0,3,2,5}, 9)
        );
    }
}
*/