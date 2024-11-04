package view;
import javax.swing.*;

import java.awt.*;

/**
 * CodeEscMenu is the main menu window for the CodeESC game. This class sets up
 * the JFrame and includes a background image, button panel, and help panel.
 */
public class CodeEscMenu extends JFrame {
	private Image backgroundImage;

	/**
	 * Constructs the CodeEscMenu and initializes the GUI components. This includes
	 * loading the background image, configuring the JFrame, and adding the
	 * background panel, button panel, and help panel.
	 */
	public CodeEscMenu() {
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
		ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.add(Box.createRigidArea(new Dimension(500, 0)));

		// Adding buttons to the button panel
		backgroundPanel.add(buttonPanel, BorderLayout.EAST);

		HelpPanel helpPanel = new HelpPanel();
		backgroundPanel.add(helpPanel, BorderLayout.NORTH);
	}

}
