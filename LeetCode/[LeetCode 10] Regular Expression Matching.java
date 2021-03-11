import java.util.Arrays;

class Solution {

    private char[] s, p;
    private int[][] cache; // init: -1, false: 0, true: 1

    public boolean isMatch(String str, String pattern) {

        cache = new int[str.length()][];
        for (int i = 0; i < str.length(); i++) {
            cache[i] = new int[pattern.length()];
            Arrays.fill(cache[i], -1);
        }

        s = str.toCharArray();
        p = pattern.toCharArray();

        if (s.length == p.length) {

            boolean check = true;
            for (int i = 0; i < this.s.length; i++) {
                if (!equals(s[i], p[i])) {
                    check = false;
                    break;
                }
            }

            if (check) {
                return true;
            }
        }

        return solve(0, 0) == 1;
    }

    private int solve(int sidx, int pidx) {

        if (sidx == s.length && pidx == p.length) {
            return 1;
        }

        if (pidx >= p.length) {
            return 0;
        }

        if (sidx >= s.length) {

            int rest = p.length - pidx;
            if (rest % 2 != 0) {
                return 0;
            }

            for (int i = pidx + 1; i < p.length; i += 2) {
                if (p[i] != '*') {
                    return 0;
                }
            }

            return 1;
        }

        if (cache[sidx][pidx] != -1) {
            return cache[sidx][pidx];
        }

        boolean isAsterisk = (pidx + 1 < p.length && p[pidx + 1] == '*');
        if (isAsterisk) {
            cache[sidx][pidx] = solve(sidx, pidx + 2);

            for (int i = sidx; i < s.length; i++) {
                if (!equals(s[i], p[pidx]) || cache[sidx][pidx] == 1) {
                    break;
                }

                cache[sidx][pidx] = solve(i + 1, pidx + 2);
            }
        } else if (equals(s[sidx], p[pidx])) {
            cache[sidx][pidx] = solve(sidx + 1, pidx + 1);
        }

        return cache[sidx][pidx];
    }

    private boolean equals(char a, char b) {
        return b == '.' || a == b;
    }
}
