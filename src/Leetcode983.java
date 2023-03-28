import java.util.*;


/**
 * Minimum Cost For Tickets - Leetcode983
 * -----------------
 * category: dp (다이나믹 프로그래밍)
 * -----------------
 * Input 1
 * days = [1,4,6,7,8,20], costs = [2,7,15]
 *
 * Output 1
 * 11
 *
 * Explanation 1
 * For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total, you spent $11 and covered all the days of your travel.
 * -----------------
 * Input 2
 * days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 *
 * Output 2
 * 17
 *
 * Explanation 2
 * For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 * In total, you spent $17 and covered all the days of your travel.
 * -----------------
 */
public class Leetcode983 {

    public static void run() {
        Solution s = new Solution();

        int[] days1 = new int[]{1, 4, 6, 7, 8, 20};
        int[] costs1 = new int[]{2, 7, 15};

        int[] days2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        int[] costs2 = new int[]{2, 7, 15};

        System.out.println(s.mincostTickets(days1, costs1));
        System.out.println(s.mincostTickets(days2, costs2));
    }

    private static class Solution {

        public int mincostTickets(int[] days, int[] costs) {
            return 0;
        }
    }
}
