import javax.swing.JFrame;

//Driver to run chess

/**/
public class PlayChess{
   public static void main(String[] args)
   {
      JFrame f = new JFrame("Chess");
      f.setSize(912-50,512+25);
      f.setLocation(100,50);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(new ChessPanel());
      f.setVisible(true);
   }
}