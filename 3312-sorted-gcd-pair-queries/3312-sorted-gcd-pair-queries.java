class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Step 1: Frequency count of each value
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // Step 2: Multiples count for each integer i
        int[] multiples = new int[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            for (int j = i; j <= maxVal; j += i) {
                multiples[i] += freq[j];
            }
        }

        // Step 3: Exact GCD pair counts using reverse sieve
        long[] gcdCount = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long c = multiples[i];
            gcdCount[i] = c * (c - 1) / 2;
            for (int j = 2 * i; j <= maxVal; j += i) {
                gcdCount[i] -= gcdCount[j];
            }
        }

        // Step 4: Prefix sums of gcd pair counts
        long[] pref = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            pref[i] = pref[i - 1] + gcdCount[i];
        }

        // Step 5: Answer queries using binary search
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            int low = 1, high = maxVal;
            int res = maxVal;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (pref[mid] > target) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            ans[i] = res;
        }

        return ans;
    }
}