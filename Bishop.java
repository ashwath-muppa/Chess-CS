import java.util.ArrayList;

public class Bishop extends Piece{
   
    public Bishop(char s, int side, int[][] b, ArrayList<ChessPiece> o){
        x = (side==0)?(128):(320);
        y = (s=='W')?(448):0;
        file = (s=='W')?("pieces/bishop.png"):("pieces/bishop1.png");
        color = s;
        board = b;
        otherPieces = o;
        type = "Bishop";
    }

    public ArrayList<int[]> legalMoves(){
        //stores final legal moves
        ArrayList<int[]> finlegal = new ArrayList<int[]>();
        //stores temp legal moves
        ArrayList<int[]> legal = new ArrayList<int[]>();
        
        int tmX = (int)(Math.floor(getX()/64));
        int tmY = (int)(Math.floor(getY()/64));
        for(int i=1;i<8;i++){
            if((tmX+i>7 || tmY-i<0)||(board[tmY-i][tmX+i]==1)){
                int[] curLegal = {(tmX+i)*64, (tmY-i)*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {(tmX+i)*64, (tmY-i)*64};
            legal.add(curLegal);
        }

        for(int i=1;i<8;i++){
            if((tmX-i<0 || tmY-i<0)||(board[tmY-i][tmX-i]==1)){
                int[] curLegal = {(tmX-i)*64, (tmY-i)*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {(tmX-i)*64, (tmY-i)*64};
            legal.add(curLegal);
        }
        for(int i=1;i<8;i++){
            if((tmX+i>7 || tmY+i>7)||(board[tmY+i][tmX+i]==1)){
                int[] curLegal = {(tmX+i)*64, (tmY+i)*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {(tmX+i)*64, (tmY+i)*64};
            legal.add(curLegal);
        }
        for(int i=1;i<8;i++){
            if((tmX-i<0 || tmY+i>7)||(board[tmY+i][tmX-i]==1)){
                int[] curLegal = {(tmX-i)*64, (tmY+i)*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {(tmX-i)*64, (tmY+i)*64};
            legal.add(curLegal);
        }

        for(int[] leg : finlegal){
            for(ChessPiece k : otherPieces){
                if(k.getY()==leg[1] && k.getX()==leg[0] && k.getColor()!=getColor()){
                    legal.add(leg);
                }
            }
        }

      return legal;
    }

    public ArrayList<int[]> protectedPieces(){
        ArrayList<int[]> finlegal = new ArrayList<int[]>();
        ArrayList<int[]> legal = new ArrayList<int[]>();

        int tmX = (int)(Math.floor(getX()/64));
        int tmY = (int)(Math.floor(getY()/64));
        for(int i=1;i<8;i++){
            if((tmX+i>7 || tmY-i<0)||(board[tmY-i][tmX+i]==1)){
                int[] curLegal = {(tmX+i)*64, (tmY-i)*64};
                finlegal.add(curLegal);
                break;
            }
        }

        for(int i=1;i<8;i++){
            if((tmX-i<0 || tmY-i<0)||(board[tmY-i][tmX-i]==1)){
                int[] curLegal = {(tmX-i)*64, (tmY-i)*64};
                finlegal.add(curLegal);
                break;
            }
        }
        for(int i=1;i<8;i++){
            if((tmX+i>7 || tmY+i>7)||(board[tmY+i][tmX+i]==1)){
                int[] curLegal = {(tmX+i)*64, (tmY+i)*64};
                finlegal.add(curLegal);
                break;
            }
        }
        for(int i=1;i<8;i++){
            if((tmX-i<0 || tmY+i>7)||(board[tmY+i][tmX-i]==1)){
                int[] curLegal = {(tmX-i)*64, (tmY+i)*64};
                finlegal.add(curLegal);
                break;
            }
        }

        for(int[] leg : finlegal){
            for(ChessPiece k : otherPieces){
                if(k.getY()==leg[1] && k.getX()==leg[0] && k.getColor()==getColor()){
                    legal.add(leg);
                }
            }
        }
        return finlegal;
    }




}
