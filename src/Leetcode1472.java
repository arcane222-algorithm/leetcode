import java.util.*;


/**
 * Design Browser History - Leetcode1472
 * -----------------
 * category: stack (스택)
 *           list (리스트)
 * -----------------
 * Input 1
 * ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
 * [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
 *
 * Output 1
 * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
 *
 * Explanation 1
 * BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
 * browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
 * browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
 * browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
 * browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
 * browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
 * browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
 * browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
 * browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
 * browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
 * browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
 * -----------------
 */
public class Leetcode1472 {

    private static List<String> example1() {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        List<String> result = new LinkedList<>();

        browserHistory.visit("google.com");
        result.add(null);

        browserHistory.visit("facebook.com");
        result.add(null);

        browserHistory.visit("youtube.com");
        result.add(null);

        result.add(browserHistory.back(1));
        result.add(browserHistory.back(1));
        result.add(browserHistory.forward(1));

        browserHistory.visit("linkedin.com");
        result.add(null);

        result.add(browserHistory.forward(2));
        result.add(browserHistory.back(2));
        result.add(browserHistory.back(7));

        return result;
    }

    public static void run() {
        System.out.println(example1());
    }

    private static class BrowserHistory {

        private final List<String> list;
        private int curr, last;

        public BrowserHistory(String homepage) {
            list = new ArrayList<>();
            list.add(homepage);
        }

        public void visit(String url) {
            curr++;
            if (list.size() > curr) list.set(curr, url);
            else list.add(url);
            last = curr;
        }

        public String back(int steps) {
            curr = Math.max(0, curr - steps);
            return list.get(curr);
        }

        public String forward(int steps) {
            curr = Math.min(curr + steps, last);
            return list.get(curr);
        }
    }
}
