import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Solution {

    private final String[] bigUnit = new String[] {"Thousand", "Million", "Billion"};
    private final String[] middleUnit = new String[] {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] smallUnit = new String[] {
        "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
        "Eighteen", "Nineteen"};

    public String numberToWords(int num) {

        if (num == 0) {
            return smallUnit[0];
        }

        var chunks = Stream.iterate(num, i -> i > 0, i -> i / 1000)
                           .map(i -> i % 1000)
                           .collect(toList());

        var answer = Stream.iterate(0, i -> i < chunks.size(), i -> i + 1)
                           .filter(i -> chunks.get(i) != 0)
                           .map(i -> {

                               int chunk = chunks.get(i);
                               var word = new ArrayList<String>();

                               if (chunk >= 100) {
                                   word.add(smallUnit[chunk / 100]);
                                   word.add("Hundred");
                                   chunk %= 100;
                               }

                               if (chunk >= 20) {
                                   word.add(middleUnit[chunk / 10]);
                                   chunk %= 10;
                               }

                               if (chunk != 0) {
                                   word.add(smallUnit[chunk]);
                               }

                               if (i >= 1) {
                                   word.add(bigUnit[i - 1]);
                               }

                               return String.join(" ", word);
                           })
                           .collect(toList());

        Collections.reverse(answer);

        return String.join(" ", answer);
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int num, String expected) {
        var actual = solution.numberToWords(num);
//        System.out.println(actual);
//        System.out.println(expected);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(123, "One Hundred Twenty Three"),
            Arguments.of(12345, "Twelve Thousand Three Hundred Forty Five"),
            Arguments.of(1234567, "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"),
            Arguments.of(1234567891, "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One")
        );
    }
}


 */