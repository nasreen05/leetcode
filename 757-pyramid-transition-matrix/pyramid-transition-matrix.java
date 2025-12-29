import java.util.*;

class Solution {

    // Map to store allowed transitions
    private Map<String, List<Character>> map = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {

        // Build the transition map
        for (String s : allowed) {
            String key = s.substring(0, 2); // bottom pair
            char top = s.charAt(2);         // top block

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(top);
        }

        // Start DFS from bottom
        return dfs(bottom);
    }

    private boolean dfs(String bottom) {
        // Base case: reached the top
        if (bottom.length() == 1) {
            return true;
        }

        // Generate all possible next rows
        List<String> nextRows = new ArrayList<>();
        buildNextRows(bottom, 0, new StringBuilder(), nextRows);

        // Try each possible next row
        for (String next : nextRows) {
            if (dfs(next)) {
                return true;
            }
        }

        return false;
    }

    private void buildNextRows(String bottom, int index,
                               StringBuilder curr,
                               List<String> result) {

        // If next row is fully built
        if (index == bottom.length() - 1) {
            result.add(curr.toString());
            return;
        }

        String key = bottom.substring(index, index + 2);

        // If no allowed transition, stop
        if (!map.containsKey(key)) {
            return;
        }

        // Try all possible top blocks
        for (char ch : map.get(key)) {
            curr.append(ch);
            buildNextRows(bottom, index + 1, curr, result);
            curr.deleteCharAt(curr.length() - 1); // backtrack
        }
    }
}
