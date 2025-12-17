class Solution {
    public long maximumProfit(int[] prices, int k) {

        long NEG = Long.MIN_VALUE / 4;

        // dp[t][0] = neutral
        // dp[t][1] = holding long
        // dp[t][2] = holding short
        long[][] dp = new long[k + 1][3];

        // Initialization
        for (int t = 0; t <= k; t++) {
            dp[t][0] = 0;
            dp[t][1] = NEG;
            dp[t][2] = NEG;
        }

        for (int price : prices) {
            long[][] next = new long[k + 1][3];

            for (int t = 0; t <= k; t++) {
                // Stay neutral
                next[t][0] = dp[t][0];

                // Open long or short
                next[t][1] = Math.max(dp[t][1], dp[t][0] - price); // buy
                next[t][2] = Math.max(dp[t][2], dp[t][0] + price); // sell (short)
            }

            for (int t = 1; t <= k; t++) {
                // Close long
                next[t][0] = Math.max(next[t][0], dp[t - 1][1] + price);

                // Close short
                next[t][0] = Math.max(next[t][0], dp[t - 1][2] - price);
            }

            dp = next;
        }

        long ans = 0;
        for (int t = 0; t <= k; t++) {
            ans = Math.max(ans, dp[t][0]);
        }
        return ans;
    }
}
