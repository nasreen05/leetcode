import java.util.*;

class Solution {
    public static int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long total = 0;
        for (int x : nums) total += x;
        int need = (int)(total % p);
        if (need == 0) return 0; // already divisible

        Map<Integer, Integer> lastIndex = new HashMap<>();
        lastIndex.put(0, -1); // prefix remainder 0 at index -1
        int res = Integer.MAX_VALUE;
        int prefix = 0;

        for (int i = 0; i < n; i++) {
            prefix = (prefix + nums[i]) % p;
            // we want (prefix - prev) % p == need  => prev == (prefix - need + p) % p
            int want = (prefix - need) % p;
            if (want < 0) want += p;

            if (lastIndex.containsKey(want)) {
                int prevIdx = lastIndex.get(want);
                // remove subarray (prevIdx+1 ... i)
                res = Math.min(res, i - prevIdx);
            }

            // record/update last index for current prefix remainder
            lastIndex.put(prefix, i);
        }

        return (res == Integer.MAX_VALUE || res == n) ? -1 : res;
    }

    // quick test
    public static void main(String[] args) {
        int[] nums = {6,3,5,2};
        int p = 9;
        System.out.println(minSubarray(nums, p)); // prints 2
    }
}
