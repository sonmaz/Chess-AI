package Client;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Sonmaz Zehtabi
 * Date: May 8, 2008
 * Time: 8:40:02 AM
 * To change template use File | Settings | File Templates.
 */
public class TestRunner {
    public static void main(String[] args) {
        try {
            Client n = new Player("client.properties") ;
            n.start() ;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }
}
