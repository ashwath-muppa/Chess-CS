// Arjun Garg and Ashwath Muppa
// Chess: 

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.lang.Math;

// Class to create and configure chess board display
public class ChessBoard extends JPanel{  
   // Variables declaration
   private char turn = 'W';   // Variable to keep track of whose turn it is ('W' for white, 'B' for black)
   public static final int FRAME = 512;   // Size of the frame
   private static final Color BACKGROUND = new Color(204, 204, 204);   // Background color of the chess board
   private BufferedImage myImage;
   private Graphics myBuffer;
   private Timer t;
   private ArrayList<BasicFunctions> animationObjects;    // List to store chess pieces
   private Color darker = new Color(118,150,86);   // Darker color for squares on the board
   private Color lighter = new Color(238,238,210);   // Lighter color for squares on the board
   
   // Constructor
   public ChessBoard(int[][] b){
      // Initialize image and buffer for drawing
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);

      // Initialize list for chess pieces
      animationObjects = new ArrayList<BasicFunctions>();

      // Add chess pieces to the list
      animationObjects.add(new King('B',b,animationObjects));
      animationObjects.add(new King('W',b,animationObjects));
      animationObjects.add(new Queen('B',b,animationObjects));
      animationObjects.add(new Queen('W',b,animationObjects));
      for(int i=0;i<8;i++){
         if(i<2){
            animationObjects.add(new Rook('W', i, b,animationObjects));
            animationObjects.add(new Rook('B', i, b,animationObjects));
            animationObjects.add(new Bishop('W', i, b,animationObjects));
            animationObjects.add(new Bishop('B', i, b,animationObjects));
            animationObjects.add(new Knight('W', i, b,animationObjects));
            animationObjects.add(new Knight('B', i, b,animationObjects));
         }
         animationObjects.add(new Pawn('W', i, b,animationObjects));
         animationObjects.add(new Pawn('B', i, b,animationObjects));
      }

      // Start timer for animation
      t = new Timer(5, new AnimationListener());
      t.start();
   }
   public void paintComponent(Graphics g){
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }

   // Methods to set different board colors
   public void setDarker(Color k){
      darker=k;
   }
   public void setLighter(Color k){
      lighter=k;
   }
   
   // Method to draw the board underneath
   public void drawBoard(){
      myBuffer.setColor(darker);
      myBuffer.fillRect(0,0,512,512);
      myBuffer.setColor(lighter);
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
   public void animate(){
      drawBoard();

      // Iterate through each chess piece and animate it
      for(BasicFunctions animationObject : animationObjects){
         animationObject.step();
         animationObject.drawMe(myBuffer);
      }        
      repaint();
   }
   // Updates the board, based on which piece is selected
   public int[] update(int x, int y){
      int [] fin = {0,0,0};
      System.out.println(turn);   // Prints whose turn it is
      for(BasicFunctions animationObject : animationObjects){  
         int pX = x-animationObject.getX();
         int pY = y-animationObject.getY();
         
            if(pX>0 && pX<64&&pY>0 && pY<64){
               if(animationObject.getColor()==turn){
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
      for(BasicFunctions animationObject : animationObjects){  
         int pX = animationObject.getX();
         int pY = animationObject.getY();
         if(pX==curx && pY==cury){
            ArrayList<int[]> moves = animationObject.legalMoves();
            for(int[] move : moves){
               int mX = x-move[0];
               int mY = y-move[1];
               if(mX>0&&mX<64&&mY>0&&mY<64){
                     animationObject.setMove((int)(Math.floor(x/64)*64), (int)(Math.floor(y/64)*64));
                     if(turn=='W'){
                        turn='B';
                     }else{
                        turn='W';
                     }
                     return true;
               }
            }
         }
      }
      return false;
   }

   // ActionListener for animation
   private class AnimationListener implements ActionListener{
      public void actionPerformed(ActionEvent e)
      {
         animate();
      }
   }
}
