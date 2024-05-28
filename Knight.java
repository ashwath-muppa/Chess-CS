// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Knight

import java.util.ArrayList;

/**
 * The Knight class represents a knight chess piece and extends the Piece class.
 */
public class Knight extends Piece {

    /**
     * Constructor for the Knight class.
     *
     * @param s The color of the knight ('W' for white, 'B' for black).
     * @param side The side of the board the knight starts on (0 or 1).
     * @param b The chess board represented as a 2D array.
     * @param o The list of other chess pieces on the board.
     * @param cap The panel displaying captured pieces.
     */
    public Knight(char s, int side, int[][] b, ArrayList<ChessPiece> o, CapturedPieces cap) {
        // Set initial position based on side and color
        x = (side == 0) ? 64 : 512 - 128;
        y = (s == 'W') ? 448 : 0;
        // Set file path for image based on color
        file = (s == 'W') ? "pieces/knight.png" : "pieces/knight1.png";
        // Set board, color, list of other pieces, type, and captured pieces panel
        color = s;
        board = b;
        otherPieces = o;
        type = "Knight";
        cappanel = cap;
    }

    /**
     * Constructor for the Knight class with specified position.
     *
     * @param x1 The x-coordinate of the knight.
     * @param y1 The y-coordinate of the knight.
     * @param s The color of the knight ('W' for white, 'B' for black).
     * @param b The chess board represented as a 2D array.
     * @param o The list of other chess pieces on the board.
     * @param cap The panel displaying captured pieces.
     */
    public Knight(int x1, int y1, char s, int[][] b, ArrayList<ChessPiece> o, CapturedPieces cap) {
        // Set position, image file, board, color, list of other pieces, type, and captured pieces panel
        x = x1;
        y = y1;
        file = (s == 'W') ? "pieces/knight.png" : "pieces/knight1.png";
        color = s;
        board = b;
        otherPieces = o;
        type = "Knight";
        cappanel = cap;
    }

    /**
     * Calculates the legal moves for the knight.
     *
     * @return A list of legal move coordinates.
     */
    public ArrayList<int[]> legalMoves() {
        // Initialize lists to store legal moves
        ArrayList<int[]> finlegal = new ArrayList<>();
        ArrayList<int[]> legal = new ArrayList<>();

        // Calculate current position of the knight on the board
        int tmX = (int) (Math.floor(getX() / 64));
        int tmY = (int) (Math.floor(getY() / 64));

        // Possible knight moves
        int[][] moves = {
            { tmX + 1, tmY + 2 }, { tmX + 1, tmY - 2 },
            { tmX - 1, tmY + 2 }, { tmX - 1, tmY - 2 },
            { tmX + 2, tmY + 1 }, { tmX + 2, tmY - 1 },
            { tmX - 2, tmY + 1 }, { tmX - 2, tmY - 1 }
        };

        // Check each possible move
        for (int[] tmp : moves) {
            if (tmp[0] >= 0 && tmp[0] < 8 && tmp[1] >= 0 && tmp[1] < 8) {
                if (board[tmp[1]][tmp[0]] != 1) {
                    tmp[0] *= 64;
                    tmp[1] *= 64;
                    legal.add(tmp);
                } else if (board[tmp[1]][tmp[0]] == 1) {
                    finlegal.add(tmp);
                }
            }
        }

        // Check if legal moves capture opponent's pieces
        for (int[] leg : finlegal) {
            for (ChessPiece k : otherPieces) {
                if (k.getY() == (leg[1] * 64) && k.getX() == (leg[0] * 64) && k.getColor() != getColor()) {
                    leg[0] *= 64;
                    leg[1] *= 64;
                    legal.add(leg);
                }
            }
        }

        // Return the list of legal moves for the knight
        return legal;
    }

    /**
     * Identifies pieces protected by the knight.
     *
     * @return A list of protected piece coordinates.
     */
    public ArrayList<int[]> protectedPieces() {
        // Initialize lists to store protected pieces
        ArrayList<int[]> finlegal = new ArrayList<>();
        ArrayList<int[]> legal = new ArrayList<>();

        // Calculate current position of the knight on the board
        int tmX = (int) (Math.floor(getX() / 64));
        int tmY = (int) (Math.floor(getY() / 64));

        // Possible knight moves
        int[][] moves = {
            { tmX + 1, tmY + 2 }, { tmX + 1, tmY - 2 },
            { tmX - 1, tmY + 2 }, { tmX - 1, tmY - 2 },
            { tmX + 2, tmY + 1 }, { tmX + 2, tmY - 1 },
            { tmX - 2, tmY + 1 }, { tmX - 2, tmY - 1 }
        };

        // Check each possible move
        for (int[] tmp : moves) {
            if (tmp[0] >= 0 && tmp[0] < 8 && tmp[1] >= 0 && tmp[1] < 8) {
                if (board[tmp[1]][tmp[0]] == 1) {
                    finlegal.add(tmp);
                }
            }
        }

        // Check if protected pieces belong to the same color
        for (int[] leg : finlegal) {
            for (ChessPiece k : otherPieces) {
                if (k.getY() == (leg[1] * 64) && k.getX() == (leg[0] * 64) && k.getColor() == getColor()) {
                    leg[0] *= 64;
                    leg[1] *= 64;
                    legal.add(leg);
                }
            }
        }

        // Return the list of protected pieces by the knight
        return legal;
    }
}