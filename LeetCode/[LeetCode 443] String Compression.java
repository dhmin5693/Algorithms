class Solution {
    public int compress(char[] chars) {
        if (chars.length == 1) {
            return 1;
        }

        StringBuilder s = new StringBuilder();

        char cur = chars[0];
        int count = 0;

        for (char c : chars) {

            if (cur == c) {
                count++;
                continue;
            }

            s.append(cur);

            if (count > 1) {
                s.append(count);
            }

            cur = c;
            count = 1;
        }

        s.append(cur);

        if (count > 1) {
            s.append(count);
        }

        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }

        return s.length();
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(char[] chars, int expected) {
        var actual = solution.compress(chars);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new char[] {'a','a','b','b','c','c','c'}, 6),
            Arguments.of(new char[] {'a'}, 1),
            Arguments.of(new char[] {'a','b','b','b','b','b','b','b','b','b','b','b','b'}, 4),
            Arguments.of(new char[] {'a','a','a','b','b','a','a'}, 6)
        );
    }
}

 */