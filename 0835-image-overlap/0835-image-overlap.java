import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        // Collect positions of all 1s
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) ones1.add(new int[]{r, c});
                if (img2[r][c] == 1) ones2.add(new int[]{r, c});
            }
        }

        // Map to store frequency of each (dr, dc) translation vector
        // Using a 2D array since dr, dc are in range [-(n-1), n-1]
        int[][] shiftCount = new int[2 * n + 1][2 * n + 1];
        int maxOverlap = 0;

        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                int dr = p2[0] - p1[0] + n;
                int dc = p2[1] - p1[1] + n;
                shiftCount[dr][dc]++;
                maxOverlap = Math.max(maxOverlap, shiftCount[dr][dc]);
            }
        }

        return maxOverlap;
    }
}