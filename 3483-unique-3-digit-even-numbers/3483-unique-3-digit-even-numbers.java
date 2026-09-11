class Solution {
    public int totalNumbers(int[] digits) {
        int[] available = new int[10];
        for (int d : digits) {
            available[d]++;
        }

        int count = 0;

        // Iterate through all 3-digit even numbers
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;         // hundreds digit
            int d2 = (num / 10) % 10;   // tens digit
            int d3 = num % 10;          // units digit

            int[] needed = new int[10];
            needed[d1]++;
            needed[d2]++;
            needed[d3]++;

            if (needed[d1] <= available[d1] && 
                needed[d2] <= available[d2] && 
                needed[d3] <= available[d3]) {
                count++;
            }
        }

        return count;
    }
}