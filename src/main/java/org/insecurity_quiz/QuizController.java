/**

 The QuizController class is the controller for the intro.fxml file.
 It handles the events and actions for the welcome text and start quiz button.
 It also loads the questions.fxml file when the start quiz button is clicked.
 */


package org.insecurity_quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.control.Button;

public class QuizController{

    private Stage applicationStage;


    @FXML
    private Label welcomeText;

    @FXML
    private Button startQuizButton;

    /**
     * The startQuizEvent method is the event handler for the start quiz button.
     * It loads the questions.fxml file using the FXMLLoader class and sets up
     * the QuestionsController for the new scene.
     *
     * @param event the event triggered by the start quiz button.
     * @throws IOException if there is an error loading the FXML file.
     */

    @FXML
    public void startQuizEvent(ActionEvent event) throws IOException {

        // Get a reference to the button's stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Load the FXML file for the new scene
        FXMLLoader loader = new FXMLLoader();
        VBox root = loader.load(new FileInputStream("GUI/questions.fxml"));

        // Create a new scene with the loaded FXML file as the root
        Scene scene = new Scene(root);

        // Set the controller for the FXMLLoader instance
        QuestionsController selectionController = (QuestionsController) loader.getController();


        // Set the new scene to the application stage
        stage.setScene(scene);
    }

    /**
     * The setApplicationStage method sets the application stage for this controller.
     *
     * @param applicationStage the stage for the application.
     */

    public void setApplicationStage(Stage applicationStage) {
        this.applicationStage = applicationStage;
    }


}


