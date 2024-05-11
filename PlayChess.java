import javax.swing.JFrame;

//Driver to run chess

/**/
public class PlayChess{
   public static void main(String[] args)
   {
      //open JFrame that contains all the panels
      JFrame f = new JFrame("Start Playing Chess");
      f.setSize(1000,300);
      f.setLocation(100,50);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(new StartScreen(f));
      
      f.setVisible(true);
   }
}