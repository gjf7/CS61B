import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B{
    private Node root;
    private class Node {
        private boolean isKey;
        private HashMap<Character, Node> next;
        private Node(boolean isKey) {
            this.isKey = isKey;
            next = new HashMap<>();
        }

        private void clear() {
            next.clear();
        }
    }
    public MyTrieSet() {
        root = new Node(false);
    }
    @Override
    public void clear() {
        root.next.clear();
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.next.containsKey(c)) {
                curr.next.put(c, new Node(false));
            }
            curr = curr.next.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.next.containsKey(c)) {
                return false;
            }
            curr = curr.next.get(c);
        }
        return curr.isKey;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> ret = new ArrayList<>();
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            curr = curr.next.get(c);
        }
        collectHelper(prefix, ret, curr);
        return ret;
    }

    private void collectHelper(String prefix, List<String> v, Node p) {
        if (p.isKey) {
            v.add(prefix);
        }
        for (char c:p.next.keySet()) {
            collectHelper(prefix + c, v, p.next.get(c));
        }
    }
}
