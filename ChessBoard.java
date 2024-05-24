// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Chess board


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.lang.Math;
/**
* Class to create and configure the chess board display.
*/
public class ChessBoard extends JPanel{  
   // Variables declaration
   private char turn = 'W';   // Variable to keep track of whose turn it is ('W' for white, 'B' for black)
   public static final int FRAME = 512;   // Size of the frame
   private static final Color BACKGROUND = new Color(204, 204, 204);   // Background color of the chess board
   private BufferedImage myImage;
   private Graphics myBuffer;
   private Timer t;
   private ArrayList<ChessPiece> animationObjects;    // List to store chess pieces
   private Color darker = new Color(118,150,86);   // Darker color for squares on the board
   private Color lighter = new Color(238,238,210);   // Lighter color for squares on the board
   private String[] loaded;
   
   /**
   * Constructor for ChessBoard.
   *
   * @param b the initial board setup
   * @param cap the captured pieces panel
   * @param game the board of piece locations
   */
   public ChessBoard(int[][] b, CapturedPieces cap, String[] game){
      // Initialize image and buffer for drawing
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);

      // Initialize list for chess pieces
      animationObjects = new ArrayList<ChessPiece>();

      // Add chess pieces to the list
      animationObjects.add(new King('B',b,animationObjects, cap));
      animationObjects.add(new King('W',b,animationObjects,cap));
      animationObjects.add(new Queen('B',b,animationObjects,cap));
      animationObjects.add(new Queen('W',b,animationObjects,cap));
      for(int i=0;i<8;i++){
         if(i<2){
            animationObjects.add(new Rook('W', i, b,animationObjects,cap));
            animationObjects.add(new Rook('B', i, b,animationObjects,cap));
            animationObjects.add(new Bishop('W', i, b,animationObjects,cap));
            animationObjects.add(new Bishop('B', i, b,animationObjects,cap));
            animationObjects.add(new Knight('W', i, b,animationObjects,cap));
            animationObjects.add(new Knight('B', i, b,animationObjects,cap));
         }
         animationObjects.add(new Pawn('W', i, b,animationObjects,cap));
         animationObjects.add(new Pawn('B', i, b,animationObjects,cap));
      }

      loaded = game;

      // Start timer for animation
      t = new Timer(5, new AnimationListener());
      t.start();
   }
   /**
   * Draws the buffered image to the panel.
   *
   * @param g the graphics object
   */
   public void paintComponent(Graphics g){
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }

   public String getWinner(){
      if(turn=='W'){
         return loaded[1];
      }else{
         return loaded[0];
      }
   }

   /**
   * Sets the darker color for the board squares.
   *
   * @param k the darker color
   */
   public void setDarker(Color k){
      darker=k;
   }
   /**
   * Sets the lighter color for the board squares.
   *
   * @param k the lighter color
   */
   public void setLighter(Color k){
      lighter=k;
   }
   /**
   * Checks if there are exactly two kings on the board.
   *
   * @return true if there are two kings, false otherwise
   */
   public boolean twoKings(){
      int ctr = 0;
      for(ChessPiece animationObject : animationObjects){
         if(animationObject.getType().equals("King")){
            ctr++;
         }
      }   
      return ctr==2;
   }
   /**
   * Draws the chess board with alternating colors.
   */
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
   /**
   * Redraws the board and animates each piece.
   */
   public void animate(){
      //re draws board to account for if the user changes the theme midway
      drawBoard();
      for(ChessPiece animationObject : animationObjects){
         animationObject.drawLegal(myBuffer);
      }
      // Iterate through each chess piece and animate it
      for(ChessPiece animationObject : animationObjects){
         animationObject.step();
         animationObject.drawMe(myBuffer);
      }        
      repaint();
   }
   /**
   * Updates the board based on which piece is selected.
   *
   * @param x the x-coordinate of the selection
   * @param y the y-coordinate of the selection
   * @return an array with update status and new coordinates
   */
   public int[] update(int x, int y){
      int [] fin = {0,0,0};
      for(ChessPiece animationObject : animationObjects){  
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
   /**
   * Checks if the user inputted a legal move for that piece, and makes the move.
   *
   * @param x the x-coordinate of the target position
   * @param y the y-coordinate of the target position
   * @param curx the current x-coordinate of the piece
   * @param cury the current y-coordinate of the piece
   * @return true if the move is legal, false otherwise
   */
   public boolean legalMove(int x, int y, int curx, int cury){
      //iterates through all pieces
      for(ChessPiece animationObject : animationObjects){  
         //stores current pieces position
         int pX = animationObject.getX();
         int pY = animationObject.getY();
         //if there is a piece where the user clicked
         if(pX==curx && pY==cury){
            //get the legal moves for that piece
            ArrayList<int[]> moves = animationObject.legalMoves();
            //iterate through the moves for that piece
            for(int[] move : moves){
               //store the individual x and y values
               int mX = x-move[0];
               int mY = y-move[1];
               //if the values are in the square
               if(mX>0&&mX<64&&mY>0&&mY<64){
                     //set the move
                     animationObject.setMove((int)(Math.floor(x/64)*64), (int)(Math.floor(y/64)*64));
                     //change the turn
                     if(turn=='W'){
                        turn='B';
                     }else{
                        turn='W';
                     }
                     //return true if the piece has been set
                     return true;
               }
            }
         }
      }
      //return false if the piece has not been set
      return false;
    }  
    
   /**
   * ActionListener for animation.
   */
   private class AnimationListener implements ActionListener{
      /**
   * Handles the action event to animate the chess pieces.
   *
   * @param e the action event
   */
      public void actionPerformed(ActionEvent e)
      {
         animate();
      }
   }
    
   }


