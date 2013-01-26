package Client;

import Shared.ChessState;
import Shared.t_move;
import Shared.Chess;
import Shared.Messages.ServerInitial;

import java.io.*;
import java.util.Properties;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: Sonmaz Zehtabi
 * Date: May 5, 2008
 * Time: 9:24:57 PM
 * To change template use File | Settings | File Templates.
 */
public abstract class Client extends Thread{
     /**
     * Contains board information.
     */
    public ChessState chessState;
    /**
     * IP address or host name of server.
     */
    String server ;
    /**
     * Listeninig port of server.
     */
    private int port;
    /**
     * Initial parameters sent from server to client.
     */
    ServerInitial init;

    public Client(int color) {
        init  = new  ServerInitial(100 , color);
        init.color = color ;
        chessState = new ChessState();
    }

    public Client(String fileName) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(fileName);
        prop.load(fis) ;
        server = prop.getProperty("Server" , "localhost") ;
        port = Integer.parseInt(prop.getProperty("Port") ) ;
        fis.close();
    }

    /**
     * The core functionality of a client. this method is
     * called whenever it's turn of the player.
     * @return  player's move
     */
    public abstract t_move doPlay(ChessState chessState) throws InterruptedException;

    public void run() {
        try {
            Socket socket = new Socket(server , port) ;
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            Writer writer = new OutputStreamWriter(socket.getOutputStream()) ;

            String line = reader.readLine() ;
            System.out.println("MSG :" + line);
            init = ServerInitial.parseString(line);
            if (init.color == Chess.WHITE)
                System.out.println("I'm White palyer");
            else
                System.out.println("I'm Black player") ;
            chessState = new ChessState();

            //noinspection InfiniteLoopStatement
            do {
                String msg = reader.readLine();
                //System.out.println("Client Received chess state :");ChessState.showChars(msg.toCharArray());
                chessState.updateState(msg.toCharArray());
                //System.out.println("writing to server...");
                String myMove = doPlay(chessState).toString();
                System.out.println("My move is: " + myMove);
                writer.write(myMove + "\n");
                writer.flush();
            } while (true) ;


        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
