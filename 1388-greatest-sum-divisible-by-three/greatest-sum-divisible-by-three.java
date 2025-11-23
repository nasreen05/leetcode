class Solution {
    public int maxSumDivThree(int[] nums) {
        // dp[i] will hold the maximum sum so far with remainder i when divided by 3
        int[] dp = new int[3];
        
        for (int num : nums) {
            int[] temp = dp.clone();  // clone dp to avoid overwriting in iteration
            
            for (int sum : temp) {
                int newSum = sum + num;
                // Update dp at remainder newSum % 3 with the max sum possible
                dp[newSum % 3] = Math.max(dp[newSum % 3], newSum);
            }
        }
        
        return dp[0]; // dp[0] has max sum divisible by 3
    }
}
