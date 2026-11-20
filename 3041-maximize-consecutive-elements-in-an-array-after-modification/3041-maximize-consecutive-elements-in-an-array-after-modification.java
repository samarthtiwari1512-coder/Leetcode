import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        
        // dp.get(val) = longest consecutive sequence ending at 'val'
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;
        
        for (int x : nums) {
            // Update x + 1 FIRST using the previous state of x
            int lenPlusOne = dp.getOrDefault(x, 0) + 1;
            dp.put(x + 1, lenPlusOne);
            
            // Update x SECOND using the previous state of x - 1
            int lenSame = dp.getOrDefault(x - 1, 0) + 1;
            dp.put(x, lenSame);
            
            maxLen = Math.max(maxLen, Math.max(lenPlusOne, lenSame));
        }
        
        return maxLen;
    }
}