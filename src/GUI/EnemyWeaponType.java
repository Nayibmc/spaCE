package GUI;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class EnemyWeaponType {

    public abstract void draw(Graphics2D g);
    public abstract void update(double delta, Shields shiekds, Player player);

    public abstract boolean collision(Rectangle rect);
    public abstract boolean destroy();

    protected abstract void wallCollide(Shields shields);
    protected abstract void isOutofBounds();

    public abstract int getxPos();
    public abstract int getyPos();
}
