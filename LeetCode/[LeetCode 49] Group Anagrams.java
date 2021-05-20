import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length == 0) {
            return List.of(List.of(""));
        }

        var map = Arrays.stream(strs)
                        .collect(groupingBy(s -> {
                            var arr = s.toCharArray();
                            Arrays.sort(arr);

                            return new String(arr);
                        }));

        return new ArrayList<>(map.values());
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String[] strs, List<List<String>> output) {

        var expected = new HashSet<Set<String>>();
        for (var o : output) {
            expected.add(new HashSet<>(o));
        }

        var actual = new HashSet<Set<String>>();
        var result = solution.groupAnagrams(strs);
        for (var v : result) {
            actual.add(new HashSet<>(v));
        }

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new String[] {"eat","tea","tan","ate","nat","bat"}, List.of(List.of("bat"), List.of("nat","tan"), List.of("ate","eat","tea"))),
            Arguments.of(new String[] {""}, List.of(List.of(""))),
            Arguments.of(new String[] {"a"}, List.of(List.of("a")))
        );
    }
}

 */
