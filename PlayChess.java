// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Chess Driver

/**
* @version Arjun Garg and Ashwath Muppa, 5/29/2024
* Period 6, CS Final Project: Chess (Stubs)
*/

import javax.swing.JFrame;


public class PlayChess{
   public static void main(String[] args)
   {
      //open JFrame that contains all the panels
      JFrame f = new JFrame("Start Playing Chess");
      f.setSize(1000,350);
      f.setLocation(100,50);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(new StartScreen(f));
      
      f.setVisible(true);
   }
}