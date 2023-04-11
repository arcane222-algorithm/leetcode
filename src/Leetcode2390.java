import java.util.*;


/**
 * Removing Stars From a String - Leetcode2390
 * -----------------
 * category: string (문자열)
 *           stack (스택)
 * -----------------
 * Input 1
 * s = "leet**cod*e"
 *
 * Output 1
 * "lecoe"
 *
 * Explanation 1
 * Performing the removals from left to right:
 * - The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
 * - The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
 * - The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
 * There are no more stars, so we return "lecoe".
 * -----------------
 * Input 2
 * s = "erase*****"
 *
 * Output 2
 * ""
 *
 * Explanation 2
 * The entire string is removed, so we return an empty string.
 * -----------------
 */
public class Leetcode2390 {

    public static void run() {
        Solution s = new Solution();

        System.out.println(s.removeStars("leet**cod*e"));
        System.out.println(s.removeStars("erase*****"));
    }

    private static class Solution {

        public String removeStars(String s) {
            StringBuilder builder = new StringBuilder();

            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '*') {
                    builder.deleteCharAt(builder.length() - 1);
                } else {
                    builder.append(s.charAt(i));
                }
            }

            return builder.toString();
        }
    }
}
