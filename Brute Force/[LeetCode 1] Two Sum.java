import java.util.HashMap;

class Solution {

    public int[] twoSum(int[] nums, int target) {

        var map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
           map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];

            if (map.containsKey(diff) && map.get(diff) != i) {
                return new int[] {i, map.get(diff)};
            }
        }

        return new int[] {0, 1};
    }
}