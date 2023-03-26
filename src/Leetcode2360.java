import java.util.*;


/**
 * Longest Cycle in a Graph - Leetcode2360
 * -----------------
 * category: dfs (깊이 우선 탐색)
 *           topological sort (위상 정렬)
 *           strongly connected component (강한 연결 요소)
 * -----------------
 * Input 1
 * edges = [3,3,4,2,3]
 *
 * Output 1
 * 3
 *
 * Explanation 1
 * The longest cycle in the graph is the cycle: 2 -> 4 -> 3 -> 2.
 * The length of this cycle is 3, so 3 is returned.
 * -----------------
 * Input 2
 * edges = [2,-1,3,1]
 *
 * Output 2
 * -1
 *
 * Explanation 2
 * There are no cycles in this graph.
 * -----------------
 */
public class Leetcode2360 {

    public static void run() {
        Solution s = new Solution();

        int[] example1 = new int[]{3, 3, 4, 2, 3};
        int[] example2 = new int[]{2, -1, 3, 1};

        System.out.println(s.longestCycle(example1));
        System.out.println(s.longestCycle(example2));
    }

    private static class Solution {

        static int nodeCount;

        static boolean[] finish;
        static int[] nodeIds;

        static List<List<Integer>> graph, sccGraph;
        static ArrayDeque<Integer> stack;
        static int max;

        public void init(int[] edges) {
            final int n = edges.length;

            nodeCount = 0;
            max = 0;
            finish = new boolean[n + 1];
            nodeIds = new int[n + 1];

            graph = new ArrayList<>(n + 1);
            sccGraph = new ArrayList<>();
            stack = new ArrayDeque<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int u = 0; u < n; u++) {
                if (edges[u] == -1) continue;
                graph.get(u + 1).add(edges[u] + 1);    // u -> v
            }
        }

        public void getScc(int n) {
            for (int i = 1; i <= n; i++) {
                if (nodeIds[i] == 0) {
                    dfs(i);
                }
            }
        }

        public static int dfs(int currIdx) {
            nodeIds[currIdx] = ++nodeCount;
            stack.addLast(currIdx);

            int parentId = nodeIds[currIdx];
            for (int adjIdx : graph.get(currIdx)) {
                if (nodeIds[adjIdx] == 0) {
                    parentId = Math.min(parentId, dfs(adjIdx));
                } else if (!finish[adjIdx]) {
                    parentId = Math.min(parentId, nodeIds[adjIdx]);
                }
            }

            if (parentId == nodeIds[currIdx]) {
                int nodeIdx = -1;
                int cnt = 0;
                while (nodeIdx != currIdx) {
                    nodeIdx = stack.removeLast();
                    finish[nodeIdx] = true;
                    cnt++;
                }
                max = Math.max(max, cnt);
            }
            return parentId;
        }

        public int longestCycle(int[] edges) {
            init(edges);
            getScc(edges.length);
            return max == 1 ? -1 : max;
        }
    }
}
