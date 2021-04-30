class Solution {
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - (a * a));
            if ((int) b == b) {
                return true;
            }
        }

        return false;
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int c, boolean expected) {
        var actual = solution.judgeSquareSum(c);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(2147483646, false),
            Arguments.of(2147482647, false),
            Arguments.of(999999999, false),
            Arguments.of(5, true),
            Arguments.of(3, false),
            Arguments.of(4, true),
            Arguments.of(1, true)
        );
    }
}

 */