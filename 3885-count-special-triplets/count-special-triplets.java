import java.util.*;

class Solution {
    public int specialTriplets(int[] nums) {
        int MOD = 1_000_000_007;
        Map<Integer, Long> right = new HashMap<>();
        Map<Integer, Long> left = new HashMap<>();

        for (int num : nums) {
            right.put(num, right.getOrDefault(num, 0L) + 1);
        }

        long result = 0;

        for (int j = 0; j < nums.length; j++) {
            int mid = nums[j];
            right.put(mid, right.get(mid) - 1); // remove current from right

            long iCount = left.getOrDefault(mid * 2, 0L);
            long kCount = right.getOrDefault(mid * 2, 0L);

            result = (result + (iCount * kCount) % MOD) % MOD;

            left.put(mid, left.getOrDefault(mid, 0L) + 1); // add to left
        }

        return (int) result;
    }
}
