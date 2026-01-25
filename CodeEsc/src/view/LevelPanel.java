package view;

import javax.swing.*;
import java.awt.*;

import model.Account;
import model.levelTimeQueries;

/**
 * This class is used to hold a LevelPanel that indeed extends
 * the JButton class for Action Listener functionality, yet UI wise
 * it functionally is like a panel due to providing so many details to the user.
 * It provides features like providing the user their best time for the level and a visually
 * appealing UI with JLabel styling and backgroundImage features.
 */
public class LevelPanel extends JButton {
    //The level this LevelPanel represents
    private int level;
    //The label for the best time the user has for this level
    private JLabel timeLabel;
    //The account that is accessing this LevelPanel
    private Account account;

    /**
     * Constructs a new LevelPanel with the given parameters
     * for configuring it.
     *
     * @param level      the level this LevelPanel represents
     * @param account    the account that is accessing this LevelPanel
     * @param imageLink  the image link to the background image to be used for this LevelPanel
     */
    public LevelPanel(int level, Account account, String imageLink) {
        this.account = account;
        this.level = level;
        setLayout(null);
        setSize(120, 140);

        JLabel topLabel = new JLabel();
        topLabel.setOpaque(true);
        topLabel.setText("Level" + level);
        topLabel.setForeground(new Color(255, 251, 224));
        topLabel.setBackground(new Color(90, 90, 90, 180));
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLabel.setVerticalAlignment(SwingConstants.CENTER);
        topLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 18));

        timeLabel = new JLabel();
        timeLabel.setOpaque(true);
        timeLabel.setText("Best time: " + levelTimeQueries.getBestTime(account.getCon(), account.getUsername(), level));
        timeLabel.setForeground(new Color(255, 251, 224));
        timeLabel.setBackground(new Color(90, 90, 90, 180));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setVerticalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Bauhaus 93", Font.ITALIC, 14));

        topLabel.setBounds(0, 0, 120, 70);
        timeLabel.setBounds(0, 70, 120, 70);

        // Load the background image from resources
        ImageIcon tempBackgroundImage = new ImageIcon(imageLink);
        Image backgroundImage = tempBackgroundImage.getImage();

        // Adding a JPanel to display the background
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(null);
        backgroundPanel.setSize(120, 140);

        add(backgroundPanel);
        backgroundPanel.add(topLabel);
        backgroundPanel.add(timeLabel);
    }

    /**
     * This is a no-argument constructor for the LevelPanel,
     * which is only used to show levels that are not implemented
     * yet.
     */
    public LevelPanel() {
        this.level = level;
        setLayout(null);
        setSize(120, 140);
        setBackground(Color.black);

        JLabel topLabel = new JLabel();
        topLabel.setText("COMING SOON");
        topLabel.setForeground(new Color(255, 251, 224));
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLabel.setVerticalAlignment(SwingConstants.CENTER);
        topLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 14));


        JLabel timeLabel = new JLabel();
        timeLabel.setText("Best time: None");
        timeLabel.setForeground(new Color(255, 251, 224));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setVerticalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Bauhaus 93", Font.ITALIC, 14));

        topLabel.setBounds(0, 0, 120, 70);
        timeLabel.setBounds(0, 70, 120, 70);

        add(topLabel);
        add(timeLabel);
    }

    /**
     * This method is used to query the backend methods which query
     * the database to retrieve the best time the user has on the
     * level that this LevelPanel represents.
     */
    public void updateTime() {
        timeLabel.setText("Best time: " + levelTimeQueries.getBestTime(account.getCon(), account.getUsername(), level));
    }
}
