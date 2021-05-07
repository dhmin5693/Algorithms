import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

class LFUCache {

    private final Map<Integer, Integer> map;
    private final Map<Integer, Data> dataMap;
    private final TreeSet<Data> set;

    private final int capacity;
    private int time;

    public LFUCache(int capacity) {
        map = new HashMap<>();
        dataMap = new HashMap<>();
        set = new TreeSet<>();
        this.capacity = capacity;
    }

    public int get(int key) {

        time++;

        if (map.containsKey(key)) {
            refresh(key);
            return map.get(key);
        }

        return -1;
    }

    public void put(int key, int value) {
        time++;

        if (capacity <= 0) {
            return;
        }

        if (map.containsKey(key)) {
            map.put(key, value);
            refresh(key);
            return;
        }

        if (map.size() >= capacity) {
            Data d = set.first();
            map.remove(d.key);
            dataMap.remove(d.key);
            set.remove(d);
        }

        map.put(key, value);
        Data d = new Data(key, time);
        dataMap.put(key, d);
        set.add(d);
    }

    private void refresh(int key) {
        Data d = dataMap.get(key);
        set.remove(d);
        d.count++;
        d.time = time;
        set.add(d);
    }

    public static class Data implements Comparable<Data> {
        int key;
        int time;
        int count;

        public Data(int key, int time) {
            this.key = key;
            this.time = time;
            this.count = 1;
        }

        @Override
        public int compareTo(Data o) {

            if (count != o.count) {
                return count - o.count;
            }

            return time - o.time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Data data = (Data) o;
            return key == data.key && time == data.time && count == data.count;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, time, count);
        }
    }
}

/*

class LFUCacheTest {

    @Test
    void test01() {
        LFUCache c = new LFUCache(2);
        c.put(1, 1);
        c.put(2, 2);
        assertEquals(1, c.get(1));
        c.put(3, 3);
        assertEquals(-1, c.get(2));
        assertEquals(3, c.get(3));
        c.put(4, 4);
        assertEquals(-1, c.get(1));
        assertEquals(3, c.get(3));
        assertEquals(4, c.get(4));
    }

    @Test
    void test02() {
        LFUCache c = new LFUCache(2);
        c.put(2, 1);
        c.put(3, 2);
        assertEquals(2, c.get(3));
        assertEquals(1, c.get(2));
        c.put(4, 3);
        assertEquals(1, c.get(2));
        assertEquals(-1, c.get(3));
        assertEquals(3, c.get(4));
    }
}

 */