// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Captured pieces

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

// Panel to display captured pieces
public class CapturedPieces extends JPanel{  
   // Declaration of variables
   private BufferedImage myImage;
   private Graphics myBuffer;
   private int startingWhite = 10;
   private int startingBlack = 792;
   
   // Constructor
   public CapturedPieces(){
      // Initialize image and buffer for drawing
      myImage =  new BufferedImage(862, 75, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(Color.WHITE);
      myBuffer.fillRect(0,0,862,100);
      myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(0,0,431,100);
      // Add labels for captured pieces
      JTextArea k = new JTextArea("White Pieces Captured      ");
      k.setBackground(Color.BLACK);
      k.setForeground(Color.WHITE);
      k.setEditable(false);
      add(k,BorderLayout.WEST);
      JTextArea b = new JTextArea("      Black Pieces Captured");
      b.setBackground(Color.WHITE);
      b.setEditable(false);
      add(b,BorderLayout.WEST);
      // Draw a divider line
      myBuffer.setColor(Color.black);
      myBuffer.fillRect(429,0,4,75);
 
   }

   // Method to paint the component
   public void paintComponent(Graphics g){
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);

   // Method to add a captured piece to the panel
   }
   public void addPiece(ChessPiece piece){
    char color = piece.getColor();
    String file = piece.getFile();
    ImageIcon pic = new ImageIcon(file); // draw the piece
    myBuffer.drawImage(pic.getImage(),(color=='W')?(startingWhite):(startingBlack), 10, 60,60, null);
    // Adjust starting position for the next piece based on color
    if(color=='W'){
        startingWhite+=20;
    }else{
        startingBlack-=20;
    }
    repaint();   // Repaint the panel
   }
    
   }


