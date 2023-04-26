package game;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

/**
 * GameView includes all of the GUI components needed to run the game.
 * 
 * @author/Professor: Paulo Sousa.
 * @author/Professor: Daniel Cormier.
 */
public class GameView {
    // R W E
    /**
     * colors for right, wrong, empty square
     */
    Color[] colors = { new Color(0, 255, 0), new Color(255, 0, 0), new Color(55, 155, 153) };
    /**
     * imageDir
     */
    String imageDir = "";
    /**
     * JButton
     */
    JButton button;
    /**
     * JMenuBar
     */
    JMenuBar menuBar;
    /**
     * menu
     */
    JMenu menu;
    /**
     * menuItems
     */
    JMenuItem[] menuItems;
    /**
     * gridSize
     */
    private int gridSize;
    /**
     * designFrame
     */
    JFrame designFrame;
    /**
     * Main panel that includes all other panels
     */
    MainGameFrame mainPanel;

    /**
     * 
     * @return mainPanel object which includes an instance of all other classes
     */
    public MainGameFrame getMainPanel() {
        return mainPanel;
    }

    /**
     * get grid size value
     * 
     * @return vbalue
     */
    public int getGridSize() {
        return gridSize;
    }

    /**
     * setting size value in view
     * 
     * @param gridSize grid size value
     */
    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    /**
     * Creates an instance of class mainPANEL
     * and sets the image directory for the gridButton
     * 
     * @param globalListener used to detect changes in elements
     */
    public void runGame(ActionListener globalListener) {

        String directory = System.getProperty("user.dir");
        imageDir = directory;
        // Makes program compatible with launch from VsCode as well as the JAR file
        if ((!imageDir.contains("\\bin"))) {
            imageDir = imageDir + "\\bin";
        }
        imageDir = imageDir + "\\images\\";
        mainPanel = new MainGameFrame(imageDir, globalListener);
    }

    /**
     * Inserts action updates into the actions JTextArea on the right panel
     * 
     * @param actionText text to be added to the JTextArea
     * @param obj        supplied when there is specific data fields stored inside
     *                   an object that will need to be accessed
     */
    public void insertAction(String actionText, Object obj) {

        if (obj instanceof GridButton) {
            GridButton gb = (GridButton) obj;
            mainPanel.rightPanel().actionsArea().insert(
                    ("Pressed " + gb.getCoordX() + ", " + gb.getCoordY() + " ["
                            + gb.getCorrectValue() + "]\n"),
                    0);
        } else {
            mainPanel.rightPanel().actionsArea().insert(actionText, 0);
        }
    }

    /**
     * Clears board and other GUI components
     */
    public void resetGame() {
        mainPanel.rightP.resetGame(mainPanel);
    }

    /**
     * Updates components to adapt to the new display theme
     * 
     * @param themeName "dark" or "light"
     */
    public void setTheme(String themeName) {
        ThemeSet localTheme = new ThemeSet(themeName);
        switch (themeName) {
            case "dark":
                mainPanel.theme.setTheme("dark");
                mainPanel.themeMODE.setTheme(mainPanel.theme.getMode());
                mainPanel.themeMODE.radioBtn("light").setSelected(false);
                mainPanel.themeMODE.radioBtn("dark").setSelected(true);
                mainPanel.themeMODE.radioBtn("dark").setEnabled(false);
                mainPanel.themeMODE.radioBtn("light").setEnabled(true);
                mainPanel.rightPanel().actionsArea().insert("Dark light Set\n", 0);
                mainPanel.topRightPanel().setTheme(mainPanel, localTheme);
                break;

            case "light":
                mainPanel.theme.setTheme("light");
                mainPanel.themeMODE.setTheme(mainPanel.theme.getMode());
                mainPanel.themeMODE.radioBtn("light").setSelected(true);
                mainPanel.themeMODE.radioBtn("dark").setSelected(false);
                mainPanel.themeMODE.radioBtn("dark").setEnabled(true);
                mainPanel.themeMODE.radioBtn("light").setEnabled(false);
                mainPanel.rightPanel().actionsArea().insert("Light Theme Set\n", 0);
                mainPanel.topRightPanel().setTheme(mainPanel, localTheme);
                break;
        }
    }

