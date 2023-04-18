import java.util.*;


/**
 * Merge Strings Alternately - Leetcode1768
 * -----------------
 * category: two-pointer (투 포인터)
 *           string (문자열)
 * -----------------
 * Input 1
 * word1 = "abc", word2 = "pqr"
 *
 * Output 1
 * "apbqcr"
 *
 * Explanation 1
 * The merged string will be merged as so:
 * word1:  a   b   c
 * word2:    p   q   r
 * merged: a p b q c r
 * -----------------
 * Input 2
 * word1 = "ab", word2 = "pqrs"
 *
 * Output 2
 * "apbqrs"
 *
 * Explanation 2
 * Notice that as word2 is longer, "rs" is appended to the end.
 * word1:  a   b
 * word2:    p   q   r   s
 * merged: a p b q   r   s
 * -----------------
 * Input 3
 * word1 = "abcd", word2 = "pq"
 *
 * Output 3
 * "apbqcd"
 *
 * Explanation 3
 * Notice that as word1 is longer, "cd" is appended to the end.
 * word1:  a   b   c   d
 * word2:    p   q
 * merged: a p b q c   d
 * -----------------
 */
public class Leetcode1768 {

    public static void run() {
        Solution s = new Solution();

        System.out.println(s.mergeAlternately("abc", "pqr"));
        System.out.println(s.mergeAlternately("ab", "pqrs"));
        System.out.println(s.mergeAlternately("abcd", "pq"));
    }

    private static class Solution {
        public String mergeAlternately(String word1, String word2) {
            StringBuilder builder = new StringBuilder();

            int large = Math.max(word1.length(), word2.length());
            for(int i = 0; i < large; i++) {
                if(word1.length() > i)
                    builder.append(word1.charAt(i));

                if(word2.length() > i)
                    builder.append(word2.charAt(i));
            }

            return builder.toString();
        }
    }
}
