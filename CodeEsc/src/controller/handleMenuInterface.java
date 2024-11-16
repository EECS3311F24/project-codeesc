package controller;

import view.CodeEscMenu;

/**
 * This class handles the UI methods dealing with interface changes
 * within the main menu of the CodeEsc project. These methods include things like switching
 * between different page outputs and the like within the menu.
 */
public class handleMenuInterface {

    /**
     * This method handles the switching of pages between the Main Menu
     * and the Level menu within the CodeEsc home/menu system after
     * logging in.
     *
     * @param menu          the menu to have its page switched
     * @param isPlayFlag    the boolean flag that says what page to switch it too
     * @return              the new boolean flag for the menu for which page it's on
     */
    public static boolean switchToPlay(CodeEscMenu menu, boolean isPlayFlag) {
        if(isPlayFlag) {
            menu.getButtonPanel().setVisible(false);
            menu.getHelpPanel().setVisible(false);
            menu.getMenuLevel().setVisible(true);
        } else {
            menu.getButtonPanel().setVisible(true);
            menu.getHelpPanel().setVisible(true);
            menu.getMenuLevel().setVisible(false);
        }

        return isPlayFlag;
    }


}
