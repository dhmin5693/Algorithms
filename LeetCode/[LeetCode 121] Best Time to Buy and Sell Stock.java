class Solution {
    public int maxProfit(int[] prices) {
        int answer = 0;
        int max = prices[prices.length - 1];

        for (int i = prices.length - 2; i >= 0; i--) {
            answer = Math.max(answer, max - prices[i]);
            max = Math.max(max, prices[i]);
        }

        return answer;
    }
}

/* Test code with Junit5
class SolutionTest {

    private final Solution code = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[] prices, int output) {

        var result = code.maxProfit(prices);
        assertThat(result, is(output));
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {1, 2}, 1),
            Arguments.of(new int[] {7, 1, 5, 3, 6, 4}, 5),
            Arguments.of(new int[] {7, 6, 4, 3, 1}, 0)
        );
    }
}
 */
