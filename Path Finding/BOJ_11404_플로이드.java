import java.io.*;
import java.util.*;

public class Main {

    public static int INF = 100000000;

    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] cost = floyd(setData(br, n));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(((cost[i][j] == INF) ? 0 : cost[i][j]) + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    private static int[][] setData(BufferedReader br, int n) throws Exception {
        int m = Integer.parseInt(br.readLine());
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            String[] inputs = br.readLine().split(" ");

            int from = Integer.parseInt(inputs[0]) - 1;
            int to = Integer.parseInt(inputs[1]) - 1;
            int val = Integer.parseInt(inputs[2]);

            cost[from][to] = Math.min(cost[from][to], val);
        }

        return cost;
    }

    private static int[][] floyd(int[][] cost) {
        int n = cost.length;

        for (int mid = 0; mid < n; mid++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {

                    if (start == end) {
                        continue;
                    }
                    
                    if (cost[start][end] > cost[start][mid] + cost[mid][end]) {
                        cost[start][end] = cost[start][mid] + cost[mid][end];
                    }
                }
            }
        }

        return cost;
    }
}