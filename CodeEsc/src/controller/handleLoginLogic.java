package controller;
import java.sql.Connection;

import model.Account;
import model.loginFormQueries;
import view.LoginForm;

/**
 * This is a controller class designed to hold all the methods for
 * the login/sign-up form to do with controlling the back-end logic by
 * performing interactions with the database and settings the normal and 
 * error flags within the login/sign-up form accordingly.
 */
public class handleLoginLogic {

	/**
	 * This method selects which controller method to call between handleActualLogin and handleActualSignUp
	 * for handling either a sign-up or login call from the user by checking the input LoginForm's isLogin
	 * flag which holds whether the login form is currently on the login or sign-up page.
	 * 
	 * @param form   the LoginForm that calls this method and holds the available information for a login or sign-up
	 * @param con    the database connection that is passed through to the eventual query methods against the SQL DB
	 */
    public static void handleLoginStart(LoginForm form, Connection con) {
        if(form.isLogin) {
            handleActualLogin(form, con);
        } else {
            handleActualSignUp(form, con);
        }
    }

    /**
     * This method is the login controller for the LoginForm which first takes in the inputs for the user-name, password, and confirm-password
     * and then validates them, makes sure the passwords match, and then either logs into the correct account or sets error flags and informs
     * the user by calling error-handling UI functions after getting the information from the database by calling query methods.
     * 
     * @param form   the LoginForm that holds all the information for the login
     * @param con    the connection to the database
     */
    public static void handleActualLogin(LoginForm form, Connection con) {
        String username = form.usernameField.getText();
        if(!loginFormQueries.accountExists(con, username)) {
            form.loginError = true;
            handleLoginInterface.handleError(form);
            return;
        }
        String password = form.passwordField.getText();
        if(loginFormQueries.signInOrNot(con, username, password)) {
            System.out.println("Signed in succesfully!");
            new Account(username, password, con, form);
        } else {
            form.loginError = true;
            handleLoginInterface.handleError(form);
        }
    }

    /**
     * This method is the sign-up controller for the LoginForm which first takes in the inputs for the user-name, password, and confirm-password
     * and then validates them, makes sure the passwords match, and then either registers a new account or sets error flags and informs
     * the user by calling error-handling UI functions after getting the information from the database by calling query methods.
     * 
     * @param form   the LoginForm that holds all the information for the sign-up
     * @param con    the connection to the database
     */
    public static void handleActualSignUp(LoginForm form, Connection con) {
        String username = form.usernameField.getText();
        if(loginFormQueries.accountExists(con, username)) {
            form.loginError = true;
            handleLoginInterface.handleError(form);
            return;
        }
        String password = form.passwordField.getText();
        loginFormQueries.insertAcc(con, username, password);
        handleLoginInterface.handleSignup(form);
    }
}
