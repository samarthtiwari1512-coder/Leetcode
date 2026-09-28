class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Step 1: Extract unique values
        boolean[] present = new boolean[2048];
        for (int num : nums) {
            present[num] = true;
        }
        
        int count = 0;
        for (boolean b : present) {
            if (b) count++;
        }
        
        int[] unique = new int[count];
        int idx = 0;
        for (int i = 0; i < 2048; i++) {
            if (present[i]) {
                unique[idx++] = i;
            }
        }
        
        // Step 2: Compute all pairwise XORs
        boolean[] pairXor = new boolean[2048];
        for (int i = 0; i < unique.length; i++) {
            for (int j = i; j < unique.length; j++) {
                pairXor[unique[i] ^ unique[j]] = true;
            }
        }
        
        // Step 3: Compute all triplet XORs
        boolean[] tripletXor = new boolean[2048];
        for (int p = 0; p < 2048; p++) {
            if (pairXor[p]) {
                for (int c : unique) {
                    tripletXor[p ^ c] = true;
                }
            }
        }
        
        // Count total unique triplet values
        int result = 0;
        for (boolean b : tripletXor) {
            if (b) result++;
        }
        
        return result;
    }
}