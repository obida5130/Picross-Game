package game;

import java.awt.*;
import javax.swing.*;

/**
 * this class is responsible to make panel angles as rounded by setting it to
 * spacific radius
 */
class RoundedPanel extends JPanel {

    /**
     * JPanel implements JComponent which implements Serializable.
     * This line prevents warnings regarding serialVersionUID
     */
    private static final long serialVersionUID = 1;

    /**
     * Used to set background color
     */
    private Color backgroundColor;
    /**
     * Set corner radius
     */
    private int cornerRadius = 15;

    /**
     * constructor for round panel class
     * 
     * @param layout calling the layout manager
     * @param radius initializing the radius for panel
     */
    public RoundedPanel(LayoutManager layout, int radius) {
        super(layout);
        cornerRadius = radius;
    }

    /**
     * another constructor for color
     * 
     * @param layout  layout manager
     * @param radius  radius
     * @param bgColor background color
     */
    public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    /**
     * set it to radius
     * 
     * @param radius raduis
     */
    public RoundedPanel(int radius) {
        super();
        cornerRadius = radius;
    }

    /**
     * 
     * @param radius  radius
     * @param bgColor background color
     */
    public RoundedPanel(int radius, Color bgColor) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    /**
     * draw the panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draws the rounded panel with borders.
        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());
        }
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint border
    }
}