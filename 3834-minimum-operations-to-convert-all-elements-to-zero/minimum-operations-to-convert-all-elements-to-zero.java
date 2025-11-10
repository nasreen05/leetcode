import java.util.*;

class Solution {
    int[][] st;
    int logn;
    int n;

    public int minOperations(int[] nums) {
        n = nums.length;
        logn = 0;
        while ((1 << logn) <= n) logn++;
        st = new int[n][logn];
        for (int i = 0; i < n; i++) st[i][0] = nums[i];
        for (int j = 1; j < logn; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
            }
        }
        int ans = 0;
        for (Map.Entry<Integer, List<Integer>> entry : pos.entrySet()) {
            int v = entry.getKey();
            List<Integer> list = entry.getValue();
            if (list.isEmpty()) continue;
            int count = 1;
            for (int k = 1; k < list.size(); k++) {
                int left = list.get(k - 1) + 1;
                int right = list.get(k) - 1;
                if (left <= right) {
                    int min_gap = query(left, right);
                    if (min_gap < v) {
                        count++;
                    }
                }
            }
            ans += count;
        }
        return ans;
    }

    private int query(int l, int r) {
        int len = r - l + 1;
        int k = 0;
        while ((1 << (k + 1)) <= len) k++;
        return Math.min(st[l][k], st[r - (1 << k) + 1][k]);
    }
}
