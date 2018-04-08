package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public class MachineGun extends PlayerWeaponType{
    private Rectangle bullet;
    private final double speed = 2.5d;

    public MachineGun(double xPos, double yPos, int width,int height){
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setWidth(width);
        this.setHeight(height);

        this.bullet = new Rectangle((int) getxPos(),(int) getyPos(), getWidth(), getHeight());
    }

    @Override
    public void draw(Graphics2D g) {
        if(bullet == null)
            return;

        g.setColor(Color.GREEN);
        g.fill(bullet);
    }

    @Override
    public void update(double delta, Shields shields) {
        if(bullet == null)
            return;

        this.setyPos(getyPos() - (delta * speed));
        bullet.y = (int) this.getyPos();
        wallCollide(shields);
        isOutofBounds();
    }

    @Override
    public boolean collisionRect(Rectangle rect) {
        if(this.bullet == null)
            return false;

        if(bullet.intersects(rect)){
            this.bullet = null;
            return true;
        }

        return false;
    }

    @Override
    public boolean collisionPoly(Polygon poly) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean destroy() {
        if(bullet == null)
            return true;

        return false;
    }

    @Override
    protected void wallCollide(Shields shields) {
        for(int i = 0; i < shields.wall.size(); i++){
            if(bullet.intersects(shields.wall.get(i))){
                shields.wall.remove(i);
                bullet = null;
                return;
            }
        }
    }

    @Override
    protected void isOutofBounds() {
        if(this.bullet == null)
            return;

        if(bullet.y < 0 || bullet.y > mainWindow.HEIGHT || bullet.x < 0 || bullet.x > mainWindow.WIDTH){
            bullet = null;
        }
    }
}
