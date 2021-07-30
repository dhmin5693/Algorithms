class Solution {

    private static final int[] DY = { 1, 0, -1, 0 };
    private static final int[] DX = { 0, 1, 0, -1 };

    private int direction, y, x;

    public boolean isRobotBounded(String instructions) {

        direction = y = x = 0;

        instructions.chars()
                    .mapToObj(i -> (char)i)
                    .forEach(this::run);

        return (y == x && x == 0) || direction != 0;
    }

    private void run(char instruction) {
        switch (instruction) {
            case 'G':
                y += DY[direction];
                x += DX[direction];
                break;
            case 'L':
                direction = direction - 1;
                if (direction < 0) {
                    direction = 3;
                }
                break;
            case 'R':
                direction = direction + 1;
                if (direction > 3) {
                    direction = 0;
                }
                break;
        }
    }
}

/* Test code with Junit5

class SolutionTest {

    private final Solution code = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String instructions, boolean output) {
        var result = code.isRobotBounded(instructions);
        assertThat(result, is(output));
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of("RLLGLRRRRGGRRRGLLRRR", true),
            Arguments.of("GGLLGG", true),
            Arguments.of("GG", false),
            Arguments.of("GL", true)
        );
    }
}

 */