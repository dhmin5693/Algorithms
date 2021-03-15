import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        var list = new ArrayList<int[]>();
        int[] temp = null;

        for (int[] interval : intervals) {

            if (temp == null) {
                temp = Arrays.copyOf(interval, 2);
                continue;
            }

            if (temp[1] >= interval[0]) {
                temp[1] = Math.max(temp[1], interval[1]);
                continue;
            }

            list.add(temp);
            temp = Arrays.copyOf(interval, 2);
        }

        if (temp != null) {
            list.add(temp);
        }

        int[][] answer = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}

/* Test code with Junit 5
class SolutionTest {

    private final Solution code = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[][] intervals, int[][] output) {

        var result = code.merge(intervals);
        System.out.println("result: " + Arrays.toString(result));
        System.out.println("output: " + Arrays.toString(output));
        assertThat(result, is(output));
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[][] {{1,3},{2,6},{8,10},{15,18}}, new int[][] {{1,6},{8,10},{15,18}}),
            Arguments.of(new int[][] {{1,4},{4,5}}, new int[][] {{1,5}})
        );
    }
}
 */