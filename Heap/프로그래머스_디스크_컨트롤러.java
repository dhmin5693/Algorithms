import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        List<Job> list = new ArrayList<>();

        for (int[] job : jobs) {
            list.add(new Job(job[0], job[1]));
        }

        list.sort((o1, o2) -> {
            if (o1.req != o2.req) return o1.req - o2.req;
            return o1.time - o2.time;
        });

        int idx = 0;
        PriorityQueue<Job> pq = new PriorityQueue<>();
        pq.add(list.get(idx++));

        int time = 0;
        while(!pq.isEmpty()) {
            Job job = pq.poll();
            if (time < job.req) {
                time = job.req;
            }
            time += job.time;
            answer += job.turnaroundTime(time);

            while (idx < list.size() && list.get(idx).req < time) {
                pq.add(list.get(idx++));
            }

            if (idx < list.size() && pq.isEmpty()) {
                pq.add(list.get(idx++));
            }
        }

        return answer / jobs.length;
    }

    class Job implements Comparable<Job> {
        int req;
        int time;
        Job(int req, int time) {
            this.req = req;
            this.time = time;
        }
        int turnaroundTime(int end) {
            return end - req;
        }

        @Override
        public int compareTo(Job o) {
            if (this.time != o.time) return this.time - o.time;
            return this.req - o.req;
        }
    }
}