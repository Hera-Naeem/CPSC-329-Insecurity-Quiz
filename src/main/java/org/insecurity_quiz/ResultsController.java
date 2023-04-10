package org.insecurity_quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class ResultsController {


    @FXML
    private Button enterButton;
    
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Stage applicationStage;

    private int score;

    private String playerName;

    @FXML
    private void initialize(){
        // Set the text of the score label to the player's score
        //scoreLabel.setText("Your score: " + score + "/10");
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
        //scoreLabel.setText("Your Score:" + String.valueOf(score) + "/20");
    }

    @FXML
    public void enterEvent(ActionEvent actionEvent) {
        // Remove all the children of the VBox
        VBox vbox = (VBox) enterButton.getParent();
        vbox.getChildren().clear();

        // Add subheading for results
        Label resultsSubheading = new Label("Your Results");
        resultsSubheading.setStyle("-fx-font-size: 18pt; -fx-padding: 20 0 10 0;");
        vbox.getChildren().add(resultsSubheading);

        // Add label to display final score
        Label scoreLabel = new Label("Your final score is: " + score + "/20");
        scoreLabel.setStyle("-fx-font-size: 14pt; -fx-padding: 0 0 10 0;");
        vbox.getChildren().add(scoreLabel);

        // Add "Gotcha!" label
        Label gotchaLabel = new Label("Gotcha!");
        gotchaLabel.setStyle("-fx-font-size: 14pt; -fx-text-fill: red; -fx-padding: 10 0 0 0;");
        vbox.getChildren().add(gotchaLabel);

        // Add "If you gave your password" label
        Label passwordLabel = new Label("If you gave your password, restart the quiz to learn why you shouldn't.");
        passwordLabel.setStyle("-fx-font-size: 12pt; -fx-padding: 10 0 0 0;");
        vbox.getChildren().add(passwordLabel);

        // Add "But being a student of CPSC 329" label
        Label cpscLabel = new Label("But being a student of CPSC 329, you probably know better!");
        cpscLabel.setStyle("-fx-font-size: 12pt; -fx-padding: 10 0 0 0;");
        vbox.getChildren().add(cpscLabel);

        // Add restart button
        Button restartButton = new Button("Restart Quiz");
        restartButton.setOnAction(this::restartEvent);
        HBox hbox = new HBox(restartButton);
        hbox.setStyle("-fx-alignment: center; -fx-padding: 20 0 0 0;");
        vbox.getChildren().add(hbox);
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
