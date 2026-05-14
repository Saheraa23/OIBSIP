import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGame extends JFrame implements ActionListener {

    // Game Variables
    int randomNumber;
    int tries = 5;
    int totalScore = 0;

    // GUI Components
    JLabel heading, instruction, result, triesLabel, scoreLabel;
    JTextField numberField;
    JButton submitButton, resetButton;

    public NumberGame() {

        // Generate random number
        randomNumber = new Random().nextInt(50) + 1;

        // Frame
        setTitle("Number Guessing Game");
        setSize(400, 320);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Heading
        heading = new JLabel("NUMBER GUESSING GAME");
        heading.setBounds(60, 20, 300, 30);
        heading.setFont(new Font("Verdana", Font.BOLD, 18));
        add(heading);

        // Instruction
        instruction = new JLabel("Guess a number from 1 to 100");
        instruction.setBounds(90, 60, 250, 25);
        instruction.setFont(new Font("Arial", Font.PLAIN, 15));
        add(instruction);

        // Text Field
        numberField = new JTextField();
        numberField.setBounds(120, 100, 140, 35);
        numberField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(numberField);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(70, 160, 110, 40);
        submitButton.addActionListener(this);
        add(submitButton);

        // Reset Button
        resetButton = new JButton("Reset");
        resetButton.setBounds(210, 160, 110, 40);
        resetButton.addActionListener(this);
        add(resetButton);

        // Result Label
        result = new JLabel("Start Guessing!");
        result.setBounds(120, 215, 200, 25);
        result.setFont(new Font("Arial", Font.BOLD, 15));
        add(result);

        // Tries Label
        triesLabel = new JLabel("Tries Left: " + tries);
        triesLabel.setBounds(30, 250, 120, 25);
        add(triesLabel);

        // Score Label
        scoreLabel = new JLabel("Score: " + totalScore);
        scoreLabel.setBounds(280, 250, 100, 25);
        add(scoreLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Submit Button Logic
        if (e.getSource() == submitButton) {

            try {

                int guess = Integer.parseInt(numberField.getText());

                tries--;

                if (guess == randomNumber) {

                    totalScore += 20;

                    result.setText("Correct Guess!");
                    JOptionPane.showMessageDialog(this,
                            "You Won!\nCorrect Number: " + randomNumber);

                    scoreLabel.setText("Score: " + totalScore);

                    submitButton.setEnabled(false);

                } else if (guess < randomNumber) {

                    result.setText("Too Low!");

                } else {

                    result.setText("Too High!");
                }

                triesLabel.setText("Tries Left: " + tries);

                // Lose condition
                if (tries == 0 && guess != randomNumber) {

                    JOptionPane.showMessageDialog(this,
                            "Game Over!\nCorrect Number was: "
                                    + randomNumber);

                    submitButton.setEnabled(false);
                }

                numberField.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        "Enter valid number!");
            }
        }

        // Reset Button Logic
        if (e.getSource() == resetButton) {

            randomNumber = new Random().nextInt(50) + 1;

            tries = 5;

            triesLabel.setText("Tries Left: " + tries);

            result.setText("New Game Started!");

            numberField.setText("");

            submitButton.setEnabled(true);
        }
    }

    public static void main(String[] args) {

        new NumberGame();
    }
}