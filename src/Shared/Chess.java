package Shared;

/**
 * Created by IntelliJ IDEA.
 * User: Shervin Alaei
 * Date: May 7, 2008
 * Time: 9:27:53 AM
 * To change template use File | Settings | File Templates.
 */
import java.util.*;


public class Chess {
	public static final char  WHITE_PAWN = 'P';
	public static final char	BLACK_PAWN = 'p';
	public static final char	WHITE_ROOK = 'R';
	public static final char	BLACK_ROOK = 'r';
	public static final char	WHITE_KNIGHT = 'N';
	public static final char	BLACK_KNIGHT = 'n';
	public static final char	WHITE_BISHOP = 'I';
	public static final char	BLACK_BISHOP = 'i';
	public static final char	WHITE_KING   = 'K';
	public static final char	BLACK_KING   = 'k';
	public static final char  WHITE_QUEEN  = 'Q';
	public static final char  BLACK_QUEEN  = 'q';
	public static final char  EMPTY        = 'X';

	public static final char WHITE	= 0;
	public static final char BLACK = 1;

	public static final char NOT_HIT = 0;
	public static final char HIT = 1;

	public static final char NOT_CHECK = 0;
	public static final char CHECK	 = 1;

	public static final char ILLEGAL = 0;
	public static final char LEGAL	  = 1;
    public  static final  int CHESS_STATE_STRING_LENGTH = 71;


    static LinkedList <ChessState> all_next_states = new LinkedList();

    static LinkedList <t_move> all_next_moves = new LinkedList<t_move>();

