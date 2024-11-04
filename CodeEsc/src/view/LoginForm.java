package view;
import javax.swing.*;
import javax.swing.border.Border;

import controller.handleLoginInterface;
import controller.handleLoginLogic;
import utils.interfaceHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * This class is the Login Form, and the first thing the user sees after
 * launching the program. This class contains the visual UI of the login form, and has 
 * various event listeners on it's components to call controller methods to do the
 * back-end logic of sign-up and login actions. This login form features a visually
 * appealing UI that is easy for users to interact with.
 */
public class LoginForm {
	//The main frame of the Form.
    public LoginFrame frame;
    //The left pane of the form where the inputs are.
    public JPanel leftPane;
    //The right pane of the form which holds just some UI design.
    public JPanel rightPane;
    //The text label for the sign in button.
    public JLabel signInLabel;
    //The text label for the user-name text field.
    public JLabel usernameLabel;
    //The text label for the password text field.
    public JLabel passwordLabel;
    //The text label for the confirm-password text field.
    public JLabel confirmLabel;
    //The sign in/sign up button.
    public JButton signInButton;
    //The user-name text field.
    public JTextField usernameField;
    //The password text field.
    public JPasswordField passwordField;
    //The confirm-password text field.
    public JPasswordField confirmField;
    //The label that holds the login form image.
    public JLabel imageHolder;
    //The label that holds the logo.
    public JLabel logoLabel;
    //The label that directs the user towards changing the field to either login or sign-up.
    public JLabel changeFormLabel;
    //The button that actually changes the field to either login or sign-up.
    public JButton changeFormToSignUp;
    //The text label that provides some info for the form.
    public JLabel infoLabel;
    //A boolean flag that indicates whether the form is currently on the login page (true) or on the sign-up page (false)
    public boolean isLogin;
    //A boolean flag for a password error.
    public boolean passError;
    //A boolean flag for a general login/sign-up error.
    public boolean loginError;
    //A boolean flag for a validation error.
    public boolean validError;
    //The text label that shows the user their corresponding error information.
    public JLabel errorLabel;
    //The text label that shows the user their corresponding validation error information.
    public JLabel validLabel;
    //The connection of the login form to the database it queries.
    private Connection connection;


    /**
     * This constructor sets up a new LoginForm by first constructing a background frame and grabbing
     * an SQL DB connection from it's parameters, and then calling various methods of the class to construct
     * the different needed UI components for the form.
     * 
     * @param sqlCon   the connection to the SQL database that the login form uses
     */
    public LoginForm(Connection sqlCon) {
        frame = new LoginFrame(true);
        this.isLogin = true;
        this.connection = sqlCon;
        setupPanels();
        setupLabels();
        setupInputs();
        setupErrors();
        frame.setVisible(true);
    }
    
    /**
     * This method sets up the various UI panels used within the login form,
     * it is called in the main constructor.
     */
    public void setupPanels() {
        frame.setLayout(null);
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setBounds(0,0,500,600);
        rightPanel.setBounds(500,0,500,600);
        leftPanel.setBackground(new Color(0xf2e9e4));
        rightPanel.setBackground(new Color(0x582f0e));
        leftPane = leftPanel;
        rightPane = rightPanel;
        this.frame.add(this.leftPane);
        this.frame.add(this.rightPane);
    }

