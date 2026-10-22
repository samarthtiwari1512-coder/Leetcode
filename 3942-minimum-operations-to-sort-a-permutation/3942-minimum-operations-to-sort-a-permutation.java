class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }

        int idx0 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx0 = i;
                break;
            }
        }

        // Check if array is an increasing cyclic shift
        boolean isInc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx0 + i) % n] != i) {
                isInc = false;
                break;
            }
        }

        if (isInc) {
            int leftShiftCost = idx0;
            int rightShiftCost = (idx0 == 0) ? 0 : (n - idx0 + 2);
            return Math.min(leftShiftCost, rightShiftCost);
        }

        // Check if array is a decreasing cyclic shift
        boolean isDec = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx0 - i + n) % n] != i) {
                isDec = false;
                break;
            }
        }

        if (isDec) {
            int cost1 = 1 + (n - 1 - idx0);
            int cost2 = (idx0 + 1) % n + 1;
            return Math.min(cost1, cost2);
        }

        return -1;
    }
}