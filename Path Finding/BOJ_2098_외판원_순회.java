import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    final static int INF = 987654321;
    static int ALL_VISIT;

    static int n;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws Exception {

//        br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        setData();
        System.out.println(tsp(0, 1));

//        bw.flush();
//        bw.close();
    }

    private static void setData() throws Exception {
        ALL_VISIT = (1 << n) - 1;
        map = new int[16][16];
        dp = new int[16][1 << n];

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    static int tsp(int cur, int bitMask) {

        if (bitMask == ALL_VISIT) {
            if (map[cur][0] == 0) {
                return INF;
            }
            
            return map[cur][0];
        }

        if (dp[cur][bitMask] != 0) {
            return dp[cur][bitMask];
        }

        int cost = INF;

        for (int next = 1; next < n; next++) {
            if ((bitMask & (1 << next)) == 0 && map[cur][next] > 0) {
                cost = Math.min(cost, tsp(next, (bitMask | (1 << next))) + map[cur][next]);
            }
        }

        return dp[cur][bitMask] = cost;
    }
}