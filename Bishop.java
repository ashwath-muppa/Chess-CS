// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Bishop

import java.util.ArrayList;

/**
 * The Bishop class represents a bishop chess piece and extends the Piece class.
 */
public class Bishop extends Piece {
   
    /**
     * Constructor for the Bishop class.
     *
     * @param s The color of the bishop ('W' for white, 'B' for black).
     * @param side The side of the board the bishop starts on (0 for one side, 1 for the other).
     * @param b The chess board represented as a 2D array.
     * @param o The list of other chess pieces on the board.
     * @param cap The panel displaying captured pieces.
     */
    public Bishop(char s, int side, int[][] b, ArrayList<ChessPiece> o, CapturedPieces cap) {
        x = (side == 0) ? 128 : 320;
        y = (s == 'W') ? 448 : 0;
        file = (s == 'W') ? "pieces/bishop.png" : "pieces/bishop1.png";
        color = s;
        board = b;
        otherPieces = o;
        type = "Bishop";
        cappanel = cap;
    }

    /**
     * Constructor for the Bishop class with specified position.
     *
     * @param x1 The x-coordinate of the bishop.
     * @param y1 The y-coordinate of the bishop.
     * @param s The color of the bishop ('W' for white, 'B' for black).
     * @param b The chess board represented as a 2D array.
     * @param o The list of other chess pieces on the board.
     * @param cap The panel displaying captured pieces.
     */
    public Bishop(int x1, int y1, char s, int[][] b, ArrayList<ChessPiece> o, CapturedPieces cap) {
        x = x1;
        y = y1;
        file = (s == 'W') ? "pieces/bishop.png" : "pieces/bishop1.png";
        color = s;
        board = b;
        otherPieces = o;
        type = "Bishop";
        cappanel = cap;
    }

    /**
     * Calculates the legal moves for the bishop.
     *
     * @return A list of legal move coordinates.
     */
    public ArrayList<int[]> legalMoves() {
        ArrayList<int[]> finlegal = new ArrayList<>();
        ArrayList<int[]> legal = new ArrayList<>();

        int tmX = (int)(Math.floor(getX() / 64));
        int tmY = (int)(Math.floor(getY() / 64));

        // Check legal moves diagonally (up-right)
        for (int i = 1; i < 8; i++) {
            if ((tmX + i > 7 || tmY - i < 0) || (board[tmY - i][tmX + i] == 1)) {
                int[] curLegal = { (tmX + i) * 64, (tmY - i) * 64 };
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = { (tmX + i) * 64, (tmY - i) * 64 };
            legal.add(curLegal);
        }

        // Check legal moves diagonally (up-left)
        for (int i = 1; i < 8; i++) {
            if ((tmX - i < 0 || tmY - i < 0) || (board[tmY - i][tmX - i] == 1)) {
                int[] curLegal = { (tmX - i) * 64, (tmY - i) * 64 };
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = { (tmX - i) * 64, (tmY - i) * 64 };
            legal.add(curLegal);
        }

        // Check legal moves diagonally (down-right)
        for (int i = 1; i < 8; i++) {
            if ((tmX + i > 7 || tmY + i > 7) || (board[tmY + i][tmX + i] == 1)) {
                int[] curLegal = { (tmX + i) * 64, (tmY + i) * 64 };
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = { (tmX + i) * 64, (tmY + i) * 64 };
            legal.add(curLegal);
        }

        // Check legal moves diagonally (down-left)
        for (int i = 1; i < 8; i++) {
            if ((tmX - i < 0 || tmY + i > 7) || (board[tmY + i][tmX - i] == 1)) {
                int[] curLegal = { (tmX - i) * 64, (tmY + i) * 64 };
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = { (tmX - i) * 64, (tmY + i) * 64 };
            legal.add(curLegal);
        }

        // Check if legal moves capture opponent's pieces
        for (int[] leg : finlegal) {
            for (ChessPiece k : otherPieces) {
                if (k.getY() == leg[1] && k.getX() == leg[0] && k.getColor() != getColor()) {
                    legal.add(leg);
                }
            }
        }

        // Return the list of legal moves for the bishop
        return legal;
    }

    /**
     * Identifies pieces protected by the bishop.
     *
     * @return A list of coordinates of protected pieces.
     */
    public ArrayList<int[]> protectedPieces() {
        ArrayList<int[]> finlegal = new ArrayList<>();
        ArrayList<int[]> legal = new ArrayList<>();

        int tmX = (int)(Math.floor(getX() / 64));
        int tmY = (int)(Math.floor(getY() / 64));

        // Check protected pieces diagonally (up-right)
        for (int i = 1; i < 8; i++) {
            if ((tmX + i > 7 || tmY - i < 0) || (board[tmY - i][tmX + i] == 1)) {
                int[] curLegal = { (tmX + i) * 64, (tmY - i) * 64 };
                finlegal.add(curLegal);
                break;
            }
        }

        // Check protected pieces diagonally (up-left)
        for (int i = 1; i < 8; i++) {
            if ((tmX - i < 0 || tmY - i < 0) || (board[tmY - i][tmX - i] == 1)) {
                int[] curLegal = { (tmX - i) * 64, (tmY - i) * 64 };
                finlegal.add(curLegal);
                break;
            }
        }

        // Check protected pieces diagonally (down-right)
        for (int i = 1; i < 8; i++) {
            if ((tmX + i > 7 || tmY + i > 7) || (board[tmY + i][tmX + i] == 1)) {
                int[] curLegal = { (tmX + i) * 64, (tmY + i) * 64 };
                finlegal.add(curLegal);
                break;
            }
        }

        // Check protected pieces diagonally (down-left)
        for (int i = 1; i < 8; i++) {
            if ((tmX - i < 0 || tmY + i > 7) || (board[tmY + i][tmX - i] == 1)) {
                int[] curLegal = { (tmX - i) * 64, (tmY + i) * 64 };
                finlegal.add(curLegal);
                break;
            }
        }

        // Check if protected pieces belong to the same color
        for (int[] leg : finlegal) {
            for (ChessPiece k : otherPieces) {
                if (k.getY() == leg[1] && k.getX() == leg[0] && k.getColor() == getColor()) {
                    legal.add(leg);
                }
            }
        }

        // Return the list of protected pieces by the bishop
        return finlegal;
    }
}