import java.io.*;
import java.util.*;

public class Main {

    // public static final String INPUT = "input.txt";

    public static final String[] WB = {
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW"
    };

    public static final String[] BW = {
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB"
    };

    static int count(int y, int x, List<String> board, String[] b) {

        int cnt = 0;

        for (int i = y; i < y + 8; i++) {
            for (int j = x; j < x + 8; j++) {
                if (board.get(i).charAt(j) != b[i - y].charAt(j - x)) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static int countWB(int y, int x, List<String> board) {
        return count(y, x, board, WB);
    }

    static int countBW(int y, int x, List<String> board) {
        return count(y, x, board, BW);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader(INPUT));

        String[] inputs = br.readLine().split(" ");

        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        List<String> board = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            board.add(br.readLine());
        }

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < n - 7; i++) {
            for (int j = 0; j < m - 7; j++) {
                int tmp = Math.min(countWB(i, j, board), countBW(i, j, board));
                result = Math.min(tmp, result);
            }
        }

        System.out.println(result);
    }
}