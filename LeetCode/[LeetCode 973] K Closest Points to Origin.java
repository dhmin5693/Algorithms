import java.util.PriorityQueue;

class Solution {
    public int[][] kClosest(int[][] points, int k) {

        var pq = new PriorityQueue<P>();

        for (int[] point : points) {
            pq.add(new P(point,
                         ((long) point[0] * point[0]) + ((long) point[1] * point[1])));
        }

        var answer = new int[k][];
        for (int i = 0; i < k; i++) {
            answer[i] = pq.poll().point;
        }

        return answer;
    }

    static class P implements Comparable<P> {
        int[] point;
        long distance;

        public P(int[] point, long distance) {
            this.point = point;
            this.distance = distance;
        }

        @Override
        public int compareTo(P o) {
            return Long.compare(this.distance, o.distance);
        }
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[][] points, int k, int[][] expected) {
        var actual = solution.kClosest(points, k);
        assertArrayEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[][] {new int[] {1, 3}, new int[] {-2, 2}}, 1, new int[][] {new int[] {-2, 2}}),
            Arguments.of(new int[][] {new int[] {3, 3}, new int[] {5, -1}, new int[] {-2, 4}}, 2, new int[][] {new int[] {3, 3}, new int[] {-2, 4}})
        );
    }
}

 */