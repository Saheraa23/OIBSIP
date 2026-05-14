import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class OnlineReservationSystem {
 // Reservation Data Class
    static class Reservation {
        String name;
        String trainNo;
        String trainName;
        String classType;
        String date;
        String from;
        String to;

        public Reservation(String name, String trainNo, String trainName,
                           String classType, String date, String from, String to) {
            this.name = name;
            this.trainNo = trainNo;
            this.trainName = trainName;
            this.classType = classType;
            this.date = date;
            this.from = from;
            this.to = to;
        }
    }

    // Store reservations using PNR
    static HashMap<String, Reservation> reservations = new HashMap<>();
    // Main Method
    public static void main(String[] args) {
        loginForm();
    }
    // Login Form
    public static void loginForm() {

        JFrame frame = new JFrame("Login Form");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("ONLINE RESERVATION SYSTEM");
        title.setBounds(70, 20, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 80, 100, 25);

        JTextField userField = new JTextField();
        userField.setBounds(150, 80, 150, 25);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 120, 100, 25);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 120, 150, 25);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(140, 170, 100, 30);

        frame.add(title);
        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginBtn);

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (username.equals("admin") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(frame, "Login Successful");
                    frame.dispose();
                    mainMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
                }
            }
        });

        frame.setVisible(true);
    }
    // Main Menu
    public static void mainMenu() {

        JFrame frame = new JFrame("Main Menu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("ONLINE RESERVATION SYSTEM");
        title.setBounds(70, 30, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        JButton reserveBtn = new JButton("Reservation Form");
        reserveBtn.setBounds(100, 90, 180, 40);

        JButton cancelBtn = new JButton("Cancellation Form");
        cancelBtn.setBounds(100, 150, 180, 40);

        frame.add(title);
        frame.add(reserveBtn);
        frame.add(cancelBtn);

        reserveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reservationForm();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancellationForm();
            }
        });

        frame.setVisible(true);
    }
    // Reservation Form
    public static void reservationForm() {

        JFrame frame = new JFrame("Reservation Form");
        frame.setSize(500, 500);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Passenger Name:");
        nameLabel.setBounds(50, 30, 120, 25);

        JTextField nameField = new JTextField();
        nameField.setBounds(200, 30, 200, 25);

        JLabel trainNoLabel = new JLabel("Train Number:");
        trainNoLabel.setBounds(50, 70, 120, 25);

        JTextField trainNoField = new JTextField();
        trainNoField.setBounds(200, 70, 200, 25);

        JLabel trainNameLabel = new JLabel("Train Name:");
        trainNameLabel.setBounds(50, 110, 120, 25);

        JTextField trainNameField = new JTextField();
        trainNameField.setBounds(200, 110, 200, 25);
        trainNameField.setEditable(false);

        JLabel classLabel = new JLabel("Class Type:");
        classLabel.setBounds(50, 150, 120, 25);

        String[] classes = {"Sleeper", "AC", "First Class"};
        JComboBox<String> classBox = new JComboBox<>(classes);
        classBox.setBounds(200, 150, 200, 25);

        JLabel dateLabel = new JLabel("Journey Date:");
        dateLabel.setBounds(50, 190, 120, 25);

        JTextField dateField = new JTextField();
        dateField.setBounds(200, 190, 200, 25);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(50, 230, 120, 25);

        JTextField fromField = new JTextField();
        fromField.setBounds(200, 230, 200, 25);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(50, 270, 120, 25);

        JTextField toField = new JTextField();
        toField.setBounds(200, 270, 200, 25);

        JButton insertBtn = new JButton("Insert");
        insertBtn.setBounds(180, 340, 100, 35);

        // Auto Train Name
        trainNoField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {

                String trainNo = trainNoField.getText();

                if (trainNo.equals("101")) {
                    trainNameField.setText("Chennai Express");
                } else if (trainNo.equals("102")) {
                    trainNameField.setText("Coimbatore Express");
                } else if (trainNo.equals("103")) {
                    trainNameField.setText("Madurai Superfast");
                } else {
                    trainNameField.setText("Unknown Train");
                }
            }
        });

        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String pnr = String.valueOf((int)(Math.random() * 100000));

                Reservation r = new Reservation(
                        nameField.getText(),
                        trainNoField.getText(),
                        trainNameField.getText(),
                        classBox.getSelectedItem().toString(),
                        dateField.getText(),
                        fromField.getText(),
                        toField.getText()
                );

                reservations.put(pnr, r);

                JOptionPane.showMessageDialog(frame,
                        "Reservation Successful\nPNR Number: " + pnr);
            }
        });
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(trainNoLabel);
        frame.add(trainNoField);
        frame.add(trainNameLabel);
        frame.add(trainNameField);
        frame.add(classLabel);
        frame.add(classBox);
        frame.add(dateLabel);
        frame.add(dateField);
        frame.add(fromLabel);
        frame.add(fromField);
        frame.add(toLabel);
        frame.add(toField);
        frame.add(insertBtn);

        frame.setVisible(true);
    }
    // Cancellation Form
    public static void cancellationForm() {

        JFrame frame = new JFrame("Cancellation Form");
        frame.setSize(450, 350);
        frame.setLayout(null);

        JLabel pnrLabel = new JLabel("Enter PNR Number:");
        pnrLabel.setBounds(40, 40, 150, 25);

        JTextField pnrField = new JTextField();
        pnrField.setBounds(190, 40, 150, 25);

        JTextArea details = new JTextArea();
        details.setBounds(40, 90, 350, 120);
        details.setEditable(false);

        JButton checkBtn = new JButton("Search");
        checkBtn.setBounds(150, 230, 100, 30);

        frame.add(pnrLabel);
        frame.add(pnrField);
        frame.add(details);
        frame.add(checkBtn);

        checkBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String pnr = pnrField.getText();

                if (reservations.containsKey(pnr)) {

                    Reservation r = reservations.get(pnr);

                    details.setText(
                            "Passenger Name: " + r.name + "\n" +
                            "Train No: " + r.trainNo + "\n" +
                            "Train Name: " + r.trainName + "\n" +
                            "Class Type: " + r.classType + "\n" +
                            "Journey Date: " + r.date + "\n" +
                            "From: " + r.from + "\n" +
                            "To: " + r.to
                    );

                    int option = JOptionPane.showConfirmDialog(
                            frame,
                            "Do you want to cancel this ticket?",
                            "Confirm Cancellation",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (option == JOptionPane.YES_OPTION) {
                        reservations.remove(pnr);
                        JOptionPane.showMessageDialog(frame,
                                "Ticket Cancelled Successfully");
                        details.setText("");
                    }

                } else {
                    JOptionPane.showMessageDialog(frame,
                            "PNR Number Not Found");
                }
            }
        });

        frame.setVisible(true);
    }
}