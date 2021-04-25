import java.util.Stack;

class Solution {

    private final Stack<Integer> st = new Stack<>();

    public int calculate(String s) {

        s = s.trim();

        int sign = 1;
        int sum = 0;
        int num = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
                continue;
            }

            if (c == '+') {
                sum += num * sign;
                num = 0;
                sign = 1;
                continue;
            }

            if (c == '-') {
                sum += num * sign;
                num = 0;
                sign = -1;
                continue;
            }

            if (c == '(') {
               st.push(sum);
               st.push(sign);
               sum = 0;
               sign = 1;
               continue;
            }

            if (c == ')') {
                sum += sign * num;
                num = 0;

                int _sign = st.pop();
                int _sum = st.pop();

                sum *= _sign;
                sum += _sum;
            }
        }

        return sum + (num * sign);
    }
}

/* test with Junit5
  
public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String s, int expected) {
        var actual = solution.calculate(s);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of("1-1", 0),
            Arguments.of("-2+ 1", -1),
            Arguments.of("2147483647", 2147483647),
            Arguments.of("(1+(4+5+2)-3)+(6+8)", 23),
            Arguments.of("1 + 1", 2),
            Arguments.of(" 2-1 + 2 ", 3)
        );
    }
}

 */