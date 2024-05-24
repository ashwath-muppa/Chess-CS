// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Interface

/**
* Interface defining the properties and behaviors of a chess piece.
*/

import java.awt.*;
import java.util.ArrayList;

// Interface defining the properties and behaviors of a chess piece
public interface ChessPiece{
   /**
   * Performs a step or move.
   */
   public void step();
   /**
   * Gets the color of the chess piece.
   *
   * @return the color of the chess piece
   */
   public char getColor();
   /**
   * Draws the chess piece on the graphics object.
   *
   * @param g the graphics object
   */
   public void drawMe(Graphics g);
   /**
   * Gets the x-coordinate of the chess piece.
   *
   * @return the x-coordinate of the chess piece
   */
   public int getX();
   /**
   * Gets the y-coordinate of the chess piece.
   *
   * @return the y-coordinate of the chess piece
   */
   public int getY();
   /**
   * Gets the file path of the image representing the chess piece.
   *
   * @return the file path of the image
   */
   public String getFile();
   /**
   * Activates the chess piece.
   */
   public void activate();
   /**
   * Gets the list of legal moves for the chess piece.
   *
   * @return the list of legal moves
   */
   public ArrayList<int[]> legalMoves();
   /**
   * Sets the new position of the chess piece.
   *
   * @param x the new x-coordinate
   * @param y the new y-coordinate
   */
   public void setMove(int x, int y);
   /**
   * Gets the type of the chess piece (e.g., "Pawn", "Rook").
   *
   * @return the type of the chess piece
   */
   public String getType();
   /**
   * Checks if the chess piece is active.
   *
   * @return true if the chess piece is active, false otherwise
   */
   public boolean getActive();
   /**
   * Gets the list of pieces protected by the chess piece.
   *
   * @return the list of protected pieces
   */
   public ArrayList<int[]> protectedPieces();
   /**
   * Draws legal moves on the graphics object.
   *
   * @param g the graphics object
   */
   public void drawLegal(Graphics g);
}