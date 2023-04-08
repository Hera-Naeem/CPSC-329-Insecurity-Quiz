package org.insecurity_quiz.Advanced_Security_Implementations;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * SocketClient represents a client for sending string messages over a TCP socket connection to a server.
 */
public class SocketClient {

    private String serverAddress;
    private int serverPort;

    /**
     * Initializes a new instance of the `SocketClient` class with the specified server address and port number.
     * @param serverAddress The IP address or hostname of the server to connect to.
     * @param serverPort The port number of the server to connect to.
     */
    public SocketClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    /**
     * Sends a string message over the TCP socket connection to the server.
     * @param string The string message to send.
     * @return `true` if the message was successfully sent, `false` otherwise.
     */
    public boolean send(String string) {
        try {
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(string);
            outputStream.flush();
            socket.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
