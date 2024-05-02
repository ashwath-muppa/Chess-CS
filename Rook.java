import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(char s, int side, int[][] b)
    {
        x = (side==0)?(0):(448);
        y = (s=='W')?(448):0;
        file = (s=='W')?("pieces/rook.png"):("pieces/rook1.png");
        board = b;
        color = s;
    }
   
   public ArrayList<int[]> legalMoves(){
      ArrayList<int[]> legal = new ArrayList<int[]>();
      int tmX = (int)(Math.floor(getX()/64));
      int tmY = (int)(Math.floor(getY()/64));
      for(int i=tmX+1;i<8;i++){
        if(board[tmY][i]==1){
            break;
        }
        int[] curLegal = {i*64, tmY*64};
        legal.add(curLegal);
      }
      for(int i=tmX-1;i>=0;i--){
        if(board[tmY][i]==1){
            break;
        }
        int[] curLegal = {i*64, tmY*64};
        legal.add(curLegal);
      }
      for(int i=tmY-1;i>=0;i--){
        if(board[i][tmX]==1){
            break;
        }
        int[] curLegal = {tmX*64, i*64};
        legal.add(curLegal);
      }
      for(int i=tmY+1;i<8;i++){
        if(board[i][tmX]==1){
            break;
        }
        int[] curLegal = {tmX*64, i*64};
        legal.add(curLegal);
      }
      return legal;
   }
   public void step(){
    if(moving){
        if(destX==x){
            if(destY>y){
                setY(getY()+getdY());
            }else{
                setY(getY()-getdY());
            }
            if(destY==y){
                moving=false;
            }
        }else{
            if(destX>x){
                setX(getX()+getdX());
            }else if(destX<x){
                setX(getX()-getdX());
            }
            if(destX==x){
                moving=false;
            }
        }
      }
   }
}
