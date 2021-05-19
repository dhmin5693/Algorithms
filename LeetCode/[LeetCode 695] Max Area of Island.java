class Solution {

    private final int[] dy = { -1, 0, 1, 0 };
    private final int[] dx = { 0, 1, 0, -1 };

    private int[][] grid;
    private boolean[][] visit;

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        init();
        return findIsland();
    }

    private void init() {
        visit = new boolean[grid.length][];
        for (int i = 0; i < visit.length; i++) {
            visit[i] = new boolean[grid[0].length];
        }
    }

    private int findIsland() {

        int max = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0 || visit[i][j]) {
                    continue;
                }
                int current = visit(i, j);
                max = Math.max(max, current);
            }
        }

        return max;
    }

    private int visit(int y, int x) {

        if (y < 0 || y >= grid.length || x < 0 || x >= grid[0].length || grid[y][x] == 0 || visit[y][x]) {
            return 0;
        }

        int count = 1;
        visit[y][x] = true;

        for (int d = 0; d < 4; d++) {
            count += visit(y + dy[d], x + dx[d]);
        }

        return count;
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[][] grid, int expected) {
        var actual = solution.maxAreaOfIsland(grid);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[][] {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}}, 4),
            Arguments.of(new int[][] {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}}, 6),
            Arguments.of(new int[][] {{0,0,0,0,0,0,0,0}}, 0)
        );
    }
}

 */
