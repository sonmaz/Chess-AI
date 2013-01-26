package Server;

import Shared.ChessState;
import Shared.t_move;
import Shared.Chess;
import Shared.Messages.ServerInitial;
import java.net.SocketTimeoutException;
import java.io.*;
import java.util.Properties;
import java.util.HashSet;
import java.util.LinkedList;
import java.net.Socket;
import java.net.ServerSocket;

/**
 * Created by IntelliJ IDEA.
 * User: Sonmaz Zehtabi
 * Date: May 4, 2008
 * Time: 6:40:38 AM
 * To change template use File | Settings | File Templates.
 */
public class Server {
    ChessState chessState;
    int timeOut;
    int listenPort;
    HashSet<OutputStreamWriter> viewers = new HashSet<OutputStreamWriter>();
    boolean whiteTurn;


    public Server(String fileName) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(fileName));
        timeOut = Integer.parseInt(prop.getProperty("Timeout"));
        listenPort = Integer.parseInt(prop.getProperty("Port"));
        initialChessState();
    }

    private void initialChessState() {
        chessState = new ChessState();
    }

    public void runPlayers() {
        try {
            BufferedReader whiteInStream , blackInStream;
            OutputStreamWriter whiteWriter , blackWriter;
            Socket whitePlayerSocket , blackPlayerSocket;
            ServerSocket server = new ServerSocket(listenPort);
            System.out.println("Listening");
            whitePlayerSocket = server.accept();
            System.out.println("White Accepted");
            blackPlayerSocket = server.accept();
            System.out.println("Black Accepted");

            whitePlayerSocket.setSoTimeout(timeOut);
            blackPlayerSocket.setSoTimeout(timeOut);

            whiteInStream = new BufferedReader(
                    new InputStreamReader(whitePlayerSocket.getInputStream()));
            blackInStream = new BufferedReader(
                    new InputStreamReader(blackPlayerSocket.getInputStream()));

            whiteWriter = new OutputStreamWriter(whitePlayerSocket.getOutputStream());
            blackWriter = new OutputStreamWriter(blackPlayerSocket.getOutputStream());


            whiteWriter.write((new ServerInitial(timeOut , Chess.WHITE)) + "\n");
            blackWriter.write((new ServerInitial(timeOut, Chess.BLACK)) + "\n");
            whiteWriter.flush();
            blackWriter.flush() ;
            whiteTurn = true;
            boolean gameTerminated = false ;

            while (!chessState.isFinished()) { 
                BufferedReader turnReader;
                OutputStreamWriter turnWriter;
                if (whiteTurn) {
                    System.out.println("White's turn...");
                    turnWriter = whiteWriter;
                    turnReader = whiteInStream;
                } else {
                    System.out.println("Black's turn...");
                    turnWriter = blackWriter;
                    turnReader = blackInStream;
                }
                //System.out.println("server-->sending chess state...");
                //System.out.println("server is sending this: ");ChessState.showChars(sendChessState().toCharArray());
                turnWriter.write(sendChessState() + "\n");
                turnWriter.flush();
                String action;
                char legal;
                try {
                    action = turnReader.readLine();
                    System.out.println("Action: " + action);
                    t_move playerMove = new t_move();
                    playerMove.setMove(action);
                    legal = Chess.is_legal(chessState.board, playerMove, chessState.player);
                    System.out.println("This action is: " + (legal == 1 ? "legal" : "illegal"));
                    if(legal == Chess.LEGAL)
                        chessState.applyMove(playerMove);

                } catch (SocketTimeoutException to) {
                    //TimeOut ;
                    System.out.println("Timeout happened. Choosing a random move for \""+
                            (whiteTurn ? "white" : "black") + "\" player");
                    choose_A_RandomMove();
                    legal = Chess.LEGAL;
                    //gameTerminated = true ;
                    //break ;
                }
                //System.out.println("Server->sendToAllViewers...");
                sendToAllViewers();
                if (legal != Chess.LEGAL) {
                    //Illegal move
                    System.out.println("Illegal move, Choosing a random move for \""+
                            (whiteTurn ? "white" : "black") + "\" player");
                    choose_A_RandomMove();
                    //gameTerminated = true ;
                    //break ;
                }
                whiteTurn = !whiteTurn ;

            }
            /*if (gameTerminated) //this is for when we want to terminate game, if an illegal move or time expiration happened
            {
                System.out.println("Game Terminated!");
                if (whiteTurn)
                    System.out.println("White player fouled!");
                else
                    System.out.println("Black player fouled!");
            } */

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }

    private void choose_A_RandomMove() {
        LinkedList<t_move> nextMoves = Chess.next_moves(chessState);
        t_move randomMove = nextMoves.get((int)Math.floor(Math.random() * nextMoves.size()));
        chessState.applyMove(randomMove);
    }

    private String sendChessState() {
        return chessState.toString();
    }

    private void sendToThisViewer(OutputStreamWriter osw) {
        System.out.println("writing initial state of chess to the new viewer...");
        try {
            osw.write(sendChessState() + "\n");
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendToAllViewers() {
        for (Object viewer : viewers) {
            OutputStreamWriter outputStreamWriter = (OutputStreamWriter) viewer;
            try {
                //System.out.println("writing chess state to viewer...");
                outputStreamWriter.write(sendChessState() + "\n");
                outputStreamWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use Options | File Templates.
            }
        }
    }
    public void acceptViewer() {
        try {
            ServerSocket ss = new ServerSocket(listenPort + 1);
            System.out.println("Listening for viewer");
            //noinspection InfiniteLoopStatement
            while (true) {
                Socket s = ss.accept() ;
                System.out.println("viewer accepted");
                OutputStreamWriter osw = new OutputStreamWriter (s.getOutputStream()) ;
                sendToThisViewer(osw);
                viewers.add(osw) ;
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }


    public void start() {
        Thread players = new Thread(new Runnable(){
            public void run() {
                runPlayers();
            }
        }) ;
        players.start();

        Thread viewers = new Thread(new Runnable() {
            public void run() {
                acceptViewer();
            }
        }) ;
        viewers.setDaemon(true);
        viewers.start();
    }
}
