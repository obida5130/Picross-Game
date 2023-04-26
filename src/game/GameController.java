package game;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
/**
 * Used to detect user actions and interactions.
 * It is used by all buttons in the game as well as all input/output fields.
 */
public class GameController implements ActionListener {

        /**
         * Used for internationlization
         */
        private ResourceBundle rb;

        /**
         * Coounts time elapsed since the first square is pressed
         */
        private ControllableTimer ct = null;

        /**
         * The model includes data fields, file I/O,
         * connects the view and the controller classes
         */
        private GameModel model;
        /**
         * Includes instances of graphical elements of the game such as buttons,
         * panels, and menus
         */
        private GameView view;
        /**
         * Used to store the size/grid selection
         */
        private Object size;
        /**
         * Used to determine if user has interacted with at least one grid button
         */
        private boolean gameStarted = false;
        /**
         * Used to track time taken
         */
        private int gridSize = 0;
        /*
         * this vairable will hold value of size grid for design mode
         */
        private int designModeGridSize = 0; // Initialize to a default value

        @Override
        /**
         * Takes in an ActionEvent object
         * 
         * @param e ActionEvent object
         */
        public void actionPerformed(ActionEvent e) {
                // print action components
                // creating command to set actions of all components in game
                String command = e.getActionCommand();

                // Size selection
                if (command == "sizeSelection") {

                        // set size of selection of grid
                        size = view.getMainPanel().rightP.getSizeSelection().getSelectedItem();
                        // insert actions of size of grid to action bar
                        view.getMainPanel().rightP.actionsArea().insert("Set Size " + size.toString() + "\n", 0);
                        switch (size.toString()) {
                                case "5x5":
                                        // reset time
                                        view.resetPoints();
                                        resetTime();
                                        // here will set game state as not started, it will start when first click on
                                        // grid buttons
                                        gameStarted = false;
                                        // here will destory any exist grid on center panel
                                        view.destroyGame(model.getGridSize());
                                        gridSize = 25;// set grid size to 25
                                        // generate game to the user
                                        model.generateRandomBoard(gridSize);
                                        // display labels grid to frame
                                        displayPicross(5, 100, 10, 50, 10, gridSize, "  ");
                                        break;

                                case "7x7":
                                        view.resetPoints();
                                        resetTime();
                                        gameStarted = false;
                                        gridSize = 49;
                                        view.destroyGame(model.getGridSize());
                                        model.generateRandomBoard(gridSize);
                                        displayPicross(7, 50, 5, 35, 17, gridSize, "   ");
                                        break;

                                case "9x9":
                                        view.resetPoints();
                                        resetTime();
                                        gameStarted = false;
                                        gridSize = 81;
                                        view.destroyGame(model.getGridSize());
                                        model.generateRandomBoard(gridSize);
                                        displayPicross(9, 15, 12, 21, 35, gridSize, "    ");
                                        break;

                                case "10x10":
                                        view.resetPoints();
                                        resetTime();
                                        gameStarted = false;
                                        gridSize = 100;
                                        view.destroyGame(model.getGridSize());
                                        model.generateRandomBoard(gridSize);
                                        displayPicross(10, 20, 1, 22, 25, gridSize, "   ");
                                        break;
                        }
                        view.selectColor(gridSize, false);// set colors to buttons depends on gridSize
                }
                if (!gameStarted) {
                        ct.setStatus(ControllableTimer.STOP);

                } else {
                        ct.setStatus(ControllableTimer.START);

                }
                /*******************************************************/
                startTimeANDPoints(e);// start time and point counter when game state is active

                // Reset button
                if (command == "resetBtn") {
                        view.resetPoints();
                        view.resetGame();// call reset method which clear any exists labels, actions bar, grid.....
                        view.insertAction("Reset_Btn\n", null);// insert state action button to bar text
                        gameStarted = false;// set game state as inactive
                        resetTime();// reset time to deafualt
                }
                /*********************
                 * START OF CHECKING USER CURRENT SOLUTION ACTION IF TRUE OR FALSE
                 *****************/
                if (command == "checkSolution") {
                        if (!gameStarted) {// if user did not clicked on grid will pop-up message to play first
                                JOptionPane.showMessageDialog(view.getMainPanel().frame,
                                                "Please start the grid first and select at least 1 square");
                        } else {// if start the game
                                ct.setStatus(ControllableTimer.STOP);
                                view.mainPanel.repaint();
                                boolean correct = true;
                                // loop throw gridSize, and set correct to values in grid
                                for (int i = 0; i < gridSize; i++) {
                                        correct = correct & view.checkAnswer(i);
                                }
                                // if valuse match in checking answer return true
                                if (correct) {
                                        view.showWinStatus(true);

                                }
                                // if valuse does not match in checking answer return false
                                else if (!correct) {
                                        view.showWinStatus(false);
                                }
                                view.getMainPanel().repaint();// make sure repaint and clean up everything for not crush
                                                              // anyhting
                                // view.resetGame();
                        }

                }
                /*********************
                 * END OF CHECKING USER CURRENT SOLUTION ACTION IF TRUE OR FALSE
                 *****************/

                /**********************************
                 * START THEME MODE ACTION
                 **************************************/
                // if selected light mode will call setTheme method and set it as "light"
                if (command == "lightBtn") {
                        view.setTheme("light");

                }
                // if selected light mode will call setTheme method and set it as "light"
                else if (command == "darkBtn") {
                        view.setTheme("dark");
                }
                /**********************************
                 * END OF THEME MODE ACTION
                 **************************************/

                /******************************
                 * START OF LANGUAGES MODE ACTIONS
                 **************************/

                // if selected english will set all text in game as english language
                if (command == "englishBtn") {
                        if (!view.getMainPanel().topLeftPanel().getLanguage().equals("English")) {
                                setLanguage("English");
                        }
                }
                // if selected turkish will set all text in game as turkish language
                else if (command == "turkishBtn") {
                        if (!view.getMainPanel().topLeftPanel().getLanguage().equals("Turkish")) {

                                setLanguage("Turkish");
                        }

                }
                // if selected french will set all text in game as french language
                else if (command == "frenchBtn") {// Set french
                        if (!view.getMainPanel().topLeftPanel().getLanguage().equals("French")) {
                                setLanguage("French");
                        }
                }
                /******************************
                 * END OF LANGUAGES MODE ACTIONS
                 **************************/

                /******************************
                 * START OF LOAD FILE ACTIONS
                 **************************/
                if (command == "loadItem") {
                        resetTime();// reset time

                        model.readGameFrom(null);// call read method from model
                        // the if-statments setting grid and labels based on grid size in file
                        gameStarted = false;// set game state as inactive

                        if (model.getLoadSizel() == 5) {
                                view.resetPoints();
                                view.resetGame();
                                resetTime();// reset time
                                gameStarted = false;// set game state as inactive
                                // destroy any exists grid on center panel
                                view.destroyGame(model.getGridSize());
                                gridSize = 25;// set gridSize based size from file
                                // display labels and grid on frame
                                displayPicross(5, 100, 10, 50, 10, gridSize, "  ");

                                // same thing for rest........
                        } else if (model.getLoadSizel() == 7) {
                                view.resetPoints();
                                view.resetGame();
                                resetTime();
                                gameStarted = false;
                                view.destroyGame(model.getGridSize());
                                gridSize = 49;
                                displayPicross(7, 50, 5, 35, 17, gridSize, "   ");
                        } else if (model.getLoadSizel() == 9) {
                                view.resetPoints();
                                view.resetGame();
                                resetTime();
                                gameStarted = false;
                                view.destroyGame(model.getGridSize());
                                gridSize = 81;
                                displayPicross(9, 15, 12, 21, 35, gridSize, "    ");
                        } else if (model.getLoadSizel() == 10) {
                                view.resetPoints();
                                view.resetGame();
                                resetTime();
                                gameStarted = false;
                                view.destroyGame(model.getGridSize());
                                gridSize = 100;
                                displayPicross(10, 20, 1, 22, 25, gridSize, "   ");

                        }
                        /*
                         * repaint left, center, and top panels
                         */
                        view.cleanUp();

                        view.selectColor(gridSize, false);// set color of grid buttons based on grid size
                        startTimeANDPoints(e);// start time and point cointer

                }
                /******************************
                 * END OF LOAD FILE ACTIONS
                 **************************/

                // Save file
                if (command == "saveItem") {
                        model.saveToFile(view.getMainPanel().rightPanel().buttons);
                }
                // display solution as pop-up window
                if (command == "solutionItem") {
                        view.displaySolutionWindow(model.getRandomGameBoard());
                }
                // set colors of grid buttons
                if (command == "colorItem") {
                        view.selectColor(gridSize, true);
                }
                /********************************
                 * CLIENT ACTIONS STATRT
                 *********************************/
                if (command == "sendData") {
                        if (!gameStarted) {// check if user did not start design on grid will display dialog messgae
                                JOptionPane.showMessageDialog(view.getMainPanel().frame,
                                                "Please play the game first");
                        } else {
                                ct.setStatus(ControllableTimer.STOP);
                                String timeResult = view.timeResult();
                                int pointsResult = view.pointsResult();
                                view.getMainPanel().client.sendResults(timeResult, pointsResult);

                        }
                }

                if (command == "newGame") {
                        view.cliantAction("New Game 5x5, Please click on Play button to start");
                        view.resetPoints();
                        view.getMainPanel().client.play.setEnabled(true);
                        view.destroyLabels();
                        view.resetGame();
                        // reset time
                        resetTime();
                        // here will set game state as not started, it will start when first click on
                        // grid buttons
                        gameStarted = false;
                        // here will destory any exist grid on center panel
                        view.destroyGame(model.getGridSize());
                        gridSize = 25;// set grid size to 25
                        // generate game to the user
                        model.generateRandomBoard(gridSize);
                        // display labels grid to frame
                        displayPicross(5, 100, 10, 50, 10, gridSize, "  ");
                        view.selectColor(gridSize, false);// set colors to buttons depends on gridSize
                        ct = new ControllableTimer(view);

                        startTimeANDPoints(e);// start time and point counter when game state is active
                }

                if (command == "play") {

                        if (model.getRandomGameBoard() == null) {// check if user did not start design on grid will
                                                                 // display dialog messgae
                                JOptionPane.showMessageDialog(view.getMainPanel().frame,
                                                "Please Create the game first");

                        } else {
                                view.getMainPanel().frame.setVisible(true);
                        }
                }
                if (command.equals("connect")) {

                        Thread clientThread;

                        clientThread = new Thread(new Runnable() {

                                @Override
                                public void run() {

                                        view.getMainPanel().client.startNet();

                                }
                        });
                        clientThread.start();

                }
                if (command.equals("sendGame")) {
                        if (model.getRandomGameBoard() == null) {// check if user did not start design on grid will
                                // display dialog messgae
                                JOptionPane.showMessageDialog(view.getMainPanel().frame,
                                                "Please Create the game first");
                                view.cliantAction("Please Create the game first");

                        } else {
                                int num = model.getRandomGameBoard().length;
                                if (num == 25) {
                                        view.sentGameToServer(model.getRandomGameBoard());
                                        view.cliantAction("Game sent 5x5");
                                } else if (num == 49) {
                                        view.sentGameToServer(model.getRandomGameBoard());
                                        view.cliantAction("Game sent 7x7");
                                } else if (num == 81) {
                                        view.sentGameToServer(model.getRandomGameBoard());
                                        view.cliantAction("Game sent 9x9");
                                } else if (num == 100) {
                                        view.sentGameToServer(model.getRandomGameBoard());
                                        view.cliantAction("Game sent 10x10");
                                }

                        }

                }
                if (command.equals("receive")) {
                        Thread receiveThread = new Thread(() -> {
                            int[] gameServer = view.getGameFromServer(view.getMainPanel());
                    
                            SwingUtilities.invokeLater(() -> {
                                if (gameServer != null) {

                                        int gameServerLength = gameServer.length;

                                        if (gameServerLength == 25) {
                                                view.cliantAction("Game receive 5x5");
                                                view.resetPoints();
                                                view.resetGame();// reset game if any exists
                                                resetTime();// reset time
                                                gameStarted = false;// set game inactive
                                                // destroy any exists grid
                                                view.destroyGame(model.getGridSize());

                                                gridSize = 25;// set size value
                                                model.setGridSize(gridSize);// set value size to model
                                                view.setGridSize(gridSize);// set value size to view
                                                model.saveUserGamefromServer(gameServer);
                                                // display grid/labels
                                                displayPicross(5, 100, 10, 50, 10, gridSize, "  ");
                                        } else if (gameServerLength == 49) {
                                                view.cliantAction("Game receive 7x7");

                                                view.resetPoints();
                                                view.resetGame();
                                                resetTime();
                                                gameStarted = false;
                                                view.destroyGame(model.getGridSize());

                                                gridSize = 49;
                                                model.setGridSize(gridSize);// set value size to model
                                                view.setGridSize(gridSize);// set value size to view
                                                model.saveUserGamefromServer(gameServer);
                                                displayPicross(7, 50, 5, 35, 17, gridSize, "   ");
                                        } else if (gameServerLength == 81) {
                                                view.cliantAction("Game receive 9x9");

                                                view.resetPoints();
                                                view.resetGame();
                                                resetTime();
                                                gameStarted = false;
                                                view.destroyGame(model.getGridSize());

                                                gridSize = 81;
                                                model.setGridSize(gridSize);// set value size to model
                                                view.setGridSize(gridSize);// set value size to view
                                                model.saveUserGamefromServer(gameServer);
                                                displayPicross(9, 15, 12, 21, 35, gridSize, "  ");
                                        } else if (gameServerLength == 100) {
                                                view.cliantAction("Game receive 10x10");

                                                view.resetPoints();
                                                view.resetGame();
                                                resetTime();
                                                gameStarted = false;
                                                view.destroyGame(model.getGridSize());

                                                gridSize = 100;
                                                model.setGridSize(gridSize);// set value size to model
                                                view.setGridSize(gridSize);// set value size to view
                                                model.saveUserGamefromServer(gameServer);
                                                displayPicross(10, 20, 1, 22, 25, gridSize, "  ");
                                        }
                                        // repaint and clean up
                                        view.cleanUp();
                                        view.selectColor(gameServerLength, false);// set color to grid
                                        ct = new ControllableTimer(view);
                                        startTimeANDPoints(e);// start time and point cointer
                                } else if (gameServer == null
                                        && view.getMainPanel().client.socket != null
                                        && view.getMainPanel().client.writer != null) {
                                }
                            });
                        });
                        receiveThread.start();
                    }
                    


                if (command.equals("end")) {
                        view.endClient();
                }

                /********************************
                 * CLIENT ACTIONS END
                 *********************************/
                /******************************************
                 * DESGIN MODE ACTIONS
                 ****************************************************/
                if (command == "designPatterns") {
                        view.designMode();
                }
                // here if user confirm of designing game base the size it selected
                if (command.equals("okButton")) {
                        ButtonModel selectedButton = view.getMainPanel().rightP.gridSizeGroup.getSelection();
                        if (selectedButton != null) {// checking if select choice if not will pop-up message to choice
                                                     // size first
                                String selectedButtonText = selectedButton.getActionCommand();
                                // if-statments will set gridSize based on choice
                                if (selectedButtonText.equals("radioButton5x5")) {
                                        designModeGridSize = 25;
                                } else if (selectedButtonText.equals("radioButton7x7")) {
                                        designModeGridSize = 49;
                                } else if (selectedButtonText.equals("radioButton9x9")) {
                                        designModeGridSize = 81;
                                } else if (selectedButtonText.equals("radioButton10x10")) {
                                        designModeGridSize = 100;
                                }
                                // after selected and hit ok button
                                view.resetGame();// reset game if any exists
                                gameStarted = false;// set game state inactive
                                resetTime();// reset time if exists
                                view.designFrame.dispose();// close design mode select grid size window
                                view.onOfPlayMode(true, false);// set ON design mode and set OFF play mode
                                view.setGridSize(designModeGridSize);// set grid size to view
                                model.setGridSize(designModeGridSize);// set grid sizr to model
                                // display grid on center panel
                                view.drawGame(model.getGridSize(), this);

                                // if user did not select, will check and display dialog message
                        } else {
                                JOptionPane.showMessageDialog(view.getMainPanel().frame, "No grid size selected");
                        }
                        // repait and clean up panels to not crush
                        view.getMainPanel().centerP.centerPanel.revalidate();
                        view.getMainPanel().centerP.centerPanel.repaint();
                        // set colors to grid buttons based on designModeGridSize value
                        view.selectColor(designModeGridSize, false);
                }
                // exit from desing select size grid
                if (command == "exitDesignSelect") {
                        view.designFrame.dispose();// close window
                }
                // save user design pattern
                if (command == "savePatterns") {
                        // check if user did not start design on grid will display dialog messgae
                        if (!gameStarted) {
                                JOptionPane.showMessageDialog(view.getMainPanel().frame,
                                                "Please start design pattern first");
                                // if pattern ready
                        } else {// save it to array in model
                                model.saveUserGame(view.getMainPanel());
                                // set correct value
                                view.setCorrectValues(model.getRandomGameBoard());
                                // save whatever in array to file by using saveToFile method
                                model.saveToFile(view.getMainPanel().rightPanel().buttons);
                        }
                }
                // exit from desing mode, back to play mode
                if (command == "exitPatterns") {
                        // set OFF design mode and set ON play mode
                        view.onOfPlayMode(false, true);
                        view.resetGame();// reset game if exists
                        resetTime();// reset time
                        gameStarted = false;// set game state as inactive
                        // destory any exists grid on center panel
                        view.destroyGame(model.getGridSize());
                        gridSize = 25;// set grid size based deafualt game
                        model.setGridSize(gridSize);// set value size to model
                        view.setGridSize(gridSize);// set value size to view
                        model.generateRandomBoard(gridSize);// generate new game
                        // display grid and labels on frame
                        displayPicross(5, 100, 10, 50, 10, gridSize, "  ");
                        view.selectColor(gridSize, false);// set color to grid buttons based on size
                        startTimeANDPoints(e);// start time and point counter when game is active

                }
                // play user design pattern
                if (command == "playPatterns") {
                        if (!gameStarted) {// check if user did not start design on grid will display dialog messgae
                                JOptionPane.showMessageDialog(view.getMainPanel().frame,
                                                "Please start design pattern first");
                        } else {
                                // if pattern ready
                                // set OFF design mode and set ON play mode
                                view.onOfPlayMode(false, true);
                                // if-statments just reseting game/time and printing grid/labels based on
                                // designModeGridSize value
                                if (designModeGridSize == 25) {
                                        view.resetPoints();
                                        view.resetGame();// reset game if any exists
                                        resetTime();// reset time
                                        gameStarted = false;// set game inactive
                                        // destroy any exists grid
                                        view.destroyGame(model.getGridSize());
                                        gridSize = 25;// set size value
                                        model.setGridSize(gridSize);// set value size to model
                                        view.setGridSize(gridSize);// set value size to view
                                        model.saveUserGame(view.getMainPanel());// save pattern to array
                                        // display grid/labels
                                        displayPicross(5, 100, 10, 50, 10, gridSize, "  ");
                                } else if (designModeGridSize == 49) {
                                        view.resetPoints();
                                        view.resetGame();
                                        resetTime();
                                        gameStarted = false;
                                        view.destroyGame(model.getGridSize());
                                        gridSize = 49;
                                        model.setGridSize(gridSize);// set value size to model
                                        view.setGridSize(gridSize);// set value size to view
                                        model.saveUserGame(view.getMainPanel());
                                        displayPicross(7, 50, 5, 35, 17, gridSize, "   ");
                                } else if (designModeGridSize == 81) {
                                        view.resetPoints();
                                        view.resetGame();

                                        resetTime();
                                        gameStarted = false;

                                        view.destroyGame(model.getGridSize());
                                        gridSize = 81;
                                        model.setGridSize(gridSize);// set value size to model
                                        view.setGridSize(gridSize);// set value size to view
                                        model.saveUserGame(view.getMainPanel());

                                        displayPicross(9, 15, 12, 21, 35, gridSize, "  ");

                                } else if (designModeGridSize == 100) {
                                        view.resetPoints();
                                        view.resetGame();
                                        resetTime();
                                        gameStarted = false;
                                        view.destroyGame(model.getGridSize());
                                        gridSize = 100;
                                        model.setGridSize(gridSize);// set value size to model
                                        view.setGridSize(gridSize);// set value size to view
                                        model.saveUserGame(view.getMainPanel());
                                        displayPicross(10, 20, 1, 22, 25, gridSize, "  ");

                                }
                                // repaint and clean up
                                view.cleanUp();
                                view.selectColor(designModeGridSize, false);// set color to grid button based on size
                                startTimeANDPoints(e);// start time and pointer when game active

                        }
                }
        }

