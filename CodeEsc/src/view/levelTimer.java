package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A subclass of JLabel designed to be a timer to be used on levels
 * for users to know how much time has passed since they have started the level.
 */
public class levelTimer extends JLabel {
    //The inner state of how many seconds have passed within the inner timer
    private int secondsPassed;
    //The inner timer of the JLabel
    private Timer timer;

    /**
     * Constructs a new levelTimer with custom styling and an inner timer,
     * the timer does not start however and needs another method to be called
     * for that. This JLabel automatically updates to show a formatted version
     * of the time depending on the inner timer's value.
     */
    public levelTimer() {
        setFont(new Font("Bauhaus 93", Font.BOLD, 24));
        setSize(800, 50);
        setOpaque(true);
        setBackground(Color.black);
        setForeground(Color.white);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);


        secondsPassed = 0;
        setText("Time:  " + String.format("%02d:%02d", secondsPassed/60, secondsPassed%60));
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassed++;
                setText("Time:  " + String.format("%02d:%02d", secondsPassed/60, secondsPassed%60));
            }
        });
    }

    /**
     * This method starts the inner time of the levelTimer class.
     */
    public void startTimer() {
        timer.start();
    }

    /**
     * This level returns the amount of seconds that have passed within the inner timer,
     * and these are the seconds displayed on the label as well.
     *
     * @return    the amount of seconds that have passed within the inner timer
     */
    public int getSecondsPassed() {
        return secondsPassed;
    }
}
