public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
           deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean palindromeHelper(Deque<Character> word) {
        if (word.isEmpty() || word.size() == 1) {
            return true;
        }
        Character first = word.removeFirst();
        Character last = word.removeLast();
        return first == last && palindromeHelper(word);
    }
    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return palindromeHelper(wordDeque);
    }

    private boolean palindromeHelper(Deque<Character> word, CharacterComparator cc) {
        if (word.isEmpty() || word.size() == 1) {
            return true;
        }
        Character first = word.removeFirst();
        Character last = word.removeLast();
        return cc.equalChars(first, last) && palindromeHelper(word, cc);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return palindromeHelper(wordDeque, cc);
    }
}