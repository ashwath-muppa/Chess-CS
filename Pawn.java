import java.util.ArrayList;
import java.lang.Math;

public class Pawn extends Piece{

    public Pawn(char s, int i, int[][] b){
        x = i*64;
        y = (s=='W')?(384):64;
        file = (s=='W')?("pieces/pawn.png"):("pieces/pawn1.png");
        color=s;
        board=b;
    }
    public ArrayList<int[]> legalMoves(){
        ArrayList<int[]> legal = new ArrayList<int[]>();
        if(color=='W'){
            if(getY()==384){
                if(board[(int)(Math.floor(getY()/64))-1][(int)(Math.floor(getX()/64))]!=1){
                    int[] curleg = {getX(),getY()-64};
                    legal.add(curleg);
                }
                if(board[(int)(Math.floor(getY()/64))-1][(int)(Math.floor(getX()/64))]!=1){
                    if(board[(int)(Math.floor(getY()/64))-2][(int)(Math.floor(getX()/64))]!=1){
                        int[] curleg1 = {getX(),getY()-128};
                        legal.add(curleg1);
                    }
                }
            }else{
                if(board[(int)(Math.floor(getY()/64))-1][(int)(Math.floor(getX()/64))]!=1){
                    int[] curleg = {getX(), getY()-64};
                    legal.add(curleg);
                }
            }
        }else{
            if(getY()==64){
                if(board[(int)(Math.floor(getY()/64))+1][(int)(Math.floor(getX()/64))]!=1){
                    int[] curleg = {getX(),getY()+64};
                    legal.add(curleg);
                }
                if(board[(int)(Math.floor(getY()/64))+1][(int)(Math.floor(getX()/64))]!=1){
                    if(board[(int)(Math.floor(getY()/64))+2][(int)(Math.floor(getX()/64))]!=1){
                        int[] curleg1 = {getX(),getY()+128};
                        legal.add(curleg1);
                    }
                }
            }else{
                if(board[(int)(Math.floor(getY()/64))+1][(int)(Math.floor(getX()/64))]!=1){
                    int[] curleg = {getX(), getY()+64};
                    legal.add(curleg);
                }
            }
        }
        return legal;
    }
    public void step(){
        if(moving){
            if(color == 'W'){
                setY(getY()-getdY());
            }else{
                setY(getY()+getdY());
            }
        }
        if(destY==y){
            moving=false;
        }
    }
}
