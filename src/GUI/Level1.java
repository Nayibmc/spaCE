package GUI;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Level1 implements SuperLevel{
    private Player player;
    private ArrayList<EnemyType> enemies = new ArrayList<EnemyType>();
    private EnemyBulletHandler bulletHandler;

    private Sound beep, boop;
    private boolean beepboop;

    public Level1(Player player, EnemyBulletHandler bulletHandler){
        this.player = player;
        this.bulletHandler = bulletHandler;
        addEnemies();

        beep = new Sound("/GUI/Resources/beep.wav");
        boop = new Sound("/GUI/Resources/boop.wav");
    }

    @Override
    public void draw(Graphics2D g){
        if(enemies == null)
            return;

        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).draw(g);
        }
        bulletHandler.draw(g);
    }

    @Override
    public void update(double delta, Shields shields) {
        if(enemies == null)
            return;

        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).update(delta, player, shields);
        }
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).collide(i, player, shields, enemies);
        }
        hasDirectionChange(delta);
        bulletHandler.update(delta, shields, player);
    }

    //Cambia la dirección de los enemigos si llegan a un borde lateral
    @Override
    public void hasDirectionChange(double delta){
        if(enemies == null)
            return;

        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).isOutOfBounds()){
                changeDirectionAllEnemys(delta);
            }
        }
    }

    //Cambia la dirección y reproduce un sonido
    @Override
    public void changeDirectionAllEnemys(double delta){
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).changeDirection(delta);
        }
        if (beepboop){
            beepboop = false;
            boop.play();
        }
        else{
            beepboop = true;
            beep.play();
        }
    }

    @Override
    public boolean isGameOver() {
        return player.getHealth() <= 0;
    }

    @Override
    public void destroy(){
        //////////////////////
    }

    @Override
    public void reset() {
        player.reset();
        enemies.clear();
        addEnemies();

        bulletHandler.reset();
    }

    public void addEnemies() {
        for(int y = 0; y < 5; y++){
            for(int x = 0; x < 10; x++){
                EnemyType e = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1 , 3, bulletHandler);
                enemies.add(e);
            }
        }
    }

    @Override
    public boolean isComplete() {
        return enemies.isEmpty();
    }
}