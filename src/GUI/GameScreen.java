package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GameScreen extends ScreensAbs{
    private Player player;
    private Shields shields;
    private Level1 level;
    private EnemyBulletHandler bulletHandler;

    public static int SCORE = 0;

    private Font gameScreen = new Font("Arial", Font.PLAIN, 48);
    private TickTimer gameOverTimer = new TickTimer(180);
    private TickTimer completeTimer = new TickTimer(180);

    public GameScreen(Screens screen){
        super(screen);
        shields = new Shields();
        bulletHandler = new EnemyBulletHandler();
        player = new Player(mainWindow.WIDTH/2-50, mainWindow.HEIGHT-75, 50, 50, shields);
        level = new Level1(player, bulletHandler);
    }

    @Override
    public void update(double delta){
        player.update(delta);
        level.update(delta, shields);

        if (level.isGameOver()){
            gameOverTimer.tick(delta);
            if (gameOverTimer.isEventReady()){
                level.reset();
                shields.reset();
                getScreen().setScreen((byte) 0);
                SCORE = 0;
            }
        }

        if (level.isComplete()){
            completeTimer.tick(delta);
            if (completeTimer.isEventReady()){
                level.reset();
            }
        }
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.white);
        g.drawString("Score: " + SCORE, 5, 15);

        g.setColor(Color.red);
        g.drawString("Health: " + player.getHealth(), 5, 35);

        shields.draw(g);
        player.draw(g);
        level.draw(g);

        if (level.isGameOver()){
            g.setColor(Color.red);
            g.setFont(gameScreen);
            String gameOver = "GAME OVER!";
            int gameOverWidth = g.getFontMetrics().stringWidth(gameOver);
            g.drawString(gameOver, (mainWindow.WIDTH/2)-(gameOverWidth/2), mainWindow.HEIGHT/2);
        }

        if (level.isComplete()){
            g.setColor(Color.green);
            g.setFont(gameScreen);
            String complete = "LEVEL COMPLETE!";
            int completeWidth = g.getFontMetrics().stringWidth(complete);
            g.drawString(complete, (mainWindow.WIDTH/2)-(completeWidth/2), mainWindow.HEIGHT/2);
        }
    }

    @Override
    public void init(Canvas canvas) {
        canvas.addKeyListener(player);
    }
}
