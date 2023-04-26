package game;
/**
 * Config class for Client and Server.
 */
public class config {
    /**
     * PROTOCOL_SEPARATOR
     */
    static final String PROTOCOL_SEPARATOR = "#";
    /**
     * FIELD_SEPARATOR
     */
    static final String FIELD_SEPARATOR = ",";
    /**
     * PROTOCOL_END
     */
    static final String PROTOCOL_END = "P0";
    /**
     * PROTOCOL_SENDGAME
     */
    static final String PROTOCOL_SENDGAME = "P1";
    /**
     * PROTOCOL_RECVGAME
     */
    static final String PROTOCOL_RECVGAME = "P2";
    /**
     * PROTOCOL_GAME_REQUEST
     */
    public static final String PROTOCOL_GAME_REQUEST = "P4";
    /**
     * PROTOCOL_GAME_NULL
     */
    public static final String PROTOCOL_GAME_NULL = "P5";
    /**
     * PROTOCOL_DATA
     */
    static final String PROTOCOL_DATA = "P3";
    /**
     * IMAGE_NAME
     */
    static final String IMAGE_NAME = "images/piccorssLogo.png";
    /**
     * PROTOCOL_SEND_USERNAME
     */
    static final String PROTOCOL_SEND_USERNAME = "Client";
    /**
     * PROTOCOL_SERVER_STOPPED
     */
    public static final String PROTOCOL_SERVER_STOPPED = "SERVER_STOPPED";
    /**
     * DEFAULT_USER
     */
    public static final String DEFAULT_USER = "Client";
    /**
     * DEFAULT_ADDR
     */
    public static final String DEFAULT_ADDR = "localhost";
    /**
     * DEFAULT_PORT
     */
    public static final int DEFAULT_PORT = 65514;
}
