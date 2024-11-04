package view;
import javax.swing.*;
import java.awt.*;

/**
 * HelpPanel is a JPanel that contains a help button. The button displays a help
 * message when clicked.
 */
public class HelpPanel extends JPanel {

	/**
	 * Constructs a HelpPanel with a help button. The button is configured to
	 * trigger a help dialog when clicked.
	 */
	public HelpPanel() {
		// Set the layout manager for the panel to FlowLayout aligned to the right
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		// Make the panel transparent
		setOpaque(false);

		// Creating Help button
		JButton helpButton = createButton("Help", new Font("Bauhaus 93", Font.PLAIN, 24));
		// Adding action listener to the help button
		helpButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Help information here."));

		// Adding the help button to the panel
		add(helpButton); // Adding the help button to the panel
	}

	/**
	 * Creates a JButton with specified text and font. The button is configured with
	 * no content area fill, no border, and no focus paint.
	 * 
	 * @param text The text to display on the button.
	 * @param font The font to use for the button text.
	 * @return A JButton configured with the specified properties.
	 */

	private JButton createButton(String text, Font font) {
		JButton button = new JButton(text);

		// Set button appearance properties
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setFont(font);
		button.setPreferredSize(new Dimension(150, 50));

		return button;// Return the configured button
	}
}
