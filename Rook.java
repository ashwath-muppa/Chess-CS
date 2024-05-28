// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Rook

import java.util.ArrayList;

/**
 * The Rook class represents a rook chess piece and extends the Piece class.
 */
public class Rook extends Piece {

    /**
     * Constructor for the Rook class.
     *
     * @param s The color of the rook ('W' for white, 'B' for black).
     * @param side The side of the board the rook starts on (0 or 1).
     * @param b The chess board represented as a 2D array.
     * @param o The list of other chess pieces on the board.
     * @param cap The panel displaying captured pieces.
     */
    public Rook(char s, int side, int[][] b, ArrayList<ChessPiece> o, CapturedPieces cap) {
        // Set initial position based on side and color
        x = (side == 0) ? 0 : 448;
        y = (s == 'W') ? 448 : 0;
        // Set file path for image based on color
        file = (s == 'W') ? "pieces/rook.png" : "pieces/rook1.png";
        // Set board, color, list of other pieces, type, and captured pieces panel
        board = b;
        color = s;
        otherPieces = o;
        type = "Rook";
        cappanel = cap;
    }

    /**
     * Constructor for the Rook class with specified position.
     *
     * @param x1 The x-coordinate of the rook.
     * @param y1 The y-coordinate of the rook.
     * @param s The color of the rook ('W' for white, 'B' for black).
     * @param b The chess board represented as a 2D array.
     * @param o The list of other chess pieces on the board.
     * @param cap The panel displaying captured pieces.
     */
    public Rook(int x1, int y1, char s, int[][] b, ArrayList<ChessPiece> o, CapturedPieces cap) {
        // Set position, image file, board, color, list of other pieces, type, and captured pieces panel
        x = x1;
        y = y1;
        file = (s == 'W') ? "pieces/rook.png" : "pieces/rook1.png";
        board = b;
        color = s;
        otherPieces = o;
        type = "Rook";
        cappanel = cap;
    }

    /**
     * Calculates the legal moves for the rook.
     *
     * @return A list of legal move coordinates.
     */
    public ArrayList<int[]> legalMoves() {
        // Initialize lists to store legal moves
        ArrayList<int[]> finlegal = new ArrayList<>();
        ArrayList<int[]> legal = new ArrayList<>();

        // Calculate current position of the rook on the board
        int tmX = (int) (Math.floor(getX() / 64));
        int tmY = (int) (Math.floor(getY() / 64));

        // Check legal moves horizontally (right)
        for (int i = tmX + 1; i < 8; i++) {
            if (board[tmY][i] == 1) {
                int[] curLegal = { i * 64, tmY * 64 };
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = { i * 64, tmY * 64 };
            legal.add(curLegal);
        }

        // Check legal moves horizontally (left)
        for (int i = tmX - 1; i >= 0; i--) {
            if (board[tmY][i] == 1) {
                int[] curLegal = { i * 64, tmY * 64 };
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = { i * 64, tmY * 64 };
            legal.add(curLegal);
        }

        // Check legal moves vertically (up)
        for (int i = tmY - 1; i >= 0; i--) {
            if (board[i][tmX] == 1) {
                int[] curLegal = { tmX * 64, i * 64 };
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = { tmX * 64, i * 64 };
            legal.add(curLegal);
        }

        // Check legal moves vertically (down)
        for (int i = tmY + 1; i < 8; i++) {
            if (board[i][tmX] == 1) {
                int[] curLegal = { tmX * 64, i * 64 };
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = { tmX * 64, i * 64 };
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

        // Return the list of legal moves for the rook
        return legal;
    }

    /**
     * Identifies pieces protected by the rook.
     *
     * @return A list of protected piece coordinates.
     */
    public ArrayList<int[]> protectedPieces() {
        // Initialize lists to store protected pieces
        ArrayList<int[]> finlegal = new ArrayList<>();
        ArrayList<int[]> legal = new ArrayList<>();

        // Calculate current position of the rook on the board
        int tmX = (int) (Math.floor(getX() / 64));
        int tmY = (int) (Math.floor(getY() / 64));

        // Check protected pieces horizontally (right)
        for (int i = tmX + 1; i < 8; i++) {
            if (board[tmY][i] == 1) {
                int[] curLegal = { i * 64, tmY * 64 };
                finlegal.add(curLegal);
                break;
            }
        }

        // Check protected pieces horizontally (left)
        for (int i = tmX - 1; i >= 0; i--) {
            if (board[tmY][i] == 1) {
                int[] curLegal = { i * 64, tmY * 64 };
                finlegal.add(curLegal);
                break;
            }
        }

        // Check protected pieces vertically (up)
        for (int i = tmY - 1; i >= 0; i--) {
            if (board[i][tmX] == 1) {
                int[] curLegal = { tmX * 64, i * 64 };
                finlegal.add(curLegal);
                break;
            }
        }

        // Check protected pieces vertically (down)
        for (int i = tmY + 1; i < 8; i++) {
            if (board[i][tmX] == 1) {
                int[] curLegal = { tmX * 64, i * 64 };
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

        // Return the list of protected pieces by the rook
        return finlegal;
    }
}