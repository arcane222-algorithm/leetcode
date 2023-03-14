import java.util.*;
import java.io.*;


/**
 * Sum Root to Leaf Numbers - Leetcode129
 * -----------------
 * category: dfs (깊이 우선 탐색)
 * -----------------
 * Input 1
 * root = [1,2,3]
 *
 * Output 1
 * 25
 * -----------------
 * Input 2
 * root = [4,9,0,5,1]
 *
 * Output 2
 * 1026
 * -----------------
 */
public class Leetcode129 {

    private static TreeNode example1() {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        return root;
    }

    private static TreeNode example2() {
        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(9);
        root.right = new TreeNode(0);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        return root;
    }

    public static void run() {
        Solution s = new Solution();
        System.out.println(s.sumNumbers(example1()));
        System.out.println(s.sumNumbers(example2()));
    }

    private static class Solution {

        private static final int TEN = 10;

        public int dfs(TreeNode curr, int val) {
            int sum = 0;
            val += curr.val;

            if(curr.left == null && curr.right == null) {
                return val;
            }

            if (curr.left != null)
                sum += dfs(curr.left, TEN * val);

            if (curr.right != null)
                sum += dfs(curr.right, TEN * val);

            return sum;
        }

        public int sumNumbers(TreeNode root) {
            return dfs(root, 0);
        }
    }
}
