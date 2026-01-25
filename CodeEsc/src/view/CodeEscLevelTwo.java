package view;

import controller.handleLevelInterface;
import controller.handleLevelOneLogic;
import controller.handleLevelTwoLogic;
import model.levelTimeQueries;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the UI representation of Level Two within the CodeEsc
 * project, it uses a background image and timer to set up its UI,
 * and then has its own logic handler to handle the user interaction within
 * the actual level.
 */
public class CodeEscLevelTwo extends JFrame {
    // The timer on top of the level for the user to see and track
    private levelTimer timer;
    // The CodeEsc menu that owns this level and is made invisible when this level is being played
    private CodeEscMenu ownerMenu;
    // The background panel that sets up the actual visuals of the Escape Room within the level.
    private BackgroundPanel backgroundPanel;
    // The hint label used within
    private JLabel hintLabel;

    /**
     * Constructs a new CodeEscLevelTwo instance, makes the CodeEsc menu invisible and itself visible,
     * and starts the timer for the user to start playing the game.
     *
     * @param ownerMenu   the menu that "owns" this level and is to be made invisible while level is playing
     */
    public CodeEscLevelTwo(CodeEscMenu ownerMenu) {
        // Set up the JFrame
        setTitle("CodeESC Level Two");
        setSize(800, 800);
        setMaximumSize(new Dimension(800, 800)); // Set maximum window size
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ownerMenu = ownerMenu;


        // Load the background image from resources
        ImageIcon tempBackgroundImage = new ImageIcon("levelTwoBackground.png");
        Image backgroundImage = tempBackgroundImage.getImage();

        // Adding a JPanel to display the background
        backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(null);

        // Set up the Timer on top
        timer = new levelTimer();
        timer.setLocation(0, 0);
        backgroundPanel.add(timer);

        // Set up exit button
        JButton exitBtn = new JButton("Exit: Go back to menu");
        exitBtn.setBounds(0, 713, 160, 50);
        exitBtn.setForeground(Color.white);
        exitBtn.setBackground(Color.red);
        exitBtn.setBorder(null);
        exitBtn.setFocusPainted(false);
        add(exitBtn);
        exitBtn.addActionListener(e -> abortLevel());

        // Set up rest of level
        initializeLevel();

        //Add background panel to UI and start the timer
        add(backgroundPanel);
        timer.startTimer();

        // Make itself visible and level menu invisible
        ownerMenu.setVisible(false);
        setVisible(true);
    }

    /**
     * This method is used to initialize the interactive components and the logic handler
     * for the actual level.
     */
    private void initializeLevel() {
        // Construct new buttons for the interactive escape room objects.
        JButton balloonButton = new JButton();
        JButton bookButton = new JButton();
        JButton safeButton = new JButton();

        // Set their location and size to match background image
        balloonButton.setBounds(85, 77, 170, 205);
        bookButton.setBounds(285, 522, 240, 105);
        safeButton.setBounds(525, 407, 160, 180);

        // Call a method to make these buttons invisible
        handleLevelInterface.makeButtonInvisible(bookButton);
        handleLevelInterface.makeButtonInvisible(balloonButton);
        handleLevelInterface.makeButtonInvisible(safeButton);

        // Construct a label to give starting hint for the Escape Room level
        hintLabel = new JLabel();
        hintLabel.setText("<html>The color right here is a needle to pop, <br>\n" +
                "The pattern from popping gives a password to a box, <br>\n" +
                "The box gives letters their count unlocks the pages, <br>\n" +
                "The pages have the last, to escape at last.</html>");
        hintLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 20));
        hintLabel.setOpaque(true);
        hintLabel.setBackground(new Color(70,120,120,190));
        hintLabel.setForeground(Color.black);
        hintLabel.setBorder(BorderFactory.createLineBorder(new Color(20,20, 20), 2));
        hintLabel.setBounds(270, 50, 520, 180);

        // Construct new logic handler that handles all the logic with the interactive parts
        handleLevelTwoLogic levelLogic = new handleLevelTwoLogic(balloonButton, bookButton, safeButton, this);

        // Add the buttons to the background panel to finish off initializing interactive components
        backgroundPanel.add(balloonButton);
        backgroundPanel.add(bookButton);
        backgroundPanel.add(safeButton);

        // Finally, add the hint label as last component
        backgroundPanel.add(hintLabel);
    }

    /**
     * This method is called when the user aborts the level through
     * the go back to menu button.
     */
    public void abortLevel() {
        System.out.println("Aborted level before finishing.");
        setVisible(false);
        ownerMenu.setVisible(true);
    }

    /**
     * This method is called when the user beats the level, their time to beat the level
     * and username is queried to a backend method to the database to store their time, it also
     * updates the Best Time label in the level menu if the user got a new best time for this level.
     *
     * @param secondsPassed   the amount of seconds it took for the user to beat the level
     */
    public void finishLevel(int secondsPassed) {
        levelTimeQueries.insertTime(ownerMenu.getAccount().getCon(), ownerMenu.getAccount().getUsername(), 2, secondsPassed);
        System.out.println("Finished level.");
        setVisible(false);
        ownerMenu.setVisible(true);
        ownerMenu.getMenuLevel().getLevelTwoBtn().updateTime();

        JOptionPane.showMessageDialog(this, "<html>You successfully completed Level Two of CodeEsc!<br>Your time was: " + String.format("%02d:%02d", secondsPassed/60, secondsPassed%60) + "</html>");
    }

    /**
     *
     * @return  the amount of seconds that have passed since this level has been started
     */
    public int getLevelTime() {
        return timer.getSecondsPassed();
    }

    /**
     *
     * @return the hint label used within the level
     */
    public JLabel getHintLabel() { return hintLabel; }
}
