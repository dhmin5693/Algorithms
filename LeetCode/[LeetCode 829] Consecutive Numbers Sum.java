class Solution {
    public int consecutiveNumbersSum(int N) {

        int sub = 0;
        int increment = 1;
        int count = 0;
        double div = 1.0;

        while ((N - sub) / div >= 1) {
            if ((N - sub) / div++ % 1 == 0) {
                count++;
            }
            sub += increment++;
        }

        return count;
    }
}
