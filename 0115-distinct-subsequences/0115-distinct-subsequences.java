class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        // If target is longer than source, no subsequences are possible
        if (m > n) return 0;

        // dp[j] stores the number of subsequences of s that equal t[0...j-1]
        // Using int because the problem guarantees the result fits in a 32-bit signed integer
        int[] dp = new int[m + 1];
        dp[0] = 1; // Empty prefix of t can always be formed (1 way)

        for (int i = 0; i < n; i++) {
            char sc = s.charAt(i);
            // Traverse backwards to use values from the previous iteration of s
            for (int j = m; j >= 1; j--) {
                if (sc == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[m];
    }
}