    public static void main(String[] args){
			t_board board = new t_board();
			t_board board2 = new t_board();

			board2.board[7][0] = 'k'; board2.board[7][1] = 'X'; board2.board[7][2] = 'X'; board2.board[7][3] = 'p'; board2.board[7][4] = 'X'; board2.board[7][5] = 'X'; board2.board[7][6] = 'X'; board2.board[7][7] = 'X';
			board2.board[6][0] = 'X'; board2.board[6][1] = 'X'; board2.board[6][2] = 'Q'; board2.board[6][3] = 'X'; board2.board[6][4] = 'X'; board2.board[6][5] = 'X'; board2.board[6][6] = 'X'; board2.board[6][7] = 'X';
			board2.board[5][0] = 'X'; board2.board[5][1] = 'X'; board2.board[5][2] = 'X'; board2.board[5][3] = 'X'; board2.board[5][4] = 'X'; board2.board[5][5] = 'X'; board2.board[5][6] = 'X'; board2.board[5][7] = 'X';
			board2.board[4][0] = 'X'; board2.board[4][1] = 'X'; board2.board[4][2] = 'X'; board2.board[4][3] = 'X'; board2.board[4][4] = 'X'; board2.board[4][5] = 'X'; board2.board[4][6] = 'X'; board2.board[4][7] = 'X';
			board2.board[3][0] = 'X'; board2.board[3][1] = 'X'; board2.board[3][2] = 'X'; board2.board[3][3] = 'X'; board2.board[3][4] = 'X'; board2.board[3][5] = 'X'; board2.board[3][6] = 'X'; board2.board[3][7] = 'X';
			board2.board[2][0] = 'X'; board2.board[2][1] = 'X'; board2.board[2][2] = 'X'; board2.board[2][3] = 'X'; board2.board[2][4] = 'X'; board2.board[2][5] = 'X'; board2.board[2][6] = 'X'; board2.board[2][7] = 'X';
			board2.board[1][0] = 'X'; board2.board[1][1] = 'p'; board2.board[1][2] = 'X'; board2.board[1][3] = 'X'; board2.board[1][4] = 'X'; board2.board[1][5] = 'X'; board2.board[1][6] = 'X'; board2.board[1][7] = 'X';
			board2.board[0][0] = 'K'; board2.board[0][1] = 'X'; board2.board[0][2] = 'X'; board2.board[0][3] = 'X'; board2.board[0][4] = 'X'; board2.board[0][5] = 'X'; board2.board[0][6] = 'X'; board2.board[0][7] = 'X';

			board.board[7][0] = 'k'; board.board[7][1] = 'X'; board.board[7][2] = 'X'; board.board[7][3] = 'X'; board.board[7][4] = 'X'; board.board[7][5] = 'X'; board.board[7][6] = 'X'; board.board[7][7] = 'X';
			board.board[6][0] = 'X'; board.board[6][1] = 'X'; board.board[6][2] = 'X'; board.board[6][3] = 'X'; board.board[6][4] = 'X'; board.board[6][5] = 'X'; board.board[6][6] = 'X'; board.board[6][7] = 'X';
			board.board[5][0] = 'X'; board.board[5][1] = 'X'; board.board[5][2] = 'X'; board.board[5][3] = 'X'; board.board[5][4] = 'X'; board.board[5][5] = 'X'; board.board[5][6] = 'X'; board.board[5][7] = 'X';
			board.board[4][0] = 'X'; board.board[4][1] = 'X'; board.board[4][2] = 'N'; board.board[4][3] = 'X'; board.board[4][4] = 'X'; board.board[4][5] = 'X'; board.board[4][6] = 'X'; board.board[4][7] = 'X';
			board.board[3][0] = 'X'; board.board[3][1] = 'X'; board.board[3][2] = 'X'; board.board[3][3] = 'X'; board.board[3][4] = 'X'; board.board[3][5] = 'X'; board.board[3][6] = 'X'; board.board[3][7] = 'X';
			board.board[2][0] = 'X'; board.board[2][1] = 'K'; board.board[2][2] = 'X'; board.board[2][3] = 'X'; board.board[2][4] = 'X'; board.board[2][5] = 'X'; board.board[2][6] = 'X'; board.board[2][7] = 'X';
			board.board[1][0] = 'X'; board.board[1][1] = 'X'; board.board[1][2] = 'X'; board.board[1][3] = 'X'; board.board[1][4] = 'X'; board.board[1][5] = 'X'; board.board[1][6] = 'X'; board.board[1][7] = 'X';
			board.board[0][0] = 'X'; board.board[0][1] = 'X'; board.board[0][2] = 'X'; board.board[0][3] = 'X'; board.board[0][4] = 'X'; board.board[0][5] = 'X'; board.board[0][6] = 'X'; board.board[0][7] = 'X';
			///System.out.println(board_difference(board,board2).move[0]+board_difference(board,board2).move[1]+"  "+board_difference(board,board2).move[2]+board_difference(board,board2).move[3]);
			//printBoard(board);

			t_move move = new t_move();

			move.move[0] = 'C';
			move.move[1] = '3';
			move.move[2] = 'C';
			move.move[3] = '2';

			//cout << (is_legal(&board, &move, BLACK)?"LEGAL":"ILLEGAL") << endl;

			ChessState my_state = new ChessState();

			my_state.board = board;
			my_state.check = NOT_CHECK;
			my_state.hit = NOT_HIT;
			my_state.last_move = null;
			//my_state.next = null;
			my_state.player = WHITE;

			//ChessState s_list[] = new ChessState[1000];

			ChessState s_list = next_states(my_state);
			System.out.println(all_next_states.size());
			for(int i=0;i<all_next_states.size();i++){
				ChessState ss = (ChessState)(all_next_states.get(i));
				printBoard(ss.board);
			}

	}

