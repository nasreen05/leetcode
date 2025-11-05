class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Use arrays of sets to track seen digits in rows, columns, and boxes
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];
        
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }
        
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;  // Skip empty cells
                
                // Check if digit already seen in row
                if (rows[r].contains(ch)) return false;
                rows[r].add(ch);
                
                // Check if digit already seen in column
                if (cols[c].contains(ch)) return false;
                cols[c].add(ch);
                
                // Determine which of the 9 boxes this cell belongs to
                int boxIndex = (r / 3) * 3 + (c / 3);
                // Check if digit already seen in the 3x3 box
                if (boxes[boxIndex].contains(ch)) return false;
                boxes[boxIndex].add(ch);
            }
        }
        
        return true; // All checks passed
    }
}