    /**
     * This method sets up the various UI labels used within the login form,
     * it is called in the main constructor.
     */
    public void setupLabels() {
        JLabel signInLabel = new JLabel();
        JLabel usernameLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel confirmLabel = new JLabel();
        JLabel changeFormLabel = new JLabel();
        JLabel infoLabel = new JLabel();

        ImageIcon backImage = new ImageIcon("background.png");
        backImage = interfaceHelper.resizeIcon(backImage, 500, 400);
        JLabel imageHolder = new JLabel();
        imageHolder.setIcon(backImage);
        JLabel logoLabel = new JLabel();
        logoLabel.setForeground(Color.white);

        infoLabel.setText("<html>We are glad to have you back</html>");
        signInLabel.setText("Login");
        usernameLabel.setText("Enter Username:");
        passwordLabel.setText("Enter Password:");
        confirmLabel.setText("Confirm Password:");
        logoLabel.setText("CodeEsc - Welcome back!");
        changeFormLabel.setText("Don't have an account?");

        signInLabel.setFont(new Font("Montserrat", Font.BOLD, 26));
        usernameLabel.setFont(new Font("Montserrat", Font.ITALIC, 15));
        passwordLabel.setFont(new Font("Montserrat", Font.ITALIC, 15));
        confirmLabel.setFont(new Font("Montserrat", Font.ITALIC, 15));
        logoLabel.setFont(new Font("Montserrat", Font.ITALIC, 26));
        infoLabel.setFont(new Font("Montserrat", Font.ITALIC, 20));
        usernameLabel.setForeground(Color.black);
        passwordLabel.setForeground(Color.black);
        confirmLabel.setForeground(Color.black);


        leftPane.setLayout(null);
        rightPane.setLayout(null);

        signInLabel.setBounds(30, 90, 150, 30);
        usernameLabel.setBounds(30,200, 150, 24);
        passwordLabel.setBounds(30, 250, 150, 24);
        confirmLabel.setBounds(30, 300, 150, 24);
        imageHolder.setBounds(0, 190, 500, 222);
        logoLabel.setBounds(35, 90, 500, 30);
        changeFormLabel.setBounds(30, 420, 160, 16);
        infoLabel.setBounds(30, 115, 400, 55);


        this.infoLabel = infoLabel; this.changeFormLabel = changeFormLabel; this.signInLabel = signInLabel; this.usernameLabel = usernameLabel; this.passwordLabel = passwordLabel; this.confirmLabel = confirmLabel; this.imageHolder = imageHolder; this.logoLabel = logoLabel;
        leftPane.add(this.signInLabel);
        leftPane.add(this.usernameLabel);
        leftPane.add(this.passwordLabel);
        leftPane.add(this.confirmLabel);
        leftPane.add(this.changeFormLabel);
        rightPane.add(this.imageHolder);
        rightPane.add(this.logoLabel);

    }

    /**
     * This method sets up the various user interactive inputs used within the login form (such as buttons and text fields),
     * it is called in the main constructor.
     */
    public void setupInputs() {
        JButton signInButton = new JButton();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField ();
        JPasswordField confirmField = new JPasswordField ();
        JButton changeFormToSignUp = new JButton();

        changeFormToSignUp.setText("Sign up");
        changeFormToSignUp.setBackground(new Color(0xf2e9e4));
        changeFormToSignUp.setForeground(new Color(0x5F4B66));
        changeFormToSignUp.setBorder(null);
        changeFormToSignUp.setFocusPainted(false);

        usernameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        confirmField.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        LoginForm toPass = this;
        changeFormToSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLogin = handleLoginInterface.handleLoginSwitch(toPass, !isLogin);
            }
        });

        signInButton.setText("Sign in!");
        signInButton.setBackground(new Color(0x648DE5));
        signInButton.setForeground(Color.white);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!handleLoginInterface.validCheck(toPass)) {
                    toPass.validError = true;
                    toPass.loginError = false;
                    toPass.passError = false;
                    handleLoginInterface.handleError(toPass);
                    return;
                }

                if(handleLoginInterface.passwordCheck(toPass)) {
                    toPass.passError = false;
                    toPass.loginError = false;
                    toPass.validError = false;
                    handleLoginInterface.handleError(toPass);
                    handleLoginLogic.handleLoginStart(toPass, toPass.connection);
                } else {
                    toPass.passError = true;
                    toPass.loginError = false;
                    toPass.validError = false;
                    handleLoginInterface.handleError(toPass);
                }
            }
        });

        signInButton.setBounds(30, 380, 370, 30);
        changeFormToSignUp.setBounds(165, 420, 45, 16);
        usernameField.setBounds(180, 200, 220, 24);
        passwordField.setBounds(180, 250, 220, 24);
        confirmField.setBounds(180, 300, 220, 24);


        this.signInButton = signInButton; this.usernameField = usernameField; this.passwordField = passwordField; this.confirmField = confirmField; this.changeFormToSignUp = changeFormToSignUp;
        leftPane.add(this.signInButton);
        leftPane.add(this.changeFormToSignUp);
        leftPane.add(this.usernameField);
        leftPane.add(this.passwordField);
        leftPane.add(this.confirmField);
    }

    /**
     * This method sets up the UI components for possible errors and the error flags,
     * it is called in the main constructor.
     */
    public void setupErrors() {
        passError = false;
        loginError = false;
        validError = false;

        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
        errorLabel.setBounds(180, 320, 220, 24);

        JLabel validLabel = new JLabel();
        validLabel.setForeground(Color.red);
        validLabel.setFont(new Font("Montserrat", Font.PLAIN, 11));
        validLabel.setBounds(405, 115, 60, 300);

        this.validLabel = validLabel;
        this.errorLabel = errorLabel;
        leftPane.add(this.errorLabel);
        leftPane.add(this.validLabel);
    }


}