	static t_move board_difference(t_board old, t_board next){ //Returns the move that makes the transition, or NULL if not applicable
		if(old==null || next== null)
			return null;
		char i, j, r1 = 0, c1 = 0 , r2 = 0, c2 = 0, diff = 0;
		t_move result;
		for(i=0; i<8; ++i)
			for(j=0; j<8; ++j)
				if(old.board[i][j] != next.board[i][j]){
					++diff;
					if(diff>=3)
						return null;
					if(diff==1){
						r1 = i;
						c1 = j;
					} else {
						r2 = i;
						c2 = j;
					}
				}

		if(diff!=2)
			return null; //Sorry! no weird move, like that of pawns hitting their hind cells! (on pass on?) no "castle" move either!

		if(next.board[r1][c1]!=EMPTY && next.board[r2][c2]!=EMPTY || next.board[r1][c1]==EMPTY && next.board[r2][c2]==EMPTY) //Just one empty is needed!
			return null;

		result = new t_move();
		if(next.board[r1][c1]==EMPTY){ //From 1 to 2
			result.move[0] = (char)('A' + c1);
			result.move[1] = (char)('1' + r1);
			result.move[2] = (char)('A' + c2);
			result.move[3] = (char)('1' + r2);
			return result;
		}

		result.move[0] = (char)('A' + c2); //From 2 to 1
		result.move[1] = (char)('1' + r2);
		result.move[2] = (char)('A' + c1);
		result.move[3] = (char)('1' + r1);
		//System.out.println(result.move[0]+" "+result.move[1]+" "+result.move[2]+" "+result.move[3]);
		return result;
	}


	public static t_board make_move(t_board old, t_move move){ //Makes the move! doesn't check for legality.
		if(old== null || move==null)
			return null;
		t_board result = new t_board();
		int i, j;
		for(i=0; i<8; ++i)
			for(j=0; j<8; ++j)
				result.board[i][j] = old.board[i][j];

		result.board[move.move[3]-'1'][move.move[2]-'A'] = old.board[move.move[1]-'1'][move.move[0]-'A'];
		result.board[move.move[1]-'1'][move.move[0]-'A'] = EMPTY;
		return result;

	}


	static char is_a_move(t_move move){ //Is this a move principally?!
		if(move.move[0]<'A' || move.move[0]>'H' || move.move[2]<'A' || move.move[2]>'H' ||
			move.move[1]<'1' || move.move[1]>'8' || move.move[3]<'1' || move.move[3]>'8')
				return ILLEGAL; //Bad format
		if(move.move[0]==move.move[2] && move.move[1]==move.move[3])
			return ILLEGAL; //No move at all
		return LEGAL;
	}


	static char color(char piece){ //Returns its color
		if(piece == EMPTY)
			return (char)-1;
		if(piece <= 'Z')
			return WHITE;
		if(piece >= 'a')
			return BLACK;
		return (char)-1;
	}


	static char is_his_turn(t_board old, t_move move, char player){ //Checks if the right player moves a piece
		if(color(old.board[move.move[1]-'1'][move.move[0]-'A']) == WHITE && player == WHITE ||
			color(old.board[move.move[1]-'1'][move.move[0]-'A']) == BLACK && player == BLACK)
				return LEGAL;
		return ILLEGAL;
	}


	static char is_his_turn(t_board old, t_board next, char player){ //Checks if the right player moves a piece
		t_move move = board_difference(old, next);
		if((move) == null)
			return ILLEGAL;
		char result = is_his_turn(old, move, player);
		//delete move;
		return result;
	}


