import java.util.ArrayList;
import java.lang.Math;

public class Pawn extends Piece{

    public Pawn(char s, int i, int[][] b,ArrayList<ChessPiece> o){
        x = i*64;
        y = (s=='W')?(384):64;
        file = (s=='W')?("pieces/pawn.png"):("pieces/pawn1.png");
        color=s;
        board=b;
        otherPieces = o;
        type = "Pawn";
    }
    public ArrayList<int[]> legalMoves(){
        ArrayList<int[]> legal = new ArrayList<int[]>();
        int tmX = (int)(Math.floor(getX()/64));
        int tmY = (int)(Math.floor(getY()/64));

        if(color=='W'){
            if(getY()==384){
                if(board[tmY-1][tmX]!=1){
                    int[] curleg = {getX(),getY()-64};
                    legal.add(curleg);
                }
                if(board[tmY-1][tmX]!=1){
                    if(board[tmY-2][(int)(Math.floor(getX()/64))]!=1){
                        int[] curleg1 = {getX(),getY()-128};
                        legal.add(curleg1);
                    }
                }
            }else{
                if(board[tmY-1][tmX]!=1){
                    int[] curleg = {getX(), getY()-64};
                    legal.add(curleg);
                }
            }

            int[][] captures = {{tmX-1,tmY-1},
                                {tmX+1,tmY-1}};
            for(int[] leg : captures){
                for(ChessPiece k : otherPieces){
                    if(k.getY()==(leg[1]*64) && k.getX()==(leg[0]*64) && k.getColor()!=getColor()){
                        leg[0]*=64;
                        leg[1]*=64;
                        legal.add(leg);
                    }
                }
            }
        }
        else{
            if(getY()==64){
                if(board[tmY+1][tmX]!=1){
                    int[] curleg = {getX(),getY()+64};
                    legal.add(curleg);
                }
                if(board[tmY+1][tmX]!=1){
                    if(board[tmY+2][tmX]!=1){
                        int[] curleg1 = {getX(),getY()+128};
                        legal.add(curleg1);
                    }
                }
            }else{
                if(board[tmY+1][tmX]!=1){
                    int[] curleg = {getX(), getY()+64};
                    legal.add(curleg);
                }
            }

            int[][] captures = {{tmX-1,tmY+1},
                                {tmX+1,tmY+1}};
            for(int[] leg : captures){
                for(ChessPiece k : otherPieces){
                    if(k.getY()==(leg[1]*64) && k.getX()==(leg[0]*64) && k.getColor()!=getColor()){
                        leg[0]*=64;
                        leg[1]*=64;
                        legal.add(leg);
                    }
                }
            }
        }
        return legal;
    }
    //there is no need write logic here, because the pawns moves are recorded in king already specially
    public ArrayList<int[]> protectedPieces(){
        ArrayList<int[]> finlegal = new ArrayList<int[]>();

        return finlegal;
    }
}
