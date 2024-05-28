// Arjun Garg and Ashwath Muppa
// Final Project (Chess): Captured pieces

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * CapturedPieces class represents the panel that displays the captured chess pieces.
 */
public class CapturedPieces extends JPanel {
    private BufferedImage myImage;
    private Graphics myBuffer;
    private int startingWhite = 10;
    private int startingBlack = 792;

    /**
     * Constructor for CapturedPieces.
     * Initializes the panel with a BufferedImage and labels for captured pieces.
     */
    public CapturedPieces() {
        // Initialize image and buffer for drawing
        myImage = new BufferedImage(862, 75, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(Color.WHITE);
        myBuffer.fillRect(0, 0, 862, 100);
        myBuffer.setColor(Color.BLACK);
        myBuffer.fillRect(0, 0, 431, 100);

        // Add labels for captured pieces
        JTextArea whiteLabel = new JTextArea("White Pieces Captured      ");
        whiteLabel.setBackground(Color.BLACK);
        whiteLabel.setForeground(Color.WHITE);
        whiteLabel.setEditable(false);
        add(whiteLabel, BorderLayout.WEST);

        JTextArea blackLabel = new JTextArea("      Black Pieces Captured");
        blackLabel.setBackground(Color.WHITE);
        blackLabel.setEditable(false);
        add(blackLabel, BorderLayout.WEST);

        // Draw a divider line
        myBuffer.setColor(Color.BLACK);
        myBuffer.fillRect(429, 0, 4, 75);
    }

    /**
     * Overrides the paintComponent method to draw the buffered image.
     *
     * @param g The Graphics object to protect.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Adds a captured piece to the panel.
     *
     * @param piece The ChessPiece object representing the captured piece.
     */
    public void addPiece(ChessPiece piece) {
        char color = piece.getColor();
        String file = piece.getFile();
        ImageIcon pic = new ImageIcon(file); // Draw the piece
        myBuffer.drawImage(pic.getImage(), (color == 'W') ? startingWhite : startingBlack, 10, 60, 60, null);
        
        // Adjust starting position for the next piece based on color
        if (color == 'W') {
            startingWhite += 20;
        } else {
            startingBlack -= 20;
        }
        repaint(); // Repaint the panel
    }
}