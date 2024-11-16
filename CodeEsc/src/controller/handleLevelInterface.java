package controller;

import javax.swing.*;

/**
 * This class is used to handle different methods for handling
 * interface changes within an actual "Escape Room Level" inside
 * the CodeEsc project.
 */
public class handleLevelInterface {

    /**
     * This method takes in a JButton and then makes it visually invisible,
     * the main use of this method is to overlay it on top of an
     * object on a level's background image, so you can emulate interacting
     * with objects with the button's Action Listeners.
     *
     * @param btn  the button to be made invisible
     */
    public static void makeButtonInvisible(JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setText("");
    }
}
