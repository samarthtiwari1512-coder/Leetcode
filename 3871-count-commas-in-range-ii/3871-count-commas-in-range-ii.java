class Solution {
    public long countCommas(long n) {
        long total = 0;
        long threshold = 1_000L;

        while (n >= threshold) {
            total += (n - threshold + 1);

            if (threshold > Long.MAX_VALUE / 1000) {
                break;
            }
            threshold *= 1000L;
        }

        return total;
    }
}