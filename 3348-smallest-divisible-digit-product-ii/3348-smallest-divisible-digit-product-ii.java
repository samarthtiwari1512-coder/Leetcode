import java.util.Arrays;

class Solution {
    public String smallestNumber(String num, long t) {
        long tempT = t;
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        
        while (tempT % 2 == 0) { tempT /= 2; c2++; }
        while (tempT % 3 == 0) { tempT /= 3; c3++; }
        while (tempT % 5 == 0) { tempT /= 5; c5++; }
        while (tempT % 7 == 0) { tempT /= 7; c7++; }

        if (tempT > 1) return "-1";

        int n = num.length();
        int[] numDigits = new int[n];
        for (int i = 0; i < n; i++) {
            numDigits[i] = num.charAt(i) - '0';
        }

        int[] pref2 = new int[n + 1];
        int[] pref3 = new int[n + 1];
        int[] pref5 = new int[n + 1];
        int[] pref7 = new int[n + 1];
        
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            if (numDigits[i] == 0) {
                firstZero = i;
                break;
            }
            int d = numDigits[i];
            pref2[i + 1] = pref2[i] + getFactorCount(d, 2);
            pref3[i + 1] = pref3[i] + getFactorCount(d, 3);
            pref5[i + 1] = pref5[i] + getFactorCount(d, 5);
            pref7[i + 1] = pref7[i] + getFactorCount(d, 7);
        }

        for (int i = Math.min(n, firstZero); i >= 0; i--) {
            int rem2 = Math.max(0, c2 - pref2[i]);
            int rem3 = Math.max(0, c3 - pref3[i]);
            int rem5 = Math.max(0, c5 - pref5[i]);
            int rem7 = Math.max(0, c7 - pref7[i]);

            int remLen = n - i;
            
            if (i == n) {
                if (rem2 == 0 && rem3 == 0 && rem5 == 0 && rem7 == 0) {
                    return num;
                }
                continue;
            }

            int startD = numDigits[i] + 1;

            for (int d = startD; d <= 9; d++) {
                int r2 = Math.max(0, rem2 - getFactorCount(d, 2));
                int r3 = Math.max(0, rem3 - getFactorCount(d, 3));
                int r5 = Math.max(0, rem5 - getFactorCount(d, 5));
                int r7 = Math.max(0, rem7 - getFactorCount(d, 7));

                int minDigitsNeeded = getMinDigitsNeeded(r2, r3, r5, r7);

                if (minDigitsNeeded <= remLen - 1) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < i; j++) {
                        sb.append(numDigits[j]);
                    }
                    sb.append(d);
                    
                    String suffix = buildSmallestSuffix(r2, r3, r5, r7, remLen - 1);
                    sb.append(suffix);
                    return sb.toString();
                }
            }
        }

        int minDigitsNeeded = getMinDigitsNeeded(c2, c3, c5, c7);
        int targetLen = Math.max(n + 1, minDigitsNeeded);
        return buildSmallestSuffix(c2, c3, c5, c7, targetLen);
    }

    private int getFactorCount(int digit, int p) {
        if (digit == 0) return 0;
        int count = 0;
        while (digit % p == 0) {
            count++;
            digit /= p;
        }
        return count;
    }

    private int getMinDigitsNeeded(int r2, int r3, int r5, int r7) {
        int count = r5 + r7;
        
        // Group factors into digits optimal for total count
        int d9 = r3 / 2;
        r3 %= 2;

        int d8 = r2 / 3;
        r2 %= 3;

        if (r3 == 1 && r2 == 2) {
            // Can be formed by '6' and '2' (2 digits) instead of '3', '4'
            count += 2;
            r3 = 0;
            r2 = 0;
        } else if (r3 == 1 && r2 == 1) {
            // Can be formed by '6' (1 digit)
            count += 1;
            r3 = 0;
            r2 = 0;
        } else {
            count += r3; // at most 1 '3'
            if (r2 == 2) count += 1; // '4'
            else if (r2 == 1) count += 1; // '2'
        }

        return count + d9 + d8;
    }

    private String buildSmallestSuffix(int r2, int r3, int r5, int r7, int totalLen) {
        StringBuilder digits = new StringBuilder();

        for (int i = 0; i < r7; i++) digits.append('7');
        for (int i = 0; i < r5; i++) digits.append('5');
        
        while (r3 >= 2) { digits.append('9'); r3 -= 2; }
        while (r2 >= 3) { digits.append('8'); r2 -= 3; }
        
        if (r3 == 1 && r2 == 2) {
            digits.append('6');
            digits.append('2');
            r3 = 0; r2 = 0;
        } else if (r3 == 1 && r2 == 1) {
            digits.append('6');
            r3 = 0; r2 = 0;
        } else if (r3 == 1) {
            digits.append('3');
            r3 = 0;
        }

        while (r2 >= 2) { digits.append('4'); r2 -= 2; }
        if (r2 == 1) { digits.append('2'); r2 = 0; }

        char[] arr = digits.toString().toCharArray();
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        int onesCount = totalLen - arr.length;
        for (int i = 0; i < onesCount; i++) {
            sb.append('1');
        }
        for (char c : arr) {
            sb.append(c);
        }

        return sb.toString();
    }
}