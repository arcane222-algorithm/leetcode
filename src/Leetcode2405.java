import java.util.*;


/**
 * Optimal Partition of String - Leetcode2405
 * -----------------
 * category: string (문자열)
 *           greedy (그리디 알고리즘)
 * -----------------
 * Input 1
 * s = "abacaba"
 *
 * Output 1
 * 4
 *
 * Explanation 1
 * Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
 * It can be shown that 4 is the minimum number of substrings needed.
 * -----------------
 * Input 2
 * s = "ssssss"
 *
 * Output 2
 * 6
 *
 * Explanation 2
 * The only valid partition is ("s","s","s","s","s","s").
 * -----------------
 */
public class Leetcode2405 {

    public static void run() {
        Solution s = new Solution();

        String s1 = "abacaba";
        String s2 = "ssssss";

        System.out.println(s.partitionString(s1));
        System.out.println(s.partitionString(s2));
    }

    private static class Solution {
        public int partitionString(String s) {
            int check, cnt;
            check = cnt = 0;

            for (int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - 'a';
                int mask = 1 << idx;

                if ((check & mask) > 0) {
                    check = 0;
                    cnt++;
                }
                check |= mask;
            }

            return cnt + 1;
        }
    }
}
