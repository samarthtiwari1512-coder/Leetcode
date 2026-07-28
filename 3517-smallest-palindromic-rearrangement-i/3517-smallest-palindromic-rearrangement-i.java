class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        StringBuilder leftHalf = new StringBuilder();
        String midChar = "";
        
        for (int i = 0; i < 26; i++) {
            char ch = (char) ('a' + i);
            
            if (count[i] % 2 != 0) {
                midChar = String.valueOf(ch);
            }
            
            for (int j = 0; j < count[i] / 2; j++) {
                leftHalf.append(ch);
            }
        }
        
        String rightHalf = new StringBuilder(leftHalf).reverse().toString();
        return leftHalf.toString() + midChar + rightHalf;
    }
}