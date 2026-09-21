class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        // dp[r] represents the count of subarrays ending at the current index with product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int modVal = num % k;

            // Single-element subarray [num]
            nextDp[modVal]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (int) (((long) r * modVal) % k);
                    nextDp[newRem] += dp[r];
                }
            }

            // Accumulate to total result
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }

            dp = nextDp;
        }

        return result;
    }
}