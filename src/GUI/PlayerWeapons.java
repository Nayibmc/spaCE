package GUI;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class PlayerWeapons{
    private Timer timer;
    public ArrayList<PlayerWeaponType> weapons = new ArrayList<PlayerWeaponType>();
    private Sound shootSound;

    public PlayerWeapons(){
        timer = new Timer();
        shootSound = new Sound("/GUI/Resources/shoot.wav");
    }

    public void draw(Graphics2D g){
        for(int i = 0; i < weapons.size(); i++){
            weapons.get(i).draw(g);
        }
    }

    public void update(double delta, Shields shields){
        for(int i = 0; i < weapons.size(); i++){
            weapons.get(i).update(delta, shields);
            if(weapons.get(i).destroy()){
                weapons.remove(i);
            }
        }
    }

    public void shootBullet(double xPos, double yPos, int width, int height){
        if(timer.timerEvent(250)) {
            if (shootSound.isPlaying()) {
                shootSound.stop();
            }
            shootSound.play();
            weapons.add(new MachineGun(xPos + 22, yPos + 15, width, height));
        }
    }

    public void reset() {
        weapons.clear();
    }
}
