import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int n = Integer.parseInt(br.readLine());
        List<Time> times = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            times.add(new Time(br.readLine().split(" ")));
        }

        Collections.sort(times);

        int last = 0, cnt = 0;
        for (Time time : times) {
            if (time.st >= last) {
                cnt++;
                last = time.ed;
            }
        }

        System.out.println(cnt);
    }

    static class Time implements Comparable<Time> {
        int st, ed;

        public Time(String[] input) {
            st = Integer.parseInt(input[0]);
            ed = Integer.parseInt(input[1]);
        }

        @Override
        public int compareTo(Time o) {
            if (this.ed != o.ed) return this.ed - o.ed;
            return this.st - o.st;
        }
    }
}