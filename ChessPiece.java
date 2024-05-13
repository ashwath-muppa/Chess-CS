// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Interface

import java.awt.*;
import java.util.ArrayList;

// Interface defining the properties and behaviors of a chess piece
public interface ChessPiece{
   public void step();   // Method to perform a step or move
   public char getColor();   // Method to get the color of the chess piece
   public void drawMe(Graphics g);   // Method to draw the chess piece on the graphics object
   public int getX();   // Method to get the x-coordinate of the chess piece
   public int getY();   // Method to get the y-coordinate of the chess piece
   public String getFile();   // Method to get the file path of the image representing the chess piece
   public void activate();   // Method to activate the chess piece
   public ArrayList<int[]> legalMoves();   // Method to get the list of legal moves for the chess piece
   public void setMove(int x, int y);   // Method to set the new position of the chess piece
   public String getType();   // Method to get the type of the chess piece (e.g., "Pawn", "Rook")
   public boolean getActive();   // Method to check if the chess piece is active
   public ArrayList<int[]> protectedPieces();   // Method to get the list of protected pieces by the chess piece
<<<<<<< HEAD
=======
   public void drawLegal(Graphics g);
>>>>>>> 84cb950463b084c1af2fa19a1cc7894023ced621
}