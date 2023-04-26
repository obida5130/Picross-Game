package game;

import java.awt.*;
import javax.swing.*;

/**
 * this class for top panel of the frame which responsible to display numbers in colum for picroos
 */
public class TopAndBottomPanel {
    /**
     * JPanel topPanel, bottomPanel
     */
    JPanel topPanel, bottomPanel;
    /**
     * POSITIONS FOR TOP PANEL
     */
    int xT = 153, yT = 15, wT = 676, hT = 140;
    /**
     * POSITIONS FOR BOTTOM PANEL
     */
    int xB = 28, yB = 842, wB = 812, hB = 40;
    /**
     * constructor for top panel class
     * 
     * @param pane  pane
     * @param theme creating object to theme class to switch the theme mode
     */
    TopAndBottomPanel(Container pane, ThemeSet theme) {

        // Color textColor = theme.getTextColor();
        Color bgColor = theme.getBgColor();
        // String htmlTextColor = theme.htmlTextColor;

        topPanel = new RoundedPanel(30);
        topPanel.setSize(wT, hT);
        topPanel.setLocation(xT, yT);
        topPanel.setBackground(bgColor);
        topPanel.setOpaque(false);
        pane.add(topPanel);

        bottomPanel = new RoundedPanel(30);
        bottomPanel.setBackground(bgColor);

        bottomPanel.setSize(wB, hB);
        bottomPanel.setLocation(xB, yB);
        bottomPanel.setOpaque(false);
        pane.add(bottomPanel);

    }
}