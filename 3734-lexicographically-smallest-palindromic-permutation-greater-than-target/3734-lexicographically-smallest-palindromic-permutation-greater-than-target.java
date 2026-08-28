import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check if a palindrome can be formed
        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // Half frequencies
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        int m = n / 2;
        String bestAns = "";

        // Case 1: Try matching the first half exactly with target[0..m-1]
        int[] tempCount = halfCount.clone();
        boolean canMatchExact = true;
        char[] firstHalf = new char[m];

        for (int i = 0; i < m; i++) {
            int ch = target.charAt(i) - 'a';
            if (tempCount[ch] > 0) {
                tempCount[ch]--;
                firstHalf[i] = target.charAt(i);
            } else {
                canMatchExact = false;
                break;
            }
        }

        if (canMatchExact) {
            String cand = buildPalindrome(firstHalf, oddChar, n);
            if (cand.compareTo(target) > 0) {
                bestAns = cand;
            }
        }

        // Case 2: Match prefix target[0..i-1], and make position i strictly greater
        for (int i = m - 1; i >= 0; i--) {
            // Count characters available for prefix target[0..i-1]
            int[] currentCount = halfCount.clone();
            boolean prefixValid = true;

            for (int j = 0; j < i; j++) {
                int ch = target.charAt(j) - 'a';
                if (currentCount[ch] > 0) {
                    currentCount[ch]--;
                } else {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) continue;

            // Try picking a character strictly greater than target[i]
            int targetChar = target.charAt(i) - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (currentCount[c] > 0) {
                    currentCount[c]--;

                    char[] half = new char[m];
                    for (int j = 0; j < i; j++) {
                        half[j] = target.charAt(j);
                    }
                    half[i] = (char) ('a' + c);

                    // Fill the rest with the smallest available characters
                    int idx = i + 1;
                    for (int charIdx = 0; charIdx < 26; charIdx++) {
                        while (currentCount[charIdx] > 0) {
                            half[idx++] = (char) ('a' + charIdx);
                            currentCount[charIdx]--;
                        }
                    }

                    String cand = buildPalindrome(half, oddChar, n);
                    if (bestAns.isEmpty() || cand.compareTo(bestAns) < 0) {
                        bestAns = cand;
                    }
                    break; // Smallest c > targetChar is already optimal for this prefix length
                }
            }

            // If we found any valid answer branching at or after index i, 
            // going to smaller i will only make the string lexicographically larger.
            if (!bestAns.isEmpty() && bestAns.substring(0, i).equals(target.substring(0, i))) {
                break;
            }
        }

        return bestAns;
    }

    private String buildPalindrome(char[] firstHalf, int oddChar, int n) {
        char[] res = new char[n];
        int m = firstHalf.length;
        for (int i = 0; i < m; i++) {
            res[i] = firstHalf[i];
            res[n - 1 - i] = firstHalf[i];
        }
        if (n % 2 != 0) {
            res[m] = (char) ('a' + oddChar);
        }
        return new String(res);
    }
}