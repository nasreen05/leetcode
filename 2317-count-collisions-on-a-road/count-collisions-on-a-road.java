class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();
        int collisions = 0;
        
        // Skip leading L's (escape left)
        int i = 0;
        while (i < n && directions.charAt(i) == 'L') i++;
        
        // Skip trailing R's (escape right) 
        int j = n - 1;
        while (j >= 0 && directions.charAt(j) == 'R') j--;
        
        // Count non-stationary cars in collision zone [i..j]
        for (int k = i; k <= j; k++) {
            char c = directions.charAt(k);
            if (c != 'S') collisions++;
        }
        
        return collisions;
    }
}
