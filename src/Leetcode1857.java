import java.util.*;


/**
 * Largest Color Value in a Directed Graph - Leetcode1857
 * -----------------
 * category: topological sorting (위상 정렬)
 *           dp (다이나믹 프로그래밍)
 * -----------------
 * Input 1
 * colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
 *
 * Output 1
 * 3
 *
 * Explanation 1
 * The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
 * -----------------
 * Input 2
 * colors = "a", edges = [[0,0]]
 *
 * Output 2
 * -1
 * 
 * Explanation 2
 * There is a cycle from 0 to 0.
 * -----------------
 */
public class Leetcode1857 {

    public static void run() {
        Solution s = new Solution();
        System.out.println(s.largestPathValue("abaca", new int[][]{{0, 1}, {0, 2}, {2, 3}, {3, 4}}));
        System.out.println(s.largestPathValue("a", new int[][]{{0, 0}}));
    }

    private static class Solution {

        public int topologicalSort(String colors, int[][] edges) {
            final int SIZE = colors.length();
            Map<Integer, int[]> countTable = new HashMap<>();
            List<List<Integer>> graph = new ArrayList<>();
            int endCount = 0;
            for (int i = 0; i < SIZE; i++) {
                graph.add(new ArrayList<>());
                countTable.put(i, new int[26]);
            }

            int[] inDegrees = new int[SIZE];
            for (int[] edge : edges) {
                inDegrees[edge[1]]++;
                graph.get(edge[0]).add(edge[1]);
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < SIZE; i++) {
                if (inDegrees[i] == 0)
                    queue.offer(i);
            }

            int max = 0;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                int colorIdx = colors.charAt(curr) - 'a';
                endCount++;
                max = Math.max(max, ++countTable.get(curr)[colorIdx]);

                for (int adj : graph.get(curr)) {
                    inDegrees[adj]--;
                    for (int i = 0; i < 26; i++) {
                        countTable.get(adj)[i] = Math.max(countTable.get(adj)[i], countTable.get(curr)[i]);
                    }

                    if (inDegrees[adj] == 0) {
                        queue.offer(adj);
                    }
                }
            }

            return endCount == SIZE ? max : -1;
        }

        public int largestPathValue(String colors, int[][] edges) {
            return topologicalSort(colors, edges);
        }
    }
}
