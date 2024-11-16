import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import view.CodeEscLevelOne;
import view.CodeEscMenu;
import view.LoginForm;

/**
 * This class is the Main class and holds the only main method
 * within the CodeEsc project, it is the entry point of the program
 * and is also used to setup the initial database connection for the
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
        String url = "jdbc:mysql://us-cluster-east-01.k8s.cleardb.net:3306/heroku_fdeac7008cbe170?reconnect=true";
        String username = "b7b93c6c56405e";
        String password = "9d108301";

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Connected to the database!");
                new LoginForm(connection);
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception: " + e.getMessage());
        }

    }
}
