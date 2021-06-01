import java.util.List;
import java.util.Stack;

class Solution {

    private final static List<Character> OPENER = List.of('(', '{', '[');

    public boolean isValid(String s) {

        var st = new Stack<Character>();

        for (char c : s.toCharArray()) {
            if (isOpener(c)) {
                st.push(c);
                continue;
            }

            if (st.isEmpty()) {
                return false;
            }

            char top = st.peek();

            if (c == ')' && top == '(') {
                st.pop();
                continue;
            }

            if (c == ']' && top == '[') {
                st.pop();
                continue;
            }

            if (c == '}' && top == '{') {
                st.pop();
                continue;
            }

            st.push(c);
        }

        return st.isEmpty();
    }

    private boolean isOpener(char c) {
        return OPENER.stream().anyMatch(o -> c == o);
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String s, boolean expected) {
        var actual = solution.isValid(s);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of("()", true),
            Arguments.of("()[]{}", true),
            Arguments.of("(]", false),
            Arguments.of("([)]", false),
            Arguments.of("{[]}", true)
        );
    }
}

 */