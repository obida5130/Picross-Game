package game;

import java.awt.Font;
import java.awt.Point;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import java.awt.*;
/**
 * SplashScreen class for game.
 */
public class SplashScreen extends JWindow {
    /**
     * loadingLabel
     */
    private JLabel loadingLabel;

    /**
     * Constructor that runs the splash screen
     */
    public SplashScreen() {
        // Set the position and size of the splash screen
        String imageDir = "";
        String directory = System.getProperty("user.dir");
        imageDir = directory;
        // Makes program compatible with launch from VsCode as well as the JAR file
        if ((!imageDir.contains("\\bin"))) {
            imageDir = imageDir + "\\bin";
        }
        imageDir = imageDir + "\\images\\";
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 250;
        int height = 200;
        setSize(width, height);
        setLocation(center.x - width / 2, center.y - height / 2);

        JLabel label = new JLabel(new ImageIcon((imageDir + "piccross.png")));
        getContentPane().add(label);
        pack();

        // Create the loading label
        loadingLabel = new JLabel("Loading...");
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(loadingLabel, BorderLayout.SOUTH);

        // Show the splash screen
        setVisible(true);

        /**
         * loadingThread
         */
        Thread loadingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    updateLoadingText();
                }
            }
        });
        loadingThread.start();
    }

    /**
     * The loading text "animation"
     */
    private void updateLoadingText() {
        String text = loadingLabel.getText();
        if (text.endsWith("...")) {
            text = "Loading";
        } else {
            text += ".";
        }
        loadingLabel.setText(text);
    }
}