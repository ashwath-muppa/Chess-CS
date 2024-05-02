import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Piece implements Animatable{
    protected int x;
    protected int y;
    protected int destY;
    protected int destX;
    protected int dY = 4;
    protected int dX = 4;
    protected String file;
    protected boolean active = false;
    protected char color;
    protected boolean moving=false;
    protected int[][] board;

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getdY(){
        return dY;
    }
    public int getdX(){
        return dX;
    }
    public void setDestX(int xValue)
    {
        destX = xValue;
    }
    public void setDestY(int yValue)
    {
        destY = yValue;
    }
   public void setX(int xValue)
   {
      x = xValue;
   }
   public void setY(int yValue)
   {
      y = yValue;
   }
    public void setMove(int x, int y){
        setDestX(x);
        setDestY(y);
        board[(int)(Math.floor(getY()/64))][(int)(Math.floor(getX()/64))]=0;
        int tpY = (int)(Math.floor(x/64));
        int tpX = (int)(Math.floor(y/64));
        board[tpX][tpY]=1;
        System.out.println("\n\n\n");
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
        active = false;
        moving = true;
    }

    public void drawMe(Graphics g)
   {  
    if(active){
        g.setColor(new Color(0,255,0,128));
        g.fillRect(getX(), getY(),64,64);
        for(int[] k : legalMoves()){
            g.fillRect((int)(Math.floor(k[0]/64)*64), (int)(Math.floor(k[1]/64)*64),64,64);
        }
    }
      ImageIcon piece = new ImageIcon(file);
      g.drawImage(piece.getImage(),getX(), getY(), 64,64,null);
   }

    
    public void activate(){
        active = !active;
    }


    public abstract ArrayList<int[]> legalMoves();
    public abstract void step();

}