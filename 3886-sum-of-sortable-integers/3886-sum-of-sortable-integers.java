import java.util.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; (long) i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        }

        int totalSum = 0;
        for (int k : divisors) {
            if (isValid(nums, sorted, n, k)) {
                totalSum += k;
            }
        }

        return totalSum;
    }

    private boolean isValid(int[] nums, int[] sorted, int n, int k) {
        for (int start = 0; start < n; start += k) {
            if (!canRotate(nums, sorted, start, k)) {
                return false;
            }
        }
        return true;
    }

    private boolean canRotate(int[] a, int[] b, int start, int k) {
        int targetVal = a[start];
        for (int offset = 0; offset < k; offset++) {
            if (b[start + offset] == targetVal) {
                boolean match = true;
                for (int i = 0; i < k; i++) {
                    if (a[start + i] != b[start + (offset + i) % k]) {
                        match = false;
                        break;
                    }
                }
                if (match) return true;
            }
        }
        return false;
    }
}