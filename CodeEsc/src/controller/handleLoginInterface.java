package controller;
import javax.swing.*;

import view.LoginForm;

import java.awt.*;

/**
 * This is a controller class designed to hold all the methods for
 * the login/sign-up form to do with controlling the UI such as switching
 * between the sign-up and login pages while also handling the displays of
 * validation and login/sign-up errors on the login form UI.
 */
public class handleLoginInterface {

	/**
	 * This method takes a login/sign-up form and then switches the UI to either
	 * the sign-up or login page, depending on the isLogin boolean flag which 
	 * specifies which one to switch to.
	 * 
	 * @param form      the form that is used to switch between the login and sign-up pages.
	 * @param isLogin   the flag that holds whether the form is to be switched to a login or sign-up page.
	 * @return
	 */
    public static boolean handleLoginSwitch(LoginForm form, boolean isLogin) {
        clearError(form);
        if(isLogin) {
            form.frame.setTitle("CodeEsc - Login");
            form.changeFormLabel.setText("Don't have an account?");
            form.signInLabel.setText("Login");
            form.changeFormToSignUp.setText("Sign up");
            form.signInButton.setText("Sign in!");
            form.logoLabel.setText("CodeEsc - Welcome back!");
            form.usernameField.setText("");
            form.passwordField.setText("");
            form.confirmField.setText("");
        } else {
            form.frame.setTitle("CodeEsc - Sign Up");
            form.changeFormLabel.setText("Have an account?");
            form.signInLabel.setText("Sign up");
            form.changeFormToSignUp.setText("Sign in");
            form.signInButton.setText("Sign up!");
            form.logoLabel.setText("CodeEsc - Join now!");
            form.usernameField.setText("");
            form.passwordField.setText("");
            form.confirmField.setText("");
        }
        return isLogin;
    }

    /**
     * This method takes in a login/sign-up form and then reads it's boolean error
     * flags to then properly display the error messages on the UI in a visually appealing
     * and clean way, these errors include login, sign-up, password matching and validation errors.
     * 
     * @param form    the LoginForm which to change the UI to display the errors, if any.
     */
    public static void handleError(LoginForm form) {
        if(form.validError) {
            form.errorLabel.setForeground(Color.red);
            form.usernameField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            form.confirmField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            form.passwordField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            form.errorLabel.setText("");
            form.validLabel.setText("<html> Inputs are invalid: Please make sure all inputs have a length of 4 to 14 characters. </html>");
        } else if(form.passError) {
            form.errorLabel.setForeground(Color.red);
            form.usernameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            form.confirmField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            form.passwordField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            form.errorLabel.setText("Passwords arent matching!");
            form.validLabel.setText("");
        } else if(form.loginError && form.isLogin) {
            form.errorLabel.setForeground(Color.red);
            form.usernameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            form.confirmField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            form.passwordField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            form.errorLabel.setText("Username or password is wrong!");
            form.validLabel.setText("");
        } else if(form.loginError && !form.isLogin) {
            form.errorLabel.setForeground(Color.red);
            form.usernameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            form.confirmField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            form.passwordField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            form.errorLabel.setText("Account already exists!");
            form.validLabel.setText("");
        } else {
            form.errorLabel.setForeground(Color.red);
            form.usernameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            form.confirmField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            form.passwordField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            form.errorLabel.setText("");
            form.validLabel.setText("");
        }
    }

    /**
     * This method takes in a login/sign-up form and then clears all the errors from it's
     * UI, if any.
     * 
     * @param form   the LoginForm which to change the UI to erase the errors, if any.
     */
    public static void clearError(LoginForm form) {
        form.confirmField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        form.passwordField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        form.errorLabel.setText("");
    }

    /**
     * This method takes in a login/sign-up form and then checks if the input values
     * in it's password and confirm password fields are the same.
     * 
     * @param form   the LoginForm which to check if the password and confirm password fields have the same input
     * @return true if the passwords are the same and false otherwise
     */
    public static boolean passwordCheck(LoginForm form) {
        String passwordValue = form.passwordField.getText();
        String confirmValue = form.confirmField.getText();
        if(passwordValue.equals(confirmValue)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * This method takes in a login/sign-up form and then validates the user-name, password, and confirm password
     * inputs by making sure they all fit within the input restrictions of having a length between 4 and 14.
     * 
     * @param form   the LoginForm which to check if user-name, password, and confirm password fields all have validated inputs
     * @return true if all inputs pass the validation restrictions and false otherwise
     */
    public static boolean validCheck(LoginForm form) {
        String usernameValue = form.usernameField.getText();
        String passValue = form.passwordField.getText();
        String confirmValue = form.confirmField.getText();

        if(usernameValue.length() > 14 || usernameValue.length() < 4) {
            return false;
        }
        if(passValue.length() > 14 || passValue.length() < 4) {
            return false;
        }
        if(confirmValue.length() > 14 || confirmValue.length() < 4) {
            return false;
        }
        return true;
    }
    
    /**
     * This method takes in a login/sign-up form and then displays the UI changes
     * for a visually appealing and clean design show-case of a successful registration.
     * 
     * @param form   the LoginForm to show the UI changes for a successful registration
     */
    public static void handleSignup(LoginForm form) {
        form.errorLabel.setForeground(Color.green);
        form.usernameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        form.confirmField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        form.passwordField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        form.errorLabel.setText("Successfully registered!");
        form.usernameField.setText("");
        form.passwordField.setText("");
        form.confirmField.setText("");
        form.validLabel.setText("");
    }
}
