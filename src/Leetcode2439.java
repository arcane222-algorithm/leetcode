import java.util.*;


/**
 * Minimize Maximum of Array - Leetcode2439
 * -----------------
 * category: prefix sum (누적 합)
 *           greedy (그리디 알고리즘)
 * -----------------
 * Input 1
 * nums = [3,7,1,6]
 *
 * Output 1
 * 5
 *
 * Explanation 1
 * One set of optimal operations is as follows:
 * 1. Choose i = 1, and nums becomes [4,6,1,6].
 * 2. Choose i = 3, and nums becomes [4,6,2,5].
 * 3. Choose i = 1, and nums becomes [5,5,2,5].
 * The maximum integer of nums is 5. It can be shown that the maximum number cannot be less than 5.
 * Therefore, we return 5.
 * -----------------
 * Input 2
 * nums = [10,1]
 *
 * Output 2
 * 10
 *
 * Explanation 2
 * It is optimal to leave nums as is, and since 10 is the maximum value, we return 10.
 * -----------------
 */
public class Leetcode2439 {

    public static void run() {
        Solution s = new Solution();

        int[] nums1 = new int[]{3, 7, 1, 6};
        int[] nums2 = new int[]{10, 1};

        System.out.println(s.minimizeArrayValue(nums1));
        System.out.println(s.minimizeArrayValue(nums2));
    }

    private static class Solution {

        public long div(long a, long b) {
            if (a % b == 0)
                return a / b;
            return (a / b) + 1;
        }

        public int minimizeArrayValue(int[] nums) {
            long sum = 0;
            long result = 0;
            long i = 1;
            for (int val : nums) {
                sum += val;
                result = Math.max(result, div(sum, i++));
            }

            return (int) result;
        }
    }
}
