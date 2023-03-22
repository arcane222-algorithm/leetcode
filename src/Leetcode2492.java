import java.util.*;


/**
 * Minimum Score of a Path Between Two Cities - Leetcode2492
 * -----------------
 * category: bfs (너비 우선 탐색)
 *           dfs (깊이 우선 탐색)
 *           disjoint-set (분리 집합)
 * -----------------
 * Input 1
 * n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
 *
 * Output 1
 * 5
 *
 * Explanation 1
 * The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
 * It can be shown that no other path has less score.
 * -----------------
 * Input 2
 * n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
 *
 * Output 2
 * 2
 *
 * Explanation 2
 * The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4.
 * The score of this path is min(2,2,4,7) = 2.
 * -----------------
 */
public class Leetcode2492 {

    public static void run() {
        Solution1 s1 = new Solution1();
        Solution2 s2 = new Solution2();

        int[][] example1 = new int[][]{{1, 2, 9}, {2, 3, 6}, {2, 4, 5}, {1, 4, 7}};
        int[][] example2 = new int[][]{{1, 2, 2}, {1, 3, 4}, {3, 4, 7}};

        System.out.println(s1.minScore(4, example1));
        System.out.println(s1.minScore(4, example2));

        System.out.println(s2.minScore(4, example1));
        System.out.println(s2.minScore(4, example2));
    }

    private static class Solution1 {

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

        public List<List<Pair>> init(int n, int[][] roads) {
            List<List<Pair>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++)
                graph.add(new ArrayList<>());

            for (int[] road : roads) {
                graph.get(road[0]).add(Pair.create(road[1], road[2]));
                graph.get(road[1]).add(Pair.create(road[0], road[2]));
            }

            return graph;
        }

        public int bfs(List<List<Pair>> graph) {
            boolean[] visited = new boolean[graph.size()];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(1);
            visited[1] = true;

            int result = Integer.MAX_VALUE;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (Pair p : graph.get(curr)) {
                    result = Math.min(result, p.y);

                    if (!visited[p.x]) {
                        queue.add(p.x);
                        visited[p.x] = true;
                    }
                }
            }

            return result;
        }

        public int minScore(int n, int[][] roads) {
            List<List<Pair>> graph = init(n, roads);
            return bfs(graph);
        }
    }

    private static class Solution2 {

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


        public int minScore(int n, int[][] roads) {
            DisjointSet dSet = new DisjointSet(n + 1);
            for (int[] road : roads) {
                if (!dSet.compareParent(road[0], road[1])) {
                    dSet.union(road[0], road[1]);
                }
            }

            int min = Integer.MAX_VALUE;
            for (int[] road : roads) {
                if (dSet.compareParent(1, road[0])) {
                    min = Math.min(min, road[2]);
                }
            }

            return min;
        }
    }
}
