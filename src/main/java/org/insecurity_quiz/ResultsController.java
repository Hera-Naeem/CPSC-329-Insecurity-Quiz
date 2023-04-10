package org.insecurity_quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class ResultsController {
    @FXML
    private Label scoreLabel;

    @FXML
    private Button restartButton;

    private Stage applicationStage;

    private int score;

    private String playerName;

    @FXML
    private void initialize(){
        // Set the text of the score label to the player's score
        scoreLabel.setText("Your score: " + score + "/10");
    }

    /**
     * Sets the player name.
     *
     * @param playerName the player name.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Sets the player score.
     *
     * @param score the player score.
     */
    public void setScore(int score) {
        this.score = score;
        scoreLabel.setText("Your Score:" + String.valueOf(score) + "/20");
    }

    /**
     * Restarts the quiz.
     *
     * @param actionEvent the event that triggered the method.
     */
    @FXML
    public void restartEvent(ActionEvent actionEvent) {
        setApplicationStage(new Stage());
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();


        applicationStage.show();
    }

    /**
     * Sets the application stage for this controller.
     *
     * @param applicationStage the stage for the application.
     */
    public void setApplicationStage(Stage applicationStage) {
        this.applicationStage = applicationStage;

        // Initialize scene and title
        try {
            FXMLLoader loader = new FXMLLoader();
            VBox root = loader.load(new FileInputStream("GUI/intro.fxml"));
            Scene scene = new Scene(root, 600, 600);

            IntroController selectionController = (IntroController) loader.getController();
            selectionController.setApplicationStage(applicationStage);

            applicationStage.setScene(scene);
            applicationStage.setTitle("The Insecurity Quiz");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
