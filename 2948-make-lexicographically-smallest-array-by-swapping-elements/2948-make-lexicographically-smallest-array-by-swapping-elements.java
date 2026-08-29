import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        int[][] sortedPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedPairs[i][0] = nums[i];
            sortedPairs[i][1] = i;
        }

        Arrays.sort(sortedPairs, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int i = 0;

        while (i < n) {
            int j = i;
            List<Integer> indices = new ArrayList<>();
            indices.add(sortedPairs[i][1]);

            while (j + 1 < n && sortedPairs[j + 1][0] - sortedPairs[j][0] <= limit) {
                j++;
                indices.add(sortedPairs[j][1]);
            }

            Collections.sort(indices);

            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = sortedPairs[i + k][0];
            }

            i = j + 1;
        }

        return result;
    }
}