import java.util.Arrays;

class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][][] memo;

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        memo = new int[n][maxVal + 1][maxVal + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= maxVal; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return solve(0, 0, 0, nums, maxVal);
    }

    private int solve(int i, int x, int y, int[] nums, int maxVal) {
        if (i == nums.length) {
            return (x > 0 && x == y) ? 1 : 0;
        }

        if (memo[i][x][y] != -1) {
            return memo[i][x][y];
        }

        long count = solve(i + 1, x, y, nums, maxVal);

        int nextX = (x == 0) ? nums[i] : gcd(x, nums[i]);
        count = (count + solve(i + 1, nextX, y, nums, maxVal)) % MOD;

        int nextY = (y == 0) ? nums[i] : gcd(y, nums[i]);
        count = (count + solve(i + 1, x, nextY, nums, maxVal)) % MOD;

        return memo[i][x][y] = (int) count;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}