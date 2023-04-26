package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.io.*;
import java.net.*;

/**
 * Game Client.
 */
public class Client {
    /**
     * Main frame
     */
    JFrame frame;
    /**
     * Buttons used for various client-server operations
     */
    JButton connect, clientEnd, clientNewGame, sendGame, receiveGame, sendData, play;
    /**
     * Client background
     */
    ImageIcon frameIcon;
    /**
     * JTextAreas used for various client information
     */
    JTextArea userN, serverN, portN, actionClient;
    /**
     * Labels that hold images
     */
    JLabel imageLabel, frameLabel;
    /**
     * Layered pane to organize the GUI components
     */
    JLayeredPane layeredPane;
    /**
     * Default server port
     */
    private static int serverPort = config.DEFAULT_PORT;
    /**
     * Keeps track of connection status with server
     */
    boolean clientConnected = false;
    /**
     * Socket used for communications with server
     */
    Socket socket;
    /**
     * BufferedReader
     */
    BufferedReader consoleReader;
    /**
     * Used for writing message to be sent to the server
     */
    PrintWriter writer;
    /**
     * Used for reading server messages
     */
    BufferedReader socketReader;
    /**
     * Stores username
     */
    String username;
    /**
     * Stores the IP address of server
     */
    String localAddress;
    /**
     * Stores server port to connect to
     */
    int localPort;

