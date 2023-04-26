package game;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * this class is mani panel which responsible of all other panels and add it to frame
 */
public class MainGameFrame extends JFrame {

    /**
     * JFrame implements TransferHandler which implements Serializable.
     * This line prevents warnings regarding serialVersionUID
     */
    private static final long serialVersionUID = 1;
    /**
     * setting theme to dark as default
     */
    ThemeSet theme = new ThemeSet("dark");
    /**
     * calling jframe from library
     */
    JFrame frame;
    /**
     * creating object top left panel
     */
    Languages topLeftPANEL;
    /**
     * creating object top right panel
     */
    RightPanelGame rightP;
    /**
     * creating object for top right panel
     */
    ThemeMode themeMODE;
    /**
     * creating object for top & bottom  panel
     */
    TopAndBottomPanel top_Bottom;
    /**
     * creating object for left panel
     */
    LeftPanelGame leftP;
    /**
     * creating object for center panel
     */
    Center centerP;
    /**
     * directory of the image
     */
    String dir;
    /**
     * gridButtonLabels
     */
    GridButtonLabels gridButtonLabels;
    /**
     * menuBar
     */
    Menu menuBar;
    /**
     * gridSize as 0
     */
    int gridSize = 0;
    /**
     * client 
     */
    Client client;
    GameView view;
    /**
     * constructor for panel class
     */
    MainGameFrame(String dir, ActionListener globalListener) {

        this.dir = dir;
        Container pane = getContentPane();
        client = new Client(dir, this, globalListener);
        view = new GameView();
        // Panels
        topLeftPANEL = new Languages(pane, theme, this, globalListener);
        top_Bottom = new TopAndBottomPanel(pane, theme);
        leftP = new LeftPanelGame(pane, theme);
        rightP = new RightPanelGame(pane, theme, this, dir, globalListener);
        centerP = new Center(pane, theme);
        themeMODE = new ThemeMode(pane, theme, this, globalListener);

        gridButtonLabels = new GridButtonLabels();
        // l5 = new Label5x5(pane, theme);
        Color frameColor = theme.getFrameColor();
        /************************************************************************************/
        final String TITLE = "Picross Game";
        frame = new JFrame(TITLE);

        // Adding the action menu
        menuBar = new Menu(this, globalListener);
        frame.setJMenuBar(menuBar.getJMenuBar());

        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// close application
        frame.setLayout(null);
        frame.setResizable(false);
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 1000;
        int height = 950;
        frame.setSize(width, height);
        frame.setLocation(center.x - width / 2, center.y - height / 2);
        frame.setVisible(false);// make window visible
        /************************************************************************************/

        frame.add(top_Bottom.topPanel);
        frame.add(rightP.playPatterns);
        frame.add(rightP.savePatterns);
        frame.add(rightP.exitPatterns);

        // Top-left panel
        frame.add(topLeftPANEL.topLeftPanel);
        // frame.add(topLeftPANEL.aroundTopLeftPanel);

        // Top-right panel
        frame.add(themeMODE.topRightPanel);
        // frame.add(themeMODE.aroundTopRightPanel);

        frame.add(leftP.leftPanel);
        // frame.add(leftP.aroundLeftPanel);

        // Right panel
        frame.add(rightP.rightPanel);
        // frame.add(rightP.aroundRightPanel);

        // Center panel
        frame.add(centerP.centerPanel);

        // Bottom panel
        frame.add(top_Bottom.bottomPanel);

        frame.getContentPane().setBackground(frameColor);
        frame.add(rightP.imageLabel, BorderLayout.CENTER);


    }

    /**
     * returns the themeMode
     * 
     * @return the themeMode
     */
    public ThemeMode topRightPanel() {
        return themeMODE;
    }

    /**
     * Returns the right panel object
     * 
     * @return the right panel object
     */
    public RightPanelGame rightPanel() {
        return rightP;
    }

    /**
     * Returns the languages panel
     * 
     * @return the languages panel
     */
    public Languages topLeftPanel() {
        return topLeftPANEL;
    }

    /**
     * Returns the image directory
     * 
     * @return the image directory
     */
    public String getImageDir() {
        return dir;
    }

    /**
     * Returns a Menu object
     * 
     * @return a Menu object
     */
    public Menu getActionsMenu() {
        return menuBar;
    }

}
