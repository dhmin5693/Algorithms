import java.util.PriorityQueue;

class Solution {
    public int solution(int stock, int[] dates, int[] supplies, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int answer = 0, idx = 0;

        for (int day = 0; day < k; day++) {

            if (idx < dates.length && day == dates[idx]) {
                pq.add(-supplies[idx++]);
            }

            if (stock == 0) {
                stock += -pq.poll();
                answer++;
            }

            stock--;
        }

        return answer;
    }
}