import java.util.HashSet;

class Solution {
    public int singleNumber(int[] nums) {

        var set = new HashSet<Integer>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
                continue;
            }
            set.add(num);
        }

        return set.stream()
                  .findFirst()
                  .orElse(0);
    }
}


/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[] num, int expected) {
        var actual = solution.singleNumber(num);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {2,2,1}, 1),
            Arguments.of(new int[] {4,1,2,1,2}, 4),
            Arguments.of(new int[] {1}, 1)
        );
    }
}


 */