class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        // dp[c] stores the count of distinct subsequences ending with character ('a' + c)
        int[] dp = new int[26];

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';
            
            // Sum up all existing distinct subsequences across all characters
            long sum = 0;
            for (int val : dp) {
                sum = (sum + val) % MOD;
            }
            
            // +1 accounts for the single-character subsequence consisting of only 'ch'
            dp[c] = (int) ((sum + 1) % MOD);
        }

        // Sum up the distinct subsequences ending with every character
        long ans = 0;
        for (int val : dp) {
            ans = (ans + val) % MOD;
        }

        return (int) ans;
    }
}