import java.util.Arrays;

class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        return maxScoreDiff(nums, 0, n - 1, memo) >= 0;
    }

    private int maxScoreDiff(int[] nums, int i, int j, int[][] memo) {
        if (i == j) {
            return nums[i];
        }

        if (memo[i][j] != Integer.MIN_VALUE) {
            return memo[i][j];
        }

        int pickLeft = nums[i] - maxScoreDiff(nums, i + 1, j, memo);

        int pickRight = nums[j] - maxScoreDiff(nums, i, j - 1, memo);

        return memo[i][j] = Math.max(pickLeft, pickRight);
    }
}