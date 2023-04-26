package game;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.io.*;
import java.net.*;

/**
 * Game Server.
 */
public class Server implements ActionListener {
    /**
     * isStopped = false; check whetjer server stop or no
     */
    private volatile boolean isStopped = false;
    /**
     * save resultsList to ArrayList
     */
    private List<String> resultsList = new ArrayList<>();
    /**
     * save clientUsernames to ArrayList
     */
    private List<String> clientUsernames = new ArrayList<>();
    /**
     * handel activeClients
     */
    private List<ClientHandler> activeClients = Collections.synchronizedList(new ArrayList<>());
    /**
     * main frame for server
     */
    JFrame frame;
    /**
     * JButton executeButton, resultButton, endButton
     */
    JButton executeButton, resultButton, endButton;
    /**
     * ImageIcon serverIcon
     */
    ImageIcon serverIcon;
    /**
     * JTextArea portN, actionsServer
     */
    JTextArea portN, actionsServer;
    /**
     * JLabel imageLabel, frameLabel
     */
    JLabel imageLabel, frameLabel;
    /**
     * JLayeredPane layeredPane
     */
    JLayeredPane layeredPane;
    /**
     * JRadioButton finalizeButton
     */
    JRadioButton finalizeButton;
    /**
     * boolean serverStarted = false
     */
    boolean serverStarted = false;
    /**
     * receivedUsername
     */
    String receivedUsername;
    /**
     * get game from client and save it here in server
     */
    public int[] game;

    // Networking
    /**
     * set port to DEFAULT_PORT
     */
    private static int port = config.DEFAULT_PORT;
    /**
     * clientID
     */
    private static int clientID = 0;
    /**
     * numClients
     */
    private static int numClients = 0;
    /**
     * serverSocket
     */
    ServerSocket serverSocket;

    /**
     * constructor of serrver class
     * 
     * @param imagePath directory of image
     */
    Server(String imagePath) {
        // Set the desired pressed color
        UIManager.put("Button.select", Color.YELLOW);

        // background image for frame
        serverIcon = new ImageIcon(imagePath + "a.png");
        frameLabel = new JLabel(serverIcon);
        frameLabel.setSize(700, 350);
        frameLabel.setLocation(0, 0);

        /*****************************************/
        // server logo
        imageLabel = new JLabel(new ImageIcon(imagePath + "server.png"));
        imageLabel.setSize(580, 150);
        imageLabel.setLocation(50, 0);

        /*****************************************/
        // Creating JTextArea for port.....
        JLabel portNumberLabel = new JLabel("Port: ", SwingConstants.CENTER);
        portNumberLabel.setSize(45, 27);
        portNumberLabel.setLocation(100, 150);
        portNumberLabel.setOpaque(true);
        portNumberLabel.setBackground(new Color(255, 255, 153));
        portNumberLabel.setBorder(new LineBorder(Color.BLACK, 1));
        portN = new JTextArea();
        portN.setSize(90, 27);
        portN.setLocation(145, 150);
        portN.setBackground(Color.white);
        portN.setFont(portN.getFont().deriveFont(18f));
        portN.setBorder(new LineBorder(Color.BLACK, 1));
        /*****************************************/
        // Finalize radio button ......
        finalizeButton = new JRadioButton("Finalize");
        finalizeButton.setSize(90, 27);
        finalizeButton.setLocation(470, 150);
        finalizeButton.setBackground(Color.white);
        finalizeButton.setFont(finalizeButton.getFont().deriveFont(13f));
        finalizeButton.setBackground(new Color(255, 255, 153));

        /*****************************************/
        // Creating buttons for server window
        // executeButton....
        executeButton = new JButton("Execute");
        executeButton.addActionListener(this);
        executeButton.setActionCommand("execute");
        executeButton.setSize(85, 27);
        executeButton.setLocation(250, 150);
        executeButton.setBackground(new Color(255, 255, 153));
        executeButton.setFocusPainted(false);
        // resultButton............
        resultButton = new JButton("Result");
        resultButton.setSize(90, 27);
        resultButton.setLocation(360, 150);
        resultButton.setBackground(new Color(255, 255, 153));
        resultButton.setFocusPainted(false);
        resultButton.addActionListener(this);
        resultButton.setActionCommand("resultButton");
        // endButton.........
        endButton = new JButton("End");
        endButton.addActionListener(this);
        endButton.setActionCommand("end");
        endButton.setEnabled(serverStarted);
        endButton.setSize(60, 27);
        endButton.setLocation(580, 150);
        endButton.setBackground(new Color(255, 255, 153));
        endButton.setFocusPainted(false);
        /*****************************************/

        // server actions
        actionsServer = new JTextArea();
        actionsServer.setFont(actionsServer.getFont().deriveFont(18f));
        actionsServer.setBorder(new LineBorder(Color.BLACK, 2));
        // scrollbars
        JScrollPane actionServerScrollPane = new JScrollPane(
                actionsServer,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        actionServerScrollPane.setSize(680, 111);
        actionServerScrollPane.setLocation(10, 200);

        /*****************************************/

        // adding all components of server together using JLayeredPane
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 500));
        layeredPane.add(frameLabel, Integer.valueOf(1));
        layeredPane.add(imageLabel, Integer.valueOf(2));

