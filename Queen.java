import java.util.ArrayList;

public class Queen extends Piece{
   
    public Queen(char s, int[][] b, ArrayList<ChessPiece> o,CapturedPieces cap){
        x = 192;
        y = (s=='W')?(448):0;
        file = (s=='W')?("pieces/queen.png"):("pieces/queen1.png");
        color=s;
        board = b;
        otherPieces = o;
        type = "Queen";
        cappanel = cap;
    }
    public Queen(int x1, int y1,char s, int[][] b, ArrayList<ChessPiece> o,CapturedPieces cap){
        x = x1;
        y = y1;
        file = (s=='W')?("pieces/queen.png"):("pieces/queen1.png");
        color=s;
        board = b;
        otherPieces = o;
        type = "Queen";
        cappanel = cap;
    }
    public ArrayList<int[]> legalMoves(){
        ArrayList<int[]> finlegal = new ArrayList<int[]>();
        ArrayList<int[]> legal = new ArrayList<int[]>();
        int tmX = (int)(Math.floor(getX()/64));
        int tmY = (int)(Math.floor(getY()/64));

        for(int i=tmX+1;i<8;i++){
            if(board[tmY][i]==1){
                int[] curLegal = {i*64, tmY*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {i*64, tmY*64};
            legal.add(curLegal);
        }
          for(int i=tmX-1;i>=0;i--){
            if(board[tmY][i]==1){
                int[] curLegal = {i*64, tmY*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {i*64, tmY*64};
            legal.add(curLegal);
    
          }
          for(int i=tmY-1;i>=0;i--){
            if(board[i][tmX]==1){
                int[] curLegal = {tmX*64, i*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {tmX*64, i*64};
            legal.add(curLegal);
          }
          for(int i=tmY+1;i<8;i++){
            if(board[i][tmX]==1){
                int[] curLegal = {tmX*64, i*64};
                finlegal.add(curLegal);
                break;
            }
            int[] curLegal = {tmX*64, i*64};
            legal.add(curLegal);
          }
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

        for(int i=tmX+1;i<8;i++){
            if(board[tmY][i]==1){
                int[] curLegal = {i*64, tmY*64};
                legal.add(curLegal);
                break;
            }
        }
          for(int i=tmX-1;i>=0;i--){
            if(board[tmY][i]==1){
                int[] curLegal = {i*64, tmY*64};
                legal.add(curLegal);
                break;
            }
          }
          for(int i=tmY-1;i>=0;i--){
            if(board[i][tmX]==1){
                int[] curLegal = {tmX*64, i*64};
                legal.add(curLegal);
                break;
            }
          }
          for(int i=tmY+1;i<8;i++){
            if(board[i][tmX]==1){
                int[] curLegal = {tmX*64, i*64};
                legal.add(curLegal);
                break;
            }
          }
          for(int i=1;i<8;i++){
            if((tmX+i>7 || tmY-i<0)||(board[tmY-i][tmX+i]==1)){
                int[] curLegal = {(tmX+i)*64, (tmY-i)*64};
                legal.add(curLegal);
                break;
            }
        }

        for(int i=1;i<8;i++){
            if((tmX-i<0 || tmY-i<0)||(board[tmY-i][tmX-i]==1)){
                int[] curLegal = {(tmX-i)*64, (tmY-i)*64};
                legal.add(curLegal);
                break;
            }
        }
        for(int i=1;i<8;i++){
            if((tmX+i>7 || tmY+i>7)||(board[tmY+i][tmX+i]==1)){
                int[] curLegal = {(tmX+i)*64, (tmY+i)*64};
                legal.add(curLegal);
                break;
            }
        }
        for(int i=1;i<8;i++){
            if((tmX-i<0 || tmY+i>7)||(board[tmY+i][tmX-i]==1)){
                int[] curLegal = {(tmX-i)*64, (tmY+i)*64};
                legal.add(curLegal);
                break;
            }
        }
        for(int[] leg : legal){
            for(ChessPiece k : otherPieces){
                if(k.getY()==leg[1] && k.getX()==leg[0] && k.getColor()==getColor()){
                    finlegal.add(leg);
                }
            }
        }
      return finlegal;
    }


}
