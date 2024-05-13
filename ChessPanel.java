import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ChessPanel extends JPanel{
   private ChessBoard display;
   private boolean pieceActive;
   private int[][] piecesBoard = {{1,1,1,1,1,1,1,1},
                                  {1,1,1,1,1,1,1,1},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {1,1,1,1,1,1,1,1},
                                  {1,1,1,1,1,1,1,1}};
   //holds piece currently selected
   private int curMX;
   private int curMY;
   private JComboBox<String> cb;
   private JLabel turn;
   private JFrame curFrame;
   private String[] gameToLoad;
   public ChessPanel(JFrame k, String[] load){
      curFrame = k;
      gameToLoad = load;
      //adds subpanel and main panel to JPanel
      setLayout(new BorderLayout());
      JPanel east = new JPanel();
      east.setLayout(new GridLayout(15,1));

      JLabel title = new JLabel("Welcome To Chess!");
      title.setFont(new Font(Font.SERIF, Font.BOLD, 35));
      title.setHorizontalAlignment(SwingConstants.CENTER);
      title.setBackground(Color.BLACK);
      title.setOpaque(true);
      title.setForeground(Color.WHITE);
      east.add(title);

      JLabel names = new JLabel(gameToLoad[0].toUpperCase()+" v. "+gameToLoad[1].toUpperCase());
      names.setFont(new Font(Font.SERIF, Font.BOLD, 20));
      names.setHorizontalAlignment(SwingConstants.CENTER);
      east.add(names);

      JLabel scores = new JLabel("Current Score: "+gameToLoad[2]+" - "+gameToLoad[3]);
      scores.setFont(new Font(Font.SERIF, Font.BOLD, 20));
      scores.setHorizontalAlignment(SwingConstants.CENTER);
      east.add(scores);

      JLabel inst = new JLabel("INSTRUCTIONS");
      inst.setBackground(Color.BLACK);
      inst.setForeground(Color.RED);
      inst.setFont(new Font(Font.SERIF, Font.BOLD, 27));
      inst.setHorizontalAlignment(SwingConstants.CENTER);
      inst.setOpaque(true);
      east.add(inst);

      east.add(new JLabel("Select Background:"));
      String[] choices = { "Green-White","Blue-White", "Brown-White"};
      cb = new JComboBox<String>(choices);
      east.add(cb);
      JButton btn = new JButton("OK");
      btn.addActionListener(new ButtonListener());
      east.add(btn);
      east.setPreferredSize(new Dimension(350,512));
      turn = new JLabel("White to Play");
      east.add(turn);

      CapturedPieces south = new CapturedPieces();
      south.setFocusable(true);
      add(south,BorderLayout.SOUTH);
      south.setPreferredSize(new Dimension(862,75));

      add(east,BorderLayout.EAST);
      display = new ChessBoard(piecesBoard, south);
      display.setFocusable(true);
      add(display,BorderLayout.CENTER);
      display.addMouseListener(new Mouse());
   }


   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String x = cb.getSelectedItem().toString();
         switch(x){
            case "Green-White":
               display.setDarker(new Color(118,150,86));
               display.setLighter(new Color(238,238,210));
               break;
            case "Blue-White":
               display.setDarker(new Color(0,68,116));
               display.setLighter(new Color(238,238,210));
               break;
            case "Brown-White":
               display.setDarker(new Color(145,140,125));
               display.setLighter(new Color(180,175,165));
               break;
         }


      }
   }

   //Recieves the mouse input
   private class Mouse extends MouseAdapter{
      public void mouseClicked(MouseEvent e){  
         //If no piece has been selected yet
         if(!pieceActive){
            //updates the piece to highlight legal moves in green
            int[] cur = display.update(e.getX(),e.getY());      
            pieceActive = (cur[0]==1);
            curMX = cur[1];
            curMY = cur[2];
         //Assuming the a piece is already selected
         }else{
            int tmpX = e.getX()-curMX;
            int tmpY = e.getY()-curMY;
            //Checking if the same piece is clicked
            //if it is it will deactivate
            if(tmpX>0&&tmpX<64&&tmpY>0&&tmpY<64){
               display.update(e.getX(), e.getY());
               pieceActive = false;
            }
            //if the user clicks somewhere else and the move is legal to the piece
            //move to there and no more pieces are active now
             else if(display.legalMove(e.getX(), e.getY(), curMX, curMY)){
               pieceActive = false;
               if(!display.twoKings()){
                  curFrame.dispose();
                  JFrame endFrame = new JFrame("Chess");
                  endFrame.setSize(862, 537+75);
                  endFrame.setLocationRelativeTo(null);
                  endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  endFrame.setContentPane(new EndScreen("la"));
                  endFrame.setVisible(true);
               }
               if(turn.getText().substring(0,5).equals("White")){
                  turn.setText("Black to Play");
               }else{
                  turn.setText("White to Play");
               }
            }
         }
      }
   }
}
