import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

class MedianFinder {

    private final PriorityQueue<Integer> max;
    private final PriorityQueue<Integer> min;

    /** initialize your data structure here. */
    public MedianFinder() {
        max = new PriorityQueue<>(Comparator.reverseOrder());
        min = new PriorityQueue<>();
    }

    public void addNum(int num) {

        if (max.size() >= min.size()) {
            min.add(num);
        } else {
            max.add(num);
        }

        if (max.size() == 0 || min.size() == 0) {
            return;
        }

        if (min.peek() <= max.peek()) {
            int a = min.poll();
            int b = max.poll();
            max.add(a);
            min.add(b);
        }
    }

    public double findMedian() {

        if (min.size() == 0 && max.size() == 0) {
            return 0.0D;
        }

        if (min.size() > max.size()) {
            return (double) min.peek();
        }

        return (((double) min.peek()) + ((double) max.peek())) / 2.0D;
    }
}