// Arjun Garg and Ashwath Muppa
// Final Project (Chess): King

import java.util.ArrayList;

/**
 * The King class represents a king chess piece and extends the Piece class.
 */
public class King extends Piece {

    /**
     * Constructor for the King class.
     *
     * @param s The color of the king ('W' for white, 'B' for black).
     * @param b The chess board represented as a 2D array.
     * @param o The list of other chess pieces on the board.
     * @param cap The panel displaying captured pieces.
     */
    public King(char s, int[][] b, ArrayList<ChessPiece> o, CapturedPieces cap) {
        // Set initial position based on color
        x = 256;
        y = (s == 'W') ? 448 : 0;
        // Set file path for image based on color
        file = (s == 'W') ? "pieces/king.png" : "pieces/king1.png";
        // Set board, color, list of other pieces, type, and captured pieces panel
        color = s;
        board = b;
        otherPieces = o;
        type = "King";
        cappanel = cap;
    }

    /**
     * Checks if an element is contained in a list.
     *
     * @param arr The list of elements.
     * @param el The element to check.
     * @return True if the element is contained in the list, false otherwise.
     */
    public boolean contains(ArrayList<int[]> arr, int[] el) {
        for (int[] each : arr) {
            if (each[0] == el[0] && each[1] == el[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Placeholder method for checking the king's status.
     *
     * @return Always returns true.
     */
    public boolean check() {
        return true;
    }

    /**
     * Calculates the legal moves for the king.
     *
     * @return A list of legal move coordinates.
     */
    public ArrayList<int[]> legalMoves() {
        ArrayList<int[]> finlegal = new ArrayList<>();
        ArrayList<int[]> legal = new ArrayList<>();
        int tmX = (int) (Math.floor(getX() / 64));
        int tmY = (int) (Math.floor(getY() / 64));
        int[][] moves = {
            { tmX + 1, tmY }, { tmX - 1, tmY }, { tmX, tmY + 1 }, { tmX, tmY - 1 },
            { tmX + 1, tmY + 1 }, { tmX + 1, tmY - 1 }, { tmX - 1, tmY + 1 }, { tmX - 1, tmY - 1 }
        };

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

        for (int[] leg : finlegal) {
            for (ChessPiece k : otherPieces) {
                if (k.getY() == (leg[1] * 64) && k.getX() == (leg[0] * 64) && k.getColor() != getColor()) {
                    leg[0] *= 64;
                    leg[1] *= 64;
                    legal.add(leg);
                }
            }
        }

        ArrayList<int[]> finalLegal = new ArrayList<>();
        ArrayList<int[]> otherColor = new ArrayList<>();

        for (ChessPiece k : otherPieces) {
            if (k.getColor() != getColor() && k.getActive()) {
                ArrayList<int[]> g = k.legalMoves();
                ArrayList<int[]> h = k.protectedPieces();

                if (k.getType().equals("Pawn")) {
                    if (k.getColor() == 'W') {
                        otherColor.add(new int[] { k.getX() - 64, k.getY() - 64 });
                        otherColor.add(new int[] { k.getX() + 64, k.getY() - 64 });
                    } else {
                        otherColor.add(new int[] { k.getX() - 64, k.getY() + 64 });
                        otherColor.add(new int[] { k.getX() + 64, k.getY() + 64 });
                    }
                } else if (k.getType().equals("King")) {
                    int otmX = (int) (Math.floor(k.getX() / 64));
                    int otmY = (int) (Math.floor(k.getY() / 64));
                    int[][] otherKingsProtected = {
                        { otmX + 1, otmY }, { otmX - 1, otmY }, { otmX, otmY + 1 }, { otmX, otmY - 1 },
                        { otmX + 1, otmY + 1 }, { otmX + 1, otmY - 1 }, { otmX - 1, otmY + 1 }, { otmX - 1, otmY - 1 }
                    };
                    for (int[] othermove : otherKingsProtected) {
                        othermove[0] *= 64;
                        othermove[1] *= 64;
                        otherColor.add(othermove);
                    }
                } else {
                    otherColor.addAll(g);
                    otherColor.addAll(h);
                }
            }
        }

        for (int[] other : legal) {
            if (!contains(otherColor, other)) {
                finalLegal.add(other);
            }
        }

        return finalLegal;
    }

    /**
     * Identifies pieces protected by the king.
     *
     * @return A list of protected piece coordinates.
     */
    public ArrayList<int[]> protectedPieces() {
        ArrayList<int[]> finlegal = new ArrayList<>();
        ArrayList<int[]> legal = new ArrayList<>();
        int tmX = (int) (Math.floor(getX() / 64));
        int tmY = (int) (Math.floor(getY() / 64));
        int[][] moves = {
            { tmX + 1, tmY }, { tmX - 1, tmY }, { tmX, tmY + 1 }, { tmX, tmY - 1 },
            { tmX + 1, tmY + 1 }, { tmX + 1, tmY - 1 }, { tmX - 1, tmY + 1 }, { tmX - 1, tmY - 1 }
        };

        for (int[] tmp : moves) {
            if (tmp[0] >= 0 && tmp[0] < 8 && tmp[1] >= 0 && tmp[1] < 8) {
                if (board[tmp[1]][tmp[0]] == 1) {
                    finlegal.add(tmp);
                }
            }
        }

        for (int[] leg : finlegal) {
            for (ChessPiece k : otherPieces) {
                if (k.getY() == (leg[1] * 64) && k.getX() == (leg[0] * 64) && k.getColor() == getColor()) {
                    leg[0] *= 64;
                    leg[1] *= 64;
                    legal.add(leg);
                }
            }
        }

        return legal;
    }
}