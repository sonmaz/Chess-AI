package Shared.Messages;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class SimpleParse {
    /**
     * Get the first paranthesis and extracts an integer vector in the extracted string.
     * @param s
     * @return ArrayList contsinig Integer values.
     */
    public static ArrayList<Integer> getFirstVector(String s) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        int indOpen , indClose ;
        String  head;
        StringTokenizer st ;
        int x ;
        indOpen = s.indexOf('(') ;
        indClose = s.indexOf(')') ;
        if (indClose == -1)
            return ret ;
        head = s.substring(indOpen + 1 , indClose ) ;
        st = new StringTokenizer(head , " ,");
        while (st.hasMoreTokens()) {
            x = Integer.parseInt(st.nextToken()) ;
            ret.add(x) ;
        }

        return ret ;

    }

    /**
     * Deletes first paranthesis from the begining of the string.
     * @param s
     * @return rest Of the string.
     */
    public static String deleteFirstVector(String s) {
        int indClose ;
        indClose = s.indexOf(')') ;
        if (indClose == -1)
            return "" ;
        return s.substring(indClose + 1) ;

    }
}
