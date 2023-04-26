package game;

import java.awt.*;
import javax.swing.*;

/**
 * this class for left panel of the frame which responsible to display numbers
 * in row for picroos
 */
public class LeftPanelGame {

    JPanel leftPanel;
    int x = 17, y = 154, w = 136, h = 726;

    /**
     * constructor for left panel class
     * 
     * @param pane  pane
     * @param theme creating object to theme class to switch the theme mode
     */
    LeftPanelGame(Container pane, ThemeSet theme) {
        // Color textColor = theme.getTextColor();
        Color bgColor = theme.getBgColor();
        // String htmlTextColor = theme.htmlTextColor;
        leftPanel = new RoundedPanel(30);
        leftPanel.setBackground(bgColor);

        leftPanel.setSize(w, h);
        leftPanel.setLocation(x, y);
        leftPanel.setOpaque(false);
        pane.add(leftPanel);
    }
}
