import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private Map<Character, List<Character>> map;
    private List<String> answer;
    char[] chars;

    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.trim().equals("")) {
            return List.of();
        }

        if (map == null) {
            map = new HashMap<>();
            map.put('2', List.of('a', 'b', 'c'));
            map.put('3', List.of('d', 'e', 'f'));
            map.put('4', List.of('g', 'h', 'i'));
            map.put('5', List.of('j', 'k', 'l'));
            map.put('6', List.of('m', 'n', 'o'));
            map.put('7', List.of('p', 'q', 'r', 's'));
            map.put('8', List.of('t', 'u', 'v'));
            map.put('9', List.of('w', 'x', 'y', 'z'));
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

        List<Character> list = map.get(chars[index]);

        for (char c : list) {
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