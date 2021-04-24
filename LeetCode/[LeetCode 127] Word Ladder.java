import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (beginWord.equals(endWord)) {
            return 1;
        }

        var dictionary = new HashSet<>(wordList);

        if (!dictionary.contains(endWord)) {
            return 0;
        }

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        dictionary.remove(beginWord);

        int answer = 0;

        while(!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {
                String word = q.poll();

                StringBuilder sb = new StringBuilder(word);

                for (int j = 0; j < word.length(); j++) {

                    char current = sb.charAt(j);

                    for (char c = 'a'; c <= 'z'; c++) {

                        if (current == c) {
                            continue;
                        }

                        sb.setCharAt(j, c);

                        String next = sb.toString();

                        if (endWord.equals(next)) {
                            return answer + 2;
                        }

                        if (dictionary.contains(next)) {
                            q.add(next);
                            dictionary.remove(next);
                        }
                    }

                    sb.setCharAt(j, current);
                }
            }

            answer++;
        }

        return 0;
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(String beginWord, String endWord, List<String> wordList, int expected) {
        var actual = solution.ladderLength(beginWord, endWord, wordList);
//        System.out.println(actual);
//        System.out.println(expected);
//        assertArrayEquals(expected, actual);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of("ymain", "oecij", List.of("ymann","yycrj","oecij","ymcnj","yzcrj","yycij","xecij","yecij","ymanj","yzcnj","ymain"), 10),
            Arguments.of("hot", "dog", List.of("hot","dog"), 0),
            Arguments.of("hit", "cog", List.of("hot","dot","dog","lot","log","cog"), 5),
            Arguments.of("hit", "cog", List.of("hot","dot","dog","lot","log"), 0)
        );
    }
}

 */