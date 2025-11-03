class Solution {
    public int divide(int dividend, int divisor) {
        // Handle overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // Get sign of result
        int sign = (dividend > 0) == (divisor > 0) ? 1 : -1;
        // Work with positive numbers (convert to long to handle overflow)
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int result = 0;
        while (a >= b) {
            long temp = b, mul = 1;
            while (a >= (temp << 1)) {
                temp <<= 1;
                mul <<= 1;
            }
            a -= temp;
            result += mul;
        }
        return sign * result;
    }
}
