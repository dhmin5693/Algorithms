import java.util.Stack;

class Solution {
    public String removeDuplicates(String s, int k) {

        if (s == null || s.length() <= 1) {
            return s;
        }

        var st = new Stack<Data>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (st.size() == 0 || st.peek().ch != c) {
                st.push(new Data(c));
            } else {
                var d = st.pop();
                d.count++;
                st.push(d);
            }

            if (st.peek().count == k) {
                st.pop();
            }
        }

        var sb = new StringBuilder();

        while (!st.isEmpty()) {
            var data = st.pop();
            for (int i = 0; i < data.count; i++) {
                sb.append(data.ch);
            }
        }

        return sb.reverse().toString();
    }

    public static class Data {
        char ch;
        int count;

        public Data(char ch) {
            this.ch = ch;
            this.count = 1;
        }
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String s, int k, String expected) {
        var actual = solution.removeDuplicates(s, k);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of("abcd", 2, "abcd"),
            Arguments.of("deeedbbcccbdaa", 3, "aa"),
            Arguments.of("pbbcggttciiippooaais", 2, "ps")
        );
    }
}

*/