    /**
     * Client constructor builds the gui for the client and adds the action
     * listeners to the appropriate components
     * 
     * @param imagePath      path to the images folder
     * @param mainPANEL      panel that stores the game board
     * @param globalListener
     */
    Client(String imagePath, MainGameFrame mainPANEL, ActionListener globalListener) {
        // Set the desired pressed color
        UIManager.put("Button.select", Color.YELLOW);
        // background image for frame
        frameIcon = new ImageIcon("a.png");
        frameLabel = new JLabel(frameIcon);
        frameLabel.setSize(700, 500);
        frameLabel.setLocation(0, 0);

        /*****************************************/
        // client logo
        imageLabel = new JLabel(new ImageIcon(imagePath + "client.png"));
        imageLabel.setSize(580, 150);
        imageLabel.setLocation(50, 0);

        /*****************************************/
        // Creating JTextArea for client window
        // userName....
        JLabel userLabel = new JLabel("User: ", SwingConstants.CENTER);
        userLabel.setSize(45, 27);
        userLabel.setLocation(30, 150);
        userLabel.setOpaque(true);
        userLabel.setBackground(new Color(255, 255, 153));
        userLabel.setBorder(new LineBorder(Color.BLACK, 1));
        userN = new JTextArea();
        userN.setSize(130, 27);
        userN.setLocation(75, 150);
        userN.setBackground(Color.white);
        userN.setFont(userN.getFont().deriveFont(18f));
        userN.setBorder(new LineBorder(Color.BLACK, 1));
        username = config.DEFAULT_USER;
        // serverName.....
        JLabel serverNLabel = new JLabel("Server: ", SwingConstants.CENTER);
        serverNLabel.setSize(45, 27);
        serverNLabel.setLocation(205, 150);
        serverNLabel.setOpaque(true);
        serverNLabel.setBackground(new Color(255, 255, 153));
        serverNLabel.setBorder(new LineBorder(Color.BLACK, 1));
        serverNLabel.setFont(serverNLabel.getFont().deriveFont(11f));
        serverN = new JTextArea();
        serverN.setSize(130, 27);
        serverN.setLocation(250, 150);
        serverN.setBackground(Color.white);
        serverN.setFont(serverN.getFont().deriveFont(18f));
        serverN.setBorder(new LineBorder(Color.BLACK, 1));
        localAddress = config.DEFAULT_ADDR;
        // portNumber.....
        JLabel portNumberLabel = new JLabel("Port: ", SwingConstants.CENTER);
        portNumberLabel.setSize(45, 27);
        portNumberLabel.setLocation(380, 150);
        portNumberLabel.setOpaque(true);
        portNumberLabel.setBackground(new Color(255, 255, 153));
        portNumberLabel.setBorder(new LineBorder(Color.BLACK, 1));
        portN = new JTextArea();
        portN.setSize(100, 27);
        portN.setLocation(425, 150);
        portN.setBackground(Color.white);
        portN.setFont(portN.getFont().deriveFont(18f));
        portN.setBorder(new LineBorder(Color.BLACK, 1));
        localPort = config.DEFAULT_PORT;

        /*****************************************/ 
        // Creating buttons for client window
        // connect....
        connect = new JButton("Conncet");
        connect.setSize(85, 27);
        connect.setLocation(535, 150);
        connect.setBackground(new Color(255, 255, 153));
        connect.setFocusPainted(false);
        connect.addActionListener(globalListener);
        connect.setActionCommand("connect");
        // clientEnd............
        clientEnd = new JButton("End");
        clientEnd.setSize(60, 27);
        clientEnd.setLocation(625, 150);
        clientEnd.setBackground(new Color(255, 255, 153));
        clientEnd.setFocusPainted(false);
        clientEnd.addActionListener(globalListener);    
        clientEnd.setActionCommand("end");
        clientEnd.setEnabled(false);

        // clientNewGame.........
        clientNewGame = new JButton("New Game");
        clientNewGame.setSize(100, 30);
        clientNewGame.setLocation(70, 200);
        clientNewGame.setBackground(new Color(255, 255, 153));
        clientNewGame.setFocusPainted(false);
        clientNewGame.addActionListener(globalListener);
        clientNewGame.setActionCommand("newGame");
        // sendGame........
        sendGame = new JButton("Send Game");
        sendGame.setSize(100, 30);
        sendGame.setLocation(190, 200);
        sendGame.setBackground(new Color(255, 255, 153));
        sendGame.setFocusPainted(false);
        sendGame.addActionListener(globalListener);
        sendGame.setActionCommand("sendGame");
        sendGame.setEnabled(false);

        //
        receiveGame = new JButton("Receive Game");
        receiveGame.setSize(120, 30);
        receiveGame.setLocation(320, 200);
        receiveGame.setBackground(new Color(255, 255, 153));
        receiveGame.setFocusPainted(false);
        receiveGame.addActionListener(globalListener);
        receiveGame.setActionCommand("receive");
        receiveGame.setEnabled(false);

        // sendData.........
        sendData = new JButton("Send Data");
        sendData.setSize(120, 30);
        sendData.setLocation(460, 200);
        sendData.setBackground(new Color(255, 255, 153));
        sendData.setFocusPainted(false);
        sendData.addActionListener(globalListener);
        sendData.setActionCommand("sendData");
        sendData.setEnabled(false);

        // play...........
        play = new JButton("Play");
        play.setSize(80, 30);
        play.setLocation(600, 200);
        play.setBackground(new Color(255, 255, 153));
        play.setFocusPainted(false);
        play.addActionListener(globalListener);
        play.setActionCommand("play");
        play.setEnabled(false);

        /*****************************************/
        // client actions
        actionClient = new JTextArea();
        actionClient.setFont(actionClient.getFont().deriveFont(18f));
        actionClient.setBorder(new LineBorder(Color.BLACK, 2));
        // scrollbars
        JScrollPane actionClientScrollPane = new JScrollPane(
                actionClient,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        actionClientScrollPane.setSize(680, 211);
        actionClientScrollPane.setLocation(10, 250);

        /*****************************************/

        // adding all components of client together using JLayeredPane
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 500));
        layeredPane.add(frameLabel, Integer.valueOf(1));
        layeredPane.add(imageLabel, Integer.valueOf(2));
        layeredPane.add(userLabel, Integer.valueOf(3));
        layeredPane.add(userN, Integer.valueOf(4));
        layeredPane.add(serverNLabel, Integer.valueOf(5));
        layeredPane.add(serverN, Integer.valueOf(6));
        layeredPane.add(portNumberLabel, Integer.valueOf(7));
        layeredPane.add(portN, Integer.valueOf(8));
        layeredPane.add(connect, Integer.valueOf(9));
        layeredPane.add(clientEnd, Integer.valueOf(10));
        layeredPane.add(clientNewGame, Integer.valueOf(11));
        layeredPane.add(sendGame, Integer.valueOf(12));
        layeredPane.add(receiveGame, Integer.valueOf(13));
        layeredPane.add(sendData, Integer.valueOf(14));
        layeredPane.add(play, Integer.valueOf(15));
        layeredPane.add(actionClientScrollPane, Integer.valueOf(16));

        /*****************************************/

