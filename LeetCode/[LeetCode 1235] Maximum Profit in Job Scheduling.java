import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toUnmodifiableList;

class Solution {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {

        if (endTime == null || endTime.length == 0) {
            return 0;
        }

        var dp = new TreeMap<Integer, Integer>();
        dp.put(0, 0);

        var jobs = Stream.iterate(0, i -> i < startTime.length, i -> i + 1)
                         .map(i -> new Job(startTime[i], endTime[i], profit[i]))
                         .sorted()
                         .collect(toUnmodifiableList());

        for (Job job : jobs) {
            int value = dp.floorEntry(job.start).getValue() + job.profit;

            if (value > dp.floorEntry(job.end).getValue()) {
                dp.put(job.end, value);
            }
        }

        return dp.lastEntry()
                 .getValue();
    }

    public static class Job implements Comparable<Job> {
        int start;
        int end;
        int profit;

        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }

        @Override
        public int compareTo(Job o) {
            if (end != o.end) {
                return end - o.end;
            }
            return start - o.start;
        }
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[] startTime, int[] endTime, int[] profit, int expected) {
        var actual = solution.jobScheduling(startTime, endTime, profit);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {4,2,4,8,2}, new int[] {5,5,5,10,8}, new int[] {1,2,8,10,4}, 18),
            Arguments.of(new int[] {1,2,3,4,6}, new int[] {3,5,10,6,9}, new int[] {20,20,100,70,60}, 150),
            Arguments.of(new int[] {1,2,3,3}, new int[] {3,4,5,6}, new int[] {50,10,40,70}, 120),
            Arguments.of(new int[] {1,1,1}, new int[] {2,3,4}, new int[] {5,6,4}, 6)
        );
    }
}

*/