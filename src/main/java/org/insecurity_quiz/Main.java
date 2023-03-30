/**
 The Main class is the entry point of the Insecurity Quiz application.
 It extends the JavaFX Application class and sets up the initial stage
 by loading the intro.fxml file using the FXMLLoader class.
 It also sets the QuizController and initializes the JavaFX scene.
 */

package org.insecurity_quiz;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Main extends Application{

    /**
     * The start method initializes the JavaFX stage by loading the intro.fxml file
     * using the FXMLLoader class. It sets up the scene and sets the QuizController.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader();
            VBox root = loader.load(new FileInputStream("GUI/intro.fxml"));
            Scene scene = new Scene(root, 600, 600);

            QuizController selectionController = (QuizController) loader.getController();
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
