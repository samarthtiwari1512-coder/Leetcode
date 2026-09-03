class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for (int num : nums1) {
            if (num < minVal) {
                minVal = num;
            }
            if (num % 2 != 0) {
                hasOdd = true;
            }
        }

        // If min element is odd, it's always possible
        if (minVal % 2 != 0) {
            return true;
        }

        // If min element is even, it's possible only if no odd elements exist
        return !hasOdd;
    }
}