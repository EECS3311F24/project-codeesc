package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

    /**
     * This method generates a String of random letters of a random length
     * between 4 and 33. This is used for a letter sequence length puzzle
     * within Level Two.
     *
     * @return
     */
    public static String randomWordGenerator() {
        Random random = new Random();
        int length = random.nextInt(30) + 4;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }
}
