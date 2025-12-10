public class Solution {
    private static final int MOD = 1_000_000_007;

    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        long c0 = complexity[0];
        for (int i = 1; i < n; i++) {
            if (complexity[i] <= c0) {
                return 0;
            }
        }

        // compute (n-1)! % MOD
        long res = 1;
        for (int i = 2; i <= n - 1; i++) {
            res = (res * i) % MOD;
        }
        return (int) res;
    }

    // For quick testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.countPermutations(new int[]{1,2,3}));       // 2
        System.out.println(sol.countPermutations(new int[]{3,3,3,4,4,4})); // 0
    }
}
