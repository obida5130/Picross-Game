package game;

import java.awt.*;
import javax.swing.*;

/**
 * center panel of the frame which responsible for grid
 */
public class Center {

    /**
     * center panel
     */
    JPanel centerPanel;
    /**
     * width, height, x and y coordinates for panel placement
     */
    int x = 155, y = 155, w = 670, h = 687;

    /**
     * constructor for center panel
     * 
     * @param pane  pane
     * @param theme creating object to theme class to switch the theme mode
     */
    Center(Container pane, ThemeSet theme) {
        // Color textColor = theme.textColor;
        // Color bgColor = theme.bgColor;
        // String htmlTextColor = theme.htmlTextColor;
        Color centerColor = theme.getCenteColor();

        centerPanel = new JPanel();
        centerPanel.setSize(w, h);
        centerPanel.setLocation(x, y);
        centerPanel.setBackground(centerColor);
    }
}
