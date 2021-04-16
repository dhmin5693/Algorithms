import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class RandomizedSet {

    private final Set<Integer> set;
    private final Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        set = new HashSet<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (set.contains(val)) {
            return false;
        }

        set.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (set.contains(val)) {
            set.remove(val);
            return true;
        }

        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {

        int index = random.nextInt(set.size());

        return set.stream()
                  .mapToInt(i -> i)
                  .toArray()[index];
    }
}

/* test with Junit5

class RandomizedSetTest {

    @Test
    void testcase01() {

        var set = new RandomizedSet();

        assertTrue(set.insert(1));
        assertFalse(set.remove(2));
        assertTrue(set.insert(2));
        assertEquals(2, set.getRandom());
        assertTrue(set.remove(1));
        assertFalse(set.insert(2));
        assertEquals(2, set.getRandom());
    }
}

 */