	static char is_legal_conventionally(t_board old, t_move move){ //Checks the move style of pieces
		int r1, c1, r2, c2;
		r1 = move.move[1]-'1';
		c1 = move.move[0]-'A';
		r2 = move.move[3]-'1';
		c2 = move.move[2]-'A';

		switch(old.board[r1][c1]){
			case WHITE_PAWN:
				if(Math.abs(c1-c2)>1)
					return ILLEGAL;
				if(Math.abs(c1-c2)==0){
					if(r2-r1 == 1)
						if(old.board[r2][c2]!=EMPTY)
							return ILLEGAL;
						else
							return LEGAL;
					if(r2-r1 == 2)
						if(r1==1 && old.board[r2][c2]!=EMPTY && old.board[r2-1][c2]!=EMPTY)
							return LEGAL;
					return ILLEGAL;
				}
				if(r2-r1 != 1 || color(old.board[r2][c2]) != BLACK)
					return ILLEGAL;
				return LEGAL;

			case BLACK_PAWN:
				if(Math.abs(c1-c2)>1)
					return ILLEGAL;
				if(Math.abs(c1-c2)==0){
					if(r2-r1 == -1)
						if(old.board[r2][c2]!=EMPTY)
							return ILLEGAL;
						else
							return LEGAL;
					if(r2-r1 == -2)
						if(r1==6 && old.board[r2][c2]!=EMPTY && old.board[r2+1][c2]!=EMPTY)
							return LEGAL;
					return ILLEGAL;
				}
				if(r2-r1 != -1 || color(old.board[r2][c2]) != WHITE)
					return ILLEGAL;
				return LEGAL;

			case WHITE_ROOK:
			case BLACK_ROOK:
				if(c1!=c2 && r1!=r2)
					return ILLEGAL;
				return LEGAL;

			case WHITE_KNIGHT:
			case BLACK_KNIGHT:
				if(Math.abs(c1-c2)==2 && Math.abs(r1-r2)==1 || Math.abs(c1-c2)==1 && Math.abs(r1-r2)==2)
					return LEGAL;
				return ILLEGAL;

			case WHITE_BISHOP:
			case BLACK_BISHOP:
				if(Math.abs(r1-r2)==Math.abs(c1-c2))
					return LEGAL;
				return ILLEGAL;

			case WHITE_KING:
			case BLACK_KING:
				if(Math.abs(r1-r2)<=1 && Math.abs(c1-c2)<=1)
					return LEGAL;
				return ILLEGAL;

			case WHITE_QUEEN:
			case BLACK_QUEEN:
				if(Math.abs(r1-r2)==Math.abs(c1-c2) || c1==c2 || r1==r2)
					return LEGAL;
				return ILLEGAL;
		}
		return ILLEGAL;
	}


	static char is_legal_conventionally(t_board old, t_board next){ //Checks the move style of pieces
		t_move move = board_difference(old, next);
		if(move==null)
			return ILLEGAL;
		char result = is_legal_conventionally(old, move);
		//delete move;
		return result;
	}


	static char is_legal_physically(t_board old, t_move move){ //Checks if the route is not barricaded
		int r1, c1, r2, c2, i, j, dr, dc;
		r1 = move.move[1]-'1';
		c1 = move.move[0]-'A';
		r2 = move.move[3]-'1';
		c2 = move.move[2]-'A';

		if(color(old.board[r1][c1])==color(old.board[r2][c2]))
			return ILLEGAL;

		switch(old.board[r1][c1]){
			case WHITE_PAWN:
			case BLACK_PAWN:
				return LEGAL;

			case WHITE_ROOK:
			case BLACK_ROOK:
				if(r1==r2){
					dc = c1 < c2 ? 1 : -1;
					for(i=c1+dc; i!=c2; i+=dc)
						if(old.board[r1][i]!=EMPTY)
							return ILLEGAL;
					return LEGAL;
				}
				dr = r1 < r2 ? 1 : -1;
				for(i=r1+dr; i!=r2; i+=dr)
					if(old.board[i][c1]!=EMPTY)
						return ILLEGAL;
				return LEGAL;

			case WHITE_KNIGHT:
			case BLACK_KNIGHT:
				return LEGAL;

			case WHITE_KING:
			case BLACK_KING:
				return LEGAL;

			case WHITE_QUEEN:
			case BLACK_QUEEN:
				if(c1==c2 || r1==r2){
					if(r1==r2){
						dc = c1 < c2 ? 1 : -1;
						for(i=c1+dc; i!=c2; i+=dc)
							if(old.board[r1][i]!=EMPTY)
								return ILLEGAL;
						return LEGAL;
					}
					dr = r1 < r2 ? 1 : -1;
					for(i=r1+dr; i!=r2; i+=dr)
						if(old.board[i][c1]!=EMPTY)
							return ILLEGAL;
					return LEGAL;
				}
			//Continues to bishops...

			case WHITE_BISHOP:
			case BLACK_BISHOP:
				dr = r1 < r2 ? 1 : -1;
				dc = c1 < c2 ? 1 : -1;
				for(i=r1+dr, j=c1+dc; i!=r2 && j!=c2; i+=dr, j+=dc)
					if(old.board[i][j]!=EMPTY)
						return ILLEGAL;
				return LEGAL;
		}
		return ILLEGAL;
	}


