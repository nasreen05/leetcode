import java.util.PriorityQueue;

class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        if (m <= 2 || n <= 2) return 0; // No water can be trapped

        // Use a min-heap to track the lowest boundary
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[m][n];

        // Add all the boundary cells to the heap
        for (int i = 0; i < m; i++) {
            heap.offer(new int[]{heightMap[i][0], i, 0});
            heap.offer(new int[]{heightMap[i][n-1], i, n-1});
            visited[i][0] = true;
            visited[i][n-1] = true;
        }
        for (int j = 1; j < n-1; j++) {
            heap.offer(new int[]{heightMap[0][j], 0, j});
            heap.offer(new int[]{heightMap[m-1][j], m-1, j});
            visited[0][j] = true;
            visited[m-1][j] = true;
        }

        int water = 0;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while (!heap.isEmpty()) {
            int[] cell = heap.poll();
            int h = cell[0], x = cell[1], y = cell[2];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    // Trapped water is the positive difference between the current water level and next cell
                    water += Math.max(0, h - heightMap[nx][ny]);
                    // Add the max of boundary or next cell height
                    heap.offer(new int[]{Math.max(heightMap[nx][ny], h), nx, ny});
                }
            }
        }
        return water;
    }
}