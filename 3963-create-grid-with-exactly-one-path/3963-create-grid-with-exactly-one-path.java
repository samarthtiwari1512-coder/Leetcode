import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        // Carve down the first column
        for (int r = 0; r < m; r++) {
            grid[r][0] = '.';
        }

        // Carve right across the bottom row
        for (int c = 0; c < n; c++) {
            grid[m - 1][c] = '.';
        }

        String[] res = new String[m];
        for (int i = 0; i < m; i++) {
            res[i] = new String(grid[i]);
        }

        return res;
    }
}