	static char is_legal_physically(t_board old, t_board next){ //Checks if the route is not barricaded
		t_move move = board_difference(old, next);
		if(move==null)
			return ILLEGAL;
		char result = is_legal_physically(old, move);
		//delete move;
		return result;
	}


	static char is_check_free(t_board old, t_move move, char player){ //Checks if the king is out of check position
		t_board next = make_move(old, move);
		if(next==null)
			return ILLEGAL;
		char result = is_check_free(old, next, player);
		//delete next;
		return result;
	}


	static char is_check_free(t_board old, t_board next, char player){ //Checks if the king is out of check position
		if(next==null)
			return ILLEGAL;
		int i, j, kr=-1, kc = 0, col;
		t_move move = new t_move();

		for(i=0; i<8; ++i)
			for(j=0; j<8; ++j)
				if(player==WHITE && next.board[i][j]==WHITE_KING || player==BLACK && next.board[i][j]==BLACK_KING){
					kr = i;
					kc = j;
					i = 8;
					break;
				}

		if(kr==-1)
			return ILLEGAL;

		move.move[2] = (char)('A' + kc);
		move.move[3] = (char)('1' + kr);
		for(move.move[0]='A'; move.move[0]<='H'; ++move.move[0])
			for(move.move[1]='1'; move.move[1]<='8'; ++move.move[1]){
				col = color(next.board[move.move[1]-'1'][move.move[0]-'A']);
				if(col != player && col != EMPTY &&
					is_legal_conventionally(next, move)== LEGAL && is_legal_physically(next, move) == LEGAL)//here I changed two "Illegal"s to "Legal"
						return ILLEGAL;
			}
		return LEGAL;
	}


    public static char is_legal(t_board old, t_move move, char player){ //Checks if the move is right overall
        if(old==null || move==null ||
            is_a_move(move)== ILLEGAL ||
            is_his_turn(old, move, player) == ILLEGAL ||
            is_legal_conventionally(old, move) == ILLEGAL ||
            is_legal_physically(old, move) == ILLEGAL||
            is_check_free(old, move, player) == ILLEGAL)
                return ILLEGAL;                                    //here I changed all LEGALs to ILLLEGAL
        return LEGAL;
    }


	static char is_legal(t_board old, t_board next, char player){ //Checks if the move is right overall
		t_move move = board_difference(old, next);
		if(move==null)
			return ILLEGAL;
		char result = is_legal(old, move, player);
		//delete move;
		return result;
	}


	static ChessState add_to_list(ChessState first, ChessState old, t_move move){ //Adds a new node to linked list of states
		//System.out.println("resid7");
		ChessState result = new ChessState();

		result.board = new t_board();
		for(char i=0;i<8;i++)
			for(char j=0;j<8;j++)
				result.board.board[i][j] = old.board.board[i][j];
		result.board.board[move.move[1]-'1'][move.move[0]-'A'] = EMPTY;
		result.board.board[move.move[3]-'1'][move.move[2]-'A'] = old.board.board[move.move[1]-'1'][move.move[0]-'A'];

		//System.out.println(old.board.board[move.move[1]-'1'][move.move[0]-'A']);
		//printBoard(result.board);
		//if(true)	System.exit(0);
		result.player = old.player==WHITE?BLACK:WHITE;

		result.check = (is_check_free(old.board, result.board, result.player)== ILLEGAL)?NOT_CHECK:CHECK;//I changed first argument from result.board to old.board
		result.hit = old.board.board[move.move[3]-'1'][move.move[2]-'A']==EMPTY?NOT_HIT:HIT;
		result.last_move = move;
		//result.next = new LinkedList();
		all_next_states.add(result);
		return result;
	}


	static ChessState next_states(ChessState old){ //Returns a linked list of available next states
		all_next_states.clear();
		ChessState result = null;
		t_move move = new t_move();
		char i, j;
		int k,m;
		int dr[] = {1, 1, 2, 2, -1, -1, -2, -2},
			dc[] = {2, -2, 1, -1,2, -2, 1, -1};

		for(i=0; i<8; ++i)
			for(j=0; j<8; ++j)
				if(color(old.board.board[i][j])==old.player){
					move.move[0] = (char)(j + 'A');
					move.move[1] = (char)(i + '1');
					switch(old.board.board[i][j]){
						case WHITE_PAWN:
						case BLACK_PAWN:
						//System.out.println("resid4");
							move.move[3] = (char)(move.move[1] + (old.player==WHITE?1:-1));
							for(k=-1; k<2; ++k){
								move.move[2] = (char)(move.move[0] + k);
								try{
									if(is_legal(old.board, move, old.player) == LEGAL)//here I changed ILLEGAL to LEGAL
										add_to_list(result, old, move);
								}catch(Exception e){System.err.println(e);}

							}
							move.move[3] = (char)(move.move[1] + (old.player==WHITE?2:-2));
							move.move[2] = move.move[0];
							try{
								if(is_legal(old.board, move, old.player)== LEGAL)//here I changed ILLEGAL to LEGAL
									add_to_list(result, old, move);
							}catch(Exception e){System.err.println(e);}

							break;

						case WHITE_QUEEN:
						case BLACK_QUEEN: //Continues...

						case WHITE_ROOK:
						case BLACK_ROOK:
						//System.out.println("resid3");
							move.move[2] = move.move[0];
							for(k=0; k<8; ++k){
								move.move[3] = (char)('1' + k);
								if(is_legal(old.board, move, old.player)== LEGAL)
									add_to_list(result, old, move);
							}

							move.move[3] = move.move[1];
							for(k=0; k<8; ++k){
								move.move[2] = (char)('A' + k);
								if(is_legal(old.board, move, old.player)== LEGAL)
									add_to_list(result, old, move);
							}

							if(old.board.board[i][j]!=WHITE_QUEEN && old.board.board[i][j]!=BLACK_QUEEN)
								break; //Queen continues...

						case WHITE_BISHOP:
						case BLACK_BISHOP:
						//System.out.println("resid2");
							for(k=-7; k<8; ++k){
								move.move[2] = (char)(move.move[0] + k);
								move.move[3] = (char)(move.move[1] + k);
								try{
									if(is_legal(old.board, move, old.player)== LEGAL)
										add_to_list(result, old, move);
								}catch(Exception e){System.err.println(e);}


								move.move[2] = (char)(move.move[0] + k);
								move.move[3] = (char)(move.move[1] - k);
								try{
									if(is_legal(old.board, move, old.player)== LEGAL)
										add_to_list(result, old, move);
								}catch(Exception e){System.err.println(e);}
							}

							break;

						case WHITE_KNIGHT:
						case BLACK_KNIGHT:
						//System.out.println("resid1");
							for(k=0; k<8; ++k){
								move.move[2] = (char)(move.move[0] + dc[k]);
								move.move[3] = (char)(move.move[1] + dr[k]);
								if(is_legal(old.board, move, old.player)== LEGAL)
									add_to_list(result, old, move);
							}

							break;

						case WHITE_KING:
						case BLACK_KING:
							//System.out.println("resid");
							for(k=-1; k<2; ++k)
								for(m=-1; m<2; ++m){

									move.move[2] = (char)(move.move[0] + k);
									move.move[3] = (char)(move.move[1] + m);
									//System.out.println("resid11");
									//printBoard(result.board);
									try{
									if(is_legal(old.board, move, old.player)== LEGAL)
										add_to_list(result, old, move);
									}catch(Exception e){System.err.println(e);}
								}

							break;
					}
				}
		return result;
	}

   public static LinkedList<t_move> next_moves(ChessState old) {
        all_next_moves.clear();
        next_states(old);
        for (ChessState next_state : all_next_states) {
            t_move nexMove = board_difference(old.board, next_state.board);
            all_next_moves.add(nexMove);
        }
        //System.out.println("here size of possibel moves = " + all_next_moves.size());
        return all_next_moves;
    }
public static void printBoard(t_board board){
	for(int i = 7; i>=0 ; i--){
		for (int j=0; j<8; j++){
			System.out.print(board.board[i][j]+" ");
		}
		System.out.println();
	}
	System.out.println();
}


}









