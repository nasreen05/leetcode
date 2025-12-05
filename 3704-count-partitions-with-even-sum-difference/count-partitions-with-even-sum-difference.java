class Solution {
    public int countPartitions(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        // If total sum is even, all (n-1) partitions are valid, otherwise none.
        return (total % 2 == 0) ? nums.length - 1 : 0;
    }
}
