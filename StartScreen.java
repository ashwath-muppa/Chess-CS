import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
* StartScreen class represents the initial screen where players can enter names
* and either start a new game or load an existing one.
*/

public class StartScreen extends JPanel {
    private JFrame parentFrame;
    private JTextField player1Field;
    private JTextField player2Field;
    private JPanel instructionsPanel;
    /**
    * Constructor for StartScreen.
    *
    * @param f the main frame of the application
    */
    public StartScreen(JFrame f) {
        parentFrame = f;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Title Panel
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(new Color(173, 216, 230));
        JLabel titleLabel = new JLabel("Welcome to Chess!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titlePanel.add(titleLabel);

        // Instructions Panel
        instructionsPanel = new JPanel(new BorderLayout());
        instructionsPanel.setBackground(new Color(240, 240, 240));
        JTextArea instructionsArea = new JTextArea();
        instructionsArea.setText("Instructions:\n\n"
                                + "Enter 2 names to load a game in (If the instance doesn't exist, it will warn you).\n\n"
                                + "If instance doesn't exist, create a new one\n\n"
                                + "If you do not want your data to be saved, leave 'N/A' for both fields.");
        instructionsArea.setEditable(false);
        instructionsArea.setFont(new Font("Arial", Font.PLAIN, 18));
        instructionsArea.setOpaque(false);
        instructionsPanel.add(instructionsArea, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(new Color(255, 248, 220));
        player1Field = new JTextField("White Player");
        player1Field.setFont(new Font("Arial", Font.PLAIN, 18));
        player2Field = new JTextField("Black Player");
        player2Field.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel player1Label = new JLabel("Player 1:");
        player1Label.setHorizontalAlignment(SwingConstants.RIGHT);
        player1Label.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel player2Label = new JLabel("Player 2:");
        player2Label.setHorizontalAlignment(SwingConstants.RIGHT);
        player2Label.setFont(new Font("Arial", Font.PLAIN, 18));
        inputPanel.add(player1Label);
        inputPanel.add(player1Field);
        inputPanel.add(player2Label);
        inputPanel.add(player2Field);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(100, 149, 237)); // Darker blue color
        JButton createButton = new JButton("Create");
        createButton.setFont(new Font("Arial", Font.BOLD, 18));
        createButton.setForeground(Color.BLACK);
        createButton.setPreferredSize(new Dimension(200, 50));
        createButton.addActionListener(new ActionListener() {
            /**
            * Handles the action event for the Create button.
            *
            * @param e the action event
            */
            public void actionPerformed(ActionEvent e) {
                String player1 = player1Field.getText().toLowerCase().replace(" ", "");
                String player2 = player2Field.getText().toLowerCase().replace(" ", "");
                String[] gameLoad = {player1, player2, "0", "0"};
                parentFrame.dispose();
                JFrame gameFrame = new JFrame("Chess");
                gameFrame.setSize(862, 537+75);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setContentPane(new ChessPanel(gameFrame, gameLoad));
                gameFrame.setVisible(true);
            }
        });
        buttonPanel.add(createButton);


        JButton startButton = new JButton("Load");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setForeground(Color.BLACK);
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.addActionListener(new ActionListener() {
            /**
            * Handles the action event for the Load button.
            *
            * @param e the action event
            */
            public void actionPerformed(ActionEvent e) {
                String player1 = player1Field.getText().toLowerCase().replace(" ", "");
                String player2 = player2Field.getText().toLowerCase().replace(" ", "");
                BufferedReader reader;
                ArrayList<String[]> data = new ArrayList<String[]>();
                try {
                    reader = new BufferedReader(new FileReader("data.txt"));
                    String line = reader.readLine();

                    while (line != null) {
                        data.add(line.split(" "));
                        // read next line
                        line = reader.readLine();
                    }
                    reader.close();
                } catch (IOException m) {
                    m.printStackTrace();
                }

                boolean instanceFound = player1.equals("n/a")&&player2.equals("n/a");
                String[] gameLoad = {"N/A", "N/A", "0", "0"};
                for(String[] game : data){
                    if(player1.equals(game[1])){
                        if(player2.equals(game[0])){
                            instanceFound = true;
                            gameLoad = game;
                            break;
                        }
                    }else if(player1.equals(game[0])){
                        if(player2.equals(game[1])){
                            instanceFound = true;
                            gameLoad = game;
                            break;
                        }
                    }
                }
                if(instanceFound){
                    parentFrame.dispose();
                    JFrame gameFrame = new JFrame("Chess");
                    gameFrame.setSize(862, 537+75);
                    gameFrame.setLocationRelativeTo(null);
                    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    gameFrame.setContentPane(new ChessPanel(gameFrame, gameLoad));
                    gameFrame.setVisible(true);
                }
                else{
                    instructionsArea.setText("Instructions:\n\n"
                                + "Enter 2 names to load a game in (If the instance doesn't exist, it will warn you).\n\n"
                                + "If instance doesn't exist, create a new one\n\n"
                                + "If you do not want your data to be saved, leave 'N/A' for both fields.\n\n"
                                + "INSTANCE NOT FOUND");
                }
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
