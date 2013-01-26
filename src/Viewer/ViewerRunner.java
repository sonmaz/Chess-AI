package Viewer;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Sonmaz Zehtabi
 * Date: May 7, 2008
 * Time: 2:08:59 PM
 * To change template use File | Settings | File Templates.
 */
public class ViewerRunner {
    public static void main(String[] args) {
        try {
            Viewer v = new Viewer("viewer.properties" , true);
            v.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
