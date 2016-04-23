package client;

public interface SocketClientConstants {
    int iECHO_PORT = 7;
    int iDAYTIME_PORT = 13;
    int iSMTP_PORT = 25;
    boolean DEBUG = true;
    
    static final int SELECT_SERVICE = 0;
    static final int PREFORM_SERVICE = 1;
    static final int PROP_TO_AUTO = 2;
    static final int GET_AVAIL_MODELS = 3;
    static final int PRINT_AUTO = 4;
    static final int SEND_AUTO = 5;
    static final int CLIENT_EXIT = 6;
    
}
