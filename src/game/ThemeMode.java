package game;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * this class for right top panel of the frame which responsible for theme mode
 */
public class ThemeMode {
    /**
     * topRightPanel
     */
    JPanel topRightPanel;
    /**
     * currTheme
     */
    private String currTheme;
    /**
     * JRadioButton
     */
    private JRadioButton darkTheme, lightTheme;
    /**
     * JLabel themeL, lightThemeLabel, darkThemeLabel
     */
    JLabel themeL, lightThemeLabel, darkThemeLabel;
    /**
     * POSITIONS
     */
    int x = 829, y = 17, w = 136, h = 136;
    /**
     * constructor
     * 
     * @param pane      pane
     * @param theme     creating object to theme class to switch the theme mode
     * @param mainPANEL mainPANEL
     */
    ThemeMode(Container pane, ThemeSet theme, MainGameFrame mainPANEL, ActionListener globListener) {

        Color textColor = theme.getTextColor();
        Color bgColor = theme.getBgColor();
        themeL = new JLabel();
        themeL.setText("Display Theme: ");
        themeL.setForeground(textColor);
        themeL.setFont(new Font("Arial", Font.BOLD, 16));
        themeL.setVerticalAlignment(JLabel.TOP);// set text to top of panel
        topRightPanel = new RoundedPanel(30);

        darkTheme = new JRadioButton();
        darkTheme.setBackground(null);
        darkTheme.addActionListener(globListener);

        lightTheme = new JRadioButton();
        lightTheme.setBackground(null);
        lightTheme.addActionListener(globListener);

        // Set action commands for light and dark theme radio buttons
        darkTheme.setActionCommand("darkBtn");
        lightTheme.setActionCommand("lightBtn");

        darkThemeLabel = new JLabel("Dark Mode");
        darkThemeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        darkThemeLabel.setForeground(textColor);
        lightThemeLabel = new JLabel("Light Mode");
        lightThemeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lightThemeLabel.setForeground(textColor);
        topRightPanel.setSize(w, h);
        topRightPanel.setLocation(x, y);
        topRightPanel.setBackground(bgColor);
        topRightPanel.add(themeL);

        topRightPanel.add(darkThemeLabel);
        topRightPanel.add(darkTheme);

        topRightPanel.add(lightThemeLabel);
        topRightPanel.add(lightTheme);
        topRightPanel.setOpaque(false);

        pane.add(topRightPanel);

    }

    /**
     * set theme to panels
     * 
     * @param mainPANEL  panel
     * @param localTheme current theme
     */
    public void setTheme(MainGameFrame mainPANEL, ThemeSet localTheme) {
        // TOP PANEL
        mainPANEL.top_Bottom.topPanel.setBackground(localTheme.getBgColor());

        // TOP-LEFT PANEL
        mainPANEL.topLeftPANEL.topLeftPanel.setBackground(localTheme.getBgColor());
        mainPANEL.topLeftPANEL.turkishLabel.setForeground(localTheme.getTextColor());
        mainPANEL.topLeftPANEL.englishLabel.setForeground(localTheme.getTextColor());
        mainPANEL.topLeftPANEL.frenchLabel.setForeground(localTheme.getTextColor());
        mainPANEL.topLeftPANEL.languageL.setForeground(localTheme.getTextColor());

        // TOP-RIGHT PANEL
        mainPANEL.themeMODE.topRightPanel.setBackground(localTheme.getBgColor());

        mainPANEL.frame.getContentPane().setBackground(localTheme.getFrameColor());

        lightThemeLabel.setForeground(localTheme.getTextColor());
        darkThemeLabel.setForeground(localTheme.getTextColor());
        themeL.setForeground(localTheme.getTextColor());

        // BOTTOM PANEL
        mainPANEL.top_Bottom.bottomPanel.setBackground(localTheme.getBgColor());

        // RIGHT PANEL
        mainPANEL.rightP.rightPanel.setBackground(localTheme.getBgColor());
        mainPANEL.rightP.timeLabel.setForeground(localTheme.getTextColor());
        mainPANEL.rightP.sizeLabel.setForeground(localTheme.getTextColor());
        mainPANEL.rightP.pointsLabel.setForeground(localTheme.getTextColor());
        mainPANEL.rightP.actionsArea().setBackground(localTheme.getTextColor());
        mainPANEL.rightP.actionsArea().setForeground(localTheme.getBgColor());
        mainPANEL.rightP.actionsArea().setBackground(localTheme.getTextColor());
        mainPANEL.rightP.actionsArea().setForeground(localTheme.getBgColor());
        mainPANEL.rightP.actionsArea().setBackground(localTheme.getTextColor());
        mainPANEL.rightP.actionsArea().setForeground(localTheme.getBgColor());

        mainPANEL.centerP.centerPanel.setBackground(localTheme.getCenteColor());

        // LEFT PANEL
        mainPANEL.leftP.leftPanel.setBackground(localTheme.getBgColor());
        //labels grid colors
        int newSquare = (int) Math.sqrt(mainPANEL.rightP.squares);
        for (int i = 0; i < newSquare; i++) {
            for (int j = 0; j < mainPANEL.gridButtonLabels.leftLabels[i].length; j++) {
                mainPANEL.gridButtonLabels.leftLabels[i][j].setForeground(localTheme.getTextColor());
            }
        }
        for (int i = 0; i < newSquare; i++) {
            for (int j = 0; j < mainPANEL.gridButtonLabels.topLabels[i].length; j++) {
                mainPANEL.gridButtonLabels.topLabels[i][j].setForeground(localTheme.getTextColor());
            }
        }
    }

    /**
     * 
     * @param radioBtn either 'dark' or 'light'
     * @return a JRadioButton object, or null
     */
    public JRadioButton radioBtn(String radioBtn) {

        if (radioBtn.equals("dark")) {
            return darkTheme;
        } else if (radioBtn.equals("light")) {
            return lightTheme;
        } else
            return null;
    }

    /**
     * Returns current theme
     * 
     * @return current theme
     */
    public String getTheme() {
        return currTheme;
    }

    /**
     * Sets the theme
     * 
     * @param theme used to set the theme
     */
    public void setTheme(String theme) {
        this.currTheme = theme;
    }

    /**
     * Gets a certain label by variable name
     * 
     * @param labelName label to be returned
     * @return label
     */
    public JLabel getLabel(String labelName) {
        labelName = labelName.toLowerCase();
        if (labelName.equals("themel"))

            return themeL;
        else if (labelName.equals("darkthemel"))
            return darkThemeLabel;
        else if (labelName.equals("lightthemel"))
            return lightThemeLabel;

        return null;
    }
}
