import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {

        Map<String, Integer> map = new HashMap<>();

        for (String p : participant) {
            map.put(p, map.containsKey(p) ? map.get(p) + 1 : 0);
        }

        for (String c : completion) {
            int cnt = map.get(c);
            if (cnt == 0) {
                map.remove(c);
                continue;
            }
            map.put(c, cnt - 1);
        }

        String answer = "";

        for (String key : map.keySet()) {
            answer = key;
        }

        return answer;
    }
}