class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        
        // Step 1: Precompute prefix sums
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
        }

        // Memoization table: memo[i][j] stores the result for range [i, j]
        int[][] memo = new int[n][n];

        return solve(0, n - 1, prefixSum, memo);
    }

    private int solve(int i, int j, int[] prefixSum, int[][] memo) {
        // Base case: only 1 stone left, game ends
        if (i == j) {
            return 0;
        }

        // Return memoized result if already calculated
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int maxScore = 0;

        // Try all possible split positions k
        for (int k = i; k < j; k++) {
            int leftSum = prefixSum[k + 1] - prefixSum[i];
            int rightSum = prefixSum[j + 1] - prefixSum[k + 1];

            if (leftSum < rightSum) {
                // Bob throws away right part, keep left
                maxScore = Math.max(maxScore, leftSum + solve(i, k, prefixSum, memo));
            } else if (leftSum > rightSum) {
                // Bob throws away left part, keep right
                maxScore = Math.max(maxScore, rightSum + solve(k + 1, j, prefixSum, memo));
            } else {
                // Sums are equal: Alice decides which side to keep
                int takeLeft = leftSum + solve(i, k, prefixSum, memo);
                int takeRight = rightSum + solve(k + 1, j, prefixSum, memo);
                maxScore = Math.max(maxScore, Math.max(takeLeft, takeRight));
            }
        }

        return memo[i][j] = maxScore;
    }
}