import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {

        int[] answer = new int[commands.length];
        int idx = 0;

        for (int[] cmd : commands) {

            int[] arr = new int[cmd[1] - cmd[0] + 1];

            int _i = 0;
            for (int i = cmd[0] - 1; i < cmd[1]; i++) {
                arr[_i++] = array[i];
            }

            Arrays.sort(arr);
            answer[idx++] = arr[cmd[2] - 1];
        }

        return answer;
    }
}