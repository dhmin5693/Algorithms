import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
class UndergroundSystem {

    private final Map<Integer, Visit> visitMap = new HashMap<>();
    private final Map<Travel, List<Integer>> timeMap = new HashMap<>();
    private final Map<Travel, Integer> sumMap = new HashMap<>();

    public void checkIn(int id, String stationName, int t) {
        visitMap.put(id, new Visit(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        var data = visitMap.get(id);
        if (data == null) {
            return;
        }

        visitMap.remove(id);

        var travel = new Travel(data.station, stationName);
        timeMap.computeIfAbsent(travel, key -> new ArrayList<>());

        int timeDiff = t - data.time;
        var times = timeMap.get(travel);
        times.add(timeDiff);

        sumMap.merge(travel, timeDiff, Integer::sum);
    }

    public double getAverageTime(String startStation, String endStation) {

        var key = new Travel(startStation, endStation);

        var times = timeMap.get(key);
        if (times == null) {
            return 0;
        }

        int sum = sumMap.get(key);
        return (double)sum / times.size();
    }

    public static class Visit {
        final String station;
        final int time;

        public Visit(String station, int t) {
            this.station = station;
            this.time = t;
        }
    }

    public static class Travel {
        final String from;
        final String to;

        public Travel(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Travel travel = (Travel) o;
            return from.equals(travel.from) && to.equals(travel.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}

/* test with Junit5

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UndergroundSystemTest {

    @Test
    void test01() {
        var system = new UndergroundSystem();
        system.checkIn(45,"Leyton",3);
        system.checkIn(32,"Paradise",8);
        system.checkIn(27,"Leyton",10);
        system.checkOut(45,"Waterloo",15);
        system.checkOut(27,"Waterloo",20);
        system.checkOut(32,"Cambridge",22);
        assertEquals(14.0D, system.getAverageTime("Paradise","Cambridge"));
        assertEquals(11.0D, system.getAverageTime("Leyton","Waterloo"));
        system.checkIn(10,"Leyton",24);
        assertEquals(11.0D, system.getAverageTime("Leyton","Waterloo"));
        system.checkOut(10,"Waterloo",38);
        assertEquals(12.0D, system.getAverageTime("Leyton","Waterloo"));
    }
}

 */