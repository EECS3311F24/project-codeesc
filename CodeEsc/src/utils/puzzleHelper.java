package utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to provide helper methods for different puzzles that appear
 * in the levels featured in the CodeEsc project.
 */
public class puzzleHelper {

    /**
     * This method takes in a String and then scrambles the characters inside
     * and returns a scrambled version of the given String. This is used for
     * a word unscrambling puzzle within Level One.
     *
     * @param word   the String to be scrambled
     * @return       the scrambled String
     */
    public static String wordScrambler(String word) {
        ArrayList<Character> charList = new ArrayList<>();
        char[] charArray = word.toCharArray();
        for(char c : charArray) {
            charList.add(c);
        }
        Collections.shuffle(charList);
        StringBuilder sb = new StringBuilder();
        for(char c : charList) {
            sb.append(c);
        }
        return sb.toString();
    }
}
