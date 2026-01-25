package view;
import controller.handleMenuInterface;
import model.levelTimeQueries;


import javax.swing.*;

import java.awt.*;

/**
 * CodeEscSettings is the settings menu page for the CodeESC game. This class sets up
 * the JPanel and includes a go back button, settings panel, and the included settings functionality
 * buttons.
 */
public class CodeEscSettings extends JPanel {
    private JPanel settingsPanel;
    private JButton goBackBtn;
    private CodeEscMenu ownerMenu;
    private LevelPanel levelOneBtn;
    private LevelPanel levelTwoBtn;
    /**
     * Constructs the CodeEscSettings and initializes the GUI components. This includes
     * configuring the JPanel, adding the settingsPanel, the Go Back Button, and the buttons
     * for the settings functions.
     */
    public CodeEscSettings(CodeEscMenu ownerMenu) {
        this.ownerMenu = ownerMenu;
        setLayout(null);
        setOpaque(false);
        // Set up the JPanel
        setSize(800, 600);

        //Setting a JPanel to display buttons
        settingsPanel = new JPanel();
        settingsPanel.setBounds(480, 117, 280, 377);
        settingsPanel.setBackground(new Color(255,251,224,190));
        add(settingsPanel);

        //Setting a button to go back to main menu
        goBackBtn = new JButton();
        goBackBtn.setBounds(480, 500, 280, 40);
        goBackBtn.setText("Go back to Menu");
        goBackBtn.setBackground(new Color(255, 251, 224));
        goBackBtn.setFont(new Font("Bauhaus 93", Font.PLAIN, 20));
        goBackBtn.setFocusPainted(false);

        //Action listener for the go back button to go back to the main menu
        goBackBtn.addActionListener(e -> {
            handleMenuInterface.switchToSettings(ownerMenu, ownerMenu.switchSettingsFlag());
        });

        add(goBackBtn);

        //Setting up the three setting buttons and their UI (heavy code for these UI characteristics)
        JButton deleteLevelOneStats = new JButton("<html> Delete Level 1 times </html>");
        JButton deleteLevelTwoStats = new JButton("<html> Delete Level 2 times </html>");
        JButton deleteAccount = new JButton("<html> Delete account </html>");

        add(deleteLevelOneStats);
        add(deleteLevelTwoStats);
        add(deleteAccount);

        deleteLevelOneStats.setBounds(560, 140, 120, 80);
        deleteLevelTwoStats.setBounds(560, 230, 120, 80);
        deleteAccount.setBounds(560, 320, 120, 80);

        deleteLevelOneStats.setBackground(Color.red);
        deleteLevelTwoStats.setBackground(Color.red);
        deleteAccount.setBackground(Color.red);

        deleteLevelOneStats.setForeground(Color.white);
        deleteLevelTwoStats.setForeground(Color.white);
        deleteAccount.setForeground(Color.white);

        deleteLevelOneStats.setFocusPainted(false);
        deleteLevelTwoStats.setFocusPainted(false);
        deleteAccount.setFocusPainted(false);

        setComponentZOrder(deleteLevelOneStats, 0);
        setComponentZOrder(deleteLevelTwoStats, 0);
        setComponentZOrder(deleteAccount, 0);

        //Settings up the action listener for the button that deletes all level one times for logged in account.
        deleteLevelOneStats.addActionListener(e -> {
            levelTimeQueries.deleteTimes(ownerMenu.getAccount().getCon(), ownerMenu.getAccount().getUsername(), 1);
            ownerMenu.getMenuLevel().getLevelOneBtn().updateTime();
        });

        //Settings up the action listener for the button that deletes all level two times for logged in account.
        deleteLevelTwoStats.addActionListener(e -> {
            levelTimeQueries.deleteTimes(ownerMenu.getAccount().getCon(), ownerMenu.getAccount().getUsername(), 2);
            ownerMenu.getMenuLevel().getLevelTwoBtn().updateTime();
        });

        //Settings up the action listener for the button that deletes all account information including level times for logged in account.
        deleteAccount.addActionListener(e -> {
            levelTimeQueries.deleteAccount(ownerMenu.getAccount().getCon(), ownerMenu.getAccount().getUsername());
            ownerMenu.getAccount().logOut();
        });

    }
}
