import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testGrow() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < 16; i++) {
            deque.addFirst("item");
        }
        assertEquals(32, deque.length);

        for (int i = 0; i < 16; i++) {
            deque.addLast("item");
        }
        assertEquals(64, deque.length);
    }

    @Test
    public void testShrink() {
        ArrayDeque<String> deque = new ArrayDeque<>();

        for (int i = 0; i < 32; i++) {
            deque.addFirst("item");
        }

        for (int i = 0; i < 26; i++) {
            deque.removeLast();
        }
        assertEquals(16, deque.length);
    }

    @Test
    public void testaddRemove() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < 16; i++) {
            deque.addFirst(i);
        }

        for (int i = 0; i < 16; i++) {
            assertEquals(Integer.valueOf(16 - i - 1), deque.get(i));
        }

        for (int i = 0; i < 16; i++) {
            deque.removeFirst();
        }

        assertEquals(0, deque.size());

        for (int i = 0; i < 16; i++) {
            deque.addLast(i);
        }

        for (int i = 0; i < 16; i++) {
            assertEquals(Integer.valueOf(i), deque.get(i));
        }

        for (int i = 0; i < 16; i++) {
            deque.removeLast();
        }

        assertEquals(0, deque.size());
    }
}