        /**************************************************
         * END OF DESGIN MODE ACTIONS
         *****************************************************************/

        /**
         * This constructor accepts 2 args, GameModel and GameView
         * and starts the game window.
         * 
         * @param model used for reading from files and data
         * @param view  used for all the graphical components
         */
        GameController(GameModel model, GameView view) {

                // Default size
                gridSize = 25;
                model.setGridSize(gridSize);
                view.setGridSize(gridSize);

                this.model = model;
                this.view = view;
                view.runGame(this);
                setLanguage("English");
                view.setTheme("dark");
                view.drawGame(model.getGridSize(), this);
                view.selectColor(gridSize, false);
                /* start generate when game launch */
                view.selectColor(gridSize, false);
                // Create timer
                ct = new ControllableTimer(view);
        }

        /**
         * Sets language and does all the required updates
         * 
         * @param language language to be set
         */
        public void setLanguage(String language) {

                String lang = "", country = "";
                switch (language) {
                        case "English":
                                lang = "en";
                                country = "US";
                                break;
                        case "Turkish":
                                lang = "tr";
                                country = "TR";
                                break;
                        case "French":
                                lang = "fr";
                                country = "FR";
                                break;
                }

                Locale currentLocale = new Locale(lang, country);

                rb = ResourceBundle.getBundle("resources.resource_bundle", currentLocale);
                view.setLanguage(language, rb);
        }

