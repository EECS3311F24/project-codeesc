package model;
import java.sql.Connection;

import view.CodeEscMenu;
import view.LoginForm;

/**
 * This class represents an Account and will be used within the project to model
 * the data that surrounds an account that is logged into the system.
 */
public class Account {
	//The user-name of the account.
    private String username;
    //The password of the account which is hashed for security reasons.
    private String hashedPass;
    //The database connection that this account currently holds.
    private Connection connection;
    //The Login Form that this account used to log in.
    private LoginForm form;
    //The Main Form, or the main menu that is launched when an account initializes itself with the system.
    private CodeEscMenu menu;

    /**
     * A constructor to make a new account, when a new account is constructed the constructor
     * also implicitly calls the initializeProgram method to launch the main menu of the program.
     * @param username   the user-name of the account
     * @param hashedPass the hashed password of the account
     * @param con        the connection to the database the account will use
     * @param form       the login form for the account to access if needed to log out
     */
    public Account(String username, String hashedPass, Connection con, LoginForm form) {
        this.username = username;
        this.hashedPass = hashedPass;
        this.connection = con;
        this.form = form;
        this.menu = new CodeEscMenu(this);
        initializeProgram();
    }

    /**
     * Returns the user-name of the account.
     * @return user-name  the user-name of the account.
     */
    public String getUsername() { return username; }
    
    /**
     * Returns the password of the account hashed.
     * @return hashedPass  the password of the account hashed.
     */
    public String getHash() { return hashedPass; }

    /**
     * Returns the connection to the database that this account uses.
     * @return connection  the connection to the database that this account uses.
     */
    public Connection getCon() { return this.connection; }
    
    /**
     * Initializes the program after login and this account is created by
     * making the login/sign-up form invisible and making the main menu 
     * visible.
     */
    private void initializeProgram() {
        form.frame.setVisible(false);
        menu.setVisible(true);
    }

    /**
     * Logs out of this account by settings all the attributes of the account to null 
     * for the garbage collector to recycle and then making a new Login Form while reusing
     * the database connection of this account to allow the user to use the sign-up/login form 
     * again.
     */
    private void logOut() {
        this.username = null;
        this.hashedPass = null;
        this.form = null;
        this.menu.setVisible(false);
        this.menu = null;
        new LoginForm(connection);
        this.connection = null;
    }
}
