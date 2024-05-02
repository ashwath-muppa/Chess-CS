import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(char s, int side, int[][] b, ArrayList<BasicFunctions> o)
    {
        x = (side==0)?(0):(448);
        y = (s=='W')?(448):0;
        file = (s=='W')?("pieces/rook.png"):("pieces/rook1.png");
        board = b;
        color = s;
        otherPieces = o;
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
      for(int[] leg : finlegal){
        for(BasicFunctions k : otherPieces){
            if(k.getY()==leg[1] && k.getX()==leg[0] && k.getColor()!=getColor()){
                legal.add(leg);
            }
        }

      }

      return legal;
   }

}
