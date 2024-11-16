package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class holds the methods for querying the database used for the CodeEsc
 * project, specifically the queries needed for the levels time feature functionality.
 */
public class levelTimeQueries {

    /**
     * This method queries the database to insert a new time, which is the time
     * taken for a user to beat a specific level within the project.
     *
     * @param con    the connection used to query the database
     * @param user   the user who's time to insert for the beaten level
     * @param level  the level that the user beat for this time
     * @param time   the time that it took the user to beat this level
     */
    public static void insertTime(Connection con, String user, int level, int time) {
        String query = "INSERT INTO levelTime (lusername, escLevel, escTime) VALUES(?, ?, ?)";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user);
            statement.setInt(2, level);
            statement.setInt(3, time);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

    }

    /**
     * This method queries the database to get the best time for
     * a user and level combination, simply the best time a user has gotten
     * on the specified level within the project.
     *
     * @param con    the connection used to query the database
     * @param user   the user who's time to retrieve for the chosen level
     * @param level  the level to get the time for
     *
     * @return   the String representation of the user's best time on a specific level, "None" if there isn't a time stored
     */
    public static String getBestTime(Connection con, String user, int level) {
        String query = "SELECT escTime FROM levelTime WHERE lusername = ? AND escLevel = ? ORDER BY escTime";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user);
            statement.setInt(2, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int timeAsInt = resultSet.getInt("escTime");
                String timeAsString = String.format("%02d:%02d", timeAsInt/60, timeAsInt%60);
                return timeAsString;
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return "None";
    }
}
