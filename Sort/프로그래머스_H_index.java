import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;

        List<Integer> list = Arrays.stream(citations)
                .boxed().collect(Collectors.toList());

        list.sort(Comparator.reverseOrder());

        while(answer < citations.length) {
            if (list.get(answer) <= answer) break;
            answer++;
        }

        return answer;
    }
}