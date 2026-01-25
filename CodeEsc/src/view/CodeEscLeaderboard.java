package view;
import controller.handleMenuInterface;
import model.levelTimeQueries;


import javax.swing.*;

import java.awt.*;

/**
 * CodeEscLeaderboard is the leaderboard menu page for the CodeESC game. This class sets up
 * the JPanel and includes a go back button and leaderboard panel with specified times.
 */
public class CodeEscLeaderboard extends JPanel {
    private JPanel leaderboardPanel;
    private JButton goBackBtn;
    private CodeEscMenu ownerMenu;
    /**
     * Constructs the CodeEscLeaderboard and initializes the GUI components. This includes
     * configuring the JPanel, adding the leaderboardPanel, the Go Back Button and the times.
     */
    public CodeEscLeaderboard(CodeEscMenu ownerMenu) {
        this.ownerMenu = ownerMenu;
        setLayout(null);
        setOpaque(false);
        // Set up the JPanel
        setSize(800, 600);

        //Setting a JPanel to display leaderboard
        leaderboardPanel = new JPanel();
        leaderboardPanel.setBounds(380, 117, 380, 377);
        leaderboardPanel.setBackground(new Color(255,251,224,190));
        add(leaderboardPanel);

        //Setting a button to go back to main menu
        goBackBtn = new JButton();
        goBackBtn.setBounds(480, 500, 280, 40);
        goBackBtn.setText("Go back to Menu");
        goBackBtn.setBackground(new Color(255, 251, 224));
        goBackBtn.setFont(new Font("Bauhaus 93", Font.PLAIN, 20));
        goBackBtn.setFocusPainted(false);

        //Action listener for the go back button to go back to the main menu
        goBackBtn.addActionListener(e -> {
            handleMenuInterface.switchToLeaderboard(ownerMenu, ownerMenu.switchLeaderboardFlag());
        });

        add(goBackBtn);

        //Calling method to initialize the leaderboard times
        updateLeaderboard();
    }

    /**
     * This method clears the leaderboard panel and then freshly grabs the 5 best times
     * for both levels within the project (Level One and Two) and adds their info to
     * the panel after calling some backend methods.
     */
    public void updateLeaderboard() {
        //Clearing the panel first.
        leaderboardPanel.removeAll();

        //Setting up layout for the leaderboard panel to have a lot of jlabels added to it for the best times.
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        leaderboardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboardPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        leaderboardPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //Calling backend methods to set up the leaderboard panel with actual times
        levelTimeQueries.getLeaderboardTimes(ownerMenu.getAccount().getCon(), 1, leaderboardPanel);
        leaderboardPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        levelTimeQueries.getLeaderboardTimes(ownerMenu.getAccount().getCon(), 2, leaderboardPanel);
    }

}
