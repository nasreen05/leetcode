import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;

    public int countTrapezoids(int[][] points) {
        // Count points per y-level
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int[] p : points) {
            cnt.put(p[1], cnt.getOrDefault(p[1], 0) + 1);
        }

        // Compute c[y] = C(size, 2) for each y (0 if size < 2)
        long totalChoose2Sum = 0L;
        List<Long> choose2List = new ArrayList<>();
        for (int size : cnt.values()) {
            if (size >= 2) {
                long ways = (long)size * (size - 1) / 2;
                ways %= MOD;
                choose2List.add(ways);
                totalChoose2Sum = (totalChoose2Sum + ways) % MOD;
            }
        }

        // Sum over all unordered pairs: sum_{i<j} choose2[i] * choose2[j]
        // = ( (sum choose2)^2 - sum (choose2^2) ) / 2
        long sumSq = 0L;
        for (long v : choose2List) {
            sumSq = (sumSq + (v * v) % MOD) % MOD;
        }
        long totalSq = (totalChoose2Sum * totalChoose2Sum) % MOD;
        long ans = (totalSq - sumSq) % MOD;
        if (ans < 0) ans += MOD;
        // divide by 2 modulo MOD (MOD is prime)
        ans = ans * ((MOD + 1) / 2) % MOD;

        return (int) ans;
    }
}
