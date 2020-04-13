import java.util.Arrays;

class Solution {
    public int solution(String numbers) {

        int MAX = 10000000;
        boolean[] era = new boolean[MAX];

        for (int i = 2; i < MAX; i++) {
            for (int j = 2; j * i < MAX; j++) {
                era[i * j] = true;
            }
        }

        int[] cnt = new int[10];
        int answer = 0;

        for (int i = 0; i < numbers.length(); i++) {
            cnt[numbers.charAt(i) - '0']++;
        }

        int[] tmp = new int[10];

        int last = (int) Math.pow(10, numbers.length());
        for (int i = 2; i < last; i++) {

            if (era[i]) {
                continue;
            }

            Arrays.fill(tmp, 0);

            String num = String.valueOf(i);
            for (int j = 0; j < num.length(); j++) {
                tmp[num.charAt(j) - '0']++;
            }

            boolean prime = true;
            for (int j = 0; j < tmp.length; j++) {
                if (tmp[j] > cnt[j]) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                answer++;
            }
        }

        return answer;
    }
}