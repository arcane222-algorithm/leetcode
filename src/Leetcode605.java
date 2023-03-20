import java.util.*;
import java.io.*;


/**
 * Can Place Flowers - Leetcode605
 * -----------------
 * category: implementation (구현)
 * -----------------
 * Input 1
 * [1,0,0,0,1], n = 1
 *
 * Output 1
 * true
 * -----------------
 * Input 2
 * [1,0,0,0,1], n = 2
 *
 * Output 2
 * false
 * -----------------
 */
public class Leetcode605 {

    public static void run() {
        Solution s = new Solution();
        System.out.println(s.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
        System.out.println(s.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
    }

    private static class Solution {
        public int getVal(int[] arr, int idx) {
            return idx < 0 || idx > arr.length - 1 ? 0 : arr[idx];
        }

        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            if (n == 0)
                return true;

            int cnt = 0;
            for (int i = 0; i < flowerbed.length; i++) {
                if (flowerbed[i] == 0) {
                    if (getVal(flowerbed, i - 1) == 0 && getVal(flowerbed, i + 1) == 0) {
                        flowerbed[i] = 1;
                        cnt++;
                    }
                }

                if (cnt == n)
                    return true;
            }

            return false;
        }
    }
}