        /********************************
         * main frame for client
         **********************************/
        /***************************************************************************************/
        final String TITLE = "Picross Client";
        frame = new JFrame(TITLE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close application
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopNet();
            }
        });

        frame.setResizable(false);
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 700;
        int height = 500;
        frame.setSize(width, height);
        frame.setLocation(center.x - width / 2, center.y - height / 2);
        Color frameBackground = new Color(55, 155, 153);
        frame.getContentPane().setBackground(frameBackground);
        frame.add(layeredPane, BorderLayout.CENTER);// add our layeredPane which has all the components
        frame.setVisible(true); // make window visible
        /************************************************************************************/
    }

    /**
     * Used to save username from input field
     */
    public void updateUsername() {
        String newUsername = userN.getText();
        if (!newUsername.isEmpty()) {
            username = newUsername;
        } else {
            userN.setText(String.valueOf(config.DEFAULT_USER));
        }
    }

    /**
     * Inserts action in the JTextArea of the client
     * 
     * @param text text to be inserted into the JTextArea
     */
    public void insertAction(String text) {
        actionClient.insert(text + "\n", 0);
    }

    /**
     * Opens sockets for communications with the server
     */
    public void startNet() {
        try {
            // this code if user did not write anything on port or local will automatic set
            // the deafualt
            String newUserlocalAddress = serverN.getText();
            String portText = portN.getText();

            if (!portText.isEmpty()) {
                int newLocalPort = Integer.parseInt(portText);
                localPort = newLocalPort;
            } else {
                portN.setText(String.valueOf(config.DEFAULT_PORT));
            }

            if (!newUserlocalAddress.isEmpty()) {
                localAddress = newUserlocalAddress;
            } else {
                serverN.setText(String.valueOf(config.DEFAULT_ADDR));
            }
            serverPort = localPort;
        } catch (NumberFormatException error) {
            insertAction("Invalid input for server address or port number");
            return;
        }
        // when successfully conncet it
        try {
            socket = new Socket(localAddress, serverPort);
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            insertAction(String.format("Connection established: %s:%d", localAddress, serverPort));
            // sent username to server to use it layter
            updateUsername();
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            writer.println(config.PROTOCOL_SEND_USERNAME + config.PROTOCOL_SEPARATOR + username);
            // here updated buttons to be enabel whem successfully conncet
            clientConnected = true;
            clientEnd.setEnabled(clientConnected);
            connect.setEnabled(!clientConnected);
            receiveGame.setEnabled(true);
            connect.setEnabled(false);
            sendGame.setEnabled(true);
            play.setEnabled(true);
            sendData.setEnabled(true);
            clientEnd.setEnabled(true);
            // start listening from server for upcoming game stored in server or disconnect
            // message
            startListeningFromServer();
            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                writer.println(userInput);
                insertAction("Server: " + socketReader.readLine());
            }
            // catch any error if connect with no server avaliable or wrong port or local
        } catch (ConnectException e) {
            insertAction("Error: Sorry, can not connect to the server at " + localAddress + ":" + serverPort);
            JOptionPane.showMessageDialog(null,
                    "Sorry, can not connect to the server at " + localAddress + ":" + serverPort
                            + ". Please make sure the server is running, or address and port are correct.",
                    "Connection refused", JOptionPane.ERROR_MESSAGE);
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + localAddress);
            insertAction("Client error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
            insertAction("Client error: " + e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("Client error: " + e.getMessage());
                insertAction("Client error: " + e.getMessage());
            }
        }
        localAddress = config.DEFAULT_ADDR; // when it failed server, updated it to default
    }

    /**
     * Breaks down the server message to perform the required actions
     * 
     * @param input server message
     * @return array of integers used for square/board configurations
     */
    public int[] handleGameServerResponse(String input) {
        if (input.startsWith(config.PROTOCOL_SEPARATOR + config.PROTOCOL_GAME_REQUEST)) {
            String gameStateStr = input.substring((config.PROTOCOL_SEPARATOR + config.PROTOCOL_GAME_REQUEST).length());
            int[] gameRequest = new int[gameStateStr.length()];
            for (int i = 0; i < gameStateStr.length(); i++) {
                gameRequest[i] = Character.getNumericValue(gameStateStr.charAt(i));

            }
            StringBuilder sb = new StringBuilder();
            sb.append(config.PROTOCOL_SEPARATOR);
            int gridSize = (int) Math.sqrt(gameRequest.length);
            for (int i = 0; i < gameRequest.length; i++) {
                sb.append(gameRequest[i]);
                if ((i + 1) % gridSize == 0 && i != gameRequest.length - 1) {
                    sb.append(config.FIELD_SEPARATOR);
                }
            }
            int gameServerLength = gameRequest.length;
            if (gameServerLength == 25) {
                insertAction("5x5" + sb.toString());
            }
            if (gameServerLength == 49) {
                insertAction("7x7" + sb.toString());
            }
            if (gameServerLength == 81) {
                insertAction("9x9" + sb.toString());
            }
            if (gameServerLength == 100) {
                insertAction("10x10" + sb.toString());
            }
            return gameRequest;
        }
        return null;
    }

    /**
     * Handles messages send by server and performs the required actions
     * 
     * @param message server message received
     */
    public void handleServerMessage(String message) {
        if (message != null) {
            if (message.startsWith(config.PROTOCOL_SERVER_STOPPED)) {
                insertAction("Server has been stopped");
                sendGame.setEnabled(false);
                play.setEnabled(false);
                sendData.setEnabled(false);
                stopNet();
            } else {
                handleGameServerResponse(message);
            }
        }
    }

    /**
     * Reads server data if game array or disconnect message from server
     */
    public void startListeningFromServer() {
        int delay = 100; // milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        try {
                            if (socketReader.ready()) {
                                String input = socketReader.readLine();
                                if (input == null) {
                                    ((Timer) evt.getSource()).stop();
                                } else if (input.startsWith(config.PROTOCOL_SEPARATOR + config.PROTOCOL_GAME_REQUEST)) {
                                    handleGameServerResponse(input);
                                } else if (input.startsWith(config.PROTOCOL_SERVER_STOPPED)) {
                                    insertAction("Server has been stopped");
                                    sendGame.setEnabled(false);
                                    sendData.setEnabled(false);
                                    receiveGame.setEnabled(false);

                                    ((Timer) evt.getSource()).stop();
                                }
                            }
                        } catch (SocketException e) {
                            if (!socket.isClosed()) {
                                insertAction("Client error: " + e.getMessage());
                                e.printStackTrace();
                            } else {
                                insertAction("Client disconnected");
                            }
                            ((Timer) evt.getSource()).stop();
                        } catch (IOException e) {
                            e.printStackTrace();
                            ((Timer) evt.getSource()).stop();
                        } finally {
                            if (!((Timer) evt.getSource()).isRunning()) {
                                stopNet();
                            }
                        }
                        return null;
                    }
                };
                worker.execute();
            }
        };
        new Timer(delay, taskPerformer).start();
    }

    /**
     * Stops the networking and sends disconnect message to server
     */
    public void stopNet() {
        if (socket != null && writer != null) {

            try {
                // here will update username to deafult when disconnect it
                username = config.DEFAULT_USER;
                writer.println(config.PROTOCOL_END);
                clientConnected = false;
                clientEnd.setEnabled(clientConnected);
                connect.setEnabled(!clientConnected);
                insertAction("Disconnected from server");
                socket.close();
            } catch (IOException e) {
                System.err.println("Failed to end connection");
            }
        } else {
            insertAction("Client not connected");
        }
    }

    /**
     * Sends square configuration to the server which consists of an integer array
     * 
     * @param gameRequest integer array containing square configuration
     */
    public void sendGameToServer(int[] gameRequest) {
        if (!socket.isConnected()) {
            stopNet();
        }

        if (socket != null && writer != null) {
            StringBuilder sb = new StringBuilder();
            int gridSize = (int) Math.sqrt(gameRequest.length);

            sb.append(config.PROTOCOL_SEPARATOR);
            sb.append(config.PROTOCOL_SENDGAME);
            sb.append(config.PROTOCOL_SEPARATOR);
            for (int i = 0; i < gameRequest.length; i++) {
                sb.append(gameRequest[i]);
                if (i > 0 && (i + 1) % gridSize == 0 && i != gameRequest.length - 1) {
                    sb.append(config.FIELD_SEPARATOR);
                }
            }
            writer.println(sb.toString());
        } else {
            insertAction("Client not connected");
        }
    }

    /**
     * Responsible for requesting and handling game configuration to and from server
     * 
     * @return an array of integers which stores the square configuration (1s and
     *         0s)
     */
    public int[] requestAndReceiveGameFromServer() {
        if (socket != null && writer != null) {
            writer.println(config.PROTOCOL_SEPARATOR + config.PROTOCOL_RECVGAME);

            try {
                String input;
                int[] gameRequest = null;
                while ((input = socketReader.readLine()) != null) {
                    gameRequest = handleGameServerResponse(input);
                    if (gameRequest != null) {
                        return gameRequest;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            insertAction("Client not connected");
        }
        return null;
    }

    /**
     * Sends game data (points, time) to the server
     * 
     * @param timeResult   time taken since game start
     * @param pointsResult points scored
     */
    public void sendResults(String timeResult, int pointsResult) {
        updateUsername();

        if (socket != null && writer != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(config.PROTOCOL_DATA);
            sb.append(" points: ").append(pointsResult);
            sb.append(", time:").append(timeResult);
            writer.println(sb.toString());
            insertAction(username + ": " + sb.toString() + " has been sent to the server");
        } else {
            insertAction("Client not connected");
        }
    }

}
