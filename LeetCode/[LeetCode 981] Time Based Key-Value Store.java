import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMap {

    private final Map<String, TreeMap<Integer, String>> cache;

    public TimeMap() {
        cache = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {

        if (!cache.containsKey(key)) {
            cache.put(key, new TreeMap<>());
        }

        var map = cache.get(key);
        map.put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        var map = cache.get(key);
        var entry = map.floorEntry(timestamp);
        return entry == null ? "": entry.getValue();
    }
}

/*

class TimeMapTest {
    @Test
    void test01() {
        var timeMap = new TimeMap();
        timeMap.set("love","high",10);
        timeMap.set("love","low",20);

        assertEquals("", timeMap.get("love",5));
        assertEquals("high", timeMap.get("love",10));
        assertEquals("high", timeMap.get("love",15));
        assertEquals("low", timeMap.get("love",20));
        assertEquals("low", timeMap.get("love",25));
    }
}

 */