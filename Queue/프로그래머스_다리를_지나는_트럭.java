import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        int sum = 0, time = 1, idx = 0;
        Queue<Truck> q = new LinkedList<>();

        sum += truck_weights[idx];
        q.add(new Truck(1, truck_weights[idx++]));

        while(!q.isEmpty()) {

            time++;

            if (time - q.peek().start == bridge_length) {
                sum -= q.poll().weight;
            }

            if (idx < truck_weights.length && weight >= sum + truck_weights[idx]) {
                sum += truck_weights[idx];
                q.add(new Truck(time, truck_weights[idx++]));
            }
        }

        return time;
    }

    class Truck {
        int start;
        int weight;

        Truck(int start, int weight) {
            this.start = start;
            this.weight = weight;
        }
    }
}