import java.util.ArrayList;

public class Queen extends Piece{
   
    public Queen(char s, int[][] b){
        x = 192;
        y = (s=='W')?(448):0;
        file = (s=='W')?("pieces/queen.png"):("pieces/queen1.png");
        color=s;
        board = b;
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


        for(int i=1;i<8;i++){
            if((tmX+i>7 || tmY-i<0)||(board[tmY-i][tmX+i]==1)){
                break;
            }
            int[] curLegal = {(tmX+i)*64, (tmY-i)*64};
            legal.add(curLegal);
        }
        for(int i=1;i<8;i++){
            if((tmX-i<0 || tmY-i<0)||(board[tmY-i][tmX-i]==1)){
                break;
            }
            int[] curLegal = {(tmX-i)*64, (tmY-i)*64};
            legal.add(curLegal);
        }

    for(int i=1;i<8;i++){
        if((tmX+i>7 || tmY+i>7)||(board[tmY+i][tmX+i]==1)){
            break;
        }
        int[] curLegal = {(tmX+i)*64, (tmY+i)*64};
        legal.add(curLegal);
    }
    for(int i=1;i<8;i++){
        if((tmX-i<0 || tmY+i>7)||(board[tmY+i][tmX-i]==1)){
            break;
        }
        int[] curLegal = {(tmX-i)*64, (tmY+i)*64};
        legal.add(curLegal);
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
