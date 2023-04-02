package org.insecurity_quiz;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import org.insecurity_quiz.Advanced_Security_Implementations.Keylogger;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Keylogger keylogger;
        try {
            keylogger = new Keylogger("Test.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NativeHookException e) {
            System.out.println("Well, didn't hook.");
        }
        while (true) {
            // Do something
        }
    }
}