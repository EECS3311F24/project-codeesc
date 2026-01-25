package view;
import controller.handleMenuInterface;


import javax.swing.*;

import java.awt.*;

/**
 * CodeEscLevels is the level menu page for the CodeESC game. This class sets up
 * the JPanel and includes a go back button, level panel, and level buttons.
 */
public class CodeEscLevels extends JPanel {
    private JPanel levelPanel;
    private JButton goBackBtn;
    private CodeEscMenu ownerMenu;
    private LevelPanel levelOneBtn;
    private LevelPanel levelTwoBtn;
    /**
     * Constructs the CodeEscLevels and initializes the GUI components. This includes
     * configuring the JPanel, adding the levelPanel, the Go Back Button, and the buttons
     * for the levels.
     */
    public CodeEscLevels(CodeEscMenu ownerMenu) {
        this.ownerMenu = ownerMenu;
        setLayout(null);
        setOpaque(false);
        // Set up the JPanel
        setSize(800, 600);

        //Setting a JPanel to display levels
        levelPanel = new JPanel();
        levelPanel.setBounds(480, 117, 280, 377);
        levelPanel.setBackground(new Color(255,251,224,190));
        add(levelPanel);

        //Setting a button to go back to main menu
        goBackBtn = new JButton();
        goBackBtn.setBounds(480, 500, 280, 40);
        goBackBtn.setText("Go back to Menu");
        goBackBtn.setBackground(new Color(255, 251, 224));
        goBackBtn.setFont(new Font("Bauhaus 93", Font.PLAIN, 20));
        goBackBtn.setFocusPainted(false);

        //Action listener for the go back button to go back to the main menu
        goBackBtn.addActionListener(e -> {
            handleMenuInterface.switchToPlay(ownerMenu, ownerMenu.switchPlayFlag());
        });

        add(goBackBtn);

        //Creating button to access level 1 (Major feature of Sprint 2)
        levelOneBtn = new LevelPanel(1, ownerMenu.getAccount(), "levelOneBackground.png");
        levelOneBtn.setLocation(560, 155);
        add(levelOneBtn);
        this.setComponentZOrder(levelOneBtn, 0);

        //Adding action listener for level 1 button to start level one
        levelOneBtn.addActionListener(e -> {
            new CodeEscLevelOne(ownerMenu);
        });

        //Creating button to access level 2 (Major feature of Sprint 3)
        levelTwoBtn = new LevelPanel(2, ownerMenu.getAccount(), "levelTwoBackground.png");
        levelTwoBtn.setLocation(560, 320);
        add(levelTwoBtn);
        this.setComponentZOrder(levelTwoBtn, 0);

        //Adding action listener for level 2 button to (currently open a dialog to show it's not added yet)
        levelTwoBtn.addActionListener(e -> {
            new CodeEscLevelTwo(ownerMenu);
        });

    }

    /**
     *
     * @return   the JButton for accessing level one
     */
    public LevelPanel getLevelOneBtn() { return levelOneBtn; }

    /**
     *
     * @return   the JButton for accessing level two
     */
    public LevelPanel getLevelTwoBtn() { return levelTwoBtn; }
}
