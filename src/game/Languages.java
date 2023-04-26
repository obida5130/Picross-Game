package game;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

/**
 * top left panel for selecting language
 */
public class Languages {

    JPanel topLeftPanel;
    private JRadioButton english, turkish, french;
    String language = "";
    JLabel englishLabel, turkishLabel, frenchLabel, languageL;
    String[] text, text1, text2, displayTheme, setDisplayTheme, time, points, size, reset;
    HashMap<String, String[]> languageText;

    /**
     * constructor for leftTopPanel class
     * 
     * @param pane      pane
     * @param theme     creating object to theme class to switch the theme mode
     * @param mainPANEL panel
     */
    Languages(Container pane, ThemeSet theme, game.MainGameFrame mainPANEL, ActionListener globalListener) {

        // Theme related variables
        Color textColor = theme.getTextColor();
        Color bgColor = theme.getBgColor();
        int x = 17, y = 17, w = 136, h = 136;
        languageL = new JLabel();
        languageL.setText("Languages:");
        languageL.setForeground(textColor);
        languageL.setFont(new Font("Arial", Font.BOLD, 16));
        languageL.setVerticalAlignment(JLabel.TOP);// set text to top of panel

        topLeftPanel = new RoundedPanel(30);
        topLeftPanel.setBackground(bgColor);

        topLeftPanel.setSize(w, h);
        topLeftPanel.setLocation(x, y);
        topLeftPanel.add(languageL);

        languageText = new HashMap<String, String[]>();
        languageText.put("languageL", new String[] { "Languages:", "Diller:", "Langues:" });
        languageText.put("setlanguages", new String[] { "English set", "Türkçe seti", "Ensemble Français" });
        languageText.put("languages", new String[] { "English", "İngilizce", "anglais", "Turkish", "Türkçe", "turque",
                "French", "Fransızca", "Français" });
        languageText.put("displayTheme", new String[] { "Display Theme:", "Ekran Teması:", "Thème d'affichage" });
        languageText.put("setDisplayTheme",
                new String[] { "Dark Mode", "Karanlık Mod", "Mode sombre", "Light Mode", "Işık Modu", "Mode lumière" });
        languageText.put("time", new String[] { "Time", "Zaman", "Temps" });
        languageText.put("points", new String[] { "Points", "Puan", "Points" });
        languageText.put("size", new String[] { "Size", "Boyut", "Taille" });
        languageText.put("reset", new String[] { "Reset", "Sıfırla", "Réinitialiser" });

        text = languageText.get("setlanguages");
        text1 = languageText.get("languages");
        text2 = languageText.get("languageL");
        displayTheme = languageText.get("displayTheme");
        setDisplayTheme = languageText.get("setDisplayTheme");
        time = languageText.get("time");
        points = languageText.get("points");
        size = languageText.get("size");
        reset = languageText.get("reset");

        // English
        english = new JRadioButton();
        english.setBackground(null);

        englishLabel = new JLabel("English");
        englishLabel.setFont(new Font("Arial", Font.BOLD, 16));
        englishLabel.setForeground(textColor);
        english.addActionListener(globalListener);
        english.setActionCommand("englishBtn");

        // Turkish
        turkish = new JRadioButton();
        turkish.addActionListener(globalListener);

        turkish.setBackground(null);
        turkish.setActionCommand("turkishBtn");
        turkishLabel = new JLabel("Turkish");
        turkishLabel.setFont(new Font("Arial", Font.BOLD, 16));
        turkishLabel.setForeground(textColor);

        // French
        french = new JRadioButton();
        french.addActionListener(globalListener);

        french.setBackground(null);
        french.setActionCommand("frenchBtn");

        frenchLabel = new JLabel("French ");
        frenchLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frenchLabel.setForeground(textColor);

        // Add English label/button
        topLeftPanel.add(englishLabel);
        topLeftPanel.add(english);

        // Add Turkish label/button
        topLeftPanel.add(turkishLabel);
        topLeftPanel.add(turkish);

        // Add French label/button
        topLeftPanel.add(frenchLabel);
        topLeftPanel.add(french);

        topLeftPanel.setOpaque(false);
        pane.add(topLeftPanel);
    }

    /**
     * Gets the name of the language
     * 
     * @param lang name of the language
     * @return language button
     */
    public JRadioButton getLangBtn(String lang) {
        if (lang.equals("English")) {
            return english;
        } else if (lang.equals("Turkish")) {
            return turkish;
        } else if (lang.equals("French")) {
            return french;
        } else
            return null;
    }

    /**
     * Returns language name as a string
     * 
     * @return language name as a string
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * Sets the lanaguage
     * 
     * @param lang the lanaguage to be set
     */
    public void setLanguage(String lang) {
        this.language = lang;
    }

    public JLabel getLabel(String labelName) {

        labelName = labelName.toLowerCase();
        if (labelName.equals("languagel"))
            return languageL;
        else if (labelName.equals("englishl"))
            return englishLabel;
        else if (labelName.equals("turkishl"))
            return turkishLabel;
        else if (labelName.equals("frenchl"))
            return frenchLabel;
        return new JLabel();
    }

    public HashMap<String, String[]> translate() {

        return languageText;
    }
}
