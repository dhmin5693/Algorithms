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
                         .sorted(Comparator.comparingInt(j -> j.end))
                         .collect(toUnmodifiableList());

        for (Job job : jobs) {
            int value = dp.floorEntry(job.start).getValue() + job.profit;
            if (value > dp.lastEntry().getValue()) {
                dp.put(job.end, value);
            }
        }

        return dp.lastEntry()
                 .getValue();
    }

    public static class Job {
        int start;
        int end;
        int profit;

        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}

/* test with Junit5



*/