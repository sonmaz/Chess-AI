package Server;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Sonmaz Zehtabi
 * Date: May 4, 2008
 * Time: 6:42:35 AM
 * To change template use File | Settings | File Templates.
 */
public class ServerRunner {
     public static void main(String[] args) {
        try {
            Server s = new Server("server.properties") ;
            s.start();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }
}
