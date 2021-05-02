import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {

    private Set<Integer> visited;
    private Queue<Integer> q;

    private int[][] map;

    public int findCircleNum(int[][] isConnected) {

        visited = new HashSet<>();
        q = new LinkedList<>();
        map = isConnected;

        int answer = 0;

        for (int i = 0; i < map.length; i++) {
            if (visited.contains(i)) {
                continue;
            }

            answer++;
            visited.add(i);
            q.add(i);

            loop();
        }

        return answer;
    }

    private void loop() {

        while(!q.isEmpty()) {
            int cur = q.poll();

            for (int next = 0; next < map[cur].length; next++) {

                if (map[cur][next] == 0 || visited.contains(next)) {
                    continue;
                }

                visited.add(next);
                q.add(next);
            }
        }
    }
}


/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[][] isConnected, int expected) {
        var actual = solution.findCircleNum(isConnected);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[][] {{1,1,0},{1,1,0},{0,0,1}}, 2),
            Arguments.of(new int[][] {{1,0,0},{0,1,0},{0,0,1}}, 3)
        );
    }
}

 */