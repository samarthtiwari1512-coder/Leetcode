class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Try matching a prefix of length i (0 <= i < n)
        for (int i = n - 1; i >= 0; i--) {
            int[] currentCount = count.clone();
            boolean prefixPossible = true;

            // Check if prefix target[0..i-1] can be formed
            for (int j = 0; j < i; j++) {
                int ch = target.charAt(j) - 'a';
                if (currentCount[ch] > 0) {
                    currentCount[ch]--;
                } else {
                    prefixPossible = false;
                    break;
                }
            }

            if (!prefixPossible) {
                continue;
            }

            // Try picking the smallest character strictly greater than target[i]
            int targetChar = target.charAt(i) - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (currentCount[c] > 0) {
                    currentCount[c]--;

                    // Build the answer
                    char[] res = new char[n];
                    for (int j = 0; j < i; j++) {
                        res[j] = target.charAt(j);
                    }
                    res[i] = (char) ('a' + c);

                    // Fill remaining positions with the smallest available characters
                    int idx = i + 1;
                    for (int charIdx = 0; charIdx < 26; charIdx++) {
                        while (currentCount[charIdx] > 0) {
                            res[idx++] = (char) ('a' + charIdx);
                            currentCount[charIdx]--;
                        }
                    }

                    return new String(res);
                }
            }
        }

        return "";
    }
}