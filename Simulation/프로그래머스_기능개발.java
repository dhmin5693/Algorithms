import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        ArrayList<Integer> answer = new ArrayList<>();

        int start = 0;

        while(start < progresses.length) {

            for (int i = start; i < progresses.length; i++) {
                progresses[i] += speeds[i];
            }

            int cnt = 0;
            for (int i = start; i < progresses.length; i++) {
                if (progresses[i] >= 100) {
                   cnt++;
                } else {
                    break;
                }
            }

            if (cnt > 0) {
                answer.add(cnt);
                start += cnt;
            }
        }


        int[] ans = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            ans[i] = answer.get(i);
        }

        return ans;
    }
}