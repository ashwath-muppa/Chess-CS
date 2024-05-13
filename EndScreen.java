// Arjun Garg and Ashwath Muppa
// Final Project (Chess): End screen

import java.awt.*;
import javax.swing.*;

public class EndScreen extends JPanel {
    private JLabel winnerLabel;

    public EndScreen(String winnerName) {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Title Panel
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(new Color(173, 216, 230));
        JLabel titleLabel = new JLabel("Game Over");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titlePanel.add(titleLabel);

        // Winner Panel
        JPanel winnerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        winnerPanel.setBackground(new Color(240, 240, 240));
        winnerLabel = new JLabel(winnerName + " wins!");
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winnerPanel.add(winnerLabel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(100, 149, 237)); // Darker blue color
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 18));
        playAgainButton.setForeground(Color.BLACK);
        playAgainButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(playAgainButton);

        // Adding Components to the Panel
        add(titlePanel, BorderLayout.NORTH);
        add(winnerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
