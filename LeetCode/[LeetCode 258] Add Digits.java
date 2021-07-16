class Solution {
    public int addDigits(int num) {

        while(num >= 10) {
            num = String.valueOf(num).chars()
                        .map(i -> i - '0')
                        .sum();
        }

        return num;
    }
}
