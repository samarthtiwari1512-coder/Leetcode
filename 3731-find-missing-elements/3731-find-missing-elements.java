import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Set<Integer> present = new HashSet<>();

        // Find min, max, and insert elements into a set
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            present.add(num);
        }

        List<Integer> missing = new ArrayList<>();

        // Collect all missing numbers in sorted order
        for (int i = min; i <= max; i++) {
            if (!present.contains(i)) {
                missing.add(i);
            }
        }

        return missing;
    }
}