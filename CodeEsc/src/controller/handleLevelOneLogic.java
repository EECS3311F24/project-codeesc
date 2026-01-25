package controller;

import utils.puzzleHelper;
import view.CodeEscLevelOne;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class is used to handle the main logic for the first level of
 * the "Escape Room" in the CodeEsc project, it is mainly used to hold
 * the variables for progression within the level and the handlers
 * for the interactive components.
 */
public class handleLevelOneLogic {
    //The UI part of the level, or simply the owner level of this logic
    private CodeEscLevelOne ownerLevel;
    //The button for the interactive book component inside the level
    private JButton bookButton;
    //The button for the interactive computer component inside the level
    private JButton computerButton;
    //The button for the interactive safe component inside the level.
    private JButton safeButton;
    //The boolean flag for whether the book component's puzzle was completed
    private boolean bookStagePassed;
    //The boolean flag for whether the computer component has had its password entered
    private boolean computerPassEntered;
    //The boolean flag for whether the safe component has had its password/code entered
    private boolean safePassEntered;

    //The randomly generated book number for the book component puzzle
    private int bookNumber;
    //The randomly generated computer password that you need to get from the book component puzzle
    private int computerPass;
    //The random unscrambled word that you have to figure out as it's the safe code
    private String unscrambledWord;
    //The scrambled version of the top word that you have to unscramble for the computer component puzzle
    private String scrambledWord;

    /**
     * This generates a new handleLevelOneLogic instance, the logic handler for its
     * owner level class.
     *
     * @param bookButton       the button used for interacting with the book component
     * @param computerButton   the button used for interacting with the computer component
     * @param safeButton       the button used for interacting with the safe component
     * @param ownerLevel       the button used for interacting with its UI counterpart
     */
    public handleLevelOneLogic(JButton bookButton, JButton computerButton, JButton safeButton, CodeEscLevelOne ownerLevel) {
        this.ownerLevel = ownerLevel;

        this.bookButton = bookButton;
        this.computerButton = computerButton;
        this.safeButton = safeButton;

        bookStagePassed = false;
        computerPassEntered = false;
        safePassEntered = false;

        initializePuzzleAnswers();

        bookButton.addActionListener(e -> bookButtonEventHandler());
        computerButton.addActionListener(e -> computerButtonEventHandler());
        safeButton.addActionListener(e -> safeButtonEventHandler());
    }

    /**
     * Initializes the puzzle answers, such as the random number between 1-15 for the book puzzle,
     * the 4 digit password for the computer, and the scrambled word you have to unscramble to
     * unlock the safe.
     */
    private void initializePuzzleAnswers() {
        Random random = new Random();

        bookNumber = random.nextInt(15) + 1;
        computerPass = random.nextInt(8999) + 1001;

        ArrayList<String> wordsList = new ArrayList<>(Arrays.asList("ALGORITHM", "PLASMA", "NEUTRON", "RADIATION", "ENERGY", "NIHILISM", "CIPHER", "RIDDLE", "HALLOWEEN", "PUMPKIN"));
        unscrambledWord = wordsList.get(random.nextInt(10));
        scrambledWord = puzzleHelper.wordScrambler(unscrambledWord);
    }

