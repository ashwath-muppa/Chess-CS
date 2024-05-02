import java.util.ArrayList;

public class Knight extends Piece{
   public Knight(char s, int side, int[][] b, ArrayList<BasicFunctions> o){
      x = (side==0)?(64):(512-128);
      y = (s=='W')?(448):0;
      file = (s=='W')?("pieces/knight.png"):("pieces/knight1.png");
      color=s;
      board = b;
      otherPieces = o;
   }
   
   public ArrayList<int[]> legalMoves(){
      ArrayList<int[]> finlegal = new ArrayList<int[]>();
      ArrayList<int[]> legal = new ArrayList<int[]>();
      int tmX = (int)(Math.floor(getX()/64));
      int tmY = (int)(Math.floor(getY()/64));
      int[][] moves= {{tmX+1,tmY+2},
                      {tmX+1,tmY-2},
                      {tmX-1,tmY+2},
                      {tmX-1,tmY-2},
                      {tmX+2,tmY+1},
                      {tmX+2,tmY-1},
                      {tmX-2,tmY+1},
                      {tmX-2,tmY-1}};
      for(int i=0;i<8;i++){
         int[] tmp = moves[i];
         if(tmp[0]>=0&&tmp[0]<8&&tmp[1]>=0&&tmp[1]<8){
            if(board[tmp[1]][tmp[0]]!=1){
                  tmp[0]*=64;
                  tmp[1]*=64;
                  legal.add(tmp);
            }else if(board[tmp[1]][tmp[0]]==1){
                  finlegal.add(tmp); 
            }
         }
      }
      for(int[] leg : finlegal){
            for(BasicFunctions k : otherPieces){
                if(k.getY()==(leg[1]*64) && k.getX()==(leg[0]*64) && k.getColor()!=getColor()){
                    leg[0]*=64;
                    leg[1]*=64;
                    legal.add(leg);
                }
            }
      }

      return legal;
   }

}

