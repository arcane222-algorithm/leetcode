import java.util.*;


/**
 * Successful Pairs of Spells and Potions - Leetcode2300
 * -----------------
 * category: binary search (이분 탐색)
 * two-pointer (투 포인터)
 * sorting (정렬)
 * -----------------
 * Input 1
 * spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * 
 * Output 1
 * [4,0,3]
 *
 * Explanation 1
 * - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
 * - 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
 * - 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
 * Thus, [4,0,3] is returned.
 * -----------------
 * Input 2
 * spells = [3,1,2], potions = [8,5,8], success = 16
 *
 * Output 2
 * [2,0,2]
 *
 * Explanation 2
 * - 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
 * - 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful.
 * - 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
 * Thus, [2,0,2] is returned.
 * -----------------
 */
public class Leetcode2300 {

    public static void run() {
        Solution1 s1 = new Solution1();

        int[] spells1 = new int[]{5, 1, 3};
        int[] potions1 = new int[]{1, 2, 3, 4, 5};

        int[] spells2 = new int[]{3, 1, 2};
        int[] potions2 = new int[]{8, 5, 8};

        System.out.println(Arrays.toString(s1.successfulPairs(spells1, potions1, 7L)));
        System.out.println(Arrays.toString(s1.successfulPairs(spells2, potions2, 16L)));
    }

    private static class Solution1 {

        public int lowerBound(int[] values, long target, long multiply) {
            int lp = 0, rp = values.length, pp = 0;
            long pivot = 0;

            while (lp < rp) {
                pp = (lp + rp) >> 1;
                pivot = multiply * values[pp];

                if (pivot >= target) rp = pp;
                else lp = pp + 1;
            }

            return lp;
        }

        public int[] successfulPairs(int[] spells, int[] potions, long success) {
            Arrays.sort(potions);
            int[] result = new int[spells.length];
            for (int i = 0; i < spells.length; i++) {
                result[i] = potions.length - lowerBound(potions, success, spells[i]);
            }

            return result;
        }
    }
}
