package view;
import controller.handleMenuInterface;

import javax.swing.*;
import java.awt.*;

/**
 * ButtonPanel is a custom JPanel that contains a vertical arrangement of
 * buttons. The panel is designed to provide controls for the game, including
 * play, settings, and leaderboard options.
 */
public class ButtonPanel extends JPanel {
	private CodeEscMenu menu;
	private CodeEscLevels menuFrame;
	/**
	 * Constructs a ButtonPanel and initializes the layout and buttons. The buttons
	 * are arranged vertically and are styled with a custom font.
	 */
	public ButtonPanel(CodeEscMenu menu) {
		// Set the layout manager to arrange components vertically
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setOpaque(false);

		// Create a font for the buttons

		Font buttonFont = new Font("Bauhaus 93", Font.PLAIN, 24);

		// Creating buttons with text
		JButton playButton = createButton("Play", buttonFont);
		JButton settingsButton = createButton("Settings", buttonFont);
		JButton leaderboardButton = createButton("Leaderboard", buttonFont);

		// Adding action listener for menu buttons to open their specific pages
		playButton.addActionListener(e -> {
			handleMenuInterface.switchToPlay(menu, menu.switchPlayFlag());
		});

		settingsButton.addActionListener(e -> {
			handleMenuInterface.switchToSettings(menu, menu.switchSettingsFlag());
		});

		leaderboardButton.addActionListener(e -> {
			handleMenuInterface.switchToLeaderboard(menu, menu.switchLeaderboardFlag());
		});

		// Adding buttons to the button panel with vertical glue for spacing
		add(Box.createVerticalGlue());
		add(playButton);
		add(settingsButton);
		add(leaderboardButton);
		add(Box.createVerticalGlue());
	}

	/**
	 * Creates a JButton with specified text and font. The button is configured to
	 * have no content area fill, no border, and no focus paint. An action listener
	 * is added to handle button clicks.
	 *
	 * @param text The text to display on the button.
	 * @param font The font to use for the button text.
	 * @return A JButton configured with the specified properties.
	 */
	private JButton createButton(String text, Font font) {
		// Set button appearance properties
		JButton button = new JButton(text);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setFont(font);
		button.setPreferredSize(new Dimension(150, 50));

		return button; // Return the configured button
	}
}
