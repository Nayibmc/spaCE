package ClientServer;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *	clase para conectarse contra el server de C para comunicarse entre lenguajes
 * @author ellioth el papi :V!!
 */
public class Client implements Runnable, Constantes{
    private int _port;
    private String _ip;
    private Socket _socket;
    private BufferedReader _inFromServer;
    private DataOutputStream _outToServer;
    private String _msgFromServer;
    private int _newMsg;

    /**
     * constructor de la clase, recibe el puerto donde se tiene que instanciar
     * y el ip a donde se tiene que conectar.
     * @param pPort dato tipo entero
     * @param pIp dato tipo String
     */
    public Client(int pPort, String pIp){
        _ip   = pIp;
        _port = pPort;
        try {
            if(DEBUG)
                System.out.println("conectando con server...");
            InetAddress ip = InetAddress.getByName(_ip);
            _socket = new Socket(ip, _port);
            if(DEBUG)
                System.out.println("conectado con server");
            _outToServer= new DataOutputStream(_socket.getOutputStream());
            _inFromServer= new BufferedReader (new InputStreamReader(_socket.getInputStream()));
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("error host desconocido");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("error IO");
        }
    }

    /**
     * metodo para enviarle un mensaje al server, se le ingresa el mensaje y
     * se resa para que llegue el mensaje y no se haya caido la parte de C.
     * @param pMensaje dato tipo String.
     */
    public void SendMsg( String pMensaje){
        try {
            if(DEBUG)
                System.out.println("enviando mensaje a server");
            _outToServer.write(pMensaje.getBytes());
            _outToServer.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * el hilo para estar ojo al cristo de los mensajes que manda el servidor.
     */
    @Override
    public void run(){
        String temp;
        try {
            if(DEBUG)
                System.out.println("esperando a leer desde server...");

            while(true){
                temp=_inFromServer.readLine();
                synchronized(this){
                    _msgFromServer=temp;
                    _newMsg=SI_MSG;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * metodo para hacer un pseudo observer y saber si llego un nuevo mensaje
     * de parte del servidor
     * @return retorna un dato tipo entero
     */
    public int ifNewMsg(){
        int temp;
        synchronized(this){
            temp=_newMsg;
        }
        return temp;
    }

    /**
     * metodo para obtener el nuevo mensaje del servidor.
     * @return retorna un dato tipo String
     */
    public String getMsgFromServer(){
        String temp;
        synchronized(this){
            temp=_msgFromServer;
            _newMsg=NO_MSG;
        }
        return temp;
    }

    /**
     * metodo para conocer el puerto al cual nos conectamos al servidor.
     * @return retorna un dato tipo entero.
     */
    public int getPort(){
        return _port;
    }

    /**
     * metodo para obtener el IP al que nos conectamos contra el server.
     * @return retorna un dato tipo string.
     */
    public String getIP(){
        return _ip;
    }
}
