class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum < 1 || (long) sum > 9L * num) {
            return "";
        }
        
        int nines = sum / 9;
        int remainder = sum % 9;
        
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < nines; i++) {
            sb.append('9');
        }
        
        if (remainder > 0) {
            sb.append(remainder);
            for (int i = 0; i < num - nines - 1; i++) {
                sb.append('0');
            }
        } else {
            for (int i = 0; i < num - nines; i++) {
                sb.append('0');
            }
        }
        
        return sb.toString();
    }
}