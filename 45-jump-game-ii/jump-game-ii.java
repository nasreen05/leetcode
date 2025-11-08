class Solution {
    public int jump(int[] nums) {
        int jumps = 0;        // Number of jumps made
        int farthest = 0;     // Farthest index reachable within current number of jumps
        int currentEnd = 0;   // End of the range covered by the current jump
        
        for (int i = 0; i < nums.length - 1; i++) {
            // Update the farthest reachable index from position i
            farthest = Math.max(farthest, i + nums[i]);
            
            // If we have reached the end of the current jump range,
            // increase jump count and update currentEnd to farthest
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }
}
