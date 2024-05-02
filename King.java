import java.util.ArrayList;

public class King extends Piece{
   public King(char s, int[][] b){
      x = 256;
      y = (s=='W')?(448):0;
      file = (s=='W')?("pieces/king.png"):("pieces/king1.png");
      color=s;
      board = b;
   }
   
   public ArrayList<int[]> legalMoves(){
      ArrayList<int[]> legal = new ArrayList<int[]>();
      int tmX = (int)(Math.floor(getX()/64));
      int tmY = (int)(Math.floor(getY()/64));
      int[][] moves = {{tmX+1,tmY},
                      {tmX-1,tmY},
                      {tmX,tmY+1},
                      {tmX,tmY-1},
                      {tmX+1,tmY+1},
                      {tmX+1,tmY-1},
                      {tmX-1,tmY+1},
                      {tmX-1,tmY-1}};
      for(int i=0;i<8;i++){
         int[] tmp = moves[i];
          if(tmp[0]>=0&&tmp[0]<8&&tmp[1]>=0&&tmp[1]<8&&(board[tmp[1]][tmp[0]]!=1)){
            tmp[0]*=64;
            tmp[1]*=64;
            legal.add(tmp);
          }
      }
      return legal;
   }
   public void step(){
      if(moving){
         if(destY>y){
               setY(getY()+getdY());
         }else{
               setY(getY()-getdY());
         }
         if(destX==x&&destY==y){
               moving=false;
         }
         if(destX>x){
               setX(getX()+getdX());
         }else if(destX<x){
               setX(getX()-getdX());
         }
         if(destX==x&&destY==y){
               moving=false;
         }
      }
   }
}