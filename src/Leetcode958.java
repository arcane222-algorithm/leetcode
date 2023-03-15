import java.util.*;


/**
 * Check Completeness of a Binary Tree - Leetcode958
 * -----------------
 * category: bfs (너비 우선 탐색)
 * -----------------
 * Input 1
 * root = [1,2,3,4,5,6]
 *
 * Output 1
 * true
 * -----------------
 * Input 2
 * root = [1,2,3,4,5,null,7]
 *
 * Output 2
 * false
 * -----------------
 */
public class Leetcode958 {

    private static TreeNode example1() {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);

        return root;
    }

    private static TreeNode example2() {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.right = new TreeNode(7);

        return root;
    }

    public static void run() {
        Solution s = new Solution();
        System.out.println(s.isCompleteTree(example1()));
        System.out.println(s.isCompleteTree(example2()));
    }

    private static class Solution {

        public boolean bfs(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            boolean check = false;

            while (!queue.isEmpty()) {
                TreeNode curr = queue.poll();

                if(curr == null) {
                    check = true;
                } else {
                    if(check)
                        return false;

                    queue.add(curr.left);
                    queue.add(curr.right);
                }
            }

            return true;
        }

        public boolean isCompleteTree(TreeNode root) {
            return bfs(root);
        }
    }
}