        /**
         * Used to reset time using the ControllableTimer
         */
        public void resetTime() {
                ct.setStatus(ControllableTimer.STOP);

                ct.setStatus(ControllableTimer.RESET);
        }

        /*
         * start time
         */
        public void startTime() {
                ct.setStatus(ControllableTimer.START);
        }

        /**
         * this method to display labels and draw the grid and set check solution
         * 
         * @param numLabels         number of labels based on grid size
         * @param horizontalSpace   space between labels
         * @param verticalSpace     space between labels
         * @param downSpace         space between labels
         * @param moveTopLabelRight space between labels
         * @param gridSize          grid size
         * @param space             space between labels
         */
        public void displayPicross(int numLabels, int horizontalSpace, int verticalSpace, int downSpace,
                        int moveTopLabelRight, int gridSize, String space) {
                ThemeSet themeDark = new ThemeSet("dark");
                view.destroyGame(model.getGridSize());
                view.setGridSize(gridSize);
                view.destroyLabels();
                view.displayLabels(numLabels, horizontalSpace, verticalSpace, downSpace, moveTopLabelRight, gridSize,
                                space, themeDark, model.getRandomGameBoard());
                model.setGridSize(gridSize);
                view.drawGame(model.getGridSize(), this);
                view.setCorrectValues(model.getRandomGameBoard());
        }

        /**
         * this method checking if game is active will start time and pointer counter if
         * not will not start
         * 
         * @param e action event for checking if game is active or inactive
         */
        public void startTimeANDPoints(ActionEvent e) {
                if (e.getSource() instanceof GridButton) {
                        // Starts time once game size is selected
                        if (!gameStarted) {
                                try {
                                        ct.start();

                                } catch (Exception Error) {

                                        ct.setStatus(ControllableTimer.START);
                                }

                                gameStarted = true;
                        }
                        GridButton gb;
                        gb = (GridButton) (e.getSource());
                        gb.incrementValue();

                        if (gb.getValue() == 0 || gb.getValue() == 1) {
                                if (view.checkAnswer(gb.getIndex())) {
                                        view.gamePoints('+');
                                } else if (!view.checkAnswer(gb.getIndex())) {
                                        view.gamePoints('-');
                                }
                        }

                        view.insertAction("Pressed (" + gb.getCoordX() + ", " + gb.getCoordY() + ") ["
                                        + gb.getCorrectValue() + "]\n", gb);
                }
        }

}