        layeredPane.add(portNumberLabel, Integer.valueOf(3));
        layeredPane.add(portN, Integer.valueOf(4));
        layeredPane.add(executeButton, Integer.valueOf(5));
        layeredPane.add(resultButton, Integer.valueOf(6));
        layeredPane.add(finalizeButton, Integer.valueOf(7));

        layeredPane.add(endButton, Integer.valueOf(8));
        layeredPane.add(actionServerScrollPane, Integer.valueOf(9));
        /*****************************************/

        // main frame for server
        /***************************************************************************************/
        final String TITLE = "Picross Server"; // tittle all windows for our game
        frame = new JFrame(TITLE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close application
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopServer();
            }
        });
        frame.setResizable(false);
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 700;
        int height = 350;
        frame.setSize(width, height);
        frame.setLocation(center.x - width / 2, center.y - height / 2);
        Color frameBackground = new Color(55, 155, 153);
        frame.getContentPane().setBackground(frameBackground);
        frame.add(layeredPane, BorderLayout.CENTER);// add our layeredPane which has all the components
        frame.setVisible(true); // make window visible
        /************************************************************************************/
    }

    /**
     * disply txt to jtxtarea
     * 
     * @param text txt insert to jtxtarea
     */
    public void insertAction(String text) {
        actionsServer.insert(text + "\n", 0);
    }

    /**
     * start the server
     */
    public void startServer() {
        if (isServerStopped()) {
            isStopped = false;
        }
        int portNumber;
        if (portN.getText().isEmpty()) {
            portNumber = config.DEFAULT_PORT;

            portN.setText("" + config.DEFAULT_PORT);
        } else {
            // catch errors here
            try {
                portNumber = Integer.parseInt(portN.getText());
            } catch (NumberFormatException e) {
                insertAction("Invalid port number. Please enter a valid port number between 1 and 65535.");
                return;
            }

            if (portNumber < 1 || portNumber > 65535) {
                insertAction("Invalid port number. Please enter a valid port number between 1 and 65535.");
                return;
            }
        }
        try {
            try {
                int localPort = portNumber;
                port = localPort;
            } catch (NumberFormatException error) {
                portN.setText(String.valueOf(port));
            }
            serverSocket = new ServerSocket(port);
            insertAction("Server listening on port " + port);
            serverStarted = true;
            endButton.setEnabled(serverStarted);
            while (!isServerStopped()) {
                Socket clientSocket;
                try {
                    clientSocket = serverSocket.accept();
                    clientID++;
                    ClientHandler clientHandler = new ClientHandler(clientSocket, clientID);
                    new Thread(clientHandler).start();
                    numClients++;
                    activeClients.add(clientHandler);

                } catch (SocketException error) {
                    if (!isServerStopped()) {
                        insertAction(String.format("Connection with client #%d has been terminated.", clientID));
                    }
                }
            }
        } catch (BindException e) {
            insertAction("Server error: Port " + port + " is already in use.");
            JOptionPane.showMessageDialog(null,
                    "Port " + port + " is already in use. Please use another port or stop the server using this port.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            insertAction(("Server error: " + e.getMessage()));
            e.printStackTrace();
        }
    }

    /**
     * stop the server
     */
    public void stopServer() {
        if (serverSocket != null) {
            try {
                insertAction("The server has been stopped");
                for (ClientHandler clientHandler : activeClients) {
                    clientHandler.sendMessageToClient(config.PROTOCOL_SERVER_STOPPED);
                }
                activeClients.clear();

                isStopped = true;
                serverSocket.close();
            } catch (IOException e) {
                insertAction("error");
            }
        }
    }

    /**
     * setting if server stoped to true/false of current
     * 
     * @return isStopped
     */
    public boolean isServerStopped() {
        return isStopped;
    }

    /**
     * ClientHandler
     */
    class ClientHandler implements Runnable {
        /**
         * username
         */
        private String username;
        /**
         * socketWriter
         */
        private PrintWriter socketWriter;
        /**
         * socket
         */
        private Socket socket;
        /**
         * clientId
         */
        private int clientId = 0;

        /**
         * ClientHandler
         */
        public ClientHandler(Socket socket, int id) {
            this.socket = socket;
            this.clientId = id;
        }

        /**
         * sendMessageToClient
         * 
         * @param message current messagew
         */
        public void sendMessageToClient(String message) {
            if (socketWriter != null) {
                socketWriter.println(message);
            }
        }

        /**
         * handleResults
         * 
         * @param input current input
         */
        public void handleResults(String input) {
            String[] inputParts = input.split(" ");
            String pointsAndTime = inputParts[inputParts.length - 3] + " " + inputParts[inputParts.length - 2] + " " + inputParts[inputParts.length - 1];
            insertAction(String.format("Results from %s#%d : %s\n", this.username, clientId, pointsAndTime));
            synchronized (resultsList) {
                resultsList.add(this.username + "#"+ clientId + ": " + pointsAndTime);
            }
        }

        /**
         * insertAction
         * 
         * @param text insertAction to jtxtarea
         */
        public void insertAction(String text) {
            actionsServer.insert(text + "\n", 0);
        }

        /**
         * ,ain run for server
         */
        @Override
        public void run() {
            try {
                BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                socketWriter = new PrintWriter(socket.getOutputStream(), true);
                String input;
                try {
                    while (!isServerStopped() && (input = socketReader.readLine()) != null) {
                        if (input.startsWith(config.PROTOCOL_SEND_USERNAME)) {
                            String receivedUsername = input.split(config.PROTOCOL_SEPARATOR)[1];
                            clientUsernames.add(receivedUsername);
                            this.username = receivedUsername;
                            insertAction(String.format("%s#%d has joined the server.", receivedUsername, clientID));
                        }

                        if (input.equals(config.PROTOCOL_END)) {
                            numClients--;
                            insertAction(String.format("%s#%d has disconnected from server.", this.username, clientID));
                            if (numClients <= 0 && finalizeButton.isSelected()) {
                                stopServer();
                            }
                            break;
                        }
                        if (input.startsWith(config.PROTOCOL_SEPARATOR + config.PROTOCOL_SENDGAME)) {
                            String gameStateStr = input.split(config.PROTOCOL_SEPARATOR)[2];

                            gameStateStr = gameStateStr.replace(config.FIELD_SEPARATOR, "");

                            int[] gameRequest = new int[gameStateStr.length()];
                            for (int i = 0; i < gameStateStr.length(); i++) {
                                gameRequest[i] = Character.getNumericValue(gameStateStr.charAt(i));
                            }
                            game = gameRequest;
                            insertAction(String.format("%d%s\n", clientId, input));
                        }

                        if (input.equals(config.PROTOCOL_SEPARATOR + config.PROTOCOL_RECVGAME)) {
                            if (game != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(config.PROTOCOL_SEPARATOR);
                                sb.append(config.PROTOCOL_GAME_REQUEST);
                                for (int i = 0; i < game.length; i++) {
                                    sb.append(game[i]);
                                }
                                socketWriter.println(sb.toString());
                                insertAction(String.format("%d%s\n", clientId, input));
                                insertAction("The game has been sent to the client");

                            } else {
                                socketWriter.println(config.PROTOCOL_GAME_NULL);
                            }
                        }
                        if (input.startsWith(config.PROTOCOL_DATA)) {
                            handleResults(input);
                        }

                        socketWriter.printf("Server (Client %d): %s\n", clientId, input);
                    }
                } catch (SocketException clientDisconnect) {
                    numClients--;
                    insertAction(String.format("Client %d disconnected.", clientId));
                }
                socket.close();
            } catch (Exception e) {
                insertAction("Error in client handler (Client # " + clientId + "): " + e.getMessage());

                e.printStackTrace();
            }
        }
    }

    /**
     * actions buttons server
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        Thread serverThread;

        if (command.equals("execute")) {
            serverThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    startServer();
                }
            });
            serverThread.start();
        } else if (command.equals("end")) {
            stopServer();
        }

        if (command.equals("resultButton")) {
            StringBuilder sb = new StringBuilder();
            synchronized (resultsList) {
                for (String result : resultsList) {
                    sb.append(result).append("\n");
                }
            }
            JOptionPane.showMessageDialog(frame, sb.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
