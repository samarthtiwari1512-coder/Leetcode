class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;
        int[] bitCounts = new int[31];
        
        for (int x : nums) {
            for (int b = 0; b < 31; b++) {
                if (((x >> b) & 1) == 1) {
                    bitCounts[b]++;
                }
            }
        }
        
        long ans = 0;
        for (int i = 0; i < k; i++) {
            long val = 0;
            for (int b = 0; b < 31; b++) {
                if (bitCounts[b] > i) {
                    val |= (1L << b);
                }
            }
            ans = (ans + (val % MOD) * (val % MOD)) % MOD;
        }
        
        return (int) ans;
    }
}