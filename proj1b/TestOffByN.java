import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testEqualChars() {
        OffByN off = new OffByN(2);
        assertTrue(off.equalChars('a','c'));
        assertTrue(off.equalChars('b','d'));
        assertFalse(off.equalChars('a','a'));
    }
}
