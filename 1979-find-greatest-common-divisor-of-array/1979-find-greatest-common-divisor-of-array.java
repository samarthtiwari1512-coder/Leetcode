class Solution {
    public int findGCD(int[] nums) {
        int minVal = nums[0];
        int maxVal = nums[0];

        for (int num : nums) {
            if (num < minVal) {
                minVal = num;
            }
            if (num > maxVal) {
                maxVal = num;
            }
        }

        return gcd(minVal, maxVal);
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