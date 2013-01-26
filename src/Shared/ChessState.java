package Shared;


/**
 * Created by IntelliJ IDEA.
 * User: Sonmaz Zehtabi
 * Date: May 7, 2008
 * Time: 10:44:57 AM
 * To change template use File | Settings | File Templates.
 */


public class ChessState { 		//Keeps information of a state
    public t_board board;			//Keeps information of the board, above definitions are used
    public t_move last_move;		//The last move, for instance "C3C5"
    public char player;			// 0: It's white's turn, 1: It's black's turn
    public char hit;				// 0: no piece was hit, 1: a piece was hit
    public char check;				// 0: the king of current player is checked!, 1: it isn't!
    //LinkedList<ChessState> next = new LinkedList();		//Reserved for linked lists

    public ChessState() {
            t_board board = new t_board();

			board.board[7][0] = Chess.BLACK_KING; board.board[7][1] = 'X'; board.board[7][2] = 'X'; board.board[7][3] = 'X'; board.board[7][4] = 'r'; board.board[7][5] = 'X'; board.board[7][6] = 'X'; board.board[7][7] = 'X';
			board.board[6][0] = Chess.BLACK_PAWN; board.board[6][1] = Chess.BLACK_PAWN; board.board[6][2] = 'X'; board.board[6][3] = 'X'; board.board[6][4] = 'X'; board.board[6][5] = 'X'; board.board[6][6] = 'B'; board.board[6][7] = Chess.BLACK_ROOK;
			board.board[5][0] = 'X'; board.board[5][1] = 'X'; board.board[5][2] = 'X'; board.board[5][3] = 'X'; board.board[5][4] = 'X'; board.board[5][5] = 'X'; board.board[5][6] = 'X'; board.board[5][7] = 'X';
			board.board[4][0] = 'X'; board.board[4][1] = 'X'; board.board[4][2] = Chess.WHITE_KNIGHT; board.board[4][3] = Chess.BLACK_BISHOP; board.board[4][4] = 'X'; board.board[4][5] = 'X'; board.board[4][6] = 'X'; board.board[4][7] = 'X';
			board.board[3][0] = 'X'; board.board[3][1] = 'X'; board.board[3][2] = 'X'; board.board[3][3] = 'X'; board.board[3][4] = 'X'; board.board[3][5] = 'X'; board.board[3][6] = 'X'; board.board[3][7] = 'X';
			board.board[2][0] = 'X'; board.board[2][1] = Chess.WHITE_KING; board.board[2][2] = 'X'; board.board[2][3] = 'X'; board.board[2][4] = 'X'; board.board[2][5] = 'X'; board.board[2][6] = 'X'; board.board[2][7] = 'X';
			board.board[1][0] = Chess.WHITE_QUEEN; board.board[1][1] = 'X'; board.board[1][2] = 'X'; board.board[1][3] = 'X'; board.board[1][4] = 'X'; board.board[1][5] = 'X'; board.board[1][6] = Chess.BLACK_KING; board.board[1][7] = 'X';
			board.board[0][0] = 'X'; board.board[0][1] = 'X'; board.board[0][2] = 'X'; board.board[0][3] = 'X'; board.board[0][4] = 'X'; board.board[0][5] = 'X'; board.board[0][6] = 'X'; board.board[0][7] = 'X';

			this.board = board;
			check = Chess.NOT_CHECK;
			hit = Chess.NOT_HIT;
			last_move = new t_move();
            last_move.move[0] = '%';//% means this last_move is null
            last_move.move[1] = '%';
            last_move.move[2] = '%';
            last_move.move[3] = '%';

            //chessState.next = null;
			player = Chess.WHITE;
    }
    public static void showChars(char[] ch) {//just a function to test validity of send and received characters
        for(int i = 0; i < ch.length; i++) {
            System.out.print("char[" + i + "] = " + ch[i] + ", ");
            if(i % 8 == 7 )
                System.out.println("");
        }
        System.out.println("");
    }
    public String toString() {
        String ret = "";
        for (int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                ret += board.board[i][j];

        if(last_move != null)
            for(int i = 0; i < 4; i++)
                 ret += last_move.move[i];
        ret += player;
        ret += hit;
        ret += check;
        return ret;
    }
    public char[] toChar() {
        char[] c = new char[Chess.CHESS_STATE_STRING_LENGTH];
        for (int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                c[i * 8 + j] = board.board[i][j];

        if(last_move != null)
            for(int i = 0; i < 4; i++)
                 c[64 + i] = last_move.move[i];
        c[68] = player;
        c[69] = hit;
        c[70] = check;
        return c;
    }
    public boolean isFinished() {
        if(Chess.next_moves(this).size() == 0)
            return true;
        return false;   //todo: Need to specify a whole time limit for the game?(e.g. 3 minutes)
    }

    public void updateState(char[] data) {
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++)
               board.board[i][j] = data[i*8+j];//data.charAt(i * 8 + j);
        }
        for(int i = 0; i < 4; i++)
            last_move.move[i] = data[64+i];//data.charAt(64 + i);
        player = data[68];//.charAt(68);
        hit = data[69];//.charAt(69) ;
        check = data[70];//.charAt(70);
    }

    public t_move getLast_move() {
        return last_move;
    }

    public void applyMove(t_move playerMove) {
       t_board oldBoard = board;
       board = (Chess.make_move(board, playerMove));
       last_move = playerMove;
       player = ( player==Chess.WHITE?Chess.BLACK:Chess.WHITE);
       hit = oldBoard.board[playerMove.move[3]-'1'][playerMove.move[2]-'A']==Chess.EMPTY?Chess.NOT_HIT:Chess.HIT;
       check = (Chess.is_check_free(oldBoard, board, player)== Chess.ILLEGAL)?Chess.NOT_CHECK:Chess.CHECK;        
    }
}
