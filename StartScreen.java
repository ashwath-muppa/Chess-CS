import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen extends JPanel {
    private JFrame parentFrame;
    private JTextField player1Field;
    private JTextField player2Field;

    public StartScreen(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Instructions Panel
        JPanel instructionsPanel = new JPanel(new BorderLayout());
        instructionsPanel.setBackground(new Color(240, 240, 240));
        JTextArea instructionsArea = new JTextArea();
        instructionsArea.setText("Instructions:\n\n"
                                + "Enter 2 names to load a game in.\n\n"
                                + "If the 2 names do not appear to be stored, it will create a new instance.\n\n"
                                + "If you do not want your data to be saved, please type in 'N/A' for both fields.");
        instructionsArea.setEditable(false);
        instructionsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionsArea.setOpaque(false);
        instructionsPanel.add(instructionsArea, BorderLayout.CENTER);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(new Color(173, 216, 230));
        JLabel titleLabel = new JLabel("Welcome to Chess!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBackground(new Color(255, 248, 220));
        player1Field = new JTextField("Player 1");
        player1Field.setPreferredSize(new Dimension(100, 30));
        player1Field.setFont(new Font("Arial", Font.PLAIN, 14));
        player1Field.setPreferredSize(new Dimension(150, 30));
        player2Field = new JTextField("Player 2");
        player2Field.setFont(new Font("Arial", Font.PLAIN, 14));
        player2Field.setPreferredSize(new Dimension(150, 30));
        JLabel player1Label = new JLabel("Player 1:");
        player1Label.setHorizontalAlignment(SwingConstants.RIGHT);
        player1Label.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel player2Label = new JLabel("Player 2:");
        player2Label.setHorizontalAlignment(SwingConstants.RIGHT);
        player2Label.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(player1Label);
        inputPanel.add(player1Field);
        inputPanel.add(player2Label);
        inputPanel.add(player2Field);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(100, 149, 237)); // Darker blue color
        JButton startButton = new JButton("Start Gameplay");
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setForeground(Color.BLACK);
        startButton.setPreferredSize(new Dimension(150, 40));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.setVisible(false);
                JFrame gameFrame = new JFrame("Chess");
                gameFrame.setSize(862, 537);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setContentPane(new ChessPanel());
                gameFrame.setVisible(true);
            }
        });
        buttonPanel.add(startButton);

        // Adding Components to the Panel
        add(titlePanel, BorderLayout.NORTH);
        add(instructionsPanel, BorderLayout.WEST);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
