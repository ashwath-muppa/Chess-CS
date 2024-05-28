// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Queen

import java.util.ArrayList;

/**
 * The Queen class represents a queen chess piece and extends the Piece class.
 */
public class Queen extends Piece {
    
    /**
     * Constructor for the Queen class.
     *
     * @param s The color of the queen ('W' for white, 'B' for black).
     * @param b The chess board represented as a 2D array.
     * @param o The list of other chess pieces on the board.
     * @param cap The panel displaying captured pieces.
     */
    public Queen(char s, int[][] b, ArrayList<ChessPiece> o, CapturedPieces cap) {
        // Set initial position
        x = 192;
        y = (s == 'W') ? 448 : 0;
        // Set file path for image based on color
        file = (s == 'W') ? "pieces/queen.png" : "pieces/queen1.png";
        // Set color, board, list of other pieces, type, and captured pieces panel
        color = s;
        board = b;
        otherPieces = o;
        type = "Queen";
        cappanel = cap;
    }

    /**
     * Overloaded constructor for the Queen class with specific coordinates.
     *
     * @param x1 The x-coordinate.
     * @param y1 The y-coordinate.
     * @param s The color of the queen ('W' for white, 'B' for black).
     * @param b The chess board represented as a 2D array.
     * @param o The list of other chess pieces on the board.
     * @param cap The panel displaying captured pieces.
     */
    public Queen(int x1, int y1, char s, int[][] b, ArrayList<ChessPiece> o, CapturedPieces cap) {
        // Set initial position
        x = x1;
        y = y1;
        // Set file path for image based on color
        file = (s == 'W') ? "pieces/queen.png" : "pieces/queen1.png";
        // Set color, board, list of other pieces, type, and captured pieces panel
        color = s;
        board = b;
        otherPieces = o;
        type = "Queen";
        cappanel = cap;
    }

