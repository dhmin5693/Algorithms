import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;

class Solution {

    private final List<String> temp = new ArrayList<>();
    private int maxWidth;

    public List<String> fullJustify(String[] words, int maxWidth) {

        this.maxWidth = maxWidth;

        var answer = new ArrayList<String>();
        int count = 0;

        for (String word : words) {
            if (count + word.length() > maxWidth) {
                answer.add(tempToSingleString());
                count = 0;
                temp.clear();
            }

            count += word.length();
            if(count + 1 <= maxWidth) {
                count++; // add space count
            }

            temp.add(word);
        }

        if (temp.size() > 0) {
            answer.add(lastLine());
        }

        return answer;
    }

    private String tempToSingleString() {

        if (temp.size() == 1) {

            var sb = new StringBuilder();
            sb.append(temp.get(0));
            sb.append(" ".repeat(Math.max(0, maxWidth - sb.length())));

            return sb.toString();
        }

        var sbs = new ArrayList<StringBuilder>();
        for (int i = 0; i < temp.size() - 1; i++) {
            sbs.add(new StringBuilder(temp.get(i)));
        }

        int totalCount = temp.stream()
                             .mapToInt(String::length)
                             .sum();

        int index = 0;
        for (int i = totalCount; i < maxWidth; i++) {
            sbs.get(index).append(" ");
            index = (index + 1) % sbs.size();
        }

        return sbs.stream()
                  .map(StringBuilder::toString)
                  .collect(joining()) + temp.get(temp.size() - 1);
    }

    private String lastLine() {
        var sb = new StringBuilder(String.join(" ", temp));
        sb.append(" ".repeat(Math.max(0, maxWidth - sb.length())));
        return sb.toString();
    }
}

/* Test code with Junit5

class SolutionTest {

    private final Solution code = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String[] words, int maxWidth, List<String> output) {

        var result = code.fullJustify(words, maxWidth);
        System.out.println("result: " + result);
        System.out.println("output: " + output);
        assertThat(result, is(output));
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new String[] {"What","must","be","acknowledgment","shall","be"}, 16, List.of("What   must   be","acknowledgment  ","shall be        ")),
            Arguments.of(new String[] {"This", "is", "an", "example", "of", "text", "justification."}, 16, List.of("This    is    an","example  of text","justification.  ")),
            Arguments.of(new String[] {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"}, 20, List.of("Science  is  what we","understand      well","enough to explain to","a  computer.  Art is","everything  else  we","do                  "))
        );
    }
}

 */