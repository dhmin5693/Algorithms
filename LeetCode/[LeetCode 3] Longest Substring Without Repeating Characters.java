import java.util.HashSet;

class Solution {
    public int lengthOfLongestSubstring(String s) {

        final int length = s.length();

        var set = new HashSet<Character>();
        int answer = 0;

        int j = 0;
        for (int i = 0; i < length; i++) {
            if (set.contains(s.charAt(i))) {
                for (; j < length; j++) {
                    set.remove(s.charAt(j));

                    if (s.charAt(i) == s.charAt(j)) {
                        j++;
                        break;
                    }
                }
            }

            set.add(s.charAt(i));
            answer = Math.max(answer, set.size());
        }

        return answer;
    }
}