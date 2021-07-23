import java.util.HashMap;

class Solution {
    public int majorityElement(int[] nums) {

        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        var map = new HashMap<Integer, Integer>();

        for (int num : nums) {
            map.merge(num, 1, (i, j) -> i + 1);
        }

        int answer = 0;
        int max = 0;
        for (int num : nums) {
            int value = map.get(num);

            if (value > max) {
                answer = num;
                max = value;
            }
        }

        return answer;
    }
}


/* test with Junit5 

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[] nums, int expected) {
        var actual = solution.majorityElement(nums);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {3,2,3}, 3),
            Arguments.of(new int[] {2,2,1,1,1,2,2}, 2),
            Arguments.of(new int[] {6,5,5}, 5)
        );
    }
}

*/
