import java.util.*;

class Solution {
    public int maxTwoEvents(int[][] events) {
        int n = events.length;

        // 1️⃣ Sort events by start time
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        // 2️⃣ Build suffix max array of values
        int[] suffixMax = new int[n];
        suffixMax[n - 1] = events[n - 1][2];

        for (int i = n - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], events[i][2]);
        }

        int answer = 0;

        // 3️⃣ For each event, try pairing it with a non-overlapping one
        for (int i = 0; i < n; i++) {
            int endTime = events[i][1];
            int value = events[i][2];

            // Binary search for next event with start >= endTime + 1
            int left = i + 1, right = n - 1;
            int idx = -1;

            while (left <= right) {
                int mid = (left + right) / 2;
                if (events[mid][0] >= endTime + 1) {
                    idx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // Only current event
            answer = Math.max(answer, value);

            // Current + best future event
            if (idx != -1) {
                answer = Math.max(answer, value + suffixMax[idx]);
            }
        }

        return answer;
    }
}
