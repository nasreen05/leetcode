class Solution {
    public int countPartitions(int[] nums, int k) {
        final int MOD = 1000000007;
        int n = nums.length;
        int[] dp = new int[n + 1];  // dp[i]: ways to partition first i elements
        int[] prefix = new int[n + 1];  // prefix[i]: sum of dp[0..i]
        
        dp[0] = 1;
        prefix[0] = 1;
        
        TreeMap<Integer, Integer> window = new TreeMap<>();
        int left = 1;
        
        for (int right = 1; right <= n; right++) {
            // Add nums[right-1] to window
            window.merge(nums[right - 1], 1, Integer::sum);
            
            // Shrink window while max - min > k
            while (window.lastKey() - window.firstKey() > k) {
                window.merge(nums[left - 1], -1, Integer::sum);
                if (window.get(nums[left - 1]) == 0) {
                    window.remove(nums[left - 1]);
                }
                left++;
            }
            
            // dp[right] = sum of dp[l-1] to dp[right-1] = prefix[right-1] - prefix[l-2]
            long ways = (prefix[right - 1] - (left >= 2 ? prefix[left - 2] : 0) + MOD) % MOD;
            dp[right] = (int) ways;
            
            // Update prefix
            prefix[right] = (prefix[right - 1] + dp[right]) % MOD;
        }
        
        return dp[n];
    }
}
