// import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public interface Animatable
{
   public void step();
   public void drawMe(Graphics g);
   public int getX();
   public int getY();
   public void activate();
   public ArrayList<int[]> legalMoves();
   public void setMove(int x, int y);
}