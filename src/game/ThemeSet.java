package game;

import java.awt.*;

/**
 * this class is the main theme of the game
 */
public class ThemeSet {

    /**
     * String representation of colors
     */
    private String mode, htmlTextColor;

    /**
     * Colors used for elements of this class
     */
    private Color textColor, bgColor, frameColor, centeColor;

    /**
     * constructor theme class
     * 
     * @param theme theme
     */
    ThemeSet(String theme) {
        setTheme(theme);
    }

    /**
     * Sets dark or light theme
     * 
     * @param mode dark or light modes
     */
    public void setTheme(String mode) {
        this.mode = mode;
        if (mode.equalsIgnoreCase("dark")) {
            textColor = Color.white;
            htmlTextColor = "white";
            bgColor = Color.black;
            frameColor = new Color(255, 255, 153); // sets the color to bright yellow
            centeColor = Color.LIGHT_GRAY;

        } else {
            textColor = Color.black;
            htmlTextColor = "black";
            bgColor = Color.white;
            frameColor = Color.LIGHT_GRAY; // sets the color to bright yellow
            centeColor = new Color(255, 255, 153);
        }
    }

    /**
     * Returns the html description of text color
     * 
     * @return the html description of text color
     */
    public String getHtmlTextColor() {
        return htmlTextColor;
    }

    /**
     * Returns dark or light mode
     * 
     * @return dark or light mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Returns text color
     * 
     * @return text color
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * Returns the background color for the current theme
     * 
     * @return the background color for the current theme
     */
    public Color getBgColor() {
        return bgColor;
    }

    /**
     * Returns color of the outer frame
     * 
     * @return color of the outer frame
     */
    public Color getFrameColor() {
        return frameColor;
    }

    /**
     * Returns centre color
     * 
     * @return centre color
     */
    public Color getCenteColor() {
        return centeColor;
    }
}
