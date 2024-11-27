package model;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    /**
     * This method queries the database to delete all level times for
     * a specific level and user combination.
     *
     * @param con    the connection used to query the database
     * @param user   the user who's time to delete for specific level
     * @param level  the level which the user specified their times to be deleted for
     */
    public static void deleteTimes(Connection con, String user, int level) {
        String query = "DELETE FROM levelTime WHERE lusername = ? AND escLevel = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user);
            statement.setInt(2, level);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

    }

    /**
     * This method queries the database to delete an account and all
     * their related information, specifically their level times.
     *
     * @param con    the connection used to query the database
     * @param user   the user who's time to delete for specific level
     */
    public static void deleteAccount(Connection con, String user) {
        String query1 = "DELETE FROM levelTime WHERE lusername = ? AND escLevel = 1";
        String query2 = "DELETE FROM levelTime WHERE lusername = ? AND escLevel = 2";
        String query3 = "DELETE FROM lib_account WHERE lusername = ?";

        try (PreparedStatement statement = con.prepareStatement(query1)) {
            statement.setString(1, user);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

        try (PreparedStatement statement = con.prepareStatement(query2)) {
            statement.setString(1, user);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

        try (PreparedStatement statement = con.prepareStatement(query3)) {
            statement.setString(1, user);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

    }

    /**
     * This method queries the database to get the 5 best times for the given level
     * and then adds them to the given leaderboard JPanel.
     *
     * @param con    the connection used to query the database
     * @param level  the level for which to find the best times for
     * @param panel  the panel to add the leaderboard times too
     */
    public static void getLeaderboardTimes(Connection con, int level, JPanel panel) {
        String query = "SELECT lusername, escLevel, MIN(escTime) AS bestTime FROM levelTime WHERE escLevel = ? GROUP BY lusername, escLevel ORDER BY bestTime ASC;";
        ArrayList<String> resultList = new ArrayList<>();
        int index = 0;

        JLabel titleLabel = new JLabel("Level " + level + ":");
        titleLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, level);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if(index == 5) { break; }
                panel.add(Box.createRigidArea(new Dimension(0, 4)));
                int levelTime = resultSet.getInt("bestTime");
                JLabel toAdd = new JLabel(resultSet.getString("lusername") + ": " + String.format("%02d:%02d", levelTime/60, levelTime%60));
                toAdd.setFont(new Font("Bauhaus 93", Font.PLAIN, 16));
                toAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(toAdd);
                index++;
            }
            while(index != 5) {
                panel.add(Box.createRigidArea(new Dimension(0, 4)));
                JLabel toAdd = new JLabel("None: None");
                toAdd.setFont(new Font("Bauhaus 93", Font.PLAIN, 16));
                toAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(toAdd);
                index++;
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }


}
