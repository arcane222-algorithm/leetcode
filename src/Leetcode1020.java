import java.util.*;


/**
 * Number of Enclaves - Leetcode1020
 * -----------------
 * category: dfs (깊이 우선 탐색)
 *           bfs (너비 우선 탐색)
 * -----------------
 * Input 1
 * grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 *
 * Output 3
 * 2
 *
 * Explanation 1
 * Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
 * -----------------
 * Input 2
 * grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 *
 * Output 2
 * 0
 *
 * Explanation 2
 * All 1s are either on the boundary or can reach the boundary.
 * -----------------
 */
public class Leetcode1020 {

    public static void run() {
        Solution s = new Solution();

        int[][] grid1 = new int[][]{{0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}};

        int[][] grid2 = new int[][]{{0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0}};

        System.out.println(s.numEnclaves(grid1));
        System.out.println(s.numEnclaves(grid2));
    }

    private static class Solution {
        private static class Pair {
            int x, y;

            private Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public static Solution.Pair create(int x, int y) {
                return new Solution.Pair(x, y);
            }

            @Override
            public boolean equals(Object o) {
                if (o == this) return true;
                if (o instanceof Solution.Pair) {
                    Solution.Pair p = (Solution.Pair) o;
                    return x == p.x && y == p.y;
                }
                return false;
            }

            @Override
            public int hashCode() {
                return (x << 5) - x + y;
            }
        }

        static final int[] dirX = new int[]{0, 0, -1, 1};
        static final int[] dirY = new int[]{-1, 1, 0, 0};
        static int w, h;

        public boolean canGo(int x, int y) {
            if (x < 0 || x > w - 1) return false;
            if (y < 0 || y > h - 1) return false;
            return true;
        }

        public boolean isEdge(int x, int y) {
            return x == 0 || x == w - 1 || y == 0 || y == h - 1;
        }

        public int bfs(int[][] grid, boolean[][] visited, int sx, int sy) {
            Queue<Solution.Pair> queue = new LinkedList<>();
            queue.offer(Solution.Pair.create(sx, sy));
            visited[sy][sx] = true;

            int count = 1;
            boolean edge = isEdge(sx, sy);
            while (!queue.isEmpty()) {
                Solution.Pair curr = queue.poll();

                for (int i = 0; i < dirX.length; i++) {
                    int nxtX = curr.x + dirX[i];
                    int nxtY = curr.y + dirY[i];

                    if (!canGo(nxtX, nxtY))
                        continue;

                    if (visited[nxtY][nxtX] || grid[nxtY][nxtX] == 0)
                        continue;

                    if (isEdge(nxtX, nxtY))
                        edge = true;

                    queue.offer(Solution.Pair.create(nxtX, nxtY));
                    visited[nxtY][nxtX] = true;
                    count++;
                }
            }

            return edge ? 0 : count;
        }

        public int numEnclaves(int[][] grid) {
            w = grid[0].length;
            h = grid.length;

            int result = 0;
            boolean[][] visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (visited[i][j] || grid[i][j] == 0)
                        continue;

                    result += bfs(grid, visited, j, i);
                }
            }

            return result;
        }
    }
}
