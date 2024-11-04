package view;
import javax.swing.*;
import java.awt.*;

/**
 * BackgroundPanel is a custom JPanel that displays a background image. This
 * panel will draw the specified image scaled to fit the panel's dimensions.
 */
public class BackgroundPanel extends JPanel {
	private Image backgroundImage;

	/**
	 * Constructs a BackgroundPanel with a specified background image.
	 *
	 * @param backgroundImage The image to be used as the background.
	 */
	public BackgroundPanel(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	/**
	 * Paints the component by drawing the background image. This method is called
	 * whenever the panel needs to be redrawn.
	 * 
	 * @param g The Graphics context to be used for painting.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the background image, scaling it to fit the panel's dimensions
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
}
