import java.util.*;


/**
 * Clone Graph - Leetcode133
 * -----------------
 * category: dfs (깊이 우선 탐색)
 *           dfs (너비 우선 탐색)
 * -----------------
 * Input 1
 * adjList = [[2,4],[1,3],[2,4],[1,3]]
 *
 * Output 1
 * [[2,4],[1,3],[2,4],[1,3]]
 *
 * Explanation 1
 * There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * -----------------
 * Input 2
 * adjList = [[]]
 *
 * Output 2
 * [[]]
 *
 * Explanation 2
 * Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * -----------------
 * Input 3
 * adjList = []
 *
 * Output 3
 * []
 *
 * Explanation 3
 * This an empty graph, it does not have any nodes.
 */
public class Leetcode133 {

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }

        public Node(int val, ArrayList<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    public static void run() {
    }

    private static class Solution {

        public Node cloneGraph(Node node) {
            if (node == null)
                return null;

            boolean[] visited = new boolean[101];
            Node[] cache = new Node[101];
            Queue<Node> queue = new LinkedList<>();

            queue.add(node);
            visited[node.val] = true;
            cache[node.val] = new Node(node.val);

            while (!queue.isEmpty()) {
                Node curr = queue.poll();

                for (Node adj : curr.neighbors) {
                    if (cache[adj.val] == null) {
                        cache[adj.val] = new Node(adj.val);
                    }
                    cache[curr.val].neighbors.add(cache[adj.val]);

                    if (!visited[adj.val]) {
                        queue.add(adj);
                        visited[adj.val] = true;
                    }
                }
            }

            return cache[node.val];
        }
    }
}
