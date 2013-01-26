package Shared.Messages;

import java.util.ArrayList;

/**
This is message for severinitial paramters sent to client.
 */
public class ServerInitial {   
    /**
     * Timeout of eaach turn. 0 means inifinity.
     */
    public int timeout ;
    /**
     * Color of player.
     */
    public int color ;

    public ServerInitial(int timeout, int color) {
        this.timeout = timeout;
        this.color = color;
    }

    public String toString() {
        return "(" + timeout + "," + color + ")"  ;
    }

    public static ServerInitial parseString(String str) {
        ArrayList l = SimpleParse.getFirstVector(str) ;

        int timeout = (Integer) l.get(0);
        int color = (Integer) l.get(1);
        return new ServerInitial(timeout , color) ;
    }
}
