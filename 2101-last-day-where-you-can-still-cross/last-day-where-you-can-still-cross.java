import java.util.*;

class Solution {
    private int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public int latestDayToCross(int row, int col, int[][] cells) {
        int left = 0, right = row * col - 1;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canCross(row, col, cells, mid)) {
                ans = mid + 1;        // days are 1-based
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private boolean canCross(int row, int col, int[][] cells, int day) {
        boolean[][] water = new boolean[row][col];

        // Mark flooded cells up to 'day'
        for (int i = 0; i <= day; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            water[r][c] = true;
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];

        // Start BFS from top row
        for (int c = 0; c < col; c++) {
            if (!water[0][c]) {
                queue.offer(new int[]{0, c});
                visited[0][c] = true;
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];

            if (r == row - 1) return true; // reached bottom

            for (int[] d : directions) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < row && nc >= 0 && nc < col &&
                    !water[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}
