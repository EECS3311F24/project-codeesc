package view;
import model.Account;

import javax.swing.*;

import java.awt.*;
import java.sql.Connection;

/**
 * CodeEscMenu is the main menu window for the CodeESC game. This class sets up
 * the JFrame and includes a background image, button panel, and help panel.
 */
public class CodeEscMenu extends JFrame {
	private Image backgroundImage;
	private ButtonPanel buttonPanel;
	private HelpPanel helpPanel;
	private CodeEscLevels menuLevel;

	private Account account;
	private boolean isPlayMenu;

	/**
	 * Constructs the CodeEscMenu and initializes the GUI components. This includes
	 * loading the background image, configuring the JFrame, and adding the
	 * background panel, button panel, and help panel.
	 */
	public CodeEscMenu(Account account) {
		//Set play menu flag to false
		this.isPlayMenu = false;

		//Set up user account as an attribute
		this.account = account;

		//Set up levels menu
		menuLevel = new CodeEscLevels(this);
		menuLevel.setVisible(false);
		add(menuLevel);

		// Load the background image from resources
		ImageIcon tempBackgroundImage = new ImageIcon("menubackground.jpg");
		backgroundImage = tempBackgroundImage.getImage();

		// Set up the JFrame
		setTitle("CodeESC Menu");
		setSize(800, 600);
		setMaximumSize(new Dimension(800, 600)); // Set maximum window size
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Adding a JPanel to display the background
		BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
		backgroundPanel.setLayout(new BorderLayout());
		add(backgroundPanel);

		// Creating button panel to hold the main buttons
		buttonPanel = new ButtonPanel(this);
		buttonPanel.add(Box.createRigidArea(new Dimension(500, 0)));

		// Adding buttons to the button panel
		backgroundPanel.add(buttonPanel, BorderLayout.EAST);

		helpPanel = new HelpPanel();
		backgroundPanel.add(helpPanel, BorderLayout.NORTH);
	}

	/**
	 *
	 * @return   the level menu page/class
	 */
	public CodeEscLevels getMenuLevel() { return menuLevel; }

	/**
	 *
	 * @return   the button panel used within the home main menu
	 */
	public ButtonPanel getButtonPanel() { return buttonPanel; }

	/**
	 *
	 * @return   the help panel used within the home main menu
	 */
	public HelpPanel getHelpPanel() { return helpPanel; }

	/**
	 *
	 * @return   switches the flag that holds whether this menu is at home or at the level menu section
	 */
	public boolean switchPlayFlag() {
		this.isPlayMenu = !isPlayMenu;
		return isPlayMenu;
	}

	/**
	 *
	 * @return   the account that is currently using this menu
	 */
	public Account getAccount() { return account; }

}
