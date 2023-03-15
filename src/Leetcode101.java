import java.util.*;
import java.io.*;


/**
 * Symmetric Tree - Leetcode101
 * -----------------
 * category: bfs (너비 우선 탐색)
 * -----------------
 * Input 1
 * root = [1,2,2,3,4,4,3]
 *
 * Output 1
 * true
 * -----------------
 * Input 2
 * root = [1,2,2,null,3,null,3]
 *
 * Output 2
 * false
 * -----------------
 * Input 3
 * root = [3, 4, 4, 5, null, null, 5, 6, null, null, 6]
 *
 * Output 3
 * true;
 * -----------------
 */
public class Leetcode101 {

    private static TreeNode example1() {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        return root;
    }

    private static TreeNode example2() {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);

        return root;
    }

    private static TreeNode example3() {
        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(4);
        root.right = new TreeNode(4);

        root.left.left = new TreeNode(5);
        root.right.right = new TreeNode(5);

        root.left.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(6);

        return root;
    }

    public static void run() {
        Solution s = new Solution();
        System.out.println(s.isSymmetric(example1()));
        System.out.println(s.isSymmetric(example2()));
        System.out.println(s.isSymmetric(example3()));
    }

    private static class Solution {

        public boolean bfs(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root.left);
            queue.add(root.right);

            while (!queue.isEmpty()) {
                TreeNode left = queue.poll();
                TreeNode right = queue.poll();

                if (left == null && right == null)
                    continue;

                if (left == null || right == null)
                    return false;

                if (left.val != right.val)
                    return false;

                queue.add(left.left);
                queue.add(right.right);
                queue.add(left.right);
                queue.add(right.left);
            }

            return true;
        }

        public boolean isSymmetric(TreeNode root) {
            return bfs(root);
        }
    }
}
