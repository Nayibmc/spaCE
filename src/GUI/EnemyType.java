package GUI;

import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class EnemyType {

    private EnemyBulletHandler bulletHandler;

    public EnemyType(EnemyBulletHandler bulletHandler) {
        this.bulletHandler = bulletHandler;
    }

    public abstract void draw(Graphics2D g);
    public abstract void update(double delta, Player player, Shields shields);
    public abstract void changeDirection(double delta);

    public abstract boolean deathScene();
    public abstract boolean collide(int i, Player player, Shields shields, ArrayList<EnemyType> enemys);
    public abstract boolean isOutOfBounds();

    public EnemyBulletHandler getBulletHandler() {
        return bulletHandler;
    }
}
