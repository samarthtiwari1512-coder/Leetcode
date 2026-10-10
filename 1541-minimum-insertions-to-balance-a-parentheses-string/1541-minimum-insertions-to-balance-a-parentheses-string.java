class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0;
        
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                if (neededRight % 2 != 0) {
                    insertions++;
                    neededRight--;
                }
                neededRight += 2;
            } else { // ch == ')'
                neededRight--;
                if (neededRight < 0) {
                    insertions++;
                    neededRight += 2;
                }
            }
        }
        
        return insertions + neededRight;
    }
}