// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Pawn

import java.util.ArrayList;
import java.lang.Math;

/**
 * The Pawn class represents a pawn chess piece and extends the Piece class.
 */
public class Pawn extends Piece {

    /**
     * Constructor for the Pawn class.
     *
     * @param s The color of the pawn ('W' for white, 'B' for black).
     * @param i The initial position of the pawn on the x-axis.
     * @param b The chess board represented as a 2D array.
     * @param o The list of other chess pieces on the board.
     * @param cap The panel displaying captured pieces.
     */
    public Pawn(char s, int i, int[][] b, ArrayList<ChessPiece> o, CapturedPieces cap) {
        x = i * 64;
        y = (s == 'W') ? 384 : 64;
        file = (s == 'W') ? "pieces/pawn.png" : "pieces/pawn1.png";
        color = s;
        board = b;
        otherPieces = o;
        type = "Pawn";
        cappanel = cap;
    }

    /**
     * Calculates the legal moves for the pawn.
     *
     * @return A list of legal move coordinates.
     */
    public ArrayList<int[]> legalMoves() {
        ArrayList<int[]> legal = new ArrayList<>();
        int tmX = (int)(Math.floor(getX() / 64));
        int tmY = (int)(Math.floor(getY() / 64));

        if (color == 'W') {
            // Check initial double-step move and single-step move
            if (getY() == 384) {
                if (board[tmY - 1][tmX] != 1) {
                    int[] curleg = { getX(), getY() - 64 };
                    legal.add(curleg);
                }
                if (board[tmY - 1][tmX] != 1) {
                    if (board[tmY - 2][tmX] != 1) {
                        int[] curleg1 = { getX(), getY() - 128 };
                        legal.add(curleg1);
                    }
                }
            } else {
                if (board[tmY - 1][tmX] != 1) {
                    int[] curleg = { getX(), getY() - 64 };
                    legal.add(curleg);
                }
            }

            // Check capture moves
            int[][] captures = { { tmX - 1, tmY - 1 }, { tmX + 1, tmY - 1 } };
            for (int[] leg : captures) {
                for (ChessPiece k : otherPieces) {
                    if (k.getY() == (leg[1] * 64) && k.getX() == (leg[0] * 64) && k.getColor() != getColor()) {
                        leg[0] *= 64;
                        leg[1] *= 64;
                        legal.add(leg);
                    }
                }
            }
        } else {
            // Check initial double-step move and single-step move
            if (getY() == 64) {
                if (board[tmY + 1][tmX] != 1) {
                    int[] curleg = { getX(), getY() + 64 };
                    legal.add(curleg);
                }
                if (board[tmY + 1][tmX] != 1) {
                    if (board[tmY + 2][tmX] != 1) {
                        int[] curleg1 = { getX(), getY() + 128 };
                        legal.add(curleg1);
                    }
                }
            } else {
                if (board[tmY + 1][tmX] != 1) {
                    int[] curleg = { getX(), getY() + 64 };
                    legal.add(curleg);
                }
            }

            // Check capture moves 
            int[][] captures = { { tmX - 1, tmY + 1 }, { tmX + 1, tmY + 1 } };
            for (int[] leg : captures) {
                for (ChessPiece k : otherPieces) {
                    if (k.getY() == (leg[1] * 64) && k.getX() == (leg[0] * 64) && k.getColor() != getColor()) {
                        leg[0] *= 64;
                        leg[1] *= 64;
                        legal.add(leg);
                    }
                }
            }
        }
        return legal;
    }

    /**
     * Identifies pieces protected by the pawn.
     *
     * @return An empty list as the logic for pawn protection is handled elsewhere.
     */
    public ArrayList<int[]> protectedPieces() {
        ArrayList<int[]> finlegal = new ArrayList<>();
        return finlegal;
    }
}
