package view;
import javax.swing.*;

/**
 * This class is a subclass of the JFrame class from the Java Swing Library
 * and is used to be a UI frame custom designed to hold the Login/Sign-Up form
 * for the CodeEsc project.
 */
public class LoginFrame extends JFrame {

	/**
	 * This constructor sets up a new LoginFrame with a width of 1000 pixels and height of 600 pixels,
	 * and uses the isLoginFlag to decide on whether the title of the frame is for a login or sign-up
	 * page. It also sets up a few extra JFrame configurations.
	 * 
	 * @param isLoginFlag  the flag for whether the frame is for a login or sign-up page.
	 */
    public LoginFrame(boolean isLoginFlag) {
        if(isLoginFlag) {
            this.setSize(1000, 600);
            this.setResizable(false);
            this.setTitle("CodeEsc - Login");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            this.setSize(1000, 600);
            this.setResizable(false);
            this.setTitle("CodeEsc - Sign Up");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
