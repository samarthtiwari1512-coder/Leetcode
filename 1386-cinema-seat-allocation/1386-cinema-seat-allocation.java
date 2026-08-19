import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map row number to a bitmask of reserved seats between 2 and 9
        Map<Integer, Integer> rowReservations = new HashMap<>();

        for (int[] res : reservedSeats) {
            int row = res[0];
            int seat = res[1];

            // Seats 1 and 10 do not affect 4-person groups
            if (seat >= 2 && seat <= 9) {
                int mask = rowReservations.getOrDefault(row, 0);
                mask |= (1 << (seat - 2)); // Shift into bits 0 to 7
                rowReservations.put(row, mask);
            }
        }

        int maxGroups = 0;

        // Bitmasks for blocks (seat 2 is bit 0, seat 9 is bit 7):
        // Left   (2, 3, 4, 5) -> bits 0, 1, 2, 3 -> binary 00001111 (0x0F)
        // Right  (6, 7, 8, 9) -> bits 4, 5, 6, 7 -> binary 11110000 (0xF0)
        // Middle (4, 5, 6, 7) -> bits 2, 3, 4, 5 -> binary 00111100 (0x3C)
        int leftMask = 0b00001111;
        int rightMask = 0b11110000;
        int middleMask = 0b00111100;

        // Process only rows with reservations in seats 2..9
        for (int mask : rowReservations.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;
            boolean middleFree = (mask & middleMask) == 0;

            if (leftFree && rightFree) {
                maxGroups += 2;
            } else if (leftFree || rightFree || middleFree) {
                maxGroups += 1;
            }
        }

        // Add 2 groups for each completely unreserved row
        int unreservedRows = n - rowReservations.size();
        maxGroups += unreservedRows * 2;

        return maxGroups;
    }
}