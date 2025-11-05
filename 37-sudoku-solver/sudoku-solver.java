class Solution {
    private boolean[][] rows;
    private boolean[][] cols;
    private boolean[][] boxes;
    private char[][] board;

    public void solveSudoku(char[][] board) {
        this.board = board;
        rows = new boolean[9][9];
        cols = new boolean[9][9];
        boxes = new boolean[9][9];

        // Initialize the used arrays with the given board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[(i / 3) * 3 + j / 3][num] = true;
                }
            }
        }

        // Start solving
        solve();
    }

    private boolean solve() {
        // Find the next empty cell
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    // Try numbers 1 to 9
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(i, j, num)) {
                            // Place the number
                            board[i][j] = (char) (num + '0');
                            rows[i][num - 1] = true;
                            cols[j][num - 1] = true;
                            boxes[(i / 3) * 3 + j / 3][num - 1] = true;

                            // Recurse
                            if (solve()) {
                                return true;
                            }

                            // Backtrack
                            board[i][j] = '.';
                            rows[i][num - 1] = false;
                            cols[j][num - 1] = false;
                            boxes[(i / 3) * 3 + j / 3][num - 1] = false;
                        }
                    }
                    return false; // No number worked for this cell
                }
            }
        }
        return true; // All cells are filled
    }

    private boolean isSafe(int i, int j, int num) {
        int n = num - 1;
        return !rows[i][n] && !cols[j][n] && !boxes[(i / 3) * 3 + j / 3][n];
    }
}
