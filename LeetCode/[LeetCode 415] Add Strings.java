import java.math.BigInteger;

class Solution {
    public String addStrings(String num1, String num2) {

        var n1 = new BigInteger(num1);
        var n2 = new BigInteger(num2);
        
        return n1.add(n2).toString();
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String num1, String num2, String expected) {
        var actual = solution.addStrings(num1, num2);
//        System.out.println(actual);
//        System.out.println(expected);
//        assertArrayEquals(expected, actual);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of("11", "123", "134"),
            Arguments.of("456", "77", "533"),
            Arguments.of("0", "0", "0")
        );
    }
}

 */