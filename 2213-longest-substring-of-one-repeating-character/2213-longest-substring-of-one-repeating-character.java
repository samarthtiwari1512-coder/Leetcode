class Solution {
    static class Node {
        int prefixLen, suffixLen, maxLen;
        char prefixChar, suffixChar;
        int len;

        Node(char c) {
            this.prefixLen = 1;
            this.suffixLen = 1;
            this.maxLen = 1;
            this.prefixChar = c;
            this.suffixChar = c;
            this.len = 1;
        }

        Node() {}
    }

    private Node[] tree;
    private char[] chars;

    private Node merge(Node left, Node right) {
        Node parent = new Node();
        parent.len = left.len + right.len;
        parent.prefixChar = left.prefixChar;
        parent.suffixChar = right.suffixChar;

        // Base max length from children
        parent.maxLen = Math.max(left.maxLen, right.maxLen);
        parent.prefixLen = left.prefixLen;
        parent.suffixLen = right.suffixLen;

        // Check if middle boundary characters match
        if (left.suffixChar == right.prefixChar) {
            parent.maxLen = Math.max(parent.maxLen, left.suffixLen + right.prefixLen);

            if (left.prefixLen == left.len) {
                parent.prefixLen = left.len + right.prefixLen;
            }
            if (right.suffixLen == right.len) {
                parent.suffixLen = right.len + left.suffixLen;
            }
        }

        return parent;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char ch) {
        if (start == end) {
            tree[node] = new Node(ch);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, ch);
        } else {
            update(2 * node + 1, mid + 1, end, idx, ch);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];

        // Step 1: Build Segment Tree
        build(1, 0, n - 1);

        int[] result = new int[k];

        // Step 2: Process queries
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, idx, ch);
            result[i] = tree[1].maxLen; // Root node holds max length for entire string
        }

        return result;
    }
}