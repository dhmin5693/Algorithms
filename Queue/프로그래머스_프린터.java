import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] priorities, int location) {

        int answer = 0;
        int[] p = new int[10];

        Queue<Doc> q = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            q.add(new Doc(priorities[i], i));
            p[priorities[i]]++;
        }

        while(!q.isEmpty()) {
            Doc doc = q.poll();

            boolean print = true;

            for (int i = doc.priority + 1; i < 10; i++) {
                if (p[i] > 0) {
                    q.add(doc);
                    print = false;
                    break;
                }
            }

            if (print) {
                p[doc.priority]--;
                answer++;

                if (doc.idx == location) {
                    break;
                }
            }
        }

        return answer;
    }

    class Doc {
        int priority, idx;
        Doc (int priority, int idx) {
            this.priority = priority;
            this.idx = idx;
        }
    }
}