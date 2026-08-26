class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0, countOnes = 0;
        String ans = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                countOnes++;
            }

            while (countOnes == k) {
                String sub = s.substring(left, right + 1);

                if (ans.isEmpty() || sub.length() < ans.length() || 
                   (sub.length() == ans.length() && sub.compareTo(ans) < 0)) {
                    ans = sub;
                }

                if (s.charAt(left) == '1') {
                    countOnes--;
                }
                left++;
            }
        }

        return ans;
    }
}