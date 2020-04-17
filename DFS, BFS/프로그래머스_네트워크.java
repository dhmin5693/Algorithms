import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public int solution(int n, int[][] computers) {
        int answer = 0;
        int size = computers.length;
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (computers[i][j] == 1) {
                    q.add(new Point(i, j));
                    computers[i][j] = computers[j][i] = 0;
                }
            }

            if (!q.isEmpty()) {
                answer++;
            }

            while(!q.isEmpty()) {
                Point p = q.poll();

                if (p.y == p.x) {
                    continue;
                }

                int y = p.x;

                for (int x = 0; x < size; x++) {
                    if (computers[y][x] == 1) {
                        q.add(new Point(y, x));
                        computers[y][x] = computers[x][y] = 0;
                    }
                }
            }
        }

        return answer;
    }

    class Point {
        int y;
        int x;
        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}