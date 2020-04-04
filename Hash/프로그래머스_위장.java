import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> map = new HashMap<>();

        for (String[] row : clothes) {
            map.put(row[1], count(map, row[1]));
        }

        for (int value : map.values()) {
            answer *= (value + 1);
        }

        return answer - 1;
    }

    private int count(Map<String, Integer> map, String key) {
        return map.containsKey(key) ? map.get(key) + 1 : 1;
    }
}
