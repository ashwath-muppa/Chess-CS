// Arjun Garg and Ashwath Muppa
// Chess: 

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

// Class to create and configure chess board display
public class CapturedPieces extends JPanel{  
   // Variables declaration
   private BufferedImage myImage;
   private Graphics myBuffer;
   private int startingWhite = 10;
   private int startingBlack = 792;
   public CapturedPieces(){
      // Initialize image and buffer for drawing
      myImage =  new BufferedImage(862, 75, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(Color.WHITE);
      myBuffer.fillRect(0,0,862,100);
      myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(0,0,431,100);
      JTextArea k = new JTextArea("White Pieces Captured      ");
      k.setBackground(Color.BLACK);
      k.setForeground(Color.WHITE);
      k.setEditable(false);
      add(k,BorderLayout.WEST);
      JTextArea b = new JTextArea("      Black Pieces Captured");
      b.setBackground(Color.WHITE);
      b.setEditable(false);
      add(b,BorderLayout.WEST);
      myBuffer.setColor(Color.black);
      myBuffer.fillRect(429,0,4,75);
 
   }
   public void paintComponent(Graphics g){
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   public void addPiece(ChessPiece piece){
    char color = piece.getColor();
    String file = piece.getFile();
    ImageIcon pic = new ImageIcon(file); // draw the piece
    myBuffer.drawImage(pic.getImage(),(color=='W')?(startingWhite):(startingBlack), 10, 60,60, null);
    if(color=='W'){
        startingWhite+=20;
    }else{
        startingBlack-=20;
    }
    repaint();
    // System.out.println("HEY");
   }
    
   }


