package game;

import javax.swing.*;
import java.awt.event.ActionListener;
/**
 * menu class for game frame.
 */
public class Menu extends JFrame {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * JMenuBar
     */
    private static JMenuBar mb;

    /**
     * JMenu
     */
    private static JMenu gameMenu, actionsMenu, helpMenu, designMode;
    /**
     * JMenuItem
     */
    private static JMenuItem newItem, solutionItem, exitItem, loadItem, saveItem, aboutItem, colorItem, designPatterns;
    /**
     * ImageIcon
     */
    private ImageIcon newIcon, solutionIcon, exitIcon, aboutIcon, colorIcon, open, save, design;

    /**
     * Menu constructor
     * 
     * @param mainPanel      MainPanel object
     * @param globalListener global action listener
     */
    Menu(MainGameFrame mainPanel, ActionListener globalListener) {

        newIcon = new ImageIcon((mainPanel.getImageDir() + "piciconnew.gif"));
        solutionIcon = new ImageIcon((mainPanel.getImageDir() + "piciconsol.gif"));
        exitIcon = new ImageIcon((mainPanel.getImageDir() + "piciconext.gif"));
        aboutIcon = new ImageIcon((mainPanel.getImageDir() + "piciconabt.gif"));
        colorIcon = new ImageIcon((mainPanel.getImageDir() + "piciconcol.gif"));
        open= new ImageIcon((mainPanel.getImageDir() + "open.gif"));
        save= new ImageIcon((mainPanel.getImageDir() + "save.gif"));
        design= new ImageIcon((mainPanel.getImageDir() + "design.gif"));
        // create a menubar
        mb = new JMenuBar();

        // create a menu
        gameMenu = new JMenu("Game");
        // create menuitems
        newItem = new JMenuItem("New");
        solutionItem = new JMenuItem("Solution");
        exitItem = new JMenuItem("Exit");

        // Add icons to each JMenuItem
        newItem.setIcon(newIcon);
        solutionItem.setIcon(solutionIcon);
        exitItem.setIcon(exitIcon);

        // add menu items to menu
        gameMenu.add(newItem);
        gameMenu.add(solutionItem);
        gameMenu.add(exitItem);

        // Design mode
        designMode = new JMenu("Design Mode");
        designPatterns = new JMenuItem("Design Patterns");
        designPatterns.setIcon(design);
        designPatterns.addActionListener(globalListener);
        designPatterns.setActionCommand("designPatterns");
        designMode.add(designPatterns);


        // Actions menu
        actionsMenu = new JMenu("Actions");
        loadItem = new JMenuItem("Load");
        loadItem.setIcon(open);
        loadItem.setActionCommand("loadItem");
        // Add listeners
        loadItem.addActionListener(globalListener);
        saveItem = new JMenuItem("Save");
        saveItem.setIcon(save);

        saveItem.addActionListener(globalListener);
        saveItem.setActionCommand("saveItem");
        solutionItem.addActionListener(globalListener);
        solutionItem.setActionCommand("solutionItem");

        actionsMenu.add(loadItem);
        actionsMenu.add(saveItem);

        helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About");
        aboutItem.setIcon(aboutIcon);
        aboutItem.addActionListener(globalListener);
        aboutItem.setActionCommand("aboutItem");
        colorItem = new JMenuItem("Color");

        colorItem.setActionCommand("colorItem");
        colorItem.addActionListener(globalListener);
        colorItem.setIcon(colorIcon);

        helpMenu.add(colorItem);
        helpMenu.add(aboutItem);

        // add menu to menu bar
        mb.add(gameMenu);
        mb.add(actionsMenu);
        mb.add(helpMenu);
        mb.add(designMode);
    }

    /**
     * Returns the JMenuBar for the game
     * 
     * @return the JMenuBar for the game
     */
    public JMenuBar getJMenuBar() {
        return mb;
    }

    /**
     * The JMenuItem for the game
     * 
     * @return JMenuItem
     */
    public JMenuItem getJMenuItem() {
        return colorItem;
    }

    /**
     * Gets the JMenuItem by variable name
     * 
     * @param itemName variable name for the JMenuItem
     * @return the JMenuItem by variable name
     */
    public JMenuItem getMenuItem(String itemName) {

        if (itemName.equals("newItem"))
            return newItem;
        else if (itemName.equals("solutionItem"))
            return solutionItem;
        else if (itemName.equals("exitItem"))
            return exitItem;
        else if (itemName.equals("loadItem"))
            return loadItem;
        else if (itemName.equals("saveItem"))
            return saveItem;
        else if (itemName.equals("colorItem"))
            return colorItem;
        else if (itemName.equals("aboutItem"))
            return aboutItem;
        else if (itemName.equals("designPatterns"))
            return designPatterns;
        return null;
    }

}