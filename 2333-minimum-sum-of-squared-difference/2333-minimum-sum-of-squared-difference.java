class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int n = nums1.length;
        int maxDiff = 0;
        int[] diffs = new int[n];
        
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diffs[i]);
        }
        
        int[] count = new int[maxDiff + 1];
        for (int d : diffs) {
            count[d]++;
        }
        
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (count[d] == 0) continue;
            
            if (k >= count[d]) {
                k -= count[d];
                count[d - 1] += count[d];
                count[d] = 0;
            } else {
                count[d] -= k;
                count[d - 1] += k;
                k = 0;
            }
        }
        
        long res = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                res += (long) count[d] * d * d;
            }
        }
        return res;
    }
}