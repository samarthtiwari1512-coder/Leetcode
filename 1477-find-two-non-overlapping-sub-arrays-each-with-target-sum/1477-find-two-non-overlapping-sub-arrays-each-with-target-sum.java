import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;

        // minLen[i] = minimum length of a valid sub-array ending at or before index i
        int[] minLen = new int[n];
        Arrays.fill(minLen, INF);

        int left = 0;
        int currentSum = 0;
        int minTotalLen = INF;

        for (int right = 0; right < n; right++) {
            currentSum += arr[right];

            // Shrink window from the left if sum exceeds target
            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }

            // Carry forward the best length from the previous position
            if (right > 0) {
                minLen[right] = minLen[right - 1];
            }

            // Found a valid sub-array ending at `right`
            if (currentSum == target) {
                int len = right - left + 1;

                // Check if a non-overlapping sub-array exists before `left`
                if (left > 0 && minLen[left - 1] != INF) {
                    minTotalLen = Math.min(minTotalLen, len + minLen[left - 1]);
                }

                // Update best length ending at or before `right`
                minLen[right] = Math.min(minLen[right], len);
            }
        }

        return minTotalLen >= INF ? -1 : minTotalLen;
    }
}