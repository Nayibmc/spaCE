package GUI;

import java.awt.Graphics2D;

public interface SuperLevel {

    void draw(Graphics2D g);
    void update(double delta, Shields shields);
    void hasDirectionChange(double delta);
    void changeDurectionAllEnemys(double delta);

    boolean isGameOver();
    boolean isComplete();

    void destroy();
    void reset();
}