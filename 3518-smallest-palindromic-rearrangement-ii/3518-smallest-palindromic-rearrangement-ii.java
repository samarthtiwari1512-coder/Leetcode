class Solution {
    public String smallestPalindrome(String s, long k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        String midChar = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                midChar = String.valueOf((char) ('a' + i));
            }
            freq[i] /= 2; 
        }

        int halfLen = 0;
        for (int count : freq) {
            halfLen += count;
        }

       
        long totalPermutations = countPermutations(freq, halfLen);
        if (k > totalPermutations) {
            return "";
        }

        StringBuilder firstHalf = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            int remainingLen = halfLen - 1 - pos;

            for (int i = 0; i < 26; i++) {
                if (freq[i] == 0) continue;

                freq[i]--;
                long count = countPermutations(freq, remainingLen);

                if (k <= count) {
                    firstHalf.append((char) ('a' + i));
                    break; 
                } else {
                    k -= count;
                    freq[i]++; 
                }
            }
        }

        String left = firstHalf.toString();
        String right = new StringBuilder(left).reverse().toString();

        return left + midChar + right;
    }

    private long countPermutations(int[] freq, int totalLen) {
        long res = 1;
        int currentLen = 0;

        for (int count : freq) {
            if (count == 0) continue;
            for (int i = 1; i <= count; i++) {
                currentLen++;
                res = res * currentLen / i;
                if (res > 1_000_000_000L) {
                    res = 1_000_000_000L;
                }
            }
        }

        return res;
    }
}