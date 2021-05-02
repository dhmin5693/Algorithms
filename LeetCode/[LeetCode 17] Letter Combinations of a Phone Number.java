import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private Map<Character, char[]> map;
    private List<String> answer;
    char[] chars;

    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.trim().equals("")) {
            return List.of();
        }

        if (map == null) {
            map = new HashMap<>();
            map.put('2', "abc".toCharArray());
            map.put('3', "def".toCharArray());
            map.put('4', "ghi".toCharArray());
            map.put('5', "jki".toCharArray());
            map.put('6', "mno".toCharArray());
            map.put('7', "pqrs".toCharArray());
            map.put('8', "tuv".toCharArray());
            map.put('9', "wxyz".toCharArray());
        }

        answer = new ArrayList<>();
        chars = digits.toCharArray();

        dfs(0, new StringBuilder());

        answer.sort(Comparator.naturalOrder());
        return answer;
    }

    public void dfs(int index, StringBuilder sb) {

        if (index >= chars.length) {
            answer.add(sb.toString());
            return;
        }

        char[] arr = map.get(chars[index]);

        for (char c : arr) {
            sb.append(c);
            dfs(index + 1, sb);
            sb.deleteCharAt(index);
        }
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String digits, List<String> expected) {
        var actual = solution.letterCombinations(digits);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of("23", List.of("ad","ae","af","bd","be","bf","cd","ce","cf")),
            Arguments.of("", List.of()),
            Arguments.of("2", List.of("a", "b", "c"))
        );
    }
}

 */