package Viewer;

import Shared.t_board;
import Shared.Chess;

import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: Shervin Alaei
 * Date: May 8, 2008
 * Time: 7:36:48 AM
 * To change template use File | Settings | File Templates.
 */


/**
 * Created by IntelliJ IDEA.
 * User: Shervin Alaei
 * Date: May 7, 2008
 * Time: 4:19:05 PM
 * To change template use File | Settings | File Templates.
 */
/*
//
//  LikeEternalAndRealNeurons (L.E.A.R.N)
//
//  Copyright (c) 2006 Gilles Bizet
//
//  This file is part of LikeEternalAndRealNeurons (L.E.A.R.N)
//
//  LikeEternalAndRealNeurons (L.E.A.R.N)
//  is free software; you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation; either version 2 of the License, or
//  (at your option) any later version.
//
//  LikeEternalAndRealNeurons (L.E.A.R.N)
//  is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//  You should have received a copy of the GNU General Public License
//  along with Foobar; if not, write to the Free Software
//  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
//
//*************************************************************************)
 */
//Thank you to the team of http://library.thinkquest.org/C001348/ for the first part
//of its tutorial (applet) - I suggest you to visit this website


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Board extends Canvas {
    public	int [] board = new int [120];
    //mode
//	boolean mode=false;

    Color claire = new Color(0x803636);
    Color sombre = new Color(0xDEDF93);
    Color rouge = new Color(0xCC0000);
    Color vert = new Color(0x009900);
    Color bleu = new Color(0x000099);

    Image[] pieces = new Image [18];
    URL[] piecesURL = new URL [18];
    int     code = 0, //coup legal = 0
            start = 21,
            alt = 21,
            end	= 21,
            x	= 0,
            y 	= 0;
    private static final int BRookIndex = 124;
    private static final int BKnightIndex = 22;
    private static final int BBishopIndex = 23;
    private static final int BQueenIndex = 25;
    private static final int BKingIndex = 126;
    private static final int BPawnIndex = 21;
    private static final int WPawnInex = 11;
    private static final int WRookIndex = 114;
    private static final int WKnightIndex = 12;
    private static final int WBishopIndex = 13;
    private static final int WQueenInex = 15;
    private static final int WKingIndex = 116;

    private static final int SIZE_OF_CELLS = 60;

    public Board(){
        super();
        //newgame();

        piecesURL [1] = getClass().getResource("/pieces/white_pawn.png");
        piecesURL [2] = getClass().getResource("/pieces/white_knight.png");
        piecesURL [3] = getClass().getResource("/pieces/white_bishop.png");
        piecesURL [4] = getClass().getResource("/pieces/white_rook.png");
        piecesURL [5] = getClass().getResource("/pieces/white_queen.png");
        piecesURL [6] = getClass().getResource("/pieces/white_king.png");

        piecesURL [WPawnInex] = getClass().getResource("/pieces/black_pawn.png");
        piecesURL [WKnightIndex] = getClass().getResource("/pieces/black_knight.png");
        piecesURL [WBishopIndex] = getClass().getResource("/pieces/black_bishop.png");
        piecesURL [14] = getClass().getResource("/pieces/black_rook.png");
        piecesURL [WQueenInex] = getClass().getResource("/pieces/black_queen.png");
        piecesURL [16] = getClass().getResource("/pieces/black_king.png");

        java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        pieces [1] = toolkit.getImage(piecesURL [1]);
        pieces [2] = toolkit.getImage(piecesURL [2]);
        pieces [3] = toolkit.getImage(piecesURL [3]);
        pieces [4] = toolkit.getImage(piecesURL [4]);
        pieces [5] = toolkit.getImage(piecesURL [5]);
        pieces [6] = toolkit.getImage(piecesURL [6]);

        pieces [WPawnInex] = toolkit.getImage(piecesURL [WPawnInex]);
        pieces [WKnightIndex] = toolkit.getImage(piecesURL [WKnightIndex]);
        pieces [WBishopIndex] = toolkit.getImage(piecesURL [WBishopIndex]);
        pieces [14] = toolkit.getImage(piecesURL [14]);
        pieces [WQueenInex] = toolkit.getImage(piecesURL [WQueenInex]);
        pieces [16] = toolkit.getImage(piecesURL [16]);


    }
    public void setInitialBoard(t_board initialBoard) {
        for ( int i = 21; i < 99; i++)//removing all pieces from the board
            board[i] = 00;
        for(int row = 0; row < 8; row++)
            for(int col = 0; col < 8 ; col++)
            {
                int index = convertCellIndex(row, col);
                switch(initialBoard.board[row][col]) {
                    case Chess.WHITE_PAWN:
                        board[index] = WPawnInex;
                        break;
                    case Chess.BLACK_PAWN:
                        board[index] = BPawnIndex;
                        break;
                    case Chess.WHITE_ROOK:
                        board[index] = WRookIndex;
                        break;
                    case Chess.BLACK_ROOK:
                        board[index] = BRookIndex;
                        break;
                    case Chess.WHITE_KNIGHT:
                        board[index] = WKnightIndex;
                        break;
                    case Chess.BLACK_KNIGHT:
                        board[index] = BKnightIndex;
                        break;
                    case Chess.WHITE_KING:
                        board[index] = WKingIndex;
                        break;
                    case Chess.BLACK_KING:
                        board[index] = BKingIndex;
                        break;
                    case Chess.WHITE_QUEEN:
                        board[index] = WQueenInex;
                        break;
                    case Chess.BLACK_QUEEN:
                        board[index] = BQueenIndex;
                        break;
                    case Chess.WHITE_BISHOP:
                        board[index] = WBishopIndex;
                        break;
                    case Chess.BLACK_BISHOP:
                        board[index] = BBishopIndex;
                        break;
                }
            }
        repaint();
    }
    public void makeMove(char[] move) {
        int start_column = move[0] - 'A';
        int start_row = move[1] - '1';
        int end_column = move[2] - 'A';
        int end_row = move[3] - '1';
        int start_cell = convertCellIndex(start_row, start_column);
        int end_cell = convertCellIndex(end_row, end_column);
        board[end_cell] = board[start_cell];
        board[start_cell] = 0;
        repaint();
    }

    private int convertCellIndex(int start_row, int start_column) {
        return 91 - 10 * start_row + start_column;
    }

    public void newgame() { //if you want to start the chess from the real start game(all nuts placed in their right places), call this
        //load start position
        int [] org = {  //
                99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
                99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
                99, BRookIndex, BKnightIndex, BBishopIndex, BQueenIndex, BKingIndex, BBishopIndex, BKnightIndex, BRookIndex, 99,
                99, BPawnIndex, BPawnIndex, BPawnIndex, BPawnIndex, BPawnIndex, BPawnIndex, BPawnIndex, BPawnIndex,	99,
                99,	00,	00,	00,	00,	00,	00,	00,	00,	99,
                99,	00,	00,	00,	00,	00,	00,	00,	00,	99,
                99,	00,	00,	00,	00,	00,	00,	00,	00,	99,
                99,	00,	00,	00,	00,	00,	00,	00,	00,	99,
                99, WPawnInex, WPawnInex, WPawnInex, WPawnInex, WPawnInex, WPawnInex, WPawnInex, WPawnInex,	99,
                99, WRookIndex, WKnightIndex, WBishopIndex, WQueenInex, WKingIndex, WBishopIndex, WKnightIndex, WRookIndex, 99,
                99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
                99,	99,	99,	99,	99,	99,	99,	99,	99,	99 };

        for (int i=0; i < 120; i++)
            board [i] = org [i];

        repaint();
    }
    public void paint(Graphics g) {
        for ( int i = 21; i < 99; i++) {
            paintField(i);
            if ( i%10 == 8)
                i += 2;
        }
    }
    public void paintField(int index) {

        Graphics g = getGraphics();


        int x = (index - 21) % 10;
        int y = (index - 21) / 10;


        if ( (x* 21 + y) % 2 == 0)
            g.setColor( sombre );
        else
            g.setColor( claire );

        g.fillRect( x * SIZE_OF_CELLS, y * SIZE_OF_CELLS, SIZE_OF_CELLS, SIZE_OF_CELLS);

        try {
            g.drawImage(pieces [board [index] % 100 - 10], x * SIZE_OF_CELLS, y * SIZE_OF_CELLS, SIZE_OF_CELLS, SIZE_OF_CELLS, this);
        } catch (ArrayIndexOutOfBoundsException e) {}


    }

//public void place (int start, int end)
//{
//	board [end] = board [start];
//
//	paintField (end);
//
//}
    /*   public void execute(int start, int end) {
      if (end != start) {
        board [end] = board [start];
        board [start] = 0;

        paintField(end);
        paintField(start);
      }
    }*/
//public void clearboard() {
//	//empty board
//	int [] org = {
//		99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
//		99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
//		99,	99,	99,	99,	99,	99,	99,	99,	99, 99,
//		99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
//		99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
//		99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
//		99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
//		99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
//		99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
//		99,	99,	99,	99,	99,	99,	99,	99,	99, 99,
//		99,	99,	99,	99,	99,	99,	99,	99,	99,	99,
//		99,	99,	99,	99,	99,	99,	99,	99,	99,	99 };
//
//	for (int i=0; i < 120; i++)
//		board [i] = org [i];
//
//	repaint ();
//
//}

}