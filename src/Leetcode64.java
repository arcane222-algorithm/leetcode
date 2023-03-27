import java.util.*;


/**
 * Minimum Path Sum - Leetcode64
 * -----------------
 * category: dp (다이나믹 프로그래밍)
 * -----------------
 * Input 1
 * grid = [[1,3,1],[1,5,1],[4,2,1]]
 *
 * Output 1
 * 7
 *
 * Explanation 1
 * Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * -----------------
 * Input 2
 * grid = [[1,2,3],[4,5,6]]
 *
 * Output 2
 * 12
 *
 * Explanation 2
 * -
 * -----------------
 */
public class Leetcode64 {

    public static void run() {
        Solution s = new Solution();

        int[][] example1 = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] example2 = new int[][]{{1, 2, 3}, {4, 5, 6}};

        System.out.println(s.minPathSum(example1));
        System.out.println(s.minPathSum(example2));
    }

    private static class Solution {

        private static final int[] dirX = {0, 1};
        private static final int[] dirY = {1, 0};

        private static class Pair {
            private final int x;
            private final int y;

            private Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public static Pair create(int x, int y) {
                return new Pair(x, y);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o instanceof Pair) {
                    Pair p = (Pair) o;
                    return x == p.x && y == p.x;
                }
                return false;
            }

            @Override
            public int hashCode() {
                return (x << 5) - x + y;
            }
        }

        public boolean canGo(int x, int y, int w, int h) {
            if (x < 0 || x > w - 1) return false;
            if (y < 0 || y > h - 1) return false;
            return true;
        }


        public int minPathSum(int[][] grid) {
            final int W = grid[0].length;
            final int H = grid.length;

            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(0, 0));
            int[][] dp = new int[H][W];
            for (int[] arr : dp)
                Arrays.fill(arr, Integer.MAX_VALUE);

            dp[0][0] = grid[0][0];
            while (!queue.isEmpty()) {
                Pair pos = queue.poll();

                for (int i = 0; i < dirX.length; i++) {
                    int nxtX = pos.x + dirX[i];
                    int nxtY = pos.y + dirY[i];

                    if (!canGo(nxtX, nxtY, W, H)) continue;
                    if (dp[nxtY][nxtX] <= dp[pos.y][pos.x] + grid[nxtY][nxtX]) continue;

                    dp[nxtY][nxtX] = dp[pos.y][pos.x] + grid[nxtY][nxtX];
                    queue.add(new Pair(nxtX, nxtY));
                }
            }

            return dp[H - 1][W - 1];
        }
    }
}
