class Solution {
    private boolean[][][] visited;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Must start with '(' and end with ')'
        if (grid[0][0] != '(' || grid[m - 1][n - 1] != ')') {
            return false;
        }

        // Max possible balance is (m + n) / 2
        visited = new boolean[m][n][(m + n) / 2 + 1];

        return dfs(0, 0, 0, grid);
    }

    private boolean dfs(int r, int c, int balance, char[][] grid) {
        balance += (grid[r][c] == '(') ? 1 : -1;

        // Balance cannot be negative
        if (balance < 0) {
            return false;
        }

        // Prune if there aren't enough remaining steps to close all parentheses
        int remaining = (m - 1 - r) + (n - 1 - c);
        if (balance > remaining) {
            return false;
        }

        // Reached the destination
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (visited[r][c][balance]) {
            return false;
        }
        visited[r][c][balance] = true;

        // Explore Down and Right
        if (r + 1 < m && dfs(r + 1, c, balance, grid)) {
            return true;
        }
        if (c + 1 < n && dfs(r, c + 1, balance, grid)) {
            return true;
        }

        return false;
    }
}