package GUI;

import org.json.simple.JSONObject;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import static ClientServer.Constantes.*;    //------------- ¿static?
import static GUI.mainWindow.mp;            //-------------

public class Player implements KeyListener{
    private final double speed = 5.0d;
    private int health;

    private BufferedImage pSprite;
    private Rectangle rect;
    private double xPos, yPos, startXPos, startYPos;
    private int width, height;
    private Shields shields;

    private boolean left = false, right = false, shoot = false;

    public PlayerWeapons playerWeapons;

    public Player(double xPos, double yPos, int width, int height, Shields shields){
        this.xPos = xPos;
        this.yPos = yPos;
        this.startXPos = xPos;
        this.startYPos = yPos;
        this.width = width;
        this.height = height;
        this.health = 5;

        rect = new Rectangle((int) xPos,(int) yPos+25, width, height-25);

        try{    //Se intenta seleccionar la imagen indicada para el jugador
            URL url = this.getClass().getResource("/GUI/Resources/Player.png");
            pSprite = ImageIO.read(url);
        }
        catch(IOException e){}

        this.shields = shields;
        playerWeapons = new PlayerWeapons();
    }

    public void draw(Graphics2D g){
        g.drawImage(pSprite,(int) xPos,(int) yPos, width, height, null);
        playerWeapons.draw(g);
    }

    //Actualiza la visualización del jugador -----------------------------------------------------------------------
    public void update(double delta){
        if(right && !left && xPos < mainWindow.WIDTH-width){
            xPos += speed * delta;
            rect.x = (int) xPos;
            mp.SendMsg(RIGHT);          /////////
        }if(!right && left && xPos > 10){
            xPos -= speed * delta;
            rect.x = (int) xPos;
            mp.SendMsg(LEFT);           /////////
        }

        playerWeapons.update(delta, shields);

        if(shoot){
            playerWeapons.shootBullet(xPos, yPos, 5, 5);
        }
    }
////////////////////////////////////////////////////////////Movimiento del jugador
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            right = true;
            //mp.SendMsg(RIGHT);
        }
        else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
            left = true;
            //mp.SendMsg(LEFT);
        }

        if (key == KeyEvent.VK_SPACE){
            shoot = true;
            //mp.SendMsg(SHOOT);
        }//////////////////¿Enviarlo sólo para activar y desactivar el movimiento?
    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            right = false;
        }else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
            left = false;
        }
        if (key == KeyEvent.VK_SPACE){
            shoot = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    public void hit() {
        setHealth(getHealth()-1);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        mp.SendMsg(TERMINATE);          ///////////////////////////////////////////////////////////////
    }

    public Rectangle getRect() {
        return rect;
    }

    public void reset(){
        health = 5;
        left = false;
        right = false;
        shoot = false;

        xPos = startXPos;
        yPos = startYPos;
        rect.x = (int) xPos;
        rect.y = (int) yPos+25;
        playerWeapons.reset();

        mp.SendMsg(TERMINATE);      ///////////////////////////////////////////////////////////////
    }
}
