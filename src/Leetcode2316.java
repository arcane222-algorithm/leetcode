import java.util.*;


/**
 * Count Unreachable Pairs of Nodes in an Undirected Graph - Leetcode2316
 * -----------------
 * category: bfs (너비 우선 탐색)
 * dfs (깊이 우선 탐색)
 * disjoint-set (분리 집합)
 * -----------------
 * Input 1
 * n = 3, edges = [[0,1],[0,2],[1,2]]
 * <p>
 * Output 1
 * 0
 * <p>
 * Explanation 1
 * There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
 * -----------------
 * Input 2
 * n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 * <p>
 * Output 2
 * 14
 * <p>
 * Explanation 2
 * There are 14 pairs of nodes that are unreachable from each other:
 * [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
 * Therefore, we return 14.
 * -----------------
 */
public class Leetcode2316 {

    public static void run() {
        Solution s = new Solution();

        int[][] example1 = new int[][]{{0, 1}, {0, 2}, {1, 2}};
        int[][] example2 = new int[][]{{0, 2}, {0, 5}, {2, 4}, {1, 6}, {5, 4}};

        System.out.println(s.countPairs(3, example1));
        System.out.println(s.countPairs(7, example2));
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

        public long countPairs(int n, int[][] edges) {
            DisjointSet dSet = new DisjointSet(n);

            for (int[] edge : edges) {
                if (!dSet.compareParent(edge[0], edge[1])) {
                    dSet.union(edge[0], edge[1]);
                }
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int parent = dSet.find(i);
                if (map.containsKey(parent)) map.put(parent, map.get(parent) + 1);
                else map.put(parent, 1);
            }

            if (map.size() == 1) {
                return 0;
            } else {
                long result = 0;
                long sum = 0;
                List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
                for (Map.Entry<Integer, Integer> integerIntegerEntry : list) {
                    sum += integerIntegerEntry.getValue();
                }

                for (Map.Entry<Integer, Integer> entry : list) {
                    sum -= entry.getValue();
                    result += sum * entry.getValue();
                }

                return result;
            }
        }
    }
}
