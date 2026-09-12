import java.util.*;

class Solution {
    private record State(long weight, List<Integer> selected) {}
    private record Interval(int left, int right, int weight, int originalIndex) {}

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        List<Interval> indexedIntervals = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            List<Integer> it = intervals.get(i);
            indexedIntervals.add(new Interval(it.get(0), it.get(1), it.get(2), i));
        }

        // Sort by left boundary
        indexedIntervals.sort(Comparator.comparingInt(Interval::left));

        State[][] memo = new State[n][5];
        State best = dp(indexedIntervals, memo, 0, 4);

        int[] result = new int[best.selected.size()];
        for (int i = 0; i < best.selected.size(); i++) {
            result[i] = best.selected.get(i);
        }
        return result;
    }

    private State dp(List<Interval> intervals, State[][] memo, int i, int quota) {
        if (i == intervals.size() || quota == 0) {
            return new State(0L, List.of());
        }
        if (memo[i][quota] != null) {
            return memo[i][quota];
        }

        // Option 1: Skip interval i
        State skip = dp(intervals, memo, i + 1, quota);

        // Option 2: Pick interval i
        Interval cur = intervals.get(i);
        int nextIdx = findFirstGreater(intervals, i + 1, cur.right);
        State nextRes = dp(intervals, memo, nextIdx, quota - 1);

        List<Integer> newSelected = new ArrayList<>(nextRes.selected);
        newSelected.add(cur.originalIndex);
        Collections.sort(newSelected);

        State pick = new State(cur.weight + nextRes.weight, newSelected);

        // Tie-breaker: choose pick if it has higher weight or identical weight with lexicographically smaller indices
        State res;
        if (pick.weight > skip.weight) {
            res = pick;
        } else if (pick.weight < skip.weight) {
            res = skip;
        } else {
            res = compareLists(pick.selected, skip.selected) < 0 ? pick : skip;
        }

        return memo[i][quota] = res;
    }

    private int findFirstGreater(List<Interval> intervals, int startFrom, int rightBoundary) {
        int l = startFrom;
        int r = intervals.size();
        while (l < r) {
            int m = l + (r - l) / 2;
            if (intervals.get(m).left > rightBoundary) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private int compareLists(List<Integer> a, List<Integer> b) {
        int minSize = Math.min(a.size(), b.size());
        for (int i = 0; i < minSize; ++i) {
            int cmp = Integer.compare(a.get(i), b.get(i));
            if (cmp != 0) return cmp;
        }
        return Integer.compare(a.size(), b.size());
    }
}