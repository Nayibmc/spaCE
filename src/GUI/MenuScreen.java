package GUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuScreen extends ScreensAbs implements KeyListener {

    private Font titleFont = new Font("Arial", Font.PLAIN, 64);
    private Font startFont = new Font("Arial", Font.PLAIN, 32);
    private String title = "Space Invaders";
    private String start = "Press Enter";

    public MenuScreen(Screens screen) {
        super(screen);
    }

    @Override
    public void update(double delta){
        // TODO Auto-generated method stub
    }

    @Override
    public void draw(Graphics2D g) {
        g.setFont(titleFont);
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.setColor(Color.yellow);
        g.drawString(title, ((mainWindow.WIDTH / 2) - (titleWidth / 2)) - 2, (mainWindow.HEIGHT / 2) - 123);
        g.setColor(Color.green);
        g.drawString(title, (mainWindow.WIDTH / 2) - (titleWidth / 2), (mainWindow.HEIGHT / 2) - 125);

        g.setFont(startFont);
        g.setColor(Color.white);
        int startWidth = g.getFontMetrics().stringWidth(start);
        g.drawString(start, (mainWindow.WIDTH / 2) - (startWidth / 2), (mainWindow.HEIGHT / 2) + 75);
    }

    @Override
    public void init(Canvas canvas) {
        canvas.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e){
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            getScreen().setScreen((byte) 1);
        }
    }

    @Override
    public void keyTyped(KeyEvent e){
        // TODO Auto-generated method stub
    }
}
