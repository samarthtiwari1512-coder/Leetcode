import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> subarrayCount = new HashMap<>();

        // Check every contiguous subarray of size k
        for (int i = 0; i <= n - k; i++) {
            Set<Integer> uniqueInWindow = new HashSet<>();
            for (int j = i; j < i + k; j++) {
                uniqueInWindow.add(nums[j]);
            }

            // Increment count for each distinct element in this window
            for (int val : uniqueInWindow) {
                subarrayCount.put(val, subarrayCount.getOrDefault(val, 0) + 1);
            }
        }

        // Find the largest value with a frequency of exactly 1
        int maxAlmostMissing = -1;
        for (Map.Entry<Integer, Integer> entry : subarrayCount.entrySet()) {
            if (entry.getValue() == 1) {
                maxAlmostMissing = Math.max(maxAlmostMissing, entry.getKey());
            }
        }

        return maxAlmostMissing;
    }
}