package GUI;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Screens{
    private ArrayList<ScreensAbs> screens = new ArrayList<ScreensAbs>();
    private Canvas canvas;
    private byte selectScreen = 0;

    public Screens(Canvas canvas){
        ScreensAbs game = new GameScreen(this);
        screens.add(game);
        ScreensAbs menu = new MenuScreen(this);
        screens.add(menu);

        this.canvas = canvas;
    }

    //Cambia de pantalla
    public void setScreen(byte i){
        for(int r = 0; r < canvas.getKeyListeners().length; r++)
            canvas.removeKeyListener(canvas.getKeyListeners()[r]);
        selectScreen = i;
        screens.get(selectScreen).init(canvas);
    }

    public byte getScreens(){
        return selectScreen;
    }

    public void draw(Graphics2D g){
        screens.get(selectScreen).draw(g);
    }

    public void update(double delta){
        screens.get(selectScreen).update(delta);
    }
}
