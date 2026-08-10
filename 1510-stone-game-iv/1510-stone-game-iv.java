class Solution {
    public boolean winnerSquareGame(int n) {
        // dp[i] represents if the player to move with i stones can win
        boolean[] dp = new boolean[n + 1];

        // Fill DP table from 1 to n
        for (int i = 1; i <= n; i++) {
            // Try removing every possible perfect square k*k <= i
            for (int k = 1; k * k <= i; k++) {
                if (!dp[i - k * k]) {
                    dp[i] = true; // Found a move that forces a loss for the opponent
                    break;
                }
            }
        }

        return dp[n];
    }
}