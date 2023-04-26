package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * LaunchPage class for Client and Server.
 */
public class LaunchPage implements ActionListener {
    /**
     * gameLaunched
     */
    private boolean gameLaunched = false;
    /**
     * creating frame of the launch window
     */
    JFrame frame = new JFrame();
    /**
     * set background image as icon vairable
     */
    Icon icon;
    /**
     * creating main button to play or start the game
     */
    JButton button;
    /**
     * Enters Server mode
     */
    JButton serverBtn = new JButton("Server");
    /**
     * Enters client mode
     */
    JButton clientBtn = new JButton("Client");
    /**
     * Image directory
     */
    String imageDir = "images\\";

    /**
     * mainn panel
     */
    MainGameFrame p;
    /**
     * CONSTRUCT
     */
    LaunchPage() {
    }
    /**
     * actionPerformed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("client")) {
            GameModel gameModel = new GameModel();
            GameView gameView = new GameView();
            // GameController gameController = new GameController(gameModel, gameView);
            new GameController(gameModel, gameView);
        } else if (e.getActionCommand().equals("server")) {
            new Server(imageDir);
        }
    }

    /**
     * Launches the splash screen the then the main game window
     */
    public void launchGame() {
        if (!gameLaunched) {
            gameLaunched = true;
            frame.dispose();
            // mainPANEL gui = new mainPANEL(imageDir, globalListener);
            // Get working directory
            String directory = System.getProperty("user.dir");
            imageDir = directory;
            // Makes program compatible with launch from VsCode as well as the JAR file
            if ((!imageDir.contains("\\bin"))) {
                imageDir = imageDir + "\\bin";
            }
            imageDir = imageDir + "\\images\\";
            icon = new ImageIcon(imageDir + "play.png");
            button = new JButton(icon);
            ImageIcon X = new ImageIcon((imageDir + "piccross.png"));
            JLabel background = new JLabel();
            background.setIcon(X);
            JPanel board = new JPanel();

            board.setSize(635, 305);
            board.add(background);
            serverBtn.setSize(130, 56);
            button.setLocation(200, 220);
            button.setFocusable(false);

            button.addActionListener(this);
            button.setActionCommand("client");
            // button.addActionListener(this);
            // this.button.addActionListener(new GameController(p, this));
            button.setSize(230, 86);
            serverBtn.setLocation(255, 5);
            serverBtn.setFocusable(false);
            serverBtn.addActionListener(this);
            serverBtn.setActionCommand("server");
            // designMode.addActionListener(this);
            // this.designMode.addActionListener(new GameController(p, this));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setResizable(false);
            Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
            int width = 650;
            int height = 345;
            frame.setSize(width, height);
            frame.setLocation(center.x - width / 2, center.y - height / 2);
            frame.setVisible(true);
            frame.add(button);
            frame.add(serverBtn);

            frame.add(board);

        }
    }
/**
 * main method to run client and server 
 * 
 * @param args args
 */
    public static void main(String[] args) {
        SplashScreen splashScreen = new SplashScreen();
        // Simulate a long initialization process
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splashScreen.dispose();
        LaunchPage lp = new LaunchPage();
        lp.launchGame();
    }
  
}
