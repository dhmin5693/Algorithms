import java.util.Arrays;

class Solution {

    private String s, p;
    private int[][] cache; // init: -1, false: 0, true: 1

    public boolean isMatch(String s, String p) {

        if (s.equals(p)) {
            return true;
        }

        cache = new int[s.length()][];
        for (int i = 0; i < s.length(); i++) {
            cache[i] = new int[p.length()];
            Arrays.fill(cache[i], -1);
        }

        this.s = s;
        this.p = p;

        return solve(0, 0) == 1;
    }

    private int solve(int sidx, int pidx) {
        if (sidx == s.length() && pidx == p.length()) {
            return 1;
        }

        if (pidx == p.length()) {
            return 0;
        }

        if (sidx == s.length()) {
            int rest = p.length() - pidx;
            boolean asterisk = (rest % 2 == 0);

            for (int i = pidx + 1; i < p.length(); i += 2) {
                asterisk &= (p.charAt(i) == '*');
            }

            return asterisk ? 1 : 0;
        }

        if (cache[sidx][pidx] != -1) {
            return cache[sidx][pidx];
        }

        boolean isNextCharAsterisk = (pidx + 1 < p.length() && p.charAt(pidx + 1) == '*');
        if (isNextCharAsterisk) {

            cache[sidx][pidx] = solve(sidx, pidx + 2);

            for (int i = sidx; i < s.length(); i++) {
                if (!equals(s.charAt(i), p.charAt(pidx)) || cache[sidx][pidx] == 1) {
                    break;
                }

                cache[sidx][pidx] = solve(i + 1, pidx + 2);
            }
        } else if (equals(s.charAt(sidx), p.charAt(pidx))) {
            cache[sidx][pidx] = solve(sidx + 1, pidx + 1);
        }

        return cache[sidx][pidx];
    }

    boolean equals(char a, char b) {
        return b == '.' || a == b;
    }
}