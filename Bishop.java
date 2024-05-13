// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Bishop

import java.util.ArrayList;

// Bishop class extends Piece class
public class Bishop extends Piece{
   
    // Constructor for Bishop class
    public Bishop(char s, int side, int[][] b, ArrayList<ChessPiece> o,CapturedPieces cap){
        x = (side==0)?(128):(320);
        y = (s=='W')?(448):0;
        file = (s=='W')?("pieces/bishop.png"):("pieces/bishop1.png");
        color = s;
        board = b;
        otherPieces = o;
        type = "Bishop";
        cappanel = cap;
    }

    // Constructor for Bishop class with specified position
    public Bishop(int x1, int y1, char s, int[][] b, ArrayList<ChessPiece> o,CapturedPieces cap){
        // Set position, file path for image, color, board, list of other pieces, type, and captured pieces panel
        x = x1;
        y = y1;
        file = (s=='W')?("pieces/bishop.png"):("pieces/bishop1.png");
        color = s;
        board = b;
        otherPieces = o;
        type = "Bishop";
        cappanel = cap;
    }

    // Method to calculate legal moves for the bishop
    public ArrayList<int[]> legalMoves(){
        //stores final legal moves
        ArrayList<int[]> finlegal = new ArrayList<int[]>();
        //stores temp legal moves
        ArrayList<int[]> legal = new ArrayList<int[]>();

        int tmX = (int)(Math.floor(getX()/64));
        int tmY = (int)(Math.floor(getY()/64));

        // Check legal moves diagonally (up-right)
        for(int i=1;i<8;i++){
            if((tmX+i>7 || tmY-i<0)||(board[tmY-i][tmX+i]==1)){
                int[] curLegal = {(tmX+i)*64, (tmY-i)*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {(tmX+i)*64, (tmY-i)*64};
            legal.add(curLegal);
        }

        // Check legal moves diagonally (up-left)
        for(int i=1;i<8;i++){
            if((tmX-i<0 || tmY-i<0)||(board[tmY-i][tmX-i]==1)){
                int[] curLegal = {(tmX-i)*64, (tmY-i)*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {(tmX-i)*64, (tmY-i)*64};
            legal.add(curLegal);
        }

        // Check legal moves diagonally (down-right)
        for(int i=1;i<8;i++){
            if((tmX+i>7 || tmY+i>7)||(board[tmY+i][tmX+i]==1)){
                int[] curLegal = {(tmX+i)*64, (tmY+i)*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {(tmX+i)*64, (tmY+i)*64};
            legal.add(curLegal);
        }

        // Check legal moves diagonally (down-left)
        for(int i=1;i<8;i++){
            if((tmX-i<0 || tmY+i>7)||(board[tmY+i][tmX-i]==1)){
                int[] curLegal = {(tmX-i)*64, (tmY+i)*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {(tmX-i)*64, (tmY+i)*64};
            legal.add(curLegal);
        }

        // Check if legal moves capture opponent's pieces
        for(int[] leg : finlegal){
            for(ChessPiece k : otherPieces){
                if(k.getY()==leg[1] && k.getX()==leg[0] && k.getColor()!=getColor()){
                    legal.add(leg);
                }
            }
        }

        // Return the list of legal moves for the bishop
        return legal;
    }

    // Method to identify protected pieces by the bishop
    public ArrayList<int[]> protectedPieces(){
        ArrayList<int[]> finlegal = new ArrayList<int[]>();
        ArrayList<int[]> legal = new ArrayList<int[]>();

        int tmX = (int)(Math.floor(getX()/64));
        int tmY = (int)(Math.floor(getY()/64));

        // Check protected pieces diagonally (up-right)
        for(int i=1;i<8;i++){
            if((tmX+i>7 || tmY-i<0)||(board[tmY-i][tmX+i]==1)){
                int[] curLegal = {(tmX+i)*64, (tmY-i)*64};
                finlegal.add(curLegal);
                break;
            }
        }

        // Check protected pieces diagonally (up-left)
        for(int i=1;i<8;i++){
            if((tmX-i<0 || tmY-i<0)||(board[tmY-i][tmX-i]==1)){
                int[] curLegal = {(tmX-i)*64, (tmY-i)*64};
                finlegal.add(curLegal);
                break;
            }
        }

        // Check protected pieces diagonally (down-right)
        for(int i=1;i<8;i++){
            if((tmX+i>7 || tmY+i>7)||(board[tmY+i][tmX+i]==1)){
                int[] curLegal = {(tmX+i)*64, (tmY+i)*64};
                finlegal.add(curLegal);
                break;
            }
        }

        // Check protected pieces diagonally (down-left)
        for(int i=1;i<8;i++){
            if((tmX-i<0 || tmY+i>7)||(board[tmY+i][tmX-i]==1)){
                int[] curLegal = {(tmX-i)*64, (tmY+i)*64};
                finlegal.add(curLegal);
                break;
            }
        }

        // Check if protected pieces belong to the same color
        for(int[] leg : finlegal){
            for(ChessPiece k : otherPieces){
                if(k.getY()==leg[1] && k.getX()==leg[0] && k.getColor()==getColor()){
                    legal.add(leg);
                }
            }
        }

        // Return the list of protected pieces by the bishop
        return finlegal;
    }




}
