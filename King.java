import java.util.ArrayList;

public class King extends Piece{
   public King(char s, int[][] b, ArrayList<BasicFunctions> o){
      x = 256;
      y = (s=='W')?(448):0;
      file = (s=='W')?("pieces/king.png"):("pieces/king1.png");
      color=s;
      board = b;
      otherPieces = o;
      type = "King";
   }

   public boolean contains(ArrayList<int[]> arr, int[] el){
      boolean ret = false;
      for(int[] each : arr){
         if(each[0]==el[0] && each[1]==el[1]){
            ret = true;
            break;
         }
      }
      return ret;
   }
   public boolean check(){

      return true;
   }
   public ArrayList<int[]> legalMoves(){
      ArrayList<int[]> finlegal = new ArrayList<int[]>();
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
      ArrayList<int[]> finalLegal = new ArrayList<int[]>();
      ArrayList<int[]> otherColor = new ArrayList<int[]>();
      
      for(BasicFunctions k : otherPieces){
          if(k.getColor()!=getColor() && (!k.getActive())){
            ArrayList<int[]> g = k.legalMoves();
            if(k.getType().equals("Pawn")){
               if(k.getColor()=='W'){
                  int[] tmpLeg = {k.getX()-64,k.getY()-64};
                  otherColor.add(tmpLeg);
                  int[] tmpLeg1 = {k.getX()+64,k.getY()-64};
                  otherColor.add(tmpLeg1);
               }else{
                  int[] tmpLeg = {k.getX()-64,k.getY()+64};
                  otherColor.add(tmpLeg);
                  int[] tmpLeg1 = {k.getX()+64,k.getY()+64};
                  otherColor.add(tmpLeg1);
               }
            }else{
               for(int[] z : g){
                  otherColor.add(z);
               }
            }
         }
       }
      for(int[] other : legal){

         if(!contains(otherColor, other)){

            finalLegal.add(other);

         }

      }

      return finalLegal;
   }
}