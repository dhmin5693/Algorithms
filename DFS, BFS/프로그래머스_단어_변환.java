import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Solution {

    int length = 0;

    public int solution(String begin, String target, String[] words) {

        length = begin.length();

        int answer = Integer.MAX_VALUE;
        boolean find = false;

        Queue<Tmp> q = new LinkedList<>();
        Map<String, Integer> visit = new HashMap<>();

        for (String word : words) {
            if (changeable(begin, word)) {
                q.add(new Tmp(word, 1));
                visit.put(word, 1);
            }
        }

        while(!q.isEmpty()) {
            Tmp t = q.poll();

            if (t.word.equals(target)) {
                find = true;
                answer = Integer.min(answer, t.cnt);
                continue;
            }

            for (String word : words) {
                if (changeable(t.word, word)) {
                    if (visit.containsKey(word) && visit.get(word) <= t.cnt) {
                        continue;
                    }

                    q.add(new Tmp(word, t.cnt + 1));
                    visit.put(word, t.cnt + 1);
                }
            }
        }

        if (!find) {
            return 0;
        }

        return answer;
    }

    class Tmp {
        String word;
        int cnt;
        Tmp(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }

    boolean changeable(String a, String b) {
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                cnt++;
            }
        }

        return length - 1 == cnt;
    }
}