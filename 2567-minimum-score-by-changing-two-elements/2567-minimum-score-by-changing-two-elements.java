import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        
        // Case 1: Modify the two smallest elements
        int case1 = nums[n - 1] - nums[2];
        
        // Case 2: Modify the two largest elements
        int case2 = nums[n - 3] - nums[0];
        
        // Case 3: Modify one smallest and one largest element
        int case3 = nums[n - 2] - nums[1];
        
        return Math.min(case1, Math.min(case2, case3));
    }
}