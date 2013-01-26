package Viewer;

import Shared.ChessState;

import java.io.*;
import java.util.Properties;
import java.net.Socket;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Sonmaz Zehtabi
 * Date: May 7, 2008
 * Time: 2:08:24 PM
 * To change template use File | Settings | File Templates.
 */
public class Viewer {
    BufferedReader reader;
    OutputStreamWriter replayWriter ;
    boolean online ;
    int port;
    String serverIP;
    ChessState chessState = new ChessState();
    private ChessBoard chessBoard = new ChessBoard();


    public Viewer(String fileName , boolean onlineMode) throws IOException {
        online = onlineMode  ;
        Properties prop = new Properties();
        prop.load(new FileInputStream(fileName));
        port = Integer.parseInt(prop.getProperty("Port"));
        serverIP = prop.getProperty("Server", "localhost");
        String rFile  = prop.getProperty("SaveFile") ;
        if (onlineMode) {
            Socket s = new Socket(serverIP, port);
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            if (rFile != null)
                replayWriter =new OutputStreamWriter (
                        ( new FileOutputStream(rFile)));
        }
        else
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(rFile))) ;
    }

    public void show() {
        try {
            System.out.println("Showing");
            chessBoard.pack();
            chessBoard.setVisible(true);

//                    JPanel panel = new BoardViewer(board);
//                    chessBoard.getContentPane().add(panel);
//                    chessBoard.pack();
//                    chessBoard.setVisible(true);

            chessBoard.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            boolean first = true;
            //noinspection InfiniteLoopStatement
            while (true) {
                //System.out.println("reading...");
                String line = reader.readLine();
                //System.out.println("MSG :" + line);
                if (!online)
                {
                    System.out.println("Press a key to continue");
                    //noinspection ResultOfMethodCallIgnored
                    System.in.read() ;
                }
                if (replayWriter != null && online)  {
                    replayWriter.write(line + "\r\n");
                    replayWriter.flush();
                }
                chessState.updateState(line.toCharArray());
                if(first) {
                    chessBoard.setInitialBoard(chessState.board);
                    first = false;
                }
                else
                    chessBoard.makeMove(chessState.last_move.toString());//todo: you passed all things from chessState! just last_move was enough!!
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }

}
