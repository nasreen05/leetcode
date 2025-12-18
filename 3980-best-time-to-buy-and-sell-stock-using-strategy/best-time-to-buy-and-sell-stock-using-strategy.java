class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;

        // 1. Original profit
        long originalProfit = 0;
        for (int i = 0; i < n; i++) {
            originalProfit += (long) strategy[i] * prices[i];
        }

        // 2. Prefix sum of strategy * prices
        long[] prefSP = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefSP[i + 1] = prefSP[i] + (long) strategy[i] * prices[i];
        }

        // 3. Prefix sum of prices
        long[] prefPrice = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefPrice[i + 1] = prefPrice[i] + prices[i];
        }

        long maxGain = 0;
        int half = k / 2;

        // 4. Sliding window
        for (int l = 0; l + k <= n; l++) {
            int mid = l + half;
            int r = l + k;

            long oldContribution = prefSP[r] - prefSP[l];
            long newContribution = prefPrice[r] - prefPrice[mid];

            long gain = newContribution - oldContribution;
            maxGain = Math.max(maxGain, gain);
        }

        // 5. Result
        return originalProfit + Math.max(0, maxGain);
    }
}
