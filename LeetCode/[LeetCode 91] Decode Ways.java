import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.Stream;

class Solution {

    private static final Set<String> set = Stream.iterate(1, i -> i <= 26, i -> i + 1)
                                                 .map(String::valueOf)
                                                 .collect(toSet());

    private int[] dp;
    private String s;

    public int numDecodings(String s) {

        if (s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        dp = new int[s.length() + 1];
        dp[1] = set.contains(s.substring(0, 1)) ? 1 : 0;

        if (s.length() >= 2) {
            dp[2] = set.contains(s.substring(0, 2)) ? 1 : 0;
        }

        for (int i = 2; i <= s.length(); i++) {
            if (set.contains(s.substring(i - 2, i))) {
                dp[i] += dp[i - 2];
            }

            if (set.contains(s.substring(i - 1, i))) {
                dp[i] += dp[i - 1];
            }
        }

        return dp[s.length()];
    }
}

/* Test code with Junit5

class SolutionTest {

    private final Solution code = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String s, int output) {

        var result = code.numDecodings(s);
        System.out.println("result: " + result);
        System.out.println("output: " + output);
        assertThat(result, is(output));
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of("12", 2),
            Arguments.of("226", 3),
            Arguments.of("0", 0),
            Arguments.of("06", 0)
        );
    }
}

 */