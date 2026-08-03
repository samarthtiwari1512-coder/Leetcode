import java.util.Arrays;

class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] memo = new int[n];
        Arrays.fill(memo, Integer.MIN_VALUE);

        int aliceScoreAdvantage = solve(0, stoneValue, memo);

        if (aliceScoreAdvantage > 0) {
            return "Alice";
        } else if (aliceScoreAdvantage < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }

    private int solve(int i, int[] stoneValue, int[] memo) {
        int n = stoneValue.length;
        // Base case: no stones left
        if (i >= n) {
            return 0;
        }

        if (memo[i] != Integer.MIN_VALUE) {
            return memo[i];
        }

        int maxAdvantage = Integer.MIN_VALUE;
        int currentTakenStones = 0;

        // Try taking 1, 2, or 3 stones
        for (int k = 0; k < 3 && i + k < n; k++) {
            currentTakenStones += stoneValue[i + k];
            int opponentAdvantage = solve(i + k + 1, stoneValue, memo);
            maxAdvantage = Math.max(maxAdvantage, currentTakenStones - opponentAdvantage);
        }

        return memo[i] = maxAdvantage;
    }
}