// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Abstract class for pieces

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.*;

// Abstract class to be implemented by all board pieces
public abstract class Piece implements ChessPiece{
    //x and y of the piece
    protected int x;
    protected int y;
    //the desination that the pieces must reach
    protected int destY;
    protected int destX;
    //the speed of the x and y values
    protected int dY = 4;
    protected int dX = 4;
    //the file for the chess piece
    protected String file;
    //the piece is active (default starts from false)
    protected boolean active = false;
    //Color of the piece
    protected char color;
    //the piece is moving (default starts from false)
    protected boolean moving=false;
    //stores if there is a piece present in a position of the board or not
    protected int[][] board;
    //contains the array list of all the other pieces
    protected ArrayList<ChessPiece> otherPieces;
    //stores the type of the piece
    protected String type;

    protected CapturedPieces cappanel;

    //gets the state if the piece is active or not
    public boolean getActive(){
        return active;
    }
    //gets the type of piece
    public String getType(){
        return type;
    }
    //gets the current x value
    public int getX(){
        return x;
    }
    //gets the current y value
    public int getY(){
        return y;
    }
    //gets the change (speed) in y of the piece
    public int getdY(){
        return dY;
    }
    //gets the change (speed) in x of the piece
    public int getdX(){
        return dX;
    }
    //set the destination x value it needs to reach
    public void setDestX(int xValue){
        destX = xValue;
    }
    //set the destination y value it needs to reach
    public void setDestY(int yValue){
        destY = yValue;
    }
    //set the x value of the piece
    public void setX(int xValue){
        x = xValue;
    }
    //set the y value of the piece
    public void setY(int yValue){
        y = yValue;
    }
    //get the color of the piece, W for white and B for black
    public char getColor(){
        return color;
    }

    public String getFile(){
        return file;
    }

    //method to set move to x and y cords provided
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

    public void drawLegal(Graphics g){
        if(active){ // if piece is active, highlight legal moves
            g.setColor(new Color(0,255,0,128));
            g.fillRect(getX(), getY(),64,64);
            for(int[] k : legalMoves()){
                g.fillRect((int)(Math.floor(k[0]/64)*64), (int)(Math.floor(k[1]/64)*64),64,64);
            }
        }
    }

    public void drawMe(Graphics g)
    {  
      ImageIcon piece = new ImageIcon(file); // draw the piece
      g.drawImage(piece.getImage(),getX(), getY(), 64,64,null);
    }

    // activates and deactivates
    public void activate(){
        active = !active;
    }
    //animation
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

    //abstract classes that each type of piece defines for itself
    public abstract ArrayList<int[]> legalMoves();
    public abstract ArrayList<int[]> protectedPieces();

}