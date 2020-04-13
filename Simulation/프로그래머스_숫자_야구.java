import java.util.TreeSet;

class Solution {
    public int solution(int[][] baseball) {

        TreeSet<Integer> answer = new TreeSet<>();

        int[][] question = new int[baseball.length][3];
        boolean[] chk = new boolean[3];

        for (int i = 0; i < baseball.length; i++) {
            question[i] = getArr(baseball[i][0]);
        }

        for (int num = 123; num <= 999; num++) {

            int[] arr = getArr(num);

            if (arr[0] == 0 || arr[1] == 0 || arr[2] == 0) {
                continue;
            }
            
            if (arr[0] == arr[1] || arr[1] == arr[2] || arr[2] == arr[0]) {
                continue;
            }

            boolean ok = true;

            for (int i = 0; i < baseball.length; i++) {

                int strike = 0;
                int ball = 0;

                for (int j = 0; j < 3; j++) {
                    chk[j] = false;
                }

                for (int j = 0; j < 3; j++) {
                    if (arr[j] == question[i][j]) {
                        strike++;
                        chk[j] = true;
                    }
                }

                for (int j = 0; j < 3; j++) {

                    if (chk[j]) {
                        continue;
                    }

                    for (int k = 0; k < 3; k++) {
                        if (j == k || chk[k]) {
                            continue;
                        }

                        if (arr[j] == question[i][k]) {
                            ball++;
                        }
                    }
                }

                if (strike != baseball[i][1] || ball != baseball[i][2]) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                answer.add(num);
            }
        }

        return answer.size();
    }

    private int[] getArr(int num) {
        return new int[] { num / 100, (num % 100) / 10, num % 10};
    }
}