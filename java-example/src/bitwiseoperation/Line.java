package bitwiseoperation;

/**
 * Created by sunghyun on 2016. 12. 19..
 */
public class Line {
    Screen screen;

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void drawHorizontalLine(int sx, int ex, int y) {
        for (int i = sx; i <= ex; i++) {
            screen.setPoint(i, y);
        }
    }
}
