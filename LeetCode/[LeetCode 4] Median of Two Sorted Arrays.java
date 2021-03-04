import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        enqueue(nums1);
        enqueue(nums2);

        if (min.size() > max.size()) {
            return (double) min.peek();
        }

        return (((double) min.peek()) + ((double) max.peek())) / 2f;
    }

    private void enqueue(int[] nums) {
        for (int num : nums) {
            if (min.size() <= max.size()) {
                min.add(num);
            } else {
                max.add(num);
            }

            if (min.size() == 0 || max.size() == 0) {
                continue;
            }

            if (min.peek() <= max.peek()) {
                int a = min.poll();
                int b = max.poll();

                max.add(a);
                min.add(b);
            }
        }
    }
}