    /**
     * This handles the logic for the book component, which has a puzzle inside
     * involving guessing a number from 1-15.
     */
    private void bookButtonEventHandler() {
        JDialog bookDialog = new JDialog();
        bookDialog.setLocationRelativeTo(bookButton);
        bookDialog.setSize(310, 200);
        bookDialog.setResizable(false);
        bookDialog.setLayout(null);
        System.out.println(bookNumber);

        if(!bookStagePassed) {
            JLabel questionLabel = new JLabel("<html>First grace of any adventure is luck, <br> guess a number from 1 to 15 to test yours.<html>");
            JTextField questionInput = new JTextField();
            JButton questionSubmit = new JButton("Submit");

            questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            questionSubmit.setHorizontalAlignment(SwingConstants.CENTER);
            questionLabel.setBounds(0,14,310,30);
            questionInput.setBounds(0,60,310,30);
            questionSubmit.setBounds(0,100,310,30);

            questionSubmit.addActionListener(e -> bookDialogHandler(bookDialog, questionInput.getText()));

            bookDialog.add(questionLabel);
            bookDialog.add(questionInput);
            bookDialog.add(questionSubmit);
        } else {
            JLabel answerLabel = new JLabel("<html>Fortunate soul, the password to the <br> glowing screen is " + computerPass + "</html>");
            answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            answerLabel.setBounds(0, 0, 310, 150);

            bookDialog.add(answerLabel);
        }

        bookDialog.setVisible(true);
    }

    /**
     * This handles the input for the logic within the book component, specifically the
     * input for its inner puzzle.
     *
     * @param dialog    the dialog to interact with
     * @param input     the input the user submitted for the puzzle
     */
    private void bookDialogHandler(JDialog dialog, String input) {
        if(input.matches("\\d+") && Integer.valueOf(input) == bookNumber) {
            dialog.dispose();
            bookStagePassed = true;
            bookButtonEventHandler();
        } else {
            JDialog errorDialog = new JDialog();
            errorDialog.setLocationRelativeTo(dialog);
            errorDialog.setSize(100, 80);
            errorDialog.setResizable(false);
            errorDialog.setLayout(null);
            JLabel errorLabel = new JLabel("WRONG ANSWER");
            errorLabel.setBounds(5, 0, 100, 40);

            errorDialog.add(errorLabel);
            errorDialog.setVisible(true);
        }
    }

    /**
     * This handles the logic for the computer component, which has a puzzle inside
     * involving unscrambling a word, but only after entering the password you got
     * from the previous puzzle.
     */
    private void computerButtonEventHandler() {
        JDialog compDialog = new JDialog();
        compDialog.setLocationRelativeTo(computerButton);
        compDialog.setSize(310, 200);
        compDialog.setResizable(false);
        compDialog.setLayout(null);


        if(!computerPassEntered) {
            JLabel questionLabel = new JLabel("<html>The glowing screen demands a password..<html>");
            JTextField questionInput = new JTextField();
            JButton questionSubmit = new JButton("Submit");

            questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            questionSubmit.setHorizontalAlignment(SwingConstants.CENTER);
            questionLabel.setBounds(0,14,310,30);
            questionInput.setBounds(0,60,310,30);
            questionSubmit.setBounds(0,100,310,30);

            questionSubmit.addActionListener(e -> compDialogHandler(compDialog, questionInput.getText()));

            compDialog.add(questionLabel);
            compDialog.add(questionInput);
            compDialog.add(questionSubmit);
        } else {
            JLabel answerLabel = new JLabel("<html> The safe needs a unique code.. <br> Unscramble this word: " + scrambledWord + "</html>");
            answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            answerLabel.setBounds(0, 0, 300, 150);

            compDialog.add(answerLabel);
        }

        compDialog.setVisible(true);
    }

    /**
     * This handles the input for the logic within the computer component, specifically the
     * input for its inner puzzle.
     *
     * @param dialog    the dialog to interact with
     * @param input     the input the user submitted for the puzzle
     */
    private void compDialogHandler(JDialog dialog, String input) {
        if(input.matches("\\d+") && Integer.valueOf(input) == computerPass) {
            dialog.dispose();
            computerPassEntered = true;
            computerButtonEventHandler();
        } else {
            JDialog errorDialog = new JDialog();
            errorDialog.setLocationRelativeTo(dialog);
            errorDialog.setSize(100, 80);
            errorDialog.setResizable(false);
            errorDialog.setLayout(null);
            JLabel errorLabel = new JLabel("WRONG ANSWER");
            errorLabel.setBounds(5, 0, 100, 40);

            errorDialog.add(errorLabel);
            errorDialog.setVisible(true);
        }
    }

