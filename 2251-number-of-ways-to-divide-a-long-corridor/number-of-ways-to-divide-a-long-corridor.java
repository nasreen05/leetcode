class Solution {
    public int numberOfWays(String corridor) {
        final int MOD = 1_000_000_007;

        int seatCount = 0;
        for (char c : corridor.toCharArray()) {
            if (c == 'S') seatCount++;
        }

        // If seats are odd or less than 2, no valid division
        if (seatCount % 2 != 0 || seatCount < 2) return 0;

        long ways = 1;
        int seatsSeen = 0;
        int plantsBetween = 0;

        for (char c : corridor.toCharArray()) {
            if (c == 'S') {
                seatsSeen++;

                // After completing a pair (except first)
                if (seatsSeen > 2 && seatsSeen % 2 == 1) {
                    ways = (ways * (plantsBetween + 1)) % MOD;
                    plantsBetween = 0;
                }
            } else if (seatsSeen >= 2 && seatsSeen % 2 == 0) {
                // Count plants between seat-pairs
                plantsBetween++;
            }
        }

        return (int) ways;
    }
}
