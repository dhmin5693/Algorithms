import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        var counter = new HashMap<Integer, Integer>();
        for (int num : nums) {
            counter.merge(num, 1, Integer::sum);
        }

        var pq = new PriorityQueue<Count>();
        for (var entry : counter.entrySet()) {
            pq.add(new Count(entry.getKey(), entry.getValue()));
        }

        var answer = new int[k];
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            answer[i] = pq.poll().num;
        }

        return answer;
    }

    public static class Count implements Comparable<Count> {

        int num;
        int cnt;

        public Count(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Count c) {
            return c.cnt - this.cnt;
        }
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[] nums, int k, int[] expected) {
        var actual = solution.topKFrequent(nums, k);
        assertArrayEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {1,1,1,2,2,3}, 2, new int[] {1,2}),
            Arguments.of(new int[] {1}, 1, new int[] {1})
        );
    }
}

 */