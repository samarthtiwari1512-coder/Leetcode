class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sum1 = 0, sum2 = 0;
        int q1 = 0, q2 = 0;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (i < n / 2) {
                if (c == '?') q1++;
                else sum1 += (c - '0');
            } else {
                if (c == '?') q2++;
                else sum2 += (c - '0');
            }
        }

        // Bob wins if and only if 2 * (sum1 - sum2) + 9 * (q1 - q2) == 0
        // If (q1 + q2) % 2 != 0, 9 * (q1 - q2) will be odd, so equality to -2 * deltaSum is impossible anyway.
        return (2 * (sum1 - sum2) + 9 * (q1 - q2)) != 0;
    }
}