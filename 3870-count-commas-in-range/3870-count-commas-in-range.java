class Solution {
    public int countCommas(int n) {
        int commas = 0;
        long threshold = 1000L;

        while (n >= threshold) {
            commas += (n - threshold + 1);
            threshold *= 1000L;
        }

        return commas;
    }
}