import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1, startC = -1;
        int litterCount = 0;
        int[][] litterIndex = new int[m][n];
        for (int[] row : litterIndex) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startR = i;
                    startC = j;
                } else if (c == 'L') {
                    litterIndex[i][j] = litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        int targetMask = (1 << litterCount) - 1;

        int[][][] maxEnergySeen = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergySeen[i][j], -1);
            }
        }

        // Queue stores: [r, c, energy, mask]
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC, energy, 0});
        maxEnergySeen[startR][startC][0] = energy;

        int moves = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // 2. BFS Traversal
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int curEnergy = curr[2];
                int mask = curr[3];

                if (mask == targetMask) {
                    return moves;
                }

                // If no energy remaining, cannot move to any adjacent cell
                if (curEnergy == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                        char cellType = classroom[nr].charAt(nc);
                        int nextEnergy = (cellType == 'R') ? energy : curEnergy - 1;
                        int nextMask = mask;

                        if (cellType == 'L') {
                            nextMask |= (1 << litterIndex[nr][nc]);
                        }

                        // Only push to queue if we arrive with strictly more energy than seen before
                        if (nextEnergy > maxEnergySeen[nr][nc][nextMask]) {
                            maxEnergySeen[nr][nc][nextMask] = nextEnergy;
                            queue.offer(new int[]{nr, nc, nextEnergy, nextMask});
                        }
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}