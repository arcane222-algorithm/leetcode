import java.util.*;


/**
 * Longest Cycle in a Graph - Leetcode2360
 * -----------------
 * category: dfs (깊이 우선 탐색)
 * topological sort (위상 정렬)
 * strongly connected component (강한 연결 요소)
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
        Solution2 s2 = new Solution2();

        int[] example1 = new int[]{3, 3, 4, 2, 3};
        int[] example2 = new int[]{2, -1, 3, 1};

        System.out.println(s.longestCycle(example1));
        System.out.println(s.longestCycle(example2));

        System.out.println(s2.longestCycle(example1));
        System.out.println(s2.longestCycle(example2));
    }

    // SCC (Strongly Connected Component) - Tarjan's Algorithm
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

    // Topological Sorting - Kahn's Algorithm (InDegree based topological sorting)
    private static class Solution2 {
        public int longestCycle(int[] edges) {
            int[] inDegrees = new int[edges.length];
            for (int dest : edges) {
                if (dest != -1) {
                    inDegrees[dest]++;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < inDegrees.length; i++) {
                if (inDegrees[i] == 0)
                    queue.add(i);
            }

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                int dest = edges[curr];

                if (dest != -1) {
                    inDegrees[dest]--;
                    if (inDegrees[dest] == 0)
                        queue.add(dest);
                }
            }

            int result = -1;
            for (int i = 0; i < inDegrees.length; i++) {
                if (inDegrees[i] > 0) {
                    int dest = edges[i];
                    int cnt = 1;
                    inDegrees[i] = 0;

                    while (dest != i) {
                        inDegrees[dest] = 0;
                        dest = edges[dest];
                        cnt++;
                    }
                    result = Math.max(result, cnt);
                }
            }

            return result;
        }
    }
}
