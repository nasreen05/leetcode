import java.util.*;

class Solution {

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        // Sort meetings by time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));

        // Union-Find structure
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // People who know the secret
        Set<Integer> knows = new HashSet<>();
        knows.add(0);
        knows.add(firstPerson);

        int i = 0;
        while (i < meetings.length) {
            int time = meetings[i][2];
            Set<Integer> involved = new HashSet<>();

            // Process all meetings at the same time
            while (i < meetings.length && meetings[i][2] == time) {
                int x = meetings[i][0];
                int y = meetings[i][1];
                union(x, y, parent);
                involved.add(x);
                involved.add(y);
                i++;
            }

            // Group by connected components
            Map<Integer, List<Integer>> groups = new HashMap<>();
            for (int p : involved) {
                int root = find(p, parent);
                groups.computeIfAbsent(root, k -> new ArrayList<>()).add(p);
            }

            // Spread secret inside valid components
            for (List<Integer> group : groups.values()) {
                boolean hasSecret = false;
                for (int p : group) {
                    if (knows.contains(p)) {
                        hasSecret = true;
                        break;
                    }
                }
                if (hasSecret) {
                    knows.addAll(group);
                }
            }

            // Reset Union-Find for next time frame
            for (int p : involved) {
                parent[p] = p;
            }
        }

        return new ArrayList<>(knows);
    }

    // Union-Find helpers
    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }

    private void union(int x, int y, int[] parent) {
        int px = find(x, parent);
        int py = find(y, parent);
        if (px != py) {
            parent[py] = px;
        }
    }
}
