class Solution {
    public int maximalSquare(char[][] matrix) {

        int[][] dp = new int[matrix.length][];
        int answer = 0;

        for (int i = 0; i < matrix.length; i++) {
            dp[i] = new int[matrix[i].length];

            for (int j = 0; j < matrix[i].length; j++) {
                dp[i][j] = matrix[i][j] - '0';
                answer = Math.max(answer, dp[i][j]);
            }
        }

        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {

                if (dp[i][j] == 0 || dp[i][j + 1] == 0 || dp[i + 1][j] == 0 || dp[i + 1][j + 1] == 0) {
                    continue;
                }

                int min1 = Math.min(dp[i][j], dp[i][j + 1]);
                int min2 = Math.min(dp[i][j], dp[i + 1][j]);
                int min3 = Math.min(min1, min2);

                if (min3 != 0) {
                    dp[i + 1][j + 1] = min3 + 1;
                }

                answer = Math.max(answer, dp[i + 1][j + 1]);
            }
        }

        return answer * answer;
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution s = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(char[][] matrix, int expected) {
        var actual = s.maximalSquare(matrix);
//        System.out.println(actual);
//        System.out.println(expected);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new char[][] {
                {'1','1','1','1','0'},
                {'1','1','1','1','0'},
                {'1','1','1','1','1'},
                {'1','1','1','1','1'},
                {'0','0','1','1','1'}}, 16),
            Arguments.of(new char[][] {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}}, 4),
            Arguments.of(new char[][] {{'0','1'},{'1','0'}}, 1),
            Arguments.of(new char[][] {{'0'}}, 0)
        );
    }
}


 */