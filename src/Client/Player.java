package Client;



import Shared.t_move;
import Shared.Chess;
import Shared.ChessState;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Sonmaz Zehtabi
 * Date: May 7, 2008
 * Time: 12:34:44 PM
 * To change template use File | Settings | File Templates.
 */
public class Player extends Client{

    public Player(String fileName) throws IOException {
        super(fileName);
    }

    public t_move doPlay(ChessState chessState) throws InterruptedException {
        Thread.sleep(3500) ;
        t_move t;
        LinkedList <t_move> nextMoves = Chess.next_moves(chessState);
        /*System.out.println("printing all possible moves:");
        for (t_move nextMove : nextMoves) {
            System.out.println(nextMove.toString());
        }*/
        t = nextMoves.get((int)Math.floor(Math.random() * nextMoves.size()));
        return t;
    }
}
