class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int totalN = n + k - 1;
        int totalK = 2 * k;

        if (totalK > totalN) {
            return 0;
        }

        int[][] dp = new int[totalN + 1][totalK + 1];

        for (int i = 0; i <= totalN; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i, totalK); j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        return dp[totalN][totalK];
    }
}