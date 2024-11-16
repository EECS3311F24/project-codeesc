package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.passwordHashing;

/**
 * This class holds the methods for querying the database used for the CodeEsc
 * project, specifically the queries needed for the login/sign-up form functionality.
 */
public class loginFormQueries {
	
	/**
	 * This method queries the database and checks whether an account with
	 * the given user-name already exists or not.
	 * 
	 * @param con        the connection to be used for the query against the database
	 * @param username   the user-name used for querying whether an account exists with it
	 * @return true if the account exists and false otherwise.
	 */
    public static boolean accountExists(Connection con, String username) {
        String query = "SELECT lusername FROM lib_account WHERE lusername = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String usernamez = resultSet.getString("lusername");
                System.out.println(usernamez);
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return false;
    }

    /**
	 * This method queries the database to add a new account into the 
	 * system with the given user-name and password parameters.
	 * 
	 * @param con        the connection to be used for the query against the database
	 * @param user       the user-name used for the creation of the account
	 * @param pass       the password used for the creation of the account
	 */
    public static void insertAcc(Connection con, String user, String pass) {
        String hashPass = passwordHashing.hashPassword(pass);
        String query = "INSERT INTO lib_account (lusername, lpassword) VALUES(?, ?)";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user);
            statement.setString(2, hashPass);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

    }

    /**
   	 * This method queries the database to sign-in into an existing account
   	 * in the database with the given user-name and password parameters.
   	 * 
   	 * @param con        the connection to be used for the query against the database
   	 * @param username   the user-name used for the account sign-in
   	 * @param password   the password used for the account sign-in
   	 * @return true if the user-name and password match an account in the database and false otherwise
   	 */
    public static boolean signInOrNot(Connection con, String username, String password) {
        String query = "SELECT lpassword FROM lib_account WHERE lusername = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String hashedPass = resultSet.getString("lpassword");
                return passwordHashing.passwordsMatch(password, hashedPass);
            }
            return false;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return false;
    }
}
