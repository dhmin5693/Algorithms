class Solution {
    public int reverse(int x) {
        if (x == 0) {
            return x;
        }

        long input = x;
        boolean isNegative = input < 0;
        if (isNegative) {
            input = -input;
        }

        StringBuilder sb = new StringBuilder().append(input);
        sb.reverse();
        long reverse = Long.parseLong(sb.toString());
        reverse = isNegative ? -reverse : reverse;

        if (reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) reverse;
    }
}