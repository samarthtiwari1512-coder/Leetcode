class Solution {
    public boolean checkValidString(String s) {
        int cMin = 0, cMax = 0;
        
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                cMin++;
                cMax++;
            } else if (ch == ')') {
                cMin--;
                cMax--;
            } else { 
                cMin--;
                cMax++;
            }
            
            if (cMax < 0) {
                return false;
            }
            
            cMin = Math.max(cMin, 0);
        }
        
        return cMin == 0;
    }
}