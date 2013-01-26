package Shared;

/**
 * Created by IntelliJ IDEA.
 * User: Sonmaz Zehtabi
 * Date: May 7, 2008
 * Time: 12:46:24 PM
 * To change template use File | Settings | File Templates.
 */
public class t_move{ //The type for moves
    char move[] = new char[4];
    public String toString() {
        return new String(move);
    }
    public void setMove(String move) {
        this.move = move.toCharArray();
    }
    public void showMove() {
        System.out.print("This move is:");
        for (char c : move) {
            System.out.print(c);
        }
        System.out.println("");
    }
}
