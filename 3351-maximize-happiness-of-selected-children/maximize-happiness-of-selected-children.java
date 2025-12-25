import java.util.Arrays;

class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        // Sort the happiness array
        Arrays.sort(happiness);

        long result = 0;
        int decrement = 0;

        // Start picking from the largest happiness values
        for (int i = happiness.length - 1; i >= 0 && k > 0; i--) {
            int current = happiness[i] - decrement;

            if (current > 0) {
                result += current;
            }

            decrement++;
            k--;
        }

        return result;
    }
}
