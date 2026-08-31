/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstCriticalPos = -1;
        int lastCriticalPos = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 2; // 1-based index of `curr`

        while (curr.next != null) {
            // Check if curr is a local maxima or minima
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {

                if (firstCriticalPos == -1) {
                    firstCriticalPos = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - lastCriticalPos);
                }
                lastCriticalPos = currentIndex;
            }

            prev = curr;
            curr = curr.next;
            currentIndex++;
        }

        // If fewer than two critical points were found
        if (firstCriticalPos == -1 || firstCriticalPos == lastCriticalPos) {
            return new int[]{-1, -1};
        }

        int maxDistance = lastCriticalPos - firstCriticalPos;
        return new int[]{minDistance, maxDistance};
    }
}