class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // Step 1: Precompute suffix sums
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // Step 2: Memoization table dp[i][M]
        // Since M starts at 1 and can at most reach n, allocating n + 1 space for M is sufficient.
        int[][] memo = new int[n][n + 1];

        return helper(piles, suffixSum, 0, 1, memo);
    }

    private int helper(int[] piles, int[] suffixSum, int i, int M, int[][] memo) {
        int n = piles.length;

        // Base Case: If the current player can take all remaining piles
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        // Return memoized result if available
        if (memo[i][M] != 0) {
            return memo[i][M];
        }

        int maxStones = 0;

        // Try taking X piles where 1 <= X <= 2 * M
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            // Current player gets all remaining stones minus what the opponent gets
            int stones = suffixSum[i] - helper(piles, suffixSum, i + X, nextM, memo);
            maxStones = Math.max(maxStones, stones);
        }

        return memo[i][M] = maxStones;
    }
}