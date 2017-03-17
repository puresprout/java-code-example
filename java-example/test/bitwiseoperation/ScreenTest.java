package bitwiseoperation;

import org.junit.Test;

/**
 * Created by sunghyun on 2016. 12. 19..
 */
public class ScreenTest {
    @Test(expected = RuntimeException.class)
    public void drawLine() {
        Screen.createScreen(10, 10);
    }

    @Test
    public void drawLine2() {
        Screen screen = Screen.createScreen(8, 8);
        screen.drawHorizontalLine(6, 7, 0);
        screen.printScreen();
    }

    @Test
    public void drawLine3() {
        Screen screen = Screen.createScreen(32, 3);
        screen.drawHorizontalLine(1, 30, 2);
        screen.printScreen();
    }

    @Test
    public void drawLine4() {
        Screen screen = Screen.createScreen(32, 3);
        screen.drawHorizontalLine(3, 29, 1);
        screen.printScreen();
    }
}
