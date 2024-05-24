// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Abstract class for pieces

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.*;

/**
* Abstract class representing a chess piece.
*/
public abstract class Piece implements ChessPiece{
    /** x-coordinate of the piece */
    protected int x;
    /** y-coordinate of the piece */
    protected int y;
    /** Destination y-coordinate */
    protected int destY;
    /** Destination x-coordinate */
    protected int destX;
    /** Speed in y-direction */
    protected int dY = 4;
    /** Speed in x-direction */
    protected int dX = 4;
    /** File path for the chess piece */
    protected String file;
    /** Flag indicating if the piece is active (default: false) */
    protected boolean active = false;
    /** Color of the piece ('W' for white, 'B' for black) */
    protected char color;
    /** Flag indicating if the piece is moving (default: false) */
    protected boolean moving=false;
    /** Board representation */
    protected int[][] board;
    /** List of other pieces */
    protected ArrayList<ChessPiece> otherPieces;
    /** Type of the piece */
    protected String type;
    /** Panel for captured pieces */
    protected CapturedPieces cappanel;

    /**
    * Retrieves the state if the piece is active or not.
    *
    * @return true if the piece is active, false otherwise
    */
    public boolean getActive(){
        return active;
    }
    /**
    * Retrieves the type of piece.
    *
    * @return the type of the piece
    */
    public String getType(){
        return type;
    }
    /**
    * Retrieves the current x-coordinate of the piece.
    *
    * @return the x-coordinate of the piece
    */
    public int getX(){
        return x;
    }
    /**
    * Retrieves the current y-coordinate of the piece.
    *
    * @return the y-coordinate of the piece
    */
    public int getY(){
        return y;
    }
    /**
    * Retrieves the change (speed) in y of the piece.
    *
    * @return the speed in y-direction
    */
    public int getdY(){
        return dY;
    }
    /**
    * Retrieves the change (speed) in x of the piece.
    *
    * @return the speed in x-direction
    */
    public int getdX(){
        return dX;
    }
    /**
    * Sets the destination x-coordinate.
    *
    * @param xValue the x-coordinate to set as destination
    */
    public void setDestX(int xValue){
        destX = xValue;
    }
    /**
    * Sets the destination y-coordinate.
    *
    * @param yValue the y-coordinate to set as destination
    */
    public void setDestY(int yValue){
        destY = yValue;
    }
    /**
    * Sets the x-coordinate of the piece.
    *
    * @param xValue the x-coordinate to set
    */
    public void setX(int xValue){
        x = xValue;
    }
    /**
    * Sets the y-coordinate of the piece.
    *
    * @param yValue the y-coordinate to set
    */
    public void setY(int yValue){
        y = yValue;
    }
    /**
    * Retrieves the color of the piece.
    *
    * @return the color of the piece ('W' for white, 'B' for black)
    */
    public char getColor(){
        return color;
    }
    /**
    * Retrieves the file path of the image representing the chess piece.
    *
    * @return the file path of the image
    */
    public String getFile(){
        return file;
    }

    /**
    * Sets the move to the provided x and y coordinates.
    *
    * @param x the x-coordinate to move to
    * @param y the y-coordinate to move to
    */
    public void setMove(int x, int y){
        //we dont know if piece captured anything yet so set it to false
        boolean captured = false;
        //check for captures
        for(ChessPiece piece : otherPieces){
            //if x and y values of another piece match the desination values
            if(piece.getX()==x&&piece.getY()==y){
                //set captured to true and remove the piece from the array list
                captured = true;
                cappanel.addPiece(piece);
                otherPieces.remove(piece);
                break;
                
            }
        }
        //set the desination values
        setDestX(x);
        setDestY(y);
        if(getType().equals("Pawn")){
                if(y==0 || y==512-64){
                    Object[] options = {"Knight", "Bishop", "Rook", "Queen"};
                    int choice = JOptionPane.showOptionDialog(null, "Select a piece to promote to", 
                    "You Reached the End!", 0, 3, null, options, null);

                    for(ChessPiece piece : otherPieces){
                        if(piece.getX()==getX()&&piece.getY()==getY()){
                            otherPieces.remove(piece);
                            break; 
                        }
                    }
                    switch (choice) {
                        case 0:
                            otherPieces.add(new Knight(x,y,getColor(),board,otherPieces, cappanel));
                            break;
                        case 1:
                            otherPieces.add(new Bishop(x,y,getColor(),board,otherPieces, cappanel));
                            break;
                        case 2:
                            otherPieces.add(new Rook(x,y,getColor(),board,otherPieces, cappanel));
                            break;
                        case 3:
                            otherPieces.add(new Queen(x,y,getColor(),board,otherPieces, cappanel));
                            break;
                    }   
                }
            }
        //the piece will move, so set the state at the current place to 0
        board[(int)(Math.floor(getY()/64))][(int)(Math.floor(getX()/64))]=0;
        //convert cords on panel to indexes
        int tpY = (int)(Math.floor(x/64));
        int tpX = (int)(Math.floor(y/64));
        //set the new place to 1
        board[tpX][tpY]=1;
                                                                //temporary debug code to print board state to terminal each move
                                                                System.out.println("\n");
                                                                for(int i=0;i<8;i++){
                                                                    for(int j=0;j<8;j++){
                                                                        System.out.print(board[i][j]); 
                                                                    }
                                                                    System.out.print("\n");
                                                                }
        //piece is no longer active
        active = false;
        //piece should now be moving towards destination
        moving = true;

        //play the audio depending on wether it captured a piece of not
        playAudio((captured)?("capture.wav"):("move.wav"));
      }
    /**
    * Plays audio based on the provided filename.
    *
    * @param filename the name of the audio file to play
    */
    public static void playAudio(String filename) {
    try {
        File audioFile = new File(filename); // opens file
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile); // convert to audio file

        Clip clip = AudioSystem.getClip(); // get audio clip
        clip.open(audioStream); // open audio clip
  
        clip.start(); // play clip
    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) { // catch any errors
        ex.printStackTrace(); // print errors
        }
    }
    /**
    * Draws legal moves on the graphics object.
    *
    * @param g the graphics object to draw on
    */
    public void drawLegal(Graphics g){
        if(active){ // if piece is active, highlight legal moves
            g.setColor(new Color(0,255,0,128));
            g.fillRect(getX(), getY(),64,64);
            for(int[] k : legalMoves()){
                g.fillRect((int)(Math.floor(k[0]/64)*64), (int)(Math.floor(k[1]/64)*64),64,64);
            }
        }
    }
    /**
    * Draws the chess piece on the graphics object.
    *
    * @param g the graphics object to draw on
    */
    public void drawMe(Graphics g)
    {  
      ImageIcon piece = new ImageIcon(file); // draw the piece
      g.drawImage(piece.getImage(),getX(), getY(), 64,64,null);
    }
    /**
    * Activates or deactivates the piece.
    */
    public void activate(){
        active = !active;
    }
    
    /**
    * Performs animation step for the piece.
    */
    public void step(){
        //if it has not reached destination yet
        if(moving){
            //if the desination is greater than cur y
            if(destY>y){
                  //increment y
                  setY(getY()+getdY());
            }else{ // if the desination is less than cur y

                  setY(getY()-getdY()); // decrement y
            }
            //if reached desination, set moving to false
            if(destX==x&&destY==y){
                  moving=false;
            }
            //same as y, but with x
            if(destX>x){
                  setX(getX()+getdX());
            }else if(destX<x){
                  setX(getX()-getdX());
            }
            if(destX==x&&destY==y){
                  moving=false;
            }
        }
    }

    /**
    * Abstract method to get the legal moves of the piece.
    *
    * @return list of legal moves
    */
    public abstract ArrayList<int[]> legalMoves();
    /**
    * Abstract method to get the protected pieces of the piece.
    *
    * @return list of protected pieces
    */
    public abstract ArrayList<int[]> protectedPieces();

}