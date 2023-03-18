import java.util.*;


/**
 * Implement Trie (Prefix Tree) - Leetcode208
 * -----------------
 * category: trie (트라이)
 * -----------------
 * Input 1
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * <p>
 * Output 1
 * [null, null, true, false, true, null, true]
 * <p>
 * Explanation 1
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 * -----------------
 */
public class Leetcode208 {

    private static List<Boolean> example1() {
        List<Boolean> list = new LinkedList<>();
        Trie trie = new Trie();

        trie.insert("apple");
        list.add(null);

        list.add(trie.search("apple"));
        list.add(trie.search("app"));
        list.add(trie.startsWith("app"));

        trie.insert("app");
        list.add(null);

        list.add(trie.search("app"));

        return list;
    }

    public static void run() {
        System.out.println(example1());
    }


    private static class Trie {
        private final Trie[] nodes;
        boolean isEnd;

        public Trie() {
            nodes = new Trie[26];
        }

        public void insert(String word) {
            Trie curr = this;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (curr.nodes[idx] == null) {
                    curr.nodes[idx] = new Trie();
                }
                curr = curr.nodes[idx];
            }
            curr.isEnd = true;
        }

        public boolean search(String word) {
            Trie curr = this;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (curr.nodes[idx] == null) {
                    return false;
                }
                curr = curr.nodes[idx];
            }

            return curr.isEnd;
        }

        public boolean startsWith(String prefix) {
            Trie curr = this;
            for (int i = 0; i < prefix.length(); i++) {
                int idx = prefix.charAt(i) - 'a';
                if (curr.nodes[idx] == null) {
                    return false;
                }
                curr = curr.nodes[idx];
            }

            return true;
        }
    }
}
