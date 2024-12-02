import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.Account;
import view.CodeEscLevelOne;
import view.CodeEscLevelTwo;
import view.CodeEscMenu;
import view.LoginForm;

/**
 * This class is the Main class and holds the only main method
 * within the CodeEsc project, it is the entry point of the program
 * and is also used to set up the initial database connection for the
 * project.
 */
public class Main {

	/**
	 * This is the main method and entry point for the CodeEsc project, it first 
	 * sets up a connection to the SQL Database used throughout the whole project and then launches
	 * a new Login form for the user to start with at first.
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        String url = "jdbc:postgresql://cfls9h51f4i86c.cluster-czrs8kj4isg7.us-east-1.rds.amazonaws.com:5432/deuac2c9jabrva";
        String username = "u5mompi8u4sa3u";
        String password = "p83fe42759727b47410e493fbd840b5b4e3013df5c2a5c496572699bf02c0939f";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Connected to the database!");
                new LoginForm(connection);
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
