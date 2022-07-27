import org.junit.Test;

import static org.junit.Assert.*;
public class FlikTest {
    @Test
    public void testIsSameNumber() {
        int i = 0,j = 0;
        while (i < 500) {
           assertTrue(Flik.isSameNumber(i++, j++));
        }
    }
}
