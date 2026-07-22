import java.util.*;

class Solution {
    static class Group {
        int start;
        int end;
        int len;
        
        Group(int s, int e) {
            this.start = s;
            this.end = e;
            this.len = e - s + 1;
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        
        int baseOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                baseOnes++;
            }
        }

        List<Group> z = new ArrayList<>();
        int sIndex = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (sIndex == -1) sIndex = i;
            } else {
                if (sIndex != -1) {
                    z.add(new Group(sIndex, i - 1));
                    sIndex = -1;
                }
            }
        }
        if (sIndex != -1) {
            z.add(new Group(sIndex, n - 1));
        }

        int m = z.size();
        
        int[] mergedLens = new int[Math.max(0, m - 1)];
        for (int i = 0; i < m - 1; i++) {
            mergedLens[i] = z.get(i).len + z.get(i + 1).len;
        }
        int[][] st = buildSparseTable(mergedLens);
        
        List<Integer> ans = new ArrayList<>(queries.length);

        for (int[] q : queries) {
            int l = q[0], r = q[1];
            
            if (m == 0) {
                ans.add(baseOnes); 
                continue;
            }

            int A = getA(z, l);
            int B = getB(z, r);

            if (A == -1 || B == -1 || A >= B) {
                ans.add(baseOnes);
                continue;
            }

            int gain = 0;
            Group gA = z.get(A);
            Group gB = z.get(B);

            int lenA = gA.end - Math.max(gA.start, l) + 1;
            int lenB = Math.min(gB.end, r) - gB.start + 1;

            if (A + 1 == B) {
                gain = lenA + lenB;
            } else {
                gain = Math.max(gain, lenA + z.get(A + 1).len);
                gain = Math.max(gain, z.get(B - 1).len + lenB);
                
                if (A + 1 <= B - 2) {
                    gain = Math.max(gain, queryST(st, A + 1, B - 2));
                }
            }
            
            ans.add(baseOnes + gain);
        }

        return ans;
    }

    private int getA(List<Group> z, int l) {
        int low = 0, high = z.size() - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (z.get(mid).end >= l) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int getB(List<Group> z, int r) {
        int low = 0, high = z.size() - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (z.get(mid).start <= r) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private int[][] buildSparseTable(int[] arr) {
        int n = arr.length;
        if (n == 0) return new int[0][0];
        int K = 32 - Integer.numberOfLeadingZeros(n);
        int[][] st = new int[K][n];
        System.arraycopy(arr, 0, st[0], 0, n);
        for (int i = 1; i < K; i++) {
            for (int j = 0; j + (1 << i) <= n; j++) {
                st[i][j] = Math.max(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
            }
        }
        return st;
    }

    private int queryST(int[][] st, int l, int r) {
        if (st.length == 0 || l > r) return 0;
        int k = 31 - Integer.numberOfLeadingZeros(r - l + 1);
        return Math.max(st[k][l], st[k][r - (1 << k) + 1]);
    }
}