import java.util.*;


/**
 * Number of Operations to Make Network Connected - Leetcode1319
 * -----------------
 * category: bfs (너비 우선 탐색)
 * dfs (깊이 우선 탐색)
 * disjoint-set (분리 집합)
 * -----------------
 * Input 1
 * n = 4, connections = [[0,1],[0,2],[1,2]]
 * <p>
 * Output 1
 * 1
 * <p>
 * Explanation 1
 * Remove cable between computer 1 and 2 and place between computers 1 and 3.
 * -----------------
 * Input 2
 * n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * <p>
 * Output 2
 * 2
 * <p>
 * Explanation 2
 * The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4.
 * The score of this path is min(2,2,4,7) = 2.
 * -----------------
 * Input 3
 * n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
 * <p>
 * Output
 * -1
 * <p>
 * Explanation 3
 * There are not enough cables.
 * -----------------
 */
public class Leetcode1319 {

    public static void run() {
        Solution s = new Solution();

        int[][] example1 = new int[][]{{0, 1}, {0, 2}, {1, 2}};
        int[][] example2 = new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        int[][] example3 = new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}};

        System.out.println(s.makeConnected(4, example1));
        System.out.println(s.makeConnected(6, example2));
        System.out.println(s.makeConnected(6, example3));
    }

    private static class Solution {

        private static class DisjointSet {
            private final int[] parents;
            private final int[] nodeCounts;

            public DisjointSet(int n) {
                parents = new int[n];
                nodeCounts = new int[n];

                for (int i = 0; i < n; i++) {
                    parents[i] = i;
                    nodeCounts[i] = 1;
                }
            }

            public void union(int a, int b) {
                int aRoot = find(a);
                int bRoot = find(b);

                if (aRoot == bRoot)
                    return;

                if (nodeCounts[aRoot] > nodeCounts[bRoot]) {
                    int tmp = aRoot;
                    aRoot = bRoot;
                    bRoot = tmp;
                }

                parents[aRoot] = bRoot;
                nodeCounts[bRoot] += nodeCounts[aRoot];
            }

            public int find(int a) {
                if (a == parents[a]) return a;
                return parents[a] = find(parents[a]);
            }

            public boolean compareParent(int a, int b) {
                return find(a) == find(b);
            }
        }

        public int makeConnected(int n, int[][] connections) {
            DisjointSet dSet = new DisjointSet(n);
            int remainCnt = 0;
            int groupCnt = n;
            for (int[] edge : connections) {
                if (!dSet.compareParent(edge[0], edge[1])) {
                    dSet.union(edge[0], edge[1]);
                    groupCnt--;
                } else {
                    remainCnt++;
                }
            }

            if (remainCnt - 1 <= groupCnt) {
                return remainCnt - 1;
            }
            return -1;
        }
    }
}
