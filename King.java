import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class King extends Piece
{
   //constructors
   public King(char s)
   {
      x = 256;
      y = (s=='W')?(448):0;
      file = (s=='W')?("pieces/king.png"):("pieces/king1.png");
   }
   
   //accessors

   public int getX()
   {
      return x;
   }
   public int getY()
   {
      return y;
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

   }
   public ArrayList<int[]> legalMoves(){
      ArrayList<int[]> legal = new ArrayList<int[]>();
      return legal;
   }
   //instance methods
   public void drawMe(Graphics g)
   {  if(active){
    g.setColor(Color.GRAY);
    g.fillRect(getX(),getY(),64,64);
   }
      ImageIcon piece = new ImageIcon(file);
      g.drawImage(piece.getImage(),getX(), getY(), 64,64,null);
   }
   public void activate(){
    active = !active;
   }
   //other useful Java methods
   
   public void step(){

   }
   
}