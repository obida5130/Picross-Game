package game;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * this class for right panel of the frame which responsible for timer, score,
 * size, control section, and reset buttom
 */
public class RightPanelGame {
    /**
     * timeResult
     */
    public String timeResult;
    /**
     * pointsResult
     */
    public int pointsResult;
    /**
     * Points
     */
    int gamePoints;
    /**
     * rightPanel, game board
     */
    JPanel rightPanel, board;
    /**
     * TextAreas used for points, timer, and action history
     */
    private JTextArea actions, timer, points;
    /**
     * size
     */
    Object size = "";
    /**
     * squares
     */
    int squares = 0;
    /**
     * position of panel
     */
    int x = 827, y = 152, w = 140, h = 730;
    /**
     * buttons array 
     */
    GridButton buttons[] = new GridButton[squares];
    /**
     * JLabel
     */
    JLabel sizeLabel, pointsLabel, timeLabel,imageLabel;
    /**
     * JButton
     */
    JButton resetButton, checkSoluationButton;
    /**
     * JComboBox
     */
    private JComboBox<String> sizeSelection;
    /**
     * numSquares
     */
    int numSquares;
    /**
     * icons for win or lose 
     */
    ImageIcon win, lost, logo;
    /**
     * topValues
     */
    int[][] topValues;
    /**
     * leftValues
     */
    int[][] leftValues;
    /**
     * JRadioButton
     */
    JRadioButton radioButton5x5, radioButton7x7, radioButton9x9, radioButton10x10;
    /**
     * JButton
     */
    JButton okButton, playPatterns, savePatterns, exitPatterns, exitDesignSelect;
    /**
     * ButtonGroup
     */
    ButtonGroup gridSizeGroup;
    /**
     * JLayeredPane
     */
    JLayeredPane layeredPane;
    /**
     * JLabel
     */
    JLabel labelDesign;
    /**
     * constructor of right class for right panel components
     * 
     * @param pane      pane
     * @param theme     creating object to theme class to switch the theme mode
     * @param mainPANEL panel class
     */
    RightPanelGame(Container pane, ThemeSet theme, MainGameFrame mainPANEL, String dir, ActionListener globalListener) {
        Color textColor = theme.getTextColor();
        Color bgColor = theme.getBgColor();
        // String htmlTextColor = theme.htmlTextColor;
        win = new ImageIcon((mainPANEL.dir + "gamepicwinner.png"));
        lost = new ImageIcon((mainPANEL.dir + "gamepicend.png"));
        logo = new ImageIcon((mainPANEL.dir + "mainGameIcon.png"));

        imageLabel = new JLabel((logo));
        imageLabel.setSize(1000, 950);
        rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        timeLabel = new JLabel("Time   ");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        pointsLabel = new JLabel("Points");
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        sizeLabel = new JLabel("Size    ");
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        timer = new JTextArea("0", 1, 6);
        points = new JTextArea("0", 1, 4);

        actions = new JTextArea("", 37, 12);
        actions.setEditable(false);
        actions.setBackground(textColor);

        timeLabel.setForeground(textColor);
        pointsLabel.setForeground(textColor);
        sizeLabel.setForeground(textColor);

        timer.setEditable(false);
        points.setEditable(false);
        rightPanel.setBackground(bgColor);
        rightPanel.setSize(w, h);
        rightPanel.setLocation(x, y);
        rightPanel.setOpaque(false);

        String[] sizes = { "5x5", "7x7", "9x9", "10x10" };
        sizeSelection = new JComboBox<>(sizes);
    
        // Listener
        sizeSelection.addActionListener(globalListener);
        // Set action command
        sizeSelection.setActionCommand("sizeSelection");

        resetButton = new JButton("Reset");
        // Listener
        resetButton.addActionListener(globalListener);
        // Set action command
        resetButton.setActionCommand("resetBtn");

        // checkSoluationButton button to let user check his solution if right and if
        // wrong it will end the game
        checkSoluationButton = new JButton("Check Your Soluion");
        checkSoluationButton.addActionListener(globalListener);
        checkSoluationButton.setActionCommand("checkSolution");
        mainPANEL.top_Bottom.bottomPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.CENTER;
        gbc.gridy = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 130, 0, 0); // Set left inset to 10 pixels
        mainPANEL.top_Bottom.bottomPanel.add(checkSoluationButton, gbc);

        board = new JPanel();
        board.setSize(635, 305);

        rightPanel.add(pointsLabel);
        rightPanel.add(points);
        rightPanel.add(sizeLabel);
        rightPanel.add(sizeSelection);
        JScrollPane scroll = new JScrollPane(actions);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBar(null);

        rightPanel.add(scroll);
        rightPanel.add(timeLabel);
        rightPanel.add(timer);
        rightPanel.add(resetButton);
        pane.add(rightPanel);

