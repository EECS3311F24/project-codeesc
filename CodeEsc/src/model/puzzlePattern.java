package model;

/**
 * This class represents a puzzle pattern of any sort, where the patternString
 * attribute is used to hold the pattern question, and the patternAnswer is the answer
 * to the pattern and is also an integer.
 */
public class puzzlePattern {
    // The actual pattern question itself represented as a string.
    private String patternString;
    // The answer to the pattern represented by this class.
    private int patternAnswer;

    /**
     * Constructs a new puzzlePattern instance with the given pattern and answer
     * to it as attributes.
     *
     * @param patternString   the puzzle pattern question represented as a String
     * @param patternAnswer   the answer to the puzzle pattern which can only be given as an integer
     */
    public puzzlePattern(String patternString, int patternAnswer) {
        this.patternString = patternString;
        this.patternAnswer = patternAnswer;
    }

    /**
     * Returns true if the given answer for this pattern is equal to the answer
     * which is stored in the inner state of this puzzlePattern instance.
     *
     * @param userAnswer   the answer the user inputted for this pattern instance
     * @return true if it matches the answer for this puzzlePattern instance, false otherwise
     */
    public boolean isRightAnswer(String userAnswer) {
        if(userAnswer.matches("\\d+") && Integer.valueOf(userAnswer) == patternAnswer) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return the puzzle pattern string, or more simplified the pattern this instance represents
     */
    public String getPatternString() { return patternString; }
}
