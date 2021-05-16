import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {

        var map = new HashMap<Integer, Integer>();
        map.put(0, 1);

        int answer = 0;
        int sum = 0;

        for (int num : nums) {
            sum += num;
            answer += map.getOrDefault(sum - k, 0);
            map.merge(sum, 1, Integer::sum);
        }

        return answer;
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int [] nums, int k, int expected) {
        var actual = solution.subarraySum(nums, k);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {1,1,1}, 2, 2),
            Arguments.of(new int[] {1,2,3}, 3, 2)
        );
    }
}


 */