        /**************************************************/
        //this interface part for design mode, DONT TOUCH//
        /**************************************************/
        labelDesign= new JLabel("Please select grid size to design");
        labelDesign.setSize(335,20);
        labelDesign.setLocation(30,20);
        /**************************************************/
        radioButton5x5 = new JRadioButton("5x5");
        radioButton5x5.setLocation(40, 50);
        radioButton5x5.setSize(85, 27);
        radioButton5x5.addActionListener(globalListener);
        radioButton5x5.setActionCommand("radioButton5x5");
        /**************************************************/
        radioButton7x7 = new JRadioButton("7x7");
        radioButton7x7.setLocation(130, 50);
        radioButton7x7.setSize(85, 27);
        radioButton7x7.addActionListener(globalListener);
        radioButton7x7.setActionCommand("radioButton7x7");
        /**************************************************/
        radioButton9x9 = new JRadioButton("9x9");
        radioButton9x9.setLocation(40, 80);
        radioButton9x9.setSize(85, 27);
        radioButton9x9.addActionListener(globalListener);
        radioButton9x9.setActionCommand("radioButton9x9");
        /**************************************************/
        radioButton10x10 = new JRadioButton("10x10");
        radioButton10x10.setLocation(130, 80);
        radioButton10x10.setSize(85, 27);
        radioButton10x10.addActionListener(globalListener);
        radioButton10x10.setActionCommand("radioButton10x10");
        /**************************************************/
        gridSizeGroup = new ButtonGroup();
        gridSizeGroup.add(radioButton5x5);
        gridSizeGroup.add(radioButton7x7);
        gridSizeGroup.add(radioButton9x9);
        gridSizeGroup.add(radioButton10x10);
        /**************************************************/
        okButton = new JButton("OK");
        okButton.setSize(85, 27);
        okButton.setLocation(30, 140);
        okButton.setBackground(new Color(255, 255, 153));
        okButton.addActionListener(globalListener);
        okButton.setActionCommand("okButton");
        /**************************************************/
        exitDesignSelect = new JButton("Exit");
        exitDesignSelect.setSize(85, 27);
        exitDesignSelect.setLocation(140, 140);
        exitDesignSelect.setBackground(new Color(255, 255, 153));
        exitDesignSelect.addActionListener(globalListener);
        exitDesignSelect.setActionCommand("exitDesignSelect");
        /**************************************************/
        layeredPane= new JLayeredPane();
        layeredPane.add(radioButton5x5, Integer.valueOf(1));
        layeredPane.add(radioButton7x7, Integer.valueOf(2));
        layeredPane.add(radioButton9x9, Integer.valueOf(3));
        layeredPane.add(radioButton10x10, Integer.valueOf(4));
        layeredPane.add(okButton, Integer.valueOf(5));
        layeredPane.add(labelDesign, Integer.valueOf(6));
        layeredPane.add(exitDesignSelect, Integer.valueOf(6));
        /*********************************************************************************/
        //this part display when design mode active so will show on main frame of the game
        /*********************************************************************************/
        playPatterns = new JButton("Play Patterns");
        playPatterns.setSize(120, 27);
        playPatterns.setLocation(250, 120);
        playPatterns.setBackground(new Color(55, 255, 153));
        playPatterns.addActionListener(globalListener);
        playPatterns.setActionCommand("playPatterns");
        playPatterns.setVisible(false);
        /**************************************************/
        savePatterns = new JButton("Save Patterns");
        savePatterns.setSize(120, 27);
        savePatterns.setLocation(400, 120);
        savePatterns.setBackground(new Color(55, 255, 153));
        savePatterns.addActionListener(globalListener);
        savePatterns.setActionCommand("savePatterns");
        savePatterns.setVisible(false);
        /**************************************************/
        exitPatterns = new JButton("Exit Design Mode");
        exitPatterns.setSize(140, 27);
        exitPatterns.setLocation(550, 120);
        exitPatterns.setBackground(new Color(55, 255, 153));
        exitPatterns.addActionListener(globalListener);
        exitPatterns.setActionCommand("exitPatterns");
        exitPatterns.setVisible(false);
        /**************************************************/
    }

    /**
     * Draws the board
     * 
     * @param imageDir       images directory - stores game graphics
     * @param mainPANEL      stores most graphical components
     * @param numSquares     size of the grid
     * @param globalListener the controller class which is responsible for all user
     *                       actions
     */
    public void drawGame(String imageDir, MainGameFrame mainPANEL, int numSquares, ActionListener globalListener) {

        numSquares = Integer.parseInt(String.valueOf(Math.round(Math.sqrt(numSquares))));

        squares = numSquares * numSquares;
        buttons = new GridButton[squares];
        mainPANEL.centerP.centerPanel.setLayout(new GridLayout(numSquares, numSquares, 2, 2));
        int lastX = 0;
        for (int i = 0; i < squares; i++) {
            buttons[i] = new GridButton(imageDir);
            buttons[i].init = true;
            buttons[i].yCoord = i % numSquares;
            buttons[i].xCoord = lastX;
            buttons[i].setCorrectValue(0);
            buttons[i].addActionListener(globalListener);
            buttons[i].setBackground(Color.white);

            // Increment x value of the gridButton
            if ((i + 1) % numSquares == 0)
                lastX = (i + 1) / numSquares;
            mainPANEL.centerP.centerPanel.add(buttons[i]);
        }
        mainPANEL.frame.revalidate();
        return;
    }


    /**
     * Resets the grid and panels
     * 
     * @param mainPANEL reference to a mainPanel object
     */
    public void resetGrid(MainGameFrame mainPANEL) {
        mainPANEL.centerP.centerPanel.removeAll();
        mainPANEL.leftP.leftPanel.removeAll();
        mainPANEL.top_Bottom.topPanel.removeAll();
        mainPANEL.rightP.timer.setText("");

        mainPANEL.frame.repaint(); // repaint the game board to update the UI
    }

    /**
     * Resets game components
     * 
     * @param mainPANEL reference to a mainPanel object
     */
    public void resetGame(MainGameFrame mainPANEL) {
        squares = 0;
        mainPANEL.centerP.centerPanel.removeAll();
        mainPANEL.leftP.leftPanel.removeAll();
        mainPANEL.top_Bottom.topPanel.removeAll();
        mainPANEL.rightP.actions.setText("");
        mainPANEL.rightP.timer.setText("0");

        /* more stuff need to reset but for later, like point and timer */
        mainPANEL.frame.repaint(); // repaint the game board to update the UI

    }
    /**
     * delete if the grid exist
     * 
     * @param mainPANEL  panel
     * @param numSquares size of the grid
     */
    public void destroy(MainGameFrame mainPANEL, int numSquares) {
        try {
            if (buttons[0].init) {
                for (int i = 0; i < numSquares; i++) {
                    mainPANEL.centerP.centerPanel.remove(buttons[i]);
                    buttons[i].init = false;
                    // gridButton[i] = null;
                }
                mainPANEL.repaint();
            }
        } catch (Exception e) {

        }
    }

    /**
     * Returns the selected size in the format of #x#
     * 
     * @return the selected size in the format of #x#
     */
    public JComboBox<String> getSizeSelection() {
        return sizeSelection;
    }

    /**
     * returns reset button object
     * 
     * @return reset button object
     */
    public JTextArea actionsArea() {
        return actions;
    }

    /**
     * returns reset button object
     * 
     * @return reset button object
     */
    public JButton getRstBtn() {
        return resetButton;
    }

    /**
     * Returns null
     * 
     * @param labelName label to be returned
     * @return a JLabel object
     */
    public JLabel getLabel(String labelName) {

        labelName = labelName.toLowerCase();
        if (labelName.equals("timelabel"))
            return timeLabel;
        else if (labelName.equals("pointslabel"))
            return pointsLabel;
        else if (labelName.equals("sizelabel"))
            return sizeLabel;

        return null;
    }

    /**
     * Returns reset button object
     * 
     * @return eset button object
     */
    public JButton getResetBtn() {
        return resetButton;
    }

    /**
     * Returns user solution
     * 
     * @return user solution
     */
    public JButton getsSoluationUser() {

        return checkSoluationButton;
    }

    /**
     * Returns the JTextArea used for time
     * 
     * @return the JTextArea used for time
     */
    public JTextArea getTimer() {
        return timer;
    }

    /**
     * Sets the timer JTextArea
     * 
     * @param timerText string
     */
    public void setTimerText(String timerText) {
        timer.setText(timerText);
    }

    /**
     * Returns win image
     * 
     * @return win image
     */
    public Icon getImageWin() {
        return win;
    }

    /**
     * Returns loss image
     * 
     * @return loss image
     */
    public Icon getImageLost() {
        return lost;
    }

    /**
     * sets square at a given index
     * 
     * @param index index of square to be set
     * @param value the value the square should be set to
     */
    void setSquareAt(int index, int value) {
        buttons[index].setCorrectValue(value);
        buttons[index].setIndex(index);
    }

    /**
     * Compares the correct value with the user-determined value
     * 
     * @param index index of the button
     * @return returns true or false
     */
    boolean checkAnswer(int index) {
        return (buttons[index].getValue() == buttons[index].getCorrectValue());
    }

    /**
     * Returns 0
     * 
     * @param operation increment or decrement points
     * @return zero
     */
    public int gamePoints(char operation) {

        if (operation == '+')
            this.gamePoints++;
        else if (operation == '-') {
            this.gamePoints--;
        }
        points.setText(String.valueOf(gamePoints));
        return 0;
    }

    /**
     * Returns game points
     * 
     * @return game points
     */
    public int getGamePoints() {
        return this.gamePoints;
    }
    /**
     * getTimeResult
     * 
     * @return timeResult
     */
    public String getTimeResult() {
        // Your existing code for handling a win event
        timeResult = getTimer().getText();
        return timeResult;
        // You can use the endTime variable as needed
    }
    /**
     * getPointsResult
     * 
     * @return pointsResult
     */
    public int getPointsResult() {
        // Your existing code for handling a win event
        pointsResult = getGamePoints();
        return pointsResult;
        // You can use the endTime variable as needed
    }
    /**
     * resetPointsResult
     */ 
    public void resetPointsResult() {
        gamePoints=0;
        points.setText(String.valueOf(gamePoints));
    }
}
