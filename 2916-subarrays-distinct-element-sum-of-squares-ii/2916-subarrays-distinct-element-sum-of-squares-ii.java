import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    static class SegmentTree {
        int n;
        long[] sum1, sum2, lazy;

        SegmentTree(int n) {
            this.n = n;
            sum1 = new long[4 * n];
            sum2 = new long[4 * n];
            lazy = new long[4 * n];
        }

        private void apply(int node, int l, int r, long val) {
            long len = r - l + 1;
            sum2[node] = (sum2[node] + 2 * val % MOD * sum1[node] % MOD + val * val % MOD * len) % MOD;
            sum1[node] = (sum1[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        private void push(int node, int l, int r) {
            if (lazy[node] != 0) {
                int mid = (l + r) >> 1;
                apply(node << 1, l, mid, lazy[node]);
                apply(node << 1 | 1, mid + 1, r, lazy[node]);
                lazy[node] = 0;
            }
        }

        public void update(int node, int l, int r, int ql, int qr, long val) {
            if (ql <= l && r <= qr) {
                apply(node, l, r, val);
                return;
            }
            push(node, l, r);
            int mid = (l + r) >> 1;
            if (ql <= mid) {
                update(node << 1, l, mid, ql, qr, val);
            }
            if (qr > mid) {
                update(node << 1 | 1, mid + 1, r, ql, qr, val);
            }
            sum1[node] = (sum1[node << 1] + sum1[node << 1 | 1]) % MOD;
            sum2[node] = (sum2[node << 1] + sum2[node << 1 | 1]) % MOD;
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        int[] lastPos = new int[100001];
        java.util.Arrays.fill(lastPos, -1);

        long ans = 0;
        for (int j = 0; j < n; j++) {
            int x = nums[j];
            int prev = lastPos[x];
            tree.update(1, 1, n, prev + 2, j + 1, 1);
            lastPos[x] = j;
            ans = (ans + tree.sum2[1]) % MOD;
        }

        return (int) ans;
    }
}