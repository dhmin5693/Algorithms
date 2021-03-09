class Solution {
    public int myAtoi(String s) {

        s = s.trim();

        if (s.length() == 0) {
            return 0;
        }

        int sign = 1;
        long sum = 0L;

        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            sign = s.charAt(0) == '+' ? 1 : -1;
            s = s.substring(1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                break;
            }

            sum *= 10L;
            sum += s.charAt(i) - '0';

            if (sign == 1 && sum >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            if (sign == -1 && -sum <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        return sign * ((int) sum);
    }
}
