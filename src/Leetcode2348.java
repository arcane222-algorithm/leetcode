import java.util.*;


/**
 * Number of Zero-Filled Subarrays - Leetcode2348
 * -----------------
 * category: mathematics (수학)
 *           array (배열)
 * -----------------
 * Input 1
 * nums = [1,3,0,0,2,0,0,4]
 *
 * Output 1
 * 6
 *
 * Explanation 1
 * There are 4 occurrences of [0] as a subarray.
 * There are 2 occurrences of [0,0] as a subarray.
 * There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.
 * -----------------
 * Input 2
 * nums = [0,0,0,2,0,0]
 *
 * Output 2
 * 9
 *
 * Explanation 2
 * There are 5 occurrences of [0] as a subarray.
 * There are 3 occurrences of [0,0] as a subarray.
 * There is 1 occurrence of [0,0,0] as a subarray.
 * There is no occurrence of a subarray with a size more than 3 filled with 0. Therefore, we return 9.
 * -----------------
 * Input 3
 * nums = [2,10,2019]
 *
 * Output 2
 * 0
 *
 * Explanation 3
 * There is no subarray filled with 0. Therefore, we return 0.
 * -----------------
 */
public class Leetcode2348 {

    public static void run() {
        Solution s = new Solution();
        System.out.println(s.zeroFilledSubarray(new int[]{1, 3, 0, 0, 2, 0, 0, 4}));
        System.out.println(s.zeroFilledSubarray(new int[]{0, 0, 0, 2, 0, 0}));
        System.out.println(s.zeroFilledSubarray(new int[]{2, 10, 2019}));
    }

    private static class Solution {

        public long term(long x) {
            return (x * (x + 1)) >> 1;
        }

        public long zeroFilledSubarray(int[] nums) {
            long sum = 0;
            long stack = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    stack++;
                } else {
                    sum += term(stack);
                    stack = 0;
                }
            }

            if(stack != 0) {
                sum += term(stack);
            }

            return sum;
        }
    }
}
