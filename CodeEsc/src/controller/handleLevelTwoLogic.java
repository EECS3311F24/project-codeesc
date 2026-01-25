package controller;

import model.puzzlePattern;
import utils.puzzleHelper;
import view.CodeEscLevelOne;
import view.CodeEscLevelTwo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * This class is used to handle the main logic for the second level of
 * the "Escape Room" in the CodeEsc project, it is mainly used to hold
 * the variables for progression within the level and the handlers
 * for the interactive components.
 */
public class handleLevelTwoLogic {
    //The UI part of the level, or simply the owner level of this logic
    private CodeEscLevelTwo ownerLevel;
    //The button for the interactive balloon component inside the level
    private JButton balloonButton;
    //The button for the interactive book component inside the level
    private JButton bookButton;
    //The button for the interactive safe component inside the level.
    private JButton safeButton;
    //The boolean flag for whether the balloon component's pattern was entered
    private boolean balloonStagePassed;
    //The boolean flag for whether the safe component has had its password entered
    private boolean safePassEntered;
    //The boolean flag for whether the book component has had its password entered
    private boolean bookPassEntered;
    //The password to solve the balloon component's puzzle, it's the color of the hint label's font
    private String balloonPass;
    //The password to solve the safe component's puzzle, it's the number from the pattern given by the balloon component
    private puzzlePattern safePattern;
    //The password to solve the book component's puzzle, it's the length of the sequence of letters given by the safe component
    private String bookPass;
    //The final password to solve the level, it's a randomly generated number from 0 to 10
    private int finalPass;

    /**
     * This generates a new handleLevelTwoLogic instance, the logic handler for its
     * owner level class.
     *
     * @param balloonButton    the button used for interacting with the balloon component
     * @param bookButton       the button used for interacting with the book component
     * @param safeButton       the button used for interacting with the safe component
     * @param ownerLevel       the button used for interacting with its UI counterpart
     */
    public handleLevelTwoLogic(JButton balloonButton, JButton bookButton, JButton safeButton, CodeEscLevelTwo ownerLevel) {
        this.ownerLevel = ownerLevel;

        this.balloonButton = balloonButton;
        this.bookButton = bookButton;
        this.safeButton = safeButton;

        balloonStagePassed = false;
        bookPassEntered = false;
        safePassEntered = false;

        initializePuzzleAnswers();

        balloonButton.addActionListener(e -> balloonButtonEventHandler());
        bookButton.addActionListener(e -> bookButtonEventHandler());
        safeButton.addActionListener(e -> safeButtonEventHandler());
    }

    /**
     * Initializes the puzzle answers, such as the color of the hint label and thus for the book
     * component puzzle, the number pattern, and it's answer to unlock the safe component,
     * the sequence of letters which it's length unlocks the book component, and the final
     * random number from 0 to 10 that you enter into the book component to finish the level.
     */
    private void initializePuzzleAnswers() {
        Random random = new Random();

        ArrayList<String> colorList = new ArrayList<>(Arrays.asList("black", "red", "blue", "orange", "yellow", "green"));
        HashMap<String, Color> colorMap = new HashMap<>();
        colorMap.put("black", Color.black);
        colorMap.put("red", Color.red);
        colorMap.put("blue", Color.blue);
        colorMap.put("orange", Color.orange);
        colorMap.put("yellow", Color.yellow);
        colorMap.put("green", Color.green);
        balloonPass = colorList.get(random.nextInt(6));
        ownerLevel.getHintLabel().setForeground(colorMap.get(balloonPass));

        ArrayList<puzzlePattern> patternList = new ArrayList<>(Arrays.asList(new puzzlePattern("1, 3, 7, 15, ?", 31), new puzzlePattern("1, 2, 0, 3, -1, ?", 4), new puzzlePattern("2, 5, 11, 23, ?", 47), new puzzlePattern("2, 2, 1, 3, 3, ?", 3)));
        safePattern = patternList.get(random.nextInt(4));

        bookPass = puzzleHelper.randomWordGenerator();

        finalPass = random.nextInt(11);
    }

