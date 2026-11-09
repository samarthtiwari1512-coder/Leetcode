import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requestCount = new int[k];

        // Stores [freeTime, serverId]
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Stores available server IDs
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        int n = arrival.length;
        for (int i = 0; i < n; i++) {
            int time = arrival[i];
            int duration = load[i];

            // Release servers that have finished before or at this arrival time
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                available.add(busy.poll()[1]);
            }

            if (available.isEmpty()) {
                continue; // Request is dropped
            }

            // Find next available server >= i % k, or wrap to the first
            int target = i % k;
            Integer chosenServer = available.ceiling(target);
            if (chosenServer == null) {
                chosenServer = available.first();
            }

            available.remove(chosenServer);
            requestCount[chosenServer]++;
            busy.offer(new int[]{time + duration, chosenServer});
        }

        int maxReq = 0;
        for (int count : requestCount) {
            maxReq = Math.max(maxReq, count);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxReq) {
                result.add(i);
            }
        }

        return result;
    }
}