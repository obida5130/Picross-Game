package game;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
public class GridButtonLabels {
    JPanel[] leftPpanels;
    JLabel[][] leftLabels;
    JPanel[] topPpanels;
    JLabel[][] topLabels;
    /**
     * panelLabelsGrid
     * 
     * @param mainPANEL         mainPANEL
     * @param theme             theme
     * @param numLabels         numLabels in grid
     * @param downSpace         downSpace move text
     * @param moveTopLabelRight move text
     * @param space             move text
     * @param verticalSpace     move text
     * @param horizontalSpace   move text
     * @param topValues         gridv labels
     * @param leftValues        gridv labels
     */
    public void panelLabelsGrid(MainGameFrame mainPANEL, ThemeSet theme, int numLabels, int downSpace, int moveTopLabelRight,
    String space, int verticalSpace, int horizontalSpace, ArrayList<ArrayList<Integer>> topValues, ArrayList<ArrayList<Integer>> leftValues) {

        Color textColor = theme.getTextColor();
        Color bgColor = theme.getBgColor();
        topLabels = new JLabel[numLabels][2];
        topPpanels = new JPanel[numLabels];
        JPanel topRowPanel = new JPanel();
        topRowPanel.setLayout(new BoxLayout(topRowPanel, BoxLayout.X_AXIS));
        topRowPanel.setBackground(bgColor);
        topRowPanel.setOpaque(false);
        for (int i = 0; i < numLabels; i++) {
            topPpanels[i] = new JPanel();
            topPpanels[i].setLayout(new BoxLayout(topPpanels[i], BoxLayout.Y_AXIS));
            topPpanels[i].setBackground(bgColor);
            topPpanels[i].setOpaque(false);
            topLabels[i] = new JLabel[topValues.get(i).size()];
            for (int j = 0; j < topValues.get(i).size(); j++) {
                topLabels[i][j] = new JLabel("" + space + topValues.get(i).get(j));
                topLabels[i][j].setBorder(BorderFactory.createEmptyBorder(10, 0, 1, moveTopLabelRight));
                if (topValues.get(i).size() == 3) {
                    topLabels[i][j].setFont(new Font("Arial", Font.BOLD, 17));

                } else if (topValues.get(i).size() == 4 || topValues.get(i).size() == 5) {
                    topLabels[i][j].setFont(new Font("Arial", Font.BOLD, 13));

                } else {
                    topLabels[i][j].setFont(new Font("Arial", Font.BOLD, 20));

                }
                topLabels[i][j].setForeground(textColor);
                topPpanels[i].add(topLabels[i][j]);
            }
            topPpanels[i].setVisible(true);
            topRowPanel.add(topPpanels[i]);
            if (i != numLabels - 1) { // add horizontal space between panels except for the last panel
                topRowPanel.add(Box.createHorizontalStrut(horizontalSpace));
            }
        }
        topRowPanel.setVisible(true);
        // add vertical space between the topPpanels and the board
        mainPANEL.top_Bottom.topPanel.removeAll();
        mainPANEL.top_Bottom.topPanel.add(Box.createVerticalStrut(-7), BorderLayout.NORTH);
        mainPANEL.top_Bottom.topPanel.add(topRowPanel, BorderLayout.CENTER);
        leftLabels = new JLabel[numLabels][2];
        leftPpanels = new JPanel[numLabels];
        JPanel leftColumnPanel = new JPanel();
        leftColumnPanel.setLayout(new BoxLayout(leftColumnPanel, BoxLayout.Y_AXIS));
        leftColumnPanel.setBackground(bgColor);
        leftColumnPanel.setOpaque(false);
        for (int i = 0; i < numLabels; i++) {
            leftPpanels[i] = new JPanel();
            leftPpanels[i].setLayout(new BoxLayout(leftPpanels[i], BoxLayout.X_AXIS));
            leftPpanels[i].setBackground(bgColor);
            leftPpanels[i].setOpaque(false);
            leftLabels[i] = new JLabel[leftValues.get(i).size()];
            for (int j = 0; j < leftValues.get(i).size(); j++) {
                leftLabels[i][j] = new JLabel("   " + leftValues.get(i).get(j));
                leftLabels[i][j].setBorder(BorderFactory.createEmptyBorder(downSpace, 0, downSpace, 0));
                if (leftValues.get(i).size() == 3) {
                    leftLabels[i][j].setFont(new Font("Arial", Font.BOLD, 18));
                } else if (leftValues.get(i).size() == 5) {
                    leftLabels[i][j].setFont(new Font("Arial", Font.BOLD, 16));
                } else {
                    leftLabels[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                }
                leftLabels[i][j].setForeground(textColor);
                leftPpanels[i].add(leftLabels[i][j]);
            }
            leftPpanels[i].setVisible(true);
            leftColumnPanel.add(leftPpanels[i]);
            leftColumnPanel.add(Box.createVerticalStrut(verticalSpace)); // add vertical space between panels
        }

        mainPANEL.leftP.leftPanel.removeAll();
        leftColumnPanel.setVisible(true);
        mainPANEL.leftP.leftPanel.add(Box.createVerticalGlue());
        mainPANEL.leftP.leftPanel.add(leftColumnPanel);
    }
}
