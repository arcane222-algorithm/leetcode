import java.util.*;


/**
 * Number of Closed Islands - Leetcode1254
 * -----------------
 * category: dfs (깊이 우선 탐색)
 *           bfs (너비 우선 탐색)
 * -----------------
 * Input 1
 * grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * <p>
 * Output 1
 * 2
 * <p>
 * Explanation 1
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 * -----------------
 * Input 2
 * grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * <p>
 * Output 2
 * 1
 * <p>
 * Explanation 2
 * It is optimal to leave nums as is, and since 10 is the maximum value, we return 10.
 * -----------------
 * Input 3
 * grid = [[1,1,1,1,1,1,1],
 * [1,0,0,0,0,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,1,0,1,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,0,0,0,0,1],
 * [1,1,1,1,1,1,1]]
 * <p>
 * Output 3
 * 2
 * -----------------
 */
public class Leetcode1254 {

    public static void run() {
        Solution s = new Solution();

        int[][] grid1 = new int[][]{{1, 1, 1, 1, 1, 1, 1, 0},
                                    {1, 0, 0, 0, 0, 1, 1, 0},
                                    {1, 0, 1, 0, 1, 1, 1, 0},
                                    {1, 0, 0, 0, 0, 1, 0, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 0}};

        int[][] grid2 = new int[][]{{0,0,1,0,0},
                                    {0,1,0,1,0},
                                    {0,1,1,1,0}};

        int[][] grid3 = new int[][]{{1,1,1,1,1,1,1},
                                    {1,0,0,0,0,0,1},
                                    {1,0,1,1,1,0,1},
                                    {1,0,1,0,1,0,1},
                                    {1,0,1,1,1,0,1},
                                    {1,0,0,0,0,0,1},
                                    {1,1,1,1,1,1,1}};

        System.out.println(s.closedIsland(grid1));
        System.out.println(s.closedIsland(grid2));
        System.out.println(s.closedIsland(grid3));
    }

    private static class Solution {
        private static class Pair {
            int x, y;

            private Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public static Pair create(int x, int y) {
                return new Pair(x, y);
            }

            @Override
            public boolean equals(Object o) {
                if (o == this) return true;
                if (o instanceof Pair) {
                    Pair p = (Pair) o;
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

        public boolean bfs(int[][] grid, boolean[][] visited, int sx, int sy) {
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(Pair.create(sx, sy));
            visited[sy][sx] = true;
            boolean result = true;

            while (!queue.isEmpty()) {
                Pair curr = queue.poll();

                for (int i = 0; i < dirX.length; i++) {
                    int nxtX = curr.x + dirX[i];
                    int nxtY = curr.y + dirY[i];

                    if (!canGo(nxtX, nxtY)) {
                        result = false;
                        continue;
                    }

                    if (visited[nxtY][nxtX] || grid[nxtY][nxtX] == 1)
                        continue;

                    queue.offer(Pair.create(nxtX, nxtY));
                    visited[nxtY][nxtX] = true;
                }
            }

            return result;
        }

        public int closedIsland(int[][] grid) {
            w = grid[0].length;
            h = grid.length;

            int count = 0;
            boolean[][] visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (visited[i][j] || grid[i][j] == 1)
                        continue;

                    if (bfs(grid, visited, j, i))
                        count++;
                }
            }

            return count;
        }
    }
}
