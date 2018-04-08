package GUI;

import java.awt.Canvas;
import java.awt.Graphics2D;

public abstract class ScreensAbs{
    private Screens screen;

    public ScreensAbs(Screens screen) {
        this.screen = screen;
    }

    public abstract void update(double delta);
    public abstract void draw(Graphics2D g);
    public abstract void init(Canvas canvas);

    public Screens getScreen(){
        return screen;
    }
}
