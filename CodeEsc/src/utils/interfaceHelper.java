package utils;
import javax.swing.*;
import java.awt.*;

/**
 * This class holds helper methods for different UI use cases in the Login Form as well as
 * the Main Menu.
 */
public class interfaceHelper {
	
	/**
	 * This method takes and Image Icon and then resizes the icon to the given
	 * width and height dimensions and returns the new resized icon.
	 * 
	 * @param icon    the Image Icon to be resized
	 * @param width   the new width of the Image Icon
	 * @param height  the new height of the Image Icon
	 * @return
	 */
    public static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image originalImage = icon.getImage();
        Image resized = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resultIcon = new ImageIcon(resized);
        return resultIcon;
    }
}
