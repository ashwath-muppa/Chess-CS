import javax.swing.JFrame;

//Driver to run chess

/**/
public class PlayChess{
   public static void main(String[] args)
   {
      //open JFrame that contains all the panels
      JFrame f = new JFrame("Chess");
      //set the size to 912-50 wide, and 512+25 tall (to ensure that the chess board panel is 512 x 512)
      f.setSize(912-50,512+25);
      f.setLocation(100,50);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(new ChessPanel());
      f.setVisible(true);
   }
}