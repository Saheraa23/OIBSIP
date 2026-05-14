import javax.swing.*;
import java.awt.event.*;

public class OnlineExamination extends JFrame implements ActionListener {

    // Login Components
    JLabel l1, l2;
    JTextField tfUser;
    JPasswordField pfPass;
    JButton btnLogin;

    // Exam Components
    JLabel questionLabel, timerLabel;
    JRadioButton option1, option2, option3, option4;
    ButtonGroup bg;
    JButton nextButton, submitButton, updateButton, logoutButton;

    // Questions and Answers
    String[] questions = {
            "1. Which language is used for Java programming?",
            "2. Which company developed Java?",
            "3. Which keyword is used for inheritance?",
            "4. Which package is used for GUI in Java?",
            "5. Which method is the entry point of Java?"
    };

    String[][] options = {
            {"Python", "Java", "C", "HTML"},
            {"Microsoft", "Apple", "Sun Microsystems", "Google"},
            {"this", "super", "extends", "implements"},
            {"java.io", "java.util", "java.awt", "java.sql"},
            {"start()", "run()", "main()", "init()"}
    };

    int[] answers = {2, 3, 3, 3, 3};

    int current = 0;
    int score = 0;
    int selected = 0;

    Timer timer;
    int timeLeft = 60;

    String username = "admin";
    String password = "1234";

    public OnlineExamination() {

        setTitle("Online Examination System");
        setSize(700, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Login Page
        l1 = new JLabel("Username:");
        l1.setBounds(200, 120, 100, 30);
        add(l1);

        tfUser = new JTextField();
        tfUser.setBounds(300, 120, 150, 30);
        add(tfUser);

        l2 = new JLabel("Password:");
        l2.setBounds(200, 170, 100, 30);
        add(l2);

        pfPass = new JPasswordField();
        pfPass.setBounds(300, 170, 150, 30);
        add(pfPass);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(280, 230, 100, 35);
        btnLogin.addActionListener(this);
        add(btnLogin);

        setVisible(true);
    }

    // Start Exam Screen
    public void startExam() {

        getContentPane().removeAll();
        repaint();

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 50, 600, 30);
        add(questionLabel);

        option1 = new JRadioButton();
        option1.setBounds(80, 100, 300, 30);

        option2 = new JRadioButton();
        option2.setBounds(80, 140, 300, 30);

        option3 = new JRadioButton();
        option3.setBounds(80, 180, 300, 30);

        option4 = new JRadioButton();
        option4.setBounds(80, 220, 300, 30);

        bg = new ButtonGroup();
        bg.add(option1);
        bg.add(option2);
        bg.add(option3);
        bg.add(option4);

        add(option1);
        add(option2);
        add(option3);
        add(option4);

        nextButton = new JButton("Next");
        nextButton.setBounds(100, 320, 100, 35);
        nextButton.addActionListener(this);
        add(nextButton);

        submitButton = new JButton("Submit");
        submitButton.setBounds(220, 320, 100, 35);
        submitButton.addActionListener(this);
        add(submitButton);

        updateButton = new JButton("Update Password");
        updateButton.setBounds(340, 320, 160, 35);
        updateButton.addActionListener(this);
        add(updateButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(520, 320, 100, 35);
        logoutButton.addActionListener(this);
        add(logoutButton);

        timerLabel = new JLabel("Time Left: 60");
        timerLabel.setBounds(550, 20, 120, 30);
        add(timerLabel);

        loadQuestion();

        // Timer
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft);

            if (timeLeft <= 0) {
                timer.stop();
                autoSubmit();
            }
        });

        timer.start();

        setVisible(true);
    }

    // Load Questions
    public void loadQuestion() {

        bg.clearSelection();

        questionLabel.setText(questions[current]);

        option1.setText(options[current][0]);
        option2.setText(options[current][1]);
        option3.setText(options[current][2]);
        option4.setText(options[current][3]);
    }

    // Check Answer
    public void checkAnswer() {

        if (option1.isSelected())
            selected = 1;
        else if (option2.isSelected())
            selected = 2;
        else if (option3.isSelected())
            selected = 3;
        else if (option4.isSelected())
            selected = 4;

        if (selected == answers[current]) {
            score++;
        }
    }

    // Auto Submit
    public void autoSubmit() {

        JOptionPane.showMessageDialog(this,
                "Time Over!\nYour Score: " + score + "/" + questions.length);

        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Login
        if (e.getSource() == btnLogin) {

            String user = tfUser.getText();
            String pass = new String(pfPass.getPassword());

            if (user.equals(username) && pass.equals(password)) {

                JOptionPane.showMessageDialog(this, "Login Successful");
                startExam();

            } else {

                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }

        // Next Button
        if (e.getSource() == nextButton) {

            checkAnswer();

            current++;

            if (current < questions.length) {
                loadQuestion();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Exam Completed\nYour Score: " + score + "/" + questions.length);

                System.exit(0);
            }
        }

        // Submit Button
        if (e.getSource() == submitButton) {

            checkAnswer();

            JOptionPane.showMessageDialog(this,
                    "Exam Submitted\nYour Score: " + score + "/" + questions.length);

            System.exit(0);
        }

        // Update Password
        if (e.getSource() == updateButton) {

            String newPass = JOptionPane.showInputDialog(this,
                    "Enter New Password:");

            if (newPass != null && !newPass.isEmpty()) {

                password = newPass;

                JOptionPane.showMessageDialog(this,
                        "Password Updated Successfully");
            }
        }

        // Logout
        if (e.getSource() == logoutButton) {

            JOptionPane.showMessageDialog(this, "Logged Out");

            System.exit(0);
        }
    }

    public static void main(String[] args) {

        new OnlineExamination();
    }
}