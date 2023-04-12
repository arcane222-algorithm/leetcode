import java.util.*;
import java.io.*;


/**
 * Simplify Path - Leetcode71
 * -----------------
 * category: string (문자열)
 *           stack (스택)
 * -----------------
 * Input 1
 * path = "/home/"
 *
 * Output 1
 * "/home"
 *
 * Explanation 1
 * Note that there is no trailing slash after the last directory name.
 * -----------------
 * Input 2
 * path = "/../"
 *
 * Output 2
 * "/"
 *
 * Explanation 2
 * Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * -----------------
 * Input 3
 * path = "/home//foo/"
 *
 * Output 3
 * "/home/foo"
 *
 * Explanation 3
 * In the canonical path, multiple consecutive slashes are replaced by a single one.
 */
public class Leetcode71 {

    public static void run() {
        Solution s = new Solution();

        System.out.println(s.simplifyPath("/home/"));
        System.out.println(s.simplifyPath("/../"));
        System.out.println(s.simplifyPath("/home//foo/"));
    }

    private static class Solution {
        public String simplifyPath(String path) {
            String[] split = path.split("/");
            Stack<String> stack = new Stack<>();
            for(String name : split) {
                if(name.equals("..") && !stack.isEmpty()) {
                    stack.pop();
                } else if(!name.equals("..") && !name.equals(".") && !name.equals("")) {
                    stack.push(name);
                }
            }

            StringBuilder builder = new StringBuilder();
            while(!stack.isEmpty()) {
                builder.insert(0, stack.pop());
                builder.insert(0, '/');
            }

            return builder.length() > 0 ? builder.toString() : "/";
        }
    }
}
