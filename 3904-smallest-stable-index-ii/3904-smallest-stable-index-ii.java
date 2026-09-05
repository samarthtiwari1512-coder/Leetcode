class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // Step 1: Precompute suffix minimums
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Step 2: Traverse from left to right tracking prefix maximum
        int prefixMax = nums[0];
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);

            // Instability score = max(nums[0..i]) - min(nums[i..n-1])
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}