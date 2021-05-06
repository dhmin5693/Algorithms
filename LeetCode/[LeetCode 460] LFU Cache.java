import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class LFUCache {

    private final Map<Integer, Integer> map;
    private final Map<Integer, Data> dataMap;
    private final PriorityQueue<Data> pq;

    private int time;
    private int capacity;

    public LFUCache(int capacity) {
        map = new HashMap<>();
        dataMap = new HashMap<>();
        pq = new PriorityQueue<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        time++;
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        time++;

        if (map.containsKey(key)) {
            map.put(key, value);
            Data d = dataMap.get(key);
            d.count++;
            d.time = time;
        }

        if (map.size() >= capacity) {
            return;
        }

        map.put(key, value);
    }

    public static class Data implements Comparable<Data> {
        int key;
        int time;
        int count;

        public Data(int key, int time, int count) {
            this.key = key;
            this.time = time;
            this.count = count;
        }

        @Override
        public int compareTo(Data o) {

            if (count != o.count) {
                return count - o.count;
            }

            return time - o.time;
        }
    }
}
