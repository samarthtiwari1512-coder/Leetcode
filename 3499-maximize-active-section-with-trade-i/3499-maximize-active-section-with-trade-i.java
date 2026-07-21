import java.util.ArrayList;
import java.util.List;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int initialOnes = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                initialOnes++;
            }
        }

        String t = "1" + s + "1";

        List<Integer> blocks = new ArrayList<>();
        int currentLen = 0;
        char currentChar = '1';

        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == currentChar) {
                currentLen++;
            } else {
                blocks.add(currentLen);
                currentChar = t.charAt(i);
                currentLen = 1;
            }
        }
        blocks.add(currentLen);

        int maxOnes = initialOnes;

        for (int i = 2; i < blocks.size() - 1; i += 2) {
            int zeroGain = blocks.get(i - 1) + blocks.get(i + 1);
            maxOnes = Math.max(maxOnes, initialOnes + zeroGain);
        }

        return maxOnes;
    }
}