import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {

    private int[] courses;
    private final StringBuilder current = new StringBuilder();
    private final Set<String> combinations = new HashSet<>();

    public String[] solution(String[] orders, int[] courses) {

        this.courses = courses;

        for (String order : orders) {
            var orderArray = order.toCharArray();
            Arrays.sort(orderArray);
            combination(orderArray, 0, order.length());
        }

        var menuCounter = computeMenuCounter(
            Arrays.stream(orders)
                  .map(this::toCharSet)
                  .collect(Collectors.toList()));

        var entries = getSortedEntries(menuCounter);

        return getAnswer(entries);
    }

    private void combination(char[] orderChars, int index, int max) {

        if (index >= max) {
            return;
        }

        for (int i = index; i < max; i++) {

            current.append(orderChars[i]);
            int length = current.length();
            for (int course : courses) {
                if (length == course) {
                    combinations.add(current.toString());
                    break;
                }
            }

            combination(orderChars, i + 1, max);
            current.deleteCharAt(length - 1);
        }
    }

    private Map<String, Integer> computeMenuCounter(List<Set<Character>> ordersSet) {

        Map<String, Integer> map = new HashMap<>();

        for (var orderSet : ordersSet) {
            for (var comb : combinations) {
                if (contains(orderSet, comb)) {
                    map.put(comb, map.getOrDefault(comb, 0) + 1);
                }
            }
        }

        return map;
    }

    private ArrayList<Entry<String, Integer>> getSortedEntries(Map<String, Integer> map) {

        var list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());

        return list;
    }

    private String[] getAnswer(ArrayList<Entry<String, Integer>> entries) {

        var result = new ArrayList<String>();
        var maxCounts = new HashMap<Integer, Integer>();

        for (var entry : entries) {

            int count = entry.getValue();
            if (count <= 1) {
                continue;
            }

            int courseSize = entry.getKey().length();

            if (count >= maxCounts.getOrDefault(courseSize, 0)) {
                maxCounts.put(courseSize, count);
                result.add(entry.getKey());
            }
        }

        Collections.sort(result);

        var answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private Stream<Character> stringToCharStream(String s) {
        return s.chars().mapToObj(i -> (char) i);
    }

    private boolean contains(Set<Character> set, String s) {
        return stringToCharStream(s).allMatch(set::contains);
    }

    private Set<Character> toCharSet(String s) {
        return stringToCharStream(s).collect(Collectors.toSet());
    }
}

