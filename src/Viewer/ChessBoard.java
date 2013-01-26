package Viewer;

import Shared.t_board;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Shervin Alaei
 * Date: May 8, 2008
 * Time: 7:37:25 AM
 * To change template use File | Settings | File Templates.
 */

import javax.swing.JFrame;

/**
 *
 * @author  root
 */
public class ChessBoard extends javax.swing.JFrame {
    Board echq;
    /** Creates new form ChessBoard */
    public ChessBoard() {
        super();
        initComponents();
       // this.setBounds(25, 25, 600, 400);
       // this.setTitle("ChessBoard");
       //  this.setPreferredSize(new java.awt.Dimension(600,400));
       //  this.setName("ChessBoard");
      //  this.setSize(600, 400);

         echq = new Board();
	    //Canvas canvas = echq;
            //add("West",canvas);
        add("West", echq);

        this.add(eastpanel, BorderLayout.EAST);
	    this.add(southpanel, BorderLayout.SOUTH);
            // southpanel.setPreferredSize(new java.awt.Dimension(600, 59));
           // eastpanel.setPreferredSize(new java.awt.Dimension(229, 370));

           // this.add(canvas, BorderLayout.WEST);
        this.add(echq, BorderLayout.WEST);

        //canvas.setPreferredSize(new java.awt.Dimension(330, 370));
        echq.setPreferredSize(new Dimension(495, 555));//330 , 370 ));
    }
    public void setInitialBoard(t_board initialBoard) {
        echq.setInitialBoard(initialBoard);
    }
    public void makeMove(String move)
    {
         echq.makeMove(move.toCharArray());
    }

    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        panel = new javax.swing.JPanel();
        eastpanel = new javax.swing.JPanel();
        southpanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Intercollegiate Chess Match - University of Tehran , Sharif University of Technology - Spring 2008");
        setName("ChessBoard");
        getAccessibleContext().setAccessibleParent(this);
        panel.setLayout(new java.awt.BorderLayout());

        eastpanel.setName("eastpanel");
        eastpanel.setPreferredSize(new java.awt.Dimension(229, 370));
        javax.swing.GroupLayout eastpanelLayout = new javax.swing.GroupLayout(eastpanel);
        eastpanel.setLayout(eastpanelLayout);
        eastpanelLayout.setHorizontalGroup(
            eastpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );
        eastpanelLayout.setVerticalGroup(
            eastpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
        );
        panel.add(eastpanel, java.awt.BorderLayout.EAST);

        southpanel.setName("southpanel");
        southpanel.setPreferredSize(new java.awt.Dimension(600, 59));
        javax.swing.GroupLayout southpanelLayout = new javax.swing.GroupLayout(southpanel);
        southpanel.setLayout(southpanelLayout);
        southpanelLayout.setHorizontalGroup(
            southpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        southpanelLayout.setVerticalGroup(
            southpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
        );
        panel.add(southpanel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panel, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-630)/2, (screenSize.height-441)/2, 630, 441);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       // java.awt.EventQueue.invokeLater(new Runnable() {
         //   public void run() {
               JFrame frame= new ChessBoard();
               frame.pack();
               frame.setVisible(true);

           // }
       // });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel eastpanel;
    public static javax.swing.JPanel panel;
    public static javax.swing.JPanel southpanel;
    // End of variables declaration//GEN-END:variables

}