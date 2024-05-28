import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * EndScreen class represents the panel displayed at the end of the game, 
 * showing the winner and options to play again.
 */
public class EndScreen extends JPanel {
    private JLabel winnerLabel;
    private JFrame curFrame;
    private String[] game;
    private String winner;

    /**
     * Constructor for EndScreen.
     *
     * @param frame The current JFrame where the panel is displayed.
     * @param winnerName The name of the player who won the game.
     * @param load The game data array containing information about the players.
     */
    public EndScreen(JFrame frame, String winnerName, String[] load) {
        curFrame = frame;
        game = load;
        winner = winnerName;

        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Title Panel
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(new Color(173, 216, 230));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        JLabel titleLabel = new JLabel("Game Over  -  ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(255, 69, 0)); // Orange-Red color
        titlePanel.add(titleLabel);

        winnerLabel = new JLabel(winnerName.toUpperCase() + " WINS!");
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winnerLabel.setForeground(new Color(34, 139, 34)); // Forest Green color
        titlePanel.add(winnerLabel);

        // Winner Panel
        JPanel winnerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        winnerPanel.setBackground(new Color(240, 240, 240));
        winnerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Icon Panel
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        iconPanel.setBackground(new Color(240, 240, 240));
        iconPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add spacing above and below

        ImageIcon trophyIcon = new ImageIcon("trophy.png");
        Image scaledTrophyImage = trophyIcon.getImage().getScaledInstance(150, -1, Image.SCALE_SMOOTH);
        ImageIcon scaledTrophyIcon = new ImageIcon(scaledTrophyImage);
        JLabel iconLabel = new JLabel(scaledTrophyIcon);
        iconPanel.add(iconLabel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(100, 149, 237)); // Darker blue color
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 18));
        playAgainButton.setForeground(Color.BLACK);
        playAgainButton.setPreferredSize(new Dimension(200, 50));
        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                curFrame.dispose();
                JFrame newFrame = new JFrame("Start Playing Chess");
                newFrame.setSize(1000, 350);
                newFrame.setLocation(100, 50);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setContentPane(new StartScreen(newFrame)); // Assuming StartScreen is another JPanel class
                newFrame.setVisible(true);
            }
        });
        buttonPanel.add(playAgainButton);

        // Adding Components to the Panel
        add(titlePanel, BorderLayout.NORTH);
        add(winnerPanel, BorderLayout.CENTER);
        add(iconPanel, BorderLayout.CENTER); // Add the icon panel
        add(buttonPanel, BorderLayout.SOUTH);

        if (!game[0].equals("N/A")) {
            BufferedReader reader;
            ArrayList<String[]> data = new ArrayList<>();
            try {
                reader = new BufferedReader(new FileReader("data.txt"));
                String line = reader.readLine();

                while (line != null) {
                    data.add(line.split(" "));
                    line = reader.readLine(); // read next line
                }
                reader.close();
            } catch (IOException m) {
                m.printStackTrace();
            }

            ArrayList<String[]> fin = new ArrayList<>();
            boolean loaded = false;
            for (String[] game : data) {
                String player1 = game[0];
                String player2 = game[1];
                if (player1.equals(this.game[1]) && player2.equals(this.game[0])) {
                    loaded = true;
                    if (player1.equals(winner)) {
                        game[2] = String.valueOf(Integer.valueOf(game[2]) + 1);
                    } else {
                        game[2] = String.valueOf(Integer.valueOf(game[2]) + 1);
                    }
                } else if (player1.equals(this.game[0]) && player2.equals(this.game[1])) {
                    loaded = true;
                    if (player1.equals(winner)) {
                        game[3] = String.valueOf(Integer.valueOf(game[3]) + 1);
                    } else {
                        game[3] = String.valueOf(Integer.valueOf(game[3]) + 1);
                    }
                }
                fin.add(game);
            }
            if (!loaded) {
                if (winner.equals(game[0])) {
                    String[] add = {game[0], game[1], String.valueOf(Integer.valueOf(game[2]) + 1), game[3]};
                    fin.add(add);
                } else {
                    String[] add = {game[0], game[1], game[2], String.valueOf(Integer.valueOf(game[3]) + 1)};
                    fin.add(add);
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
                for (String[] array : fin) {
                    StringBuilder line = new StringBuilder();
                    for (String element : array) {
                        line.append(element).append(" ");
                    }
                    writer.write(line.toString().trim());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
