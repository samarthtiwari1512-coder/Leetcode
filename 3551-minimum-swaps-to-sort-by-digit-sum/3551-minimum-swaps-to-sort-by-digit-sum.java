import java.util.Arrays;

class Solution {
    private int getDigitSum(int val) {
        int sum = 0;
        while (val > 0) {
            sum += val % 10;
            val /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] order = new Integer[n];
        int[] digitSums = new int[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
            digitSums[i] = getDigitSum(nums[i]);
        }

        Arrays.sort(order, (i, j) -> {
            if (digitSums[i] != digitSums[j]) {
                return Integer.compare(digitSums[i], digitSums[j]);
            }
            return Integer.compare(nums[i], nums[j]);
        });

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || order[i] == i) {
                continue;
            }

            int cycleLen = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = order[curr];
                cycleLen++;
            }

            if (cycleLen > 1) {
                swaps += cycleLen - 1;
            }
        }

        return swaps;
    }
}