    /**
     * Does the required updates to the GUI when user changes language selection
     * 
     * @param language language to be used (eg. English, Turkish, French)
     * @param rb       loads bundle for each language
     */
    public void setLanguage(String language, ResourceBundle rb) {

        mainPanel.rightPanel().actionsArea()
                .insert((rb.getString("languageSet")) + "\n", 0);

        mainPanel.topLeftPanel().getLabel("languageL")
                .setText(rb.getString("languagesLabel") + "     ");

        mainPanel.topLeftPanel().getLabel("englishL")
                .setText(rb.getString("english"));
        mainPanel.topLeftPanel().getLabel("turkishL")
                .setText(rb.getString("turkish"));
        mainPanel.topLeftPanel().getLabel("frenchL")
                .setText(rb.getString("french"));
        mainPanel.topRightPanel().getLabel("themeL")
                .setText(rb.getString("displayThemeLabel") + "     ");
        mainPanel.topRightPanel().getLabel("darkThemeL")
                .setText(rb.getString("darkThemeLabel"));
        mainPanel.topRightPanel().getLabel("lightThemeL")
                .setText(rb.getString("lightThemeLabel"));
        mainPanel.rightPanel().getLabel("timeLabel")
                .setText(rb.getString("timeLabel"));
        mainPanel.rightPanel().getLabel("pointsLabel")
                .setText(rb.getString("pointsLabel"));
        mainPanel.rightPanel().getLabel("sizeLabel")
                .setText(rb.getString("sizeLabelText"));
        mainPanel.rightPanel().getResetBtn().setText(rb.getString("resetText"));

        // Update which buttons would be enabled or disabled
        // Deselect the previously selected language
        mainPanel.topLeftPanel().getLangBtn(language).setSelected(true);
        mainPanel.topLeftPanel().getLangBtn(language).setEnabled(false);
        String otherLangs[] = { "English", "Turkish", "French" };
        for (String i : otherLangs) {
            if (!i.equals(language)) {
                mainPanel.topLeftPanel().setLanguage(language);
                mainPanel.topLeftPanel().getLangBtn(i).setSelected(false);

                mainPanel.topLeftPanel().getLangBtn(i).setEnabled(true);
                mainPanel.topLeftPanel().getLangBtn(language).setEnabled(false);
            }
        }
    }

    /**
     * display solution as window
     * 
     * @param randomGameBoard main game board array
     */
    public void displaySolutionWindow(int[] randomGameBoard) {
        int newSquare = (int) Math.sqrt(gridSize);

        JFrame frame = new JFrame("Solution");
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 670;
        int height = 687;
        frame.setSize(width, height);
        frame.setLocation(center.x - width / 2, center.y - height / 2);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(newSquare, newSquare, 2, 2));
        for (int i = 0; i < gridSize; i++) {
            JLabel label = new JLabel();
            label.setOpaque(true); // Set label to be opaque
            label.setPreferredSize(new Dimension(100, 100)); // Set preferred size for labels in the grid
            if (randomGameBoard[i] == 1) {
                label.setBackground(Color.GREEN);
            } else {
                label.setBackground(Color.YELLOW);
            }
            panel.add(label);
        }

        frame.add(panel);
        frame.setVisible(true);
    }
    ArrayList<ArrayList<Integer>> topLabels ;
    ArrayList<ArrayList<Integer>> leftLabels ;
    /**
     * storing labels from game board game to top side
     * 
     * @param randomGameBoard main game board array
     * @return top labels
     */
    public ArrayList<ArrayList<Integer>> generateTopLabels(int[] randomGameBoard) {
        int newSquare = (int) Math.sqrt(gridSize);
        topLabels = new ArrayList<>();
        for (int i = 0; i < gridSize / newSquare; i++) {
            ArrayList<Integer> columnLabels = new ArrayList<>();
            int count = 0;

            for (int j = 0; j < newSquare; j++) {
                if (randomGameBoard[j * newSquare + i] == 1) {
                    count++;
                } else {
                    if (count > 0) {
                        columnLabels.add(count);
                        count = 0;
                    }
                }
            }

            if (count > 0) {
                columnLabels.add(count);
            }

            if (columnLabels.isEmpty()) {
                columnLabels.add(0);
            }

            topLabels.add(columnLabels);
        }

        return topLabels;
    }

    /**
     * storing labels from game board game to left side
     * 
     * @param randomGameBoard main game board array
     * @return left labels
     */
    public ArrayList<ArrayList<Integer>> generateLeftLabels(int[] randomGameBoard) {
        int newSquare = (int) Math.sqrt(gridSize);
        leftLabels = new ArrayList<>();

        for (int i = 0; i < gridSize / newSquare; i++) {
            ArrayList<Integer> rowLabels = new ArrayList<>();
            int count = 0;

            for (int j = 0; j < newSquare; j++) {
                if (randomGameBoard[i * newSquare + j] == 1) {
                    count++;
                } else {
                    if (count > 0) {
                        rowLabels.add(count);
                        count = 0;
                    }
                }
            }

            if (count > 0) {
                rowLabels.add(count);
            }

            if (rowLabels.isEmpty()) {
                rowLabels.add(0);
            }

            leftLabels.add(rowLabels);
        }

        return leftLabels;
    }
    ArrayList<ArrayList<Integer>> getleftLabels(){
        return leftLabels;
    }
    ArrayList<ArrayList<Integer>> gettopLabels(){
        return topLabels;
    }
    /**
     * draw game method for controller class can access it
     * 
     * @param modelSize size grid
     * @param current   public action listener
     */
    public void drawGame(int modelSize, ActionListener current) {
        mainPanel.rightP.drawGame(imageDir, mainPanel, modelSize, current);
    }

    /**
     * call GridButtonsLabels class to display the current game labes to the frame
     * 
     * @param numLabels         sqrt of size game
     * @param horizontalSpace   moving text position
     * @param verticalSpace     moving text position
     * @param downSpace         moving text position
     * @param moveTopLabelRight moving text position
     * @param gridSize          size of grid
     * @param space             moving text position
     * @param themeDark         theme
     * @param randomGameBoard   array of the game
     */
    public void displayLabels(int numLabels, int horizontalSpace, int verticalSpace, int downSpace,
            int moveTopLabelRight, int gridSize, String space, ThemeSet themeDark, int[] randomGameBoard) {
        mainPanel.gridButtonLabels.panelLabelsGrid(mainPanel,
                themeDark, numLabels, downSpace, moveTopLabelRight, space,
                verticalSpace, horizontalSpace,
                generateTopLabels(randomGameBoard),
                generateLeftLabels(randomGameBoard));
    }

    /**
     * distroy any exist game
     * 
     * @param modelSize grid size
     */
    public void destroyGame(int modelSize) {
        mainPanel.rightP.destroy(mainPanel, modelSize);
    }

    /**
     * refresh the panels on frame
     */
    public void cleanUp() {
        mainPanel.leftP.leftPanel.revalidate();
        mainPanel.leftP.leftPanel.repaint();
        mainPanel.top_Bottom.topPanel.revalidate();
        mainPanel.top_Bottom.topPanel.repaint();
        mainPanel.centerP.centerPanel.revalidate();
        mainPanel.centerP.centerPanel.repaint();
    }

    /**
     * distroy any exist lables
     */
    public void destroyLabels() {
        mainPanel.leftP.leftPanel.removeAll();
        mainPanel.top_Bottom.topPanel.removeAll();
    }
    /**
     * when end the client, it will disable the buttons 
     */
    public void endClient() {
        mainPanel.client.connect.setEnabled(true);
        mainPanel.client.sendGame.setEnabled(false);
        mainPanel.client.sendData.setEnabled(false);
        mainPanel.client.receiveGame.setEnabled(false);

        mainPanel.client.stopNet();
    }
    /**
     * sentGameToServer
     * 
     * @param randomGameBoard main array of the game 
     */
    public void sentGameToServer(int[] randomGameBoard) {
        mainPanel.client.sendGameToServer(randomGameBoard);
    }
    /**
     * resetPoints
     */
    public void resetPoints() {
        mainPanel.rightP.resetPointsResult();

    }
    /**
     * timeResult it will show on server result
     *  
     * @return getTimeResult
     */
    public String timeResult() {
        return mainPanel.rightP.getTimeResult();

    }
    /**
     * pointsResult it will show on server result
     *  
     * @return getPointsResult
     */
    public int pointsResult() {
        return mainPanel.rightP.getPointsResult();

    }
    /**
     * display text in jtextarea
     * 
     * @param txt txt
     */
    public void cliantAction(String txt) {
        mainPanel.client.actionClient.insert(txt + "\n", 0);

    }

    /**
     * design mode window for selkect size of grid
     */
    public void designMode() {

        final String TITLE = "Design Mode";
        designFrame = new JFrame(TITLE);
        designFrame.setLayout(new BorderLayout());
        designFrame.setResizable(false);
        designFrame.setResizable(false);
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 250;
        int height = 250;
        designFrame.setSize(width, height);
        designFrame.setLocation(center.x - width / 2, center.y - height / 2);
        designFrame.add(mainPanel.rightP.layeredPane, BorderLayout.CENTER);// add our layeredPane which has all the
                                                                           // components
        designFrame.setVisible(true); // make window visible
    }

    /**
     * set ON and OFF mode play or design mode
     * 
     * @param designMode return true or false
     * @param playMode   return true or false
     */
    public void onOfPlayMode(boolean designMode, boolean playMode) {
        mainPanel.rightP.playPatterns.setVisible(designMode);
        mainPanel.rightP.savePatterns.setVisible(designMode);
        mainPanel.rightP.exitPatterns.setVisible(designMode);
        mainPanel.rightP.rightPanel.setVisible(playMode);
        mainPanel.topLeftPANEL.topLeftPanel.setVisible(playMode);
        mainPanel.topRightPanel().topRightPanel.setVisible(playMode);
        mainPanel.leftP.leftPanel.setVisible(playMode);
        mainPanel.top_Bottom.topPanel.setVisible(playMode);
        mainPanel.top_Bottom.bottomPanel.setVisible(playMode);
    }

    /**
     * Sets the correct values for a given amount of squares
     * 
     * @param values array of integers that are randomly generated.
     */
    public void setCorrectValues(int[] values) {
        for (int i = 0; i < values.length; i++) {
            mainPanel.rightPanel().setSquareAt(i, values[i]);
        }
    }

    /**
     * Returns whether the correct value is selected for a button at a specific
     * index
     * 
     * @param index of the button to be checked
     * @return true/false depending on user input
     */
    boolean checkAnswer(int index) {
        return mainPanel.rightPanel().checkAnswer(index);
    }

    /**
     * Updates the time in the GUI
     * 
     * @param timeElapsed time (seconds) passed since the first square is pressed
     */
    public void setTime(int timeElapsed) {
        mainPanel.rightPanel().getTimer().setText(String.valueOf(timeElapsed));

    }

    /**
     * Show a JOptionPane that indicates to the user whether they won or lost.
     * 
     * @param win true indicates a win, false indicates a loss
     */
    public void showWinStatus(boolean win) {
        resetGame();
        // Win
        if (win) {
            mainPanel.rightP.resetGrid(mainPanel);
            JOptionPane.showMessageDialog(mainPanel.frame, "",
                    "Congratulations!",
                    JOptionPane.INFORMATION_MESSAGE,
                    mainPanel.rightP.getImageWin());
        }
        // Loss
        else if (!win) {
            mainPanel.rightP.resetGrid(mainPanel);
            JOptionPane.showMessageDialog(mainPanel.frame, "", "You Lost!",
                    JOptionPane.INFORMATION_MESSAGE,
                    mainPanel.rightP.getImageLost());
        }
    }
    /**
     * set color 
     * 
     * @param gridSize gridSize
     * @param prompt prompt
     */
    public void selectColor(int gridSize, boolean prompt) {
        if (prompt) {
            JFrame frame = new JFrame("Solution");
            Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
            int width = 670;
            int height = 687;
            frame.setSize(width, height);
            frame.setLocation(center.x - width / 2, center.y - height / 2);
            frame.setLocationRelativeTo(null);
            menuBar = new JMenuBar();
            menu = new JMenu("Set Colors");
            menuBar.add(menu);
            JPanel panel = new JPanel();
            JButton buttonRightSquare = new JButton("right square");
            JButton buttonWRONGSquare = new JButton("wrong square");
            JButton buttonEMPTYSquare = new JButton("empty square");
            buttonRightSquare.setBackground(colors[0]);
            buttonWRONGSquare.setBackground(colors[1]);
            buttonEMPTYSquare.setBackground(colors[2]);
            menuItems = new JMenuItem[3];
            for (int i = 0; i < 3; i++) {
                menuItems[i] = new JMenuItem("Click " + (i + 1) + " Color");
                int index = i;
                menuItems[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color color = JColorChooser.showDialog(frame, "Choose a Color", colors[index]);
                        if (color != null) {
                            boolean colorExists = false;
                            for (int j = 0; j < colors.length; j++) {
                                if (j != index && color.equals(colors[j])) {
                                    colorExists = true;
                                    break;
                                }
                            }
                            if (colorExists) {
                                JOptionPane.showMessageDialog(frame,
                                        "Color already chosen. Please choose a different color.");
                            } else {
                                colors[index] = color;
                                for (int j = 0; j < gridSize; j++) {

                                    mainPanel.rightPanel().buttons[j].updateIconColor(0, colors[0]);
                                    mainPanel.rightPanel().buttons[j].updateIconColor(1, colors[1]);
                                    mainPanel.rightPanel().buttons[j].updateIconColor(2, colors[2]);
                                    mainPanel.rightPanel().buttons[j].setBackground(colors[2]);

                                    buttonRightSquare.setBackground(colors[0]);
                                    buttonWRONGSquare.setBackground(colors[1]);
                                    buttonEMPTYSquare.setBackground(colors[2]);

                                    mainPanel.repaint();
                                    mainPanel.revalidate();

                                }
                            }
                        }
                    }
                });
                menu.add(menuItems[i]);
            }

            panel.add(buttonRightSquare);
            panel.add(buttonWRONGSquare);
            panel.add(buttonEMPTYSquare);
            frame.add(panel);
            frame.setJMenuBar(menuBar);
            frame.pack();
            frame.setVisible(true);
        } else if (!prompt) {
            for (int j = 0; j < gridSize; j++) {
                mainPanel.rightPanel().buttons[j].updateIconColor(0, colors[2]);
                mainPanel.rightPanel().buttons[j].updateIconColor(1, colors[0]);
                mainPanel.rightPanel().buttons[j].updateIconColor(2, colors[1]);
                mainPanel.rightPanel().buttons[j].setBackground(colors[2]);

            }

        }
    }
    /**
     * gamePoints
     * 
     * @param operation gamePoints
     */
    public void gamePoints(char operation) {

        mainPanel.rightPanel().gamePoints(operation);
    }
    /**
     * getGamePoints
     * 
     * @return getGamePoints
     */
    public int getGamePoints() {
        return mainPanel.rightPanel().getGamePoints();
    }
    /**
     * get game from server
     * 
     * @param local mainPanel
     * @return array requestAndReceiveGameFromServer
     */
    public int [] getGameFromServer(MainGameFrame local){
        return local.client.requestAndReceiveGameFromServer();
    }
}
