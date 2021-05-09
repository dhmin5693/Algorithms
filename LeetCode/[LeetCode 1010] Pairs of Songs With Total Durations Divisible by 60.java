import java.util.HashMap;

class Solution {
    public int numPairsDivisibleBy60(int[] time) {

        var map = new HashMap<Integer, Integer>(60);
        int answer = 0;

        for (int t : time) {
            int key = t % 60;

            int num = (60 - key) % 60;
            if (map.containsKey(num)) {
                answer += map.get(num);
            }

            map.merge(key, 1, Integer::sum);
        }

        return answer;
    }
}

/*

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[] time, int expected) {
        var actual = solution.numPairsDivisibleBy60(time);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[]{60,60,60}, 3),
            Arguments.of(new int[]{30,20,150,100,40}, 3)
        );
    }
}

 */