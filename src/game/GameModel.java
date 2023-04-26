package game;
import java.io.*;
import java.util.Random;
import javax.swing.JFileChooser;

 /**
 * Mostly used for file I/O and data fields and generate game, save user design game 
 */
public class GameModel {
    /**
     * Used for various calculations as well as when doing file I/O
     */
    private int gridSize;
    /**
     * Stores path to where the images are stored
     */
    private String imageDir;
    /**
     * Stores path to where the files are stored
     */
    private String loadDir;

    /**
     * getting grid size value
     * @return grid size
     */
    public int getGridSize() {
        return gridSize;
    }
    /**
     * setting value of size grid 
     * @param gridSize grid size of pattern
     */
    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }
    /**
     * main 1D array for game board of load, design mode, and generate game...
     */
    private int[] randomGameBoard;

    /**
     * size from file
     */
    private int loadSizel;
    /**
     * Reads gane from a file
     * 
     * @param fileName   currently not used, keeping for future use
     * @return nothing
     */
    public int[] readGameFrom(String fileName) {
        loadSizel = 0;
        randomGameBoard = null;
    
        JFileChooser j = new JFileChooser(getLoadDir());
    
        int returnValue = j.showOpenDialog(null);
    
        // If the user canceled the dialog, return null
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            return null;
        }
    
        try {
            BufferedReader br = new BufferedReader(new FileReader(j.getSelectedFile()));
            String line;
            int index = 0;
    
            // Read grid size
            if ((line = br.readLine()) != null) {
                loadSizel = Integer.parseInt(line);
                randomGameBoard = new int[loadSizel * loadSizel];
            }
    
            // Read game data
            while ((line = br.readLine()) != null) {
                char[] charArray = line.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    randomGameBoard[index++] = Character.getNumericValue(charArray[i]);
                }
            }
    
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return randomGameBoard;
    }
    
    
    /**
     * get size of grid from file
     * @return loadSizel
     */
    public int getLoadSizel() {
        return loadSizel;
    }

    /**
     * Saves game to file
     * 
     * @param gridButtonArray array of GridButton objects
     * @return true/false
     */
    boolean saveToFile(GridButton[] gridButtonArray) {
        JFileChooser j = new JFileChooser(getLoadDir());
    
        try {
            // Open the save dialog
            int returnValue = j.showSaveDialog(null);
    
            // If the user canceled the dialog, return false
            if (returnValue != JFileChooser.APPROVE_OPTION) {
                return false;
            }
    
            String allSquareValues = "", writeToFile = "";
            for (int i = 0; i < gridButtonArray.length; i++) {
                allSquareValues += String.valueOf(gridButtonArray[i].getCorrectValue());
                if (i % Math.sqrt(gridButtonArray.length) == 0) {
                    writeToFile += "\n";
                }
                writeToFile += allSquareValues.charAt(i);
            }
    
            FileWriter fileName;
            fileName = new FileWriter(j.getSelectedFile());
    
            BufferedWriter geekwrite = new BufferedWriter(fileName);
            geekwrite.write(String.valueOf(Math.round(Math.sqrt(gridSize))));
            geekwrite.write(writeToFile);
            geekwrite.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
    
    /**
     * Gets images directory
     * 
     * @return the images directory
     */
    String getImageDir() {
        String directory = System.getProperty("user.dir");
        imageDir = directory;
        // Makes program compatible with launch from VsCode as well as the JAR file
        if ((!imageDir.contains("\\bin"))) {
            imageDir = imageDir + "\\bin";
        }
        imageDir = imageDir + "\\images\\";

        return imageDir;
    }
    /**
     * GET RIGHT PATH FROM LOAD FROM FILE
     * 
     * @return LOADDIR
     */
    String getLoadDir() {
        String directory = System.getProperty("user.dir");
        loadDir = directory;
        // Makes program compatible with launch from VsCode as well as the JAR file
        if ((!loadDir.contains("\\src"))) {
            loadDir = loadDir + "\\src";
        }
        loadDir = loadDir + "\\game\\";

        return loadDir;
    }
    /**
     * save user dsign mode game to 1D array
     * 
     * @param locPanel main panel
     * @return main game arraY
     */
    public int[] saveUserGame(MainGameFrame locPanel) {
        randomGameBoard = new int[gridSize];
    
        for (int i = 0; i < gridSize; i++) {
            GridButton button = locPanel.rightP.buttons[i];
            randomGameBoard[i] = button.value;
        }
    
        return randomGameBoard; 
    }
    /**
     * get receive game from server and stored it here
     * 
     * @param serverGame array which get game from server
     * @return return main arrray of the game
     */
    public int[] saveUserGamefromServer(int [] serverGame) {
        randomGameBoard = new int[gridSize];

        for (int i = 0; i < gridSize; i++) {
            randomGameBoard[i] = serverGame[i];
        }
        return randomGameBoard; 
    }

    /**
     * Generate a random array of integers representing a game board
     * 
     * @param gridSize gridSize
     * @return  randomGameBoard
     */
    public int[] generateRandomBoard(int gridSize) {
        this.gridSize = gridSize;

        randomGameBoard = new int[gridSize];
        Random rand = new Random();
        int randomInt = 0;
        for (int i = 0; i < gridSize; i++) {
            randomInt = rand.nextInt(2);
            randomGameBoard[i] = randomInt;
        }
        return randomGameBoard;
    }

    /**
     * gets to access randomGameBoard outside of class
     * 
     * @return retun main array for load, generate, and user design game mode
     */
    public int[] getRandomGameBoard() {
        return randomGameBoard;
    }

}