    /**
     * This handles the logic for the safe component, which has a puzzle inside
     * involving remembering the answer of the first puzzle, but only after entering the password
     * of an unscrambled word you got from the previous puzzle.
     */
    private void safeButtonEventHandler() {
        JDialog safeDialog = new JDialog();
        safeDialog.setLocationRelativeTo(safeButton);
        safeDialog.setSize(310, 200);
        safeDialog.setResizable(false);
        safeDialog.setLayout(null);

        if(!safePassEntered) {
            JLabel questionLabel = new JLabel("<html>The safe needs a password.. It needs.. A word? </html>");
            JTextField questionInput = new JTextField();
            JButton questionSubmit = new JButton("Submit");

            questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            questionSubmit.setHorizontalAlignment(SwingConstants.CENTER);
            questionLabel.setBounds(0,14,310,30);
            questionInput.setBounds(0,60,310,30);
            questionSubmit.setBounds(0,100,310,30);

            questionSubmit.addActionListener(e -> safeDialogHandler(safeDialog, questionInput.getText()));

            safeDialog.add(questionLabel);
            safeDialog.add(questionInput);
            safeDialog.add(questionSubmit);
        } else {
            JLabel questionLabel = new JLabel("<html>A last test before you escape, <br> what's the first number that you guessed on the pages?</html>");
            JTextField questionInput = new JTextField();
            JButton questionSubmit = new JButton("Submit");

            questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            questionSubmit.setHorizontalAlignment(SwingConstants.CENTER);
            questionLabel.setBounds(0,14,310,50);
            questionInput.setBounds(0,70,310,30);
            questionSubmit.setBounds(0,110,310,30);

            questionSubmit.addActionListener(e -> finalDialogHandler(safeDialog, questionInput.getText()));

            safeDialog.add(questionLabel);
            safeDialog.add(questionInput);
            safeDialog.add(questionSubmit);
        }

        safeDialog.setVisible(true);
    }

    /**
     * This handles the input for the logic within the safe component, specifically the
     * input for its inner puzzle.
     *
     * @param dialog    the dialog to interact with
     * @param input     the input the user submitted for the puzzle
     */
    private void safeDialogHandler(JDialog dialog, String input) {
        if(input.toLowerCase().matches(unscrambledWord.toLowerCase())) {
            dialog.dispose();
            safePassEntered = true;
            safeButtonEventHandler();
        } else {
            JDialog errorDialog = new JDialog();
            errorDialog.setLocationRelativeTo(dialog);
            errorDialog.setSize(100, 80);
            errorDialog.setResizable(false);
            errorDialog.setLayout(null);
            JLabel errorLabel = new JLabel("WRONG ANSWER");
            errorLabel.setBounds(5, 0, 100, 40);

            errorDialog.add(errorLabel);
            errorDialog.setVisible(true);
        }
    }

    /**
     * This handles the input for the logic within the saafe component, specifically the
     * input for its last puzzle.
     *
     * @param dialog    the dialog to interact with
     * @param input     the input the user submitted for the puzzle
     */
    private void finalDialogHandler(JDialog dialog, String input) {
        if(input.matches("\\d+") && Integer.valueOf(input) == bookNumber) {
            dialog.dispose();
            ownerLevel.finishLevel(ownerLevel.getLevelTime());
        } else {
            JDialog errorDialog = new JDialog();
            errorDialog.setLocationRelativeTo(dialog);
            errorDialog.setSize(100, 80);
            errorDialog.setResizable(false);
            errorDialog.setLayout(null);
            JLabel errorLabel = new JLabel("WRONG ANSWER");
            errorLabel.setBounds(5, 0, 100, 40);

            errorDialog.add(errorLabel);
            errorDialog.setVisible(true);
        }
    }
}
