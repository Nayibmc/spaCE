package GUI;

import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;

import ClientServer.Client;
import ClientServer.Constantes;
import org.json.simple.JSONObject;

//+++++++++++++++++++++++++++++++

public class mainWindow extends Canvas implements Runnable, Constantes{
    //Constantes y variables
    private static final long serialVersionUID = 1L;
    public static int WIDTH = 800, HEIGHT = 600;
    public int FPS;
    public static Screens screen;
    private boolean running = false;
    private Thread thread;

    //Socket////////////////////////////////////////////
    public static Client mp;
    public static int clientType;       //Para saber si es jugador o espectador

    //JSON con los datos del servidor
    public static JSONObject gameJSON = new JSONObject();

    //////////////////////////////~~~~~~~~~~~~~~~~~~~~~~~~MAIN~~~~~~~~~~~~~~~~~~~~~~~~////////////////////////////////
    public static void main(String[] args){
        //Se crea la ventana
        mainWindow display = new mainWindow();
        JFrame frame = new JFrame();
        frame.add(display);
        frame.pack();
        frame.setTitle("spaCEInvaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        display.start();    //Empieza la ejecución

        ////////////////////////////~~~~~~~~~~~~~~~~~~~~~~~~Socket~~~~~~~~~~~~~~~~~~~~~~~~//////////////////////////////
        mp = new Client(8080, "127.0.0.1");
        (new Thread(mp)).start();

        while (true){
            try{
                if (mp.ifNewMsg() == SI_MSG)
                    System.out.println(mp.getMsgFromServer());
                Thread.sleep(2000);
                mp.SendMsg(LEFT);
            }
            catch (InterruptedException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //Configuración de la pantalla
    public mainWindow(){
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(true);

        screen = new Screens(this, mp);
        screen.setScreen((byte) 0);
    }

    //Inicia el thread
    public synchronized void start(){
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    //Detiene el thread
    public synchronized void stop() {
        if (!running)
            return;

        running = false;
        try {
            thread.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Empieza la ejecuión
    @Override
    public void run(){
        long timer = System.currentTimeMillis();
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000/TARGET_FPS;
        int frames = 0;

        this.createBufferStrategy(3);
        BufferStrategy buffStrat = this.getBufferStrategy();

        //Manejo del tiempo y cuadros por segundo, para fluidez
        while (running){
            long currentTime = System.nanoTime();
            long updateLength = currentTime - lastLoopTime;
            lastLoopTime = currentTime;
            double delta = updateLength/((double) OPTIMAL_TIME);

            frames++;

            if (System.currentTimeMillis()-timer > 1000){
                timer += 1000;
                FPS = frames;
                frames = 0;
            }

            draw(buffStrat);
            update(delta);

            try{
                Thread.sleep(((lastLoopTime - System.nanoTime()) + OPTIMAL_TIME) / 1000000);
            }
            catch (Exception e){
            }
        }
    }

    //Dibujar la interfaz
    public void draw(BufferStrategy buffStrat) {
        do {
            do {
                Graphics2D g = (Graphics2D) buffStrat.getDrawGraphics();
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, WIDTH + 50, HEIGHT + 50);

                screen.draw(g);

                g.dispose();
            } while (buffStrat.contentsRestored());

            buffStrat.show();
        } while (buffStrat.contentsLost());
    }

    public void update(double delta){
        screen.update(delta);
    }
}