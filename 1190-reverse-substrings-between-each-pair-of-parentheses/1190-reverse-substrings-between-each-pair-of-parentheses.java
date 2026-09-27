import java.util.*;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // Step 1: Precompute matching parentheses positions
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int openIdx = stack.pop();
                pair[openIdx] = i;
                pair[i] = openIdx;
            }
        }

        // Step 2: Traverse using the wormhole simulation
        StringBuilder result = new StringBuilder();
        int dir = 1; // 1 means moving forward, -1 means moving backward

        for (int i = 0; i < n; i += dir) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                i = pair[i];     // Jump to matching bracket
                dir = -dir;      // Reverse traversal direction
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}