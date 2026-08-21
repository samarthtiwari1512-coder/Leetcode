import java.util.*;

public class Solution {
    // Helper to compute Greatest Common Divisor
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Helper to compute Least Common Multiple
    private static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    // Class to store precomputed LCM and its sign (+1 or -1) for each subset
    private static class SubsetInfo {
        long lcmVal;
        int sign;

        SubsetInfo(long lcmVal, int sign) {
            this.lcmVal = lcmVal;
            this.sign = sign;
        }
    }

    public long findKthSmallest(int[] coins, int k) {
        // Step 1: Sort and filter out redundant coins (multiples of smaller coins)
        Arrays.sort(coins);
        List<Integer> filtered = new ArrayList<>();
        for (int c : coins) {
            boolean isMultiple = false;
            for (int existing : filtered) {
                if (c % existing == 0) {
                    isMultiple = true;
                    break;
                }
            }
            if (!isMultiple) {
                filtered.add(c);
            }
        }

        int n = filtered.size();
        int[] cleanCoins = new int[n];
        for (int i = 0; i < n; i++) {
            cleanCoins[i] = filtered.get(i);
        }

        // Step 2: Precompute (LCM, sign) for all non-empty subsets (2^n - 1)
        List<SubsetInfo> subsets = new ArrayList<>();
        int totalSubsets = 1 << n;

        for (int mask = 1; mask < totalSubsets; mask++) {
            long currentLcm = 1;
            int bitCount = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentLcm = lcm(currentLcm, cleanCoins[i]);
                    bitCount++;
                }
            }

            int sign = (bitCount % 2 == 1) ? 1 : -1;
            subsets.add(new SubsetInfo(currentLcm, sign));
        }

        // Step 3: Binary Search for the answer
        long low = 1;
        long high = (long) cleanCoins[0] * k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (countMultiples(mid, subsets) >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    // Counts how many numbers <= x are divisible by at least one coin
    private long countMultiples(long x, List<SubsetInfo> subsets) {
        long total = 0;
        for (SubsetInfo info : subsets) {
            total += (long) info.sign * (x / info.lcmVal);
        }
        return total;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] coins1 = {3, 6, 9};
        int k1 = 3;
        System.out.println("Example 1 Output: " + sol.findKthSmallest(coins1, k1)); // Expected: 9

        // Example 2
        int[] coins2 = {5, 2};
        int k2 = 7;
        System.out.println("Example 2 Output: " + sol.findKthSmallest(coins2, k2)); // Expected: 12
    }
}