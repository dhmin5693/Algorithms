class Solution {

    private static final int[] DY = new int[] { -1, 0, 1, 0 };
    private static final int[] DX = new int[] { 0, 1, 0, -1 };

    private int MAX_Y;
    private int MAX_X;

    private char[][] grid;

    public int numIslands(char[][] grid) {

        MAX_Y = grid.length;
        MAX_X = grid[0].length;

        this.grid = grid;

        return getAnswer();
    }

    private int getAnswer() {

        int sum = 0;

        for (int i = 0; i < MAX_Y; i++) {
            for (int j = 0; j < MAX_X; j++) {
                sum += floodFill(i, j);
            }
        }

        return sum;
    }

    private int floodFill(int y, int x) {

        if (isNotVisitable(y, x)) {
            return 0;
        }

        grid[y][x] = '0';

        for (int d = 0; d < 4; d++) {
            floodFill(y + DY[d], x + DX[d]);
        }

        return 1;
    }

    private boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= MAX_Y || x >= MAX_X;
    }

    private boolean isNotVisitable(int y, int x) {
        return isOutOfRange(y, x) || grid[y][x] == '0';
    }
}

/* TEST CODE with Junit 5

class SolutionTest {

    private final Solution code = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(char[][] grid, int output) {
        var result = code.numIslands(grid);
        assertThat(result, is(output));
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new char[][] {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}}, 1),
            Arguments.of(new char[][] {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}}, 3)
        );
    }
}
*/
