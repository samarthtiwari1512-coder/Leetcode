import java.util.Arrays;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // right[j] stores the largest index in word1 from which word2[j...m-1] 
        // can be matched EXACTLY (0 mismatches)
        int[] right = new int[m + 1];
        Arrays.fill(right, -1);
        right[m] = n;

        int ptr = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(j)) {
                ptr--;
            }
            right[j] = ptr;
            if (ptr >= 0) {
                ptr--; // Move left for next matching character
            }
        }

        int[] result = new int[m];
        boolean changed = false;
        int i = 0; // Pointer in word1

        for (int j = 0; j < m; j++) {
            // Option 1: Try exact match at current position
            while (i < n && word1.charAt(i) != word2.charAt(j)) {
                // Option 2: Try using our 1 allowed mismatch at position 'i'
                if (!changed && i + 1 <= right[j + 1]) {
                    break;
                }
                i++;
            }

            // If we reached end of word1 without finding a valid index
            if (i >= n) return new int[0];

            // If characters don't match, we are using our 1 mismatch
            if (word1.charAt(i) != word2.charAt(j)) {
                changed = true;
            }

            result[j] = i;
            i++; // Advance pointer in word1
        }

        return result;
    }
}