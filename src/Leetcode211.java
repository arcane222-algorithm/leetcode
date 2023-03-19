import java.util.*;


/**
 * Design Add and Search Words Data Structure - Leetcode211
 * -----------------
 * category: trie (트라이)
 * -----------------
 * Input 1
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * <p>
 * Output 1
 * [null,null,null,null,false,true,true,true]
 * <p>
 * Explanation 1
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * -----------------
 */
public class Leetcode211 {

    private static List<Boolean> example1() {
        List<Boolean> list = new LinkedList<>();

        WordDictionary wordDictionary = new WordDictionary();
        list.add(null);

        wordDictionary.addWord("bad");
        list.add(null);

        wordDictionary.addWord("dad");
        list.add(null);

        wordDictionary.addWord("mad");
        list.add(null);

        list.add(wordDictionary.search("pad"));
        list.add(wordDictionary.search("bad"));
        list.add(wordDictionary.search(".ad"));
        list.add(wordDictionary.search("b.."));

        return list;
    }

    public static void run() {
        System.out.println(example1());
    }


    private static class WordDictionary {

        private static class Trie {
            private static final int CHAR_SIZE = 26;
            private final Trie[] nodes;
            private boolean isEnd;

            public Trie() {
                nodes = new Trie[CHAR_SIZE];
            }

            public void add(String word) {
                Trie curr = this;
                for (int i = 0; i < word.length(); i++) {
                    int idx = word.charAt(i) - 'a';
                    Trie nxt = curr.nodes[idx];
                    if (nxt == null)
                        nxt = curr.nodes[idx]= new Trie();

                    curr = nxt;
                }
                curr.isEnd = true;
            }

            public boolean search(String word, int offset) {
                Trie curr = this;
                for (int i = offset; i < word.length(); i++) {
                    if (word.charAt(i) == '.') {
                        for (int j = 0; j < Trie.CHAR_SIZE; j++) {
                            if (curr.nodes[j] != null && curr.nodes[j].search(word, i + 1))
                                return true;
                        }
                        return false;
                    } else {
                        int idx = word.charAt(i) - 'a';
                        Trie nxt = curr.nodes[idx];
                        if (nxt == null) {
                            return false;
                        }

                        curr = nxt;
                    }
                }

                return curr.isEnd;
            }
        }

        private final Trie root;

        public WordDictionary() {
            root = new Trie();
        }

        public void addWord(String word) {
            root.add(word);
        }

        public boolean search(String word) {
            return root.search(word, 0);
        }
    }
}
