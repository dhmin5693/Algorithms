import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SolutionTest {

    private final Solution code = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String s, List<String> dictionary, String output) {

        var result = code.solution(s, dictionary);
        System.out.println("result: " + result);
        System.out.println("output: " + output);

        assertThat(result, is(output));
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of("abpcplea", List.of("ale","apple","monkey","plea"), "apple"),
            Arguments.of("abpcplea", List.of("a","b","c"), "a"),
            Arguments.of("abcde", List.of("abc","abd","abe"), "abc")
        );
    }
}
