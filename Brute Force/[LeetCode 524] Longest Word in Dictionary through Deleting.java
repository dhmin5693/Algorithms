import static java.util.stream.Collectors.toList;

import java.util.List;

/*
 * Given a string s and a string array dictionary,
 * return the longest string in the dictionary
 * that can be formed by deleting some of the given string characters.
 * If there is more than one possible result,
 * return the longest word with the smallest lexicographical order.
 * If there is no possible result, return the empty string.
 */

class Solution {
    public String findLongestWord(String s, List<String> dictionary) {

        var list = dictionary.stream()
                             .filter(item -> isPossibleWord(s, item))
                             .sorted(this::sort)
                             .collect(toList());

        if (list.size() == 0) {
            return "";
        }

        return list.get(0);
    }

    private boolean isPossibleWord(String s, String item) {

        int current = 0;
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == item.charAt(current)) {
                current++;
            }

            if (current == item.length()) {
                return true;
            }
        }

        return false;
    }
    
    private int sort(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return s2.length() - s1.length();
        }
        return s1.compareTo(s2);
    }
}