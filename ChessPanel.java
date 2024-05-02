import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ChessPanel extends JPanel
{
   private ChessBoard display;
   private boolean pieceActive;
   private int[][] piecesBoard = {{1,1,1,1,1,1,1,1},
                                  {1,1,1,1,1,1,1,1},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {1,1,1,1,1,1,1,1},
                                  {1,1,1,1,1,1,1,1}};

   //holds piece currently selected
   private int curMX;
   private int curMY;

   

   public ChessPanel()
   {
      //adds subpanel and main panel to JPanel
      setLayout(new BorderLayout());
      JPanel east = new JPanel();
      east.setPreferredSize(new Dimension(188,512));
      east.add(new JLabel("(DISCLAMER) You have to"));
      east.add(new JLabel("unselect a piece "));
      east.add(new JLabel("to select a new one."));
      add(east,BorderLayout.EAST);
      display = new ChessBoard(piecesBoard);
      display.setFocusable(true);
      add(display,BorderLayout.CENTER);
      display.addMouseListener(new Mouse());
      
   }
   //Recieves the mouse input
   private class Mouse extends MouseAdapter
   {
      public void mouseClicked(MouseEvent e)
      {  //If no piece has been selected yet
         if(!pieceActive){
            //updates the piece to highlight legal moves in green
            int[] cur = display.update(e.getX(),e.getY());      
            pieceActive = (cur[0]==1);
            curMX = cur[1];
            curMY = cur[2];
         //Assuming the a piece is already selected
         }else{
            int tmpX = e.getX()-curMX;
            int tmpY = e.getY()-curMY;
            //Checking if the same piece is clicked
            //if it is it will deactivate
            if(tmpX>0&&tmpX<64&&tmpY>0&&tmpY<64){
               display.update(e.getX(), e.getY());
               pieceActive = false;
            }
            //if the user clicks somewhere else and the move is legal to the piece
            //move to there and no more pieces are active now
             else if(display.legalMove(e.getX(), e.getY(), curMX, curMY)){
               pieceActive = false;
             }
         }
      }
   }
}
