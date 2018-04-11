package ClientServer;

public interface Constantes {
    public static final int DECREMENT=-5;
    public static final int INCREMENT=5;
    public static final int SCREEN_X=800;
    public static final int SCREEN_Y=600;
    /*cantidad total de bloques con lo que trabajaremos*/
    public static final int TOTAL_BRICKS=64;
    /*cantidad total de bloques por columna*/
    public static final int COL_BRICK=4;
    /*cantidad total de bloques por fila*/
    public static final int ROW_BRICK=16;
    /*tamaño del bloque*/
    public static final int BRICK_SIZE=50;

    public static final int PLAYER_LENGHT=100;
    /*largo de la paleta del jugador en Y*/
    public static final int PLAYER_LENGHT_Y=20;

    public static final int INIT_POS_X_PLY=(SCREEN_X/2)-(PLAYER_LENGHT/2);
    /*posicion fija en Y*/
    public static final int POS_Y_PLY=SCREEN_Y-(PLAYER_LENGHT_Y+100);
    /*tamaño de la pelota*/
    public static final int BALL_SIZE=20;

    public static final int SI_MSG=1;

    public static final int NO_MSG=-1;

    public static final boolean DEBUG=true;

    public static final String LEFT="{\"id\": 0,\"action\":1}";
    public static final String RIGHT="{\"id\": 0,\"action\":2}";
    public static final String SHOT="{\"id\": 0,\"action\":3}";
    public static final String PLAYER_IMAGE="paleta.png";
    public static final String BALL_IMAGE="Master_Ball.png";
    public static final String TYPE_CONNECTION= "typeOfConnection";
    public static final String TERMINATE= "terminate";
    public static final String ID= "id";
    public static final String MOVE= "move";
    public static final String PLAYERS= "players";
    public static final String BRICK_HIT= "brickH";
    public static final String BRICKS= "bricks";
    public static final String BRICK_DEL= "del";
    public static final String SCORE="score";
    public static final String BALL_POS= "ballPos";
    public static final String POWER="power";
    public static final String POS= "pos";
}