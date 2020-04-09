import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String solution(int[] numbers) {

        List<String> list = new ArrayList<>();

        for (int num : numbers) {
            list.add(String.valueOf(num));
        }

        list.sort(((o1, o2) -> {
            int a = Integer.parseInt(o1 + o2);
            int b = Integer.parseInt(o2 + o1);
            if (a > b) return -1;
            else if (a == b) return 0;
            return 1;
        }));

        StringBuilder sb = new StringBuilder();

        boolean isZero = true;
        for (String str : list) {
            sb.append(str);
            if (!str.equals("0")) {
                isZero = false;
            }
        }

        if (isZero) {
            return "0";
        }
        
        return sb.toString();
    }
}