import java.awt.*;
import java.util.ArrayList;
public interface BasicFunctions{
   public void step();
   public char getColor();
   public void drawMe(Graphics g);
   public int getX();
   public int getY();
   public void activate();
   public ArrayList<int[]> legalMoves();
   public void setMove(int x, int y);
   public String getType();
   public boolean getActive();
}