import java.util.HashSet;
import java.util.List;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        var set = new HashSet<>(wordDict);
        
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[s.length()];
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String s, List<String> wordDict, boolean expected) {
        var actual = solution.wordBreak(s, wordDict);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of("aaaaaaa", List.of("aaaa","aaa"), true),
            Arguments.of("leetcode", List.of("leet","code"), true),
            Arguments.of("applepenapple", List.of("apple","pen"), true),
            Arguments.of("catsandog", List.of("cats","dog","sand","and","cat"), false)
        );
    }
}

 */