    /**
     * This handles the logic for the balloon component, which has a puzzle inside
     * involving solving a number pattern, but only after entering the password attained
     * from the hint label.
     */
    private void balloonButtonEventHandler() {
        JDialog balloonDialog = new JDialog();
        balloonDialog.setLocationRelativeTo(balloonButton);
        balloonDialog.setSize(310, 200);
        balloonDialog.setResizable(false);
        balloonDialog.setLayout(null);


        if(!balloonStagePassed) {
            JLabel questionLabel = new JLabel("<html>What color of needle is needed to pop the balloon.. <br> Hint: The hint is the hint <html>");
            JTextField questionInput = new JTextField();
            JButton questionSubmit = new JButton("Submit");

            questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            questionSubmit.setHorizontalAlignment(SwingConstants.CENTER);
            questionLabel.setBounds(0,14,310,30);
            questionInput.setBounds(0,60,310,30);
            questionSubmit.setBounds(0,100,310,30);

            questionSubmit.addActionListener(e -> balloonDialogHandler(balloonDialog, questionInput.getText()));

            balloonDialog.add(questionLabel);
            balloonDialog.add(questionInput);
            balloonDialog.add(questionSubmit);
        } else {
            JLabel answerLabel = new JLabel("<html> The safe needs a unique code.. <br> Solve: " + safePattern.getPatternString() + " </html>");
            answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            answerLabel.setBounds(0, 0, 300, 150);

            balloonDialog.add(answerLabel);
        }

        balloonDialog.setVisible(true);
    }

    /**
     * This handles the input for the logic within the balloon component, specifically the
     * input for its inner puzzle.
     *
     * @param dialog    the dialog to interact with
     * @param input     the input the user submitted for the puzzle
     */
    private void balloonDialogHandler(JDialog dialog, String input) {
        if(input.toLowerCase().matches(balloonPass)) {
            dialog.dispose();
            balloonStagePassed = true;
            balloonButtonEventHandler();
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
     * involving counting the characters in a sequence of letters, but only after entering the password
     * of the number pattern you got from the previous puzzle (the balloon).
     */
    private void safeButtonEventHandler() {
        JDialog safeDialog = new JDialog();
        safeDialog.setLocationRelativeTo(safeButton);
        safeDialog.setSize(310, 200);
        safeDialog.setResizable(false);
        safeDialog.setLayout(null);

        if(!safePassEntered) {
            JLabel questionLabel = new JLabel("<html>The safe needs a password.. It needs.. a number. </html>");
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
            JLabel answerLabel = new JLabel("<html> The book needs a password too.. <br> Hint, it's simply the length:<br> " + bookPass + " </html>");
            answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            answerLabel.setBounds(0, 0, 300, 150);

            safeDialog.add(answerLabel);
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
        if(safePattern.isRightAnswer(input)) {
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
     * This handles the logic for the book component, which has a puzzle inside
     * involving guessing a number from 0-10, but only after you entered
     * the answer from the previous puzzle of the length of a sequence of letters (safe puzzle).
     */
    private void bookButtonEventHandler() {
        JDialog bookDialog = new JDialog();
        bookDialog.setLocationRelativeTo(bookButton);
        bookDialog.setSize(310, 200);
        bookDialog.setResizable(false);
        bookDialog.setLayout(null);


        if(!bookPassEntered) {
            JLabel questionLabel = new JLabel("<html> The book needs a password.. How is it even locked? <html>");
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
            JLabel questionLabel = new JLabel("<html>A grace of any adventure is luck, <br> guess a number from 0 to 10 to test yours.<html>");
            JTextField questionInput = new JTextField();
            JButton questionSubmit = new JButton("Submit");

            questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            questionSubmit.setHorizontalAlignment(SwingConstants.CENTER);
            questionLabel.setBounds(0,14,310,30);
            questionInput.setBounds(0,60,310,30);
            questionSubmit.setBounds(0,100,310,30);

            questionSubmit.addActionListener(e -> finalDialogHandler(bookDialog, questionInput.getText()));

            bookDialog.add(questionLabel);
            bookDialog.add(questionInput);
            bookDialog.add(questionSubmit);
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
        if(input.matches("\\d+") && Integer.valueOf(input) == bookPass.length()) {
            dialog.dispose();
            bookPassEntered = true;
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
     * This handles the input for the logic within the book component, specifically the
     * input for its last puzzle to finish the level.
     *
     * @param dialog    the dialog to interact with
     * @param input     the input the user submitted for the puzzle
     */
    private void finalDialogHandler(JDialog dialog, String input) {
        if(input.matches("\\d+") && Integer.valueOf(input) == finalPass) {
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
