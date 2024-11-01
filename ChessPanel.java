// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Panel

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
* ChessPanel class represents the main panel for the chess game.
*/
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

   /**
   * Constructor for ChessPanel.
   *
   * @param k the main frame
   * @param load the game data to load
   */

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

      turn = new JLabel("White to Play");
      turn.setHorizontalAlignment(SwingConstants.CENTER);
      turn.setBackground(Color.WHITE);
      turn.setForeground(Color.BLACK);
      turn.setOpaque(true);
      turn.setFont(new Font(Font.SERIF, Font.BOLD, 20));
      east.add(turn);

      //east.add(new JLabel(""));

      JLabel inst = new JLabel("INSTRUCTIONS");
      inst.setBackground(Color.BLACK);
      inst.setForeground(Color.RED);
      inst.setFont(new Font(Font.SERIF, Font.BOLD, 27));
      inst.setHorizontalAlignment(SwingConstants.CENTER);
      inst.setOpaque(true);
      east.add(inst);

      String[] instructions = {
         "1. Click on a piece to select it.",
         "2. Legal moves will be highlighted in green.",
         "3. Click on a highlighted square to move.",
         "4. Capture pieces by moving to their square.",
         "5. Capture the opponent's king to win.",
         "6. No castling or enpassant or checks",
         "7. Use the menu to change board color."
     };

     for (String instruction : instructions) {
         JLabel instructionLabel = new JLabel(instruction);
         instructionLabel.setFont(new Font(Font.SERIF, Font.BOLD, 15));
         instructionLabel.setBackground(new Color(255, 253, 208));
         instructionLabel.setOpaque(true);
         instructionLabel.setHorizontalAlignment(SwingConstants.LEFT);
         east.add(instructionLabel);
     }

      east.add(new JLabel("Select Background:"));
      String[] choices = { "Green-White","Blue-White", "Brown-White"};
      cb = new JComboBox<String>(choices);
      east.add(cb);
      JButton btn = new JButton("OK");
      btn.addActionListener(new ButtonListener());
      east.add(btn);
      east.setPreferredSize(new Dimension(350,512));


      CapturedPieces south = new CapturedPieces();
      south.setFocusable(true);
      add(south,BorderLayout.SOUTH);
      south.setPreferredSize(new Dimension(862,75));

      add(east,BorderLayout.EAST);
      display = new ChessBoard(piecesBoard, south, gameToLoad);
      display.setFocusable(true);
      add(display,BorderLayout.CENTER);
      display.addMouseListener(new Mouse());
   }

   /**
   * ButtonListener class handles button click events.
   */
   private class ButtonListener implements ActionListener
   {
   /**
   * Handles the button click event to change board color.
   *
   * @param e the action event
   */
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

   /**
   * Mouse class handles mouse click events.
   */
   private class Mouse extends MouseAdapter{
      /**
      * Handles mouse click events for piece selection and movement.
      *
      * @param e the mouse event
      */
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
                  endFrame.setSize(800, 400);
                  endFrame.setLocationRelativeTo(null);
                  endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  endFrame.setContentPane(new EndScreen(endFrame, display.getWinner(), gameToLoad));
                  endFrame.setVisible(true);
               }
               if(turn.getText().substring(0,5).equals("White")){
                  turn.setText("Black to Play");
                  turn.setBackground(Color.BLACK);
                  turn.setForeground(Color.WHITE);
               }else{
                  turn.setText("White to Play");
                  turn.setBackground(Color.WHITE);
                  turn.setForeground(Color.BLACK);
               }
            }
         }
      }
   }
}
