import java.util.*;


/**
 * Boats to Save People - Leetcode881
 * -----------------
 * category: two-pointer (투 포인터)
 * sorting (정렬)
 * -----------------
 * Input 1
 * people = [1,2], limit = 3
 *
 * Output 1
 * 1
 *
 * Explanation 1
 * 1 boat (1, 2)
 * -----------------
 * Input 2
 * people = [3,2,2,1], limit = 3
 *
 * Output 2
 * 3
 *
 * Explanation 2
 * 3 boats (1, 2), (2) and (3)
 * -----------------
 * Input 3
 * people = [3,5,3,4], limit = 5
 *
 * Output 3
 * 4
 *
 * Explanation 3
 * Explanation: 4 boats (3), (3), (4), (5)
 * -----------------
 */
public class Leetcode881 {


    public static void run() {
        Solution s = new Solution();

        int[] people1 = new int[]{1, 2};
        int[] people2 = new int[]{3, 2, 2, 1};
        int[] people3 = new int[]{3, 5, 3, 4};

        System.out.println(s.numRescueBoats(people1, 3));
        System.out.println(s.numRescueBoats(people2, 3));
        System.out.println(s.numRescueBoats(people3, 5));
    }

    private static class Solution {

        public int twoPointerSearch(int[] people, int limit) {
            int lp, rp, cnt;   // left pointer, right pointer, count
            lp = cnt = 0;
            rp = people.length - 1;

//            while (lp <= rp) {
//                if (limit < people[lp] + people[rp]) {
//                    // 이 경우 people[rp] 혼자 보트에 탈 수 있음
//                    // 가장 우측 사람 제외 (rp--)
//                    cnt++;
//                    rp--;
//                } else {
//                    // 보트에 두명이 타는 경우
//                    // 양쪽 사람 제외 (lp++, rp--)
//                    cnt++;
//                    lp++;
//                    rp--;
//                }
//
//                if (limit >= people[lp] + people[rp]) {
//                    lp++;
//                }
//                rp--;
//                cnt++;
//            }

            // 상단의 주석 while 문 중첩 연산을 합칠 경우
            while (lp <= rp) {
                if (limit >= people[lp] + people[rp]) {
                    lp++;
                }
                rp--;
                cnt++;
            }

            return cnt;
        }

        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
            return twoPointerSearch(people, limit);
        }
    }
}