    /**
     * Calculates the legal moves for the queen.
     *
     * @return A list of legal move coordinates.
     */
    public ArrayList<int[]> legalMoves() {
        ArrayList<int[]> legal = new ArrayList<>();
        ArrayList<int[]> finlegal = new ArrayList<>();
        int tmX = (int) (Math.floor(getX() / 64));
        int tmY = (int) (Math.floor(getY() / 64));

        // Horizontal and vertical moves
        for (int i = tmX + 1; i < 8; i++) {
            if (board[tmY][i] == 1) {
                int[] curLegal = {i * 64, tmY * 64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {i * 64, tmY * 64};
            legal.add(curLegal);
        }
        for (int i = tmX - 1; i >= 0; i--) {
            if (board[tmY][i] == 1) {
                int[] curLegal = {i * 64, tmY * 64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {i * 64, tmY * 64};
            legal.add(curLegal);
        }
        for (int i = tmY - 1; i >= 0; i--) {
            if (board[i][tmX] == 1) {
                int[] curLegal = {tmX * 64, i * 64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {tmX * 64, i * 64};
            legal.add(curLegal);
        }
        for (int i = tmY + 1; i < 8; i++) {
            if (board[i][tmX] == 1) {
                int[] curLegal = {tmX * 64, i * 64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {tmX * 64, i * 64};
            legal.add(curLegal);
        }

        // Diagonal moves
        for (int i = 1; i < 8; i++) {
            if (tmX + i > 7 || tmY - i < 0 || board[tmY - i][tmX + i] == 1) {
                if (tmX + i <= 7 && tmY - i >= 0) {
                    int[] curLegal = {(tmX + i) * 64, (tmY - i) * 64};
                    finlegal.add(curLegal);
                }
                break;
            }
            int[] curLegal = {(tmX + i) * 64, (tmY - i) * 64};
            legal.add(curLegal);
        }

        for (int i = 1; i < 8; i++) {
            if (tmX - i < 0 || tmY - i < 0 || board[tmY - i][tmX - i] == 1) {
                if (tmX - i >= 0 && tmY - i >= 0) {
                    int[] curLegal = {(tmX - i) * 64, (tmY - i) * 64};
                    finlegal.add(curLegal);
                }
                break;
            }
            int[] curLegal = {(tmX - i) * 64, (tmY - i) * 64};
            legal.add(curLegal);
        }
        for (int i = 1; i < 8; i++) {
            if (tmX + i > 7 || tmY + i > 7 || board[tmY + i][tmX + i] == 1) {
                if (tmX + i <= 7 && tmY + i <= 7) {
                    int[] curLegal = {(tmX + i) * 64, (tmY + i) * 64};
                    finlegal.add(curLegal);
                }
                break;
            }
            int[] curLegal = {(tmX + i) * 64, (tmY + i) * 64};
            legal.add(curLegal);
        }
        for (int i = 1; i < 8; i++) {
            if (tmX - i < 0 || tmY + i > 7 || board[tmY + i][tmX - i] == 1) {
                if (tmX - i >= 0 && tmY + i <= 7) {
                    int[] curLegal = {(tmX - i) * 64, (tmY + i) * 64};
                    finlegal.add(curLegal);
                }
                break;
            }
            int[] curLegal = {(tmX - i) * 64, (tmY + i) * 64};
            legal.add(curLegal);
        }

        for (int[] leg : finlegal) {
            for (ChessPiece k : otherPieces) {
                if (k.getY() == leg[1] && k.getX() == leg[0] && k.getColor() != getColor()) {
                    legal.add(leg);
                }
            }
        }

        return legal;
    }

    /**
     * Identifies pieces protected by the queen.
     *
     * @return A list of protected piece coordinates.
     */
    public ArrayList<int[]> protectedPieces() {
        ArrayList<int[]> finlegal = new ArrayList<>();
        ArrayList<int[]> legal = new ArrayList<>();

        int tmX = (int) (Math.floor(getX() / 64));
        int tmY = (int) (Math.floor(getY() / 64));

        // Horizontal and vertical moves
        for (int i = tmX + 1; i < 8; i++) {
            if (board[tmY][i] == 1) {
                int[] curLegal = {i * 64, tmY * 64};
                legal.add(curLegal);
                break;
            }
        }
        for (int i = tmX - 1; i >= 0; i--) {
            if (board[tmY][i] == 1) {
                int[] curLegal = {i * 64, tmY * 64};
                legal.add(curLegal);
                break;
            }
        }
        for (int i = tmY - 1; i >= 0; i--) {
            if (board[i][tmX] == 1) {
                int[] curLegal = {tmX * 64, i * 64};
                legal.add(curLegal);
                break;
            }
        }
        for (int i = tmY + 1; i < 8; i++) {
            if (board[i][tmX] == 1) {
                int[] curLegal = {tmX * 64, i * 64};
                legal.add(curLegal);
                break;
            }
        }

        // Diagonal moves
        for (int i = 1; i < 8; i++) {
            if (tmX + i > 7 || tmY - i < 0 || board[tmY - i][tmX + i] == 1) {
                if (tmX + i <= 7 && tmY - i >= 0) {
                    int[] curLegal = {(tmX + i) * 64, (tmY - i) * 64};
                    legal.add(curLegal);
                }
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (tmX - i < 0 || tmY - i < 0 || board[tmY - i][tmX - i] == 1) {
                if (tmX - i >= 0 && tmY - i >= 0) {
                    int[] curLegal = {(tmX - i) * 64, (tmY - i) * 64};
                    legal.add(curLegal);
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (tmX + i > 7 || tmY + i > 7 || board[tmY + i][tmX + i] == 1) {
                if (tmX + i <= 7 && tmY + i <= 7) {
                    int[] curLegal = {(tmX + i) * 64, (tmY + i) * 64};
                    legal.add(curLegal);
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (tmX - i < 0 || tmY + i > 7 || board[tmY + i][tmX - i] == 1) {
                if (tmX - i >= 0 && tmY + i <= 7) {
                    int[] curLegal = {(tmX - i) * 64, (tmY + i) * 64};
                    legal.add(curLegal);
                }
                break;
            }
        }

        for (int[] leg : legal) {
            for (ChessPiece k : otherPieces) {
                if (k.getY() == leg[1] && k.getX() == leg[0] && k.getColor() == getColor()) {
                    finlegal.add(leg);
                }
            }
        }
        return finlegal;
    }
}