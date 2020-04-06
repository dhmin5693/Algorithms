import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int sco : scoville) {
            pq.add(sco);
        }

        while(!pq.isEmpty() && pq.peek() <= K) {
            
            if (pq.size() == 1) {
                return -1;
            }
            
            int sco1 = pq.poll();
            int sco2 = pq.poll() * 2;

            pq.add(sco1 + sco2);
            answer++;
        }

        if (!pq.isEmpty() && pq.peek() < K) {
            return -1;
        }
        
        return answer;
    }
}