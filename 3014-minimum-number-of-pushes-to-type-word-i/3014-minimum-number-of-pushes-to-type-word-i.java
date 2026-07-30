class Solution {
    public int minimumPushes(String word) {
        int totalPushes = 0;
        int len = word.length();

        for (int i = 0; i < len; i++) {
            totalPushes += (i / 8) + 1;
        }

        return totalPushes;
    }
}