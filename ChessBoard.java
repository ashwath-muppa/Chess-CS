import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.lang.Math;

public class ChessBoard extends JPanel
{  
   public static final int FRAME = 512;
   private static final Color BACKGROUND = new Color(204, 204, 204);
   private BufferedImage myImage;
   private Graphics myBuffer;
   private Timer t;
   private ArrayList<Animatable> animationObjects;
   public ChessBoard(int[][] b)
   { 
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);


      animationObjects = new ArrayList<Animatable>();
      animationObjects.add(new King('B'));
      animationObjects.add(new King('W'));
      animationObjects.add(new Queen('B',b));
      animationObjects.add(new Queen('W',b));
      for(int i=0;i<8;i++){
         if(i<2){
            animationObjects.add(new Rook('W', i, b));
            animationObjects.add(new Rook('B', i, b));
            animationObjects.add(new Bishop('W', i, b));
            animationObjects.add(new Bishop('B', i, b));
            animationObjects.add(new Knight('W', i, b));
            animationObjects.add(new Knight('B', i, b));
         }
         animationObjects.add(new Pawn('W', i, b));
         animationObjects.add(new Pawn('B', i, b));
      }

      t = new Timer(5, new AnimationListener());
      t.start();
   }
   
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }

   //Draws the board underneath
   public void drawBoard(){
      myBuffer.setColor(new Color(118,150,86));
      myBuffer.fillRect(0,0,512,512);
      myBuffer.setColor(new Color(238,238,210));
      for(int i=0;i<512;i=i+128){
         for(int j=0;j<512;j=j+128){
            myBuffer.fillRect(i,j,64,64);
         }
      }
      for(int i=64;i<512;i=i+128){
         for(int j=64;j<512;j=j+128){
            myBuffer.fillRect(i,j,64,64);
         }
      }
   }
   //Draws the board and each piece
   public void animate()
   {
      drawBoard();

      for(Animatable animationObject : animationObjects)
      {
         animationObject.step();
         animationObject.drawMe(myBuffer);
      }        
      repaint();
   }
   //Updates the board, based on which piece is selected
   public int[] update(int x, int y){
      int [] fin = {0,0,0};
      for(Animatable animationObject : animationObjects)
      {  
         int pX = x-animationObject.getX();
         int pY = y-animationObject.getY();
         if(pX>0 && pX<64){
            if(pY>0 && pY<64){
               animationObject.activate();
               fin[0]=1;
               fin[1]=animationObject.getX();
               fin[2]=animationObject.getY();
               return fin;
            }
         }
      }
      return fin;
   }
   //Checks if user inputted legal move for that piece, and makes move if legal
   //returns a boolean so that the panel can set its field accordingly
   public boolean legalMove(int x, int y, int curx, int cury){
      for(Animatable animationObject : animationObjects)
      {  
         int pX = animationObject.getX();
         int pY = animationObject.getY();
         if(pX==curx && pY==cury){
            ArrayList<int[]> moves = animationObject.legalMoves();
            for(int[] move : moves){
               int mX = x-move[0];
               int mY = y-move[1];
               if(mX>0&&mX<64&&mY>0&&mY<64){
                     animationObject.setMove((int)(Math.floor(x/64)*64), (int)(Math.floor(y/64)*64));
                     return true;
               }
            }
         }
      }
      return false;
    }
  
   private class AnimationListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         animate();
      }
   }
   
}
