package GUI;

import java.awt.*;

public class EnemyBasicBullet extends EnemyWeaponType{
    private Rectangle bullet;
    private double speed = 2.5d;
    private int xPos, yPos;

    public EnemyBasicBullet(double xPos, double yPos) {
        bullet = new Rectangle((int) xPos, (int) yPos, 5, 5);
        setxPos((int) xPos);
        setyPos((int) yPos);
    }

    @Override
    public void draw(Graphics2D g) {
        if (bullet == null) {
            return;
        }

        g.setColor(Color.RED);
        g.fill(bullet);
    }

    @Override
    public void update(double delta, Shields shields, Player player) {
        if (bullet == null) {
            return;
        }

        setyPos((int) (getyPos() + (delta * speed)));
        bullet.y = getyPos();

        isOutofBounds();
        wallCollide(shields);
    }

    @Override
    public boolean collision(Rectangle rect) {
        if (bullet != null && bullet.intersects(rect)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean destroy() {
        return false;
    }

    @Override
    protected void wallCollide(Shields shields) {
        if (bullet == null) {
            return;
        }

        for (int w = 0; w < shields.wall.size(); w++) {
            if(bullet.intersects(shields.wall.get(w))) {
                shields.wall.remove(w);
                bullet = null;
                break;
            }
        }
    }

    @Override
    protected void isOutofBounds() {
        if(bullet != null && bullet.y < 0 || bullet.y > mainWindow.HEIGHT || bullet.x < 0 || bullet.x > mainWindow.WIDTH){
            bullet = null;
        }
    }

    public Rectangle getBullet() {
        return bullet;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
