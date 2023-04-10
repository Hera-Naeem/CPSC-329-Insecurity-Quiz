package org.insecurity_quiz;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

public class IntroController {

    @FXML
    private VBox cipherVBox;
    @FXML
    private Label errorMessage;
    private Stage applicationStage;

    @FXML
    private TextField nameField;

    private String playerName;

    // FXML values for intro scene
    @FXML
    private Label welcomeText;

    @FXML
    private Button startQuizButton;

    // FXML values for cipherText window
    @FXML
    private Label decryptQue;

    @FXML
    private Label plainTextAns;

    @FXML
    private TextField plainTextField;

    private String cipherAnswer;

    @FXML
    private Button submitButton;

    @FXML
    private Label cipherText;

    @FXML
    private Label keyLabel;

    public void initialize() {
        // Initialize the UI elements and any necessary data here

    }


    /**
     * Validates that a player's name has been inputted before starting the quiz.
     * If the name field is empty, displays an error message and does not start the quiz.
     * Otherwise, sets the playerName variable to the inputted name and starts the quiz.
     *
     * @param event the event triggered by the start quiz button.
     * @throws IOException if there is an error loading the FXML file.
     */
    @FXML
    public void startQuizEvent(ActionEvent event) throws IOException {
        // Check if the name field is empty
        if (nameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter your name before starting the quiz.");
            alert.showAndWait();
        } else {

            // Save the player's name
            playerName = nameField.getText();

            // Get a reference to the button's stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Load the FXML file for the new scene
            FXMLLoader loader = new FXMLLoader();
            VBox root = loader.load(new FileInputStream("GUI/cipher.fxml"));

            // Create a new scene with the loaded FXML file as the root
            Scene scene = new Scene(root);

            // Set the controller for the FXMLLoader instance
            IntroController intoController = loader.getController();
            intoController.setPlayerName(playerName);

            // Set the new scene to the application stage
            stage.setScene(scene);
        }
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCipherAnswer(String answer){
        this.cipherAnswer = answer;
    }

    public void validateCipher(String answer) {
        String expectedInput = "Janet loves Frogs";

        boolean checkAnswer = answer.equals(expectedInput);
        System.out.println(checkAnswer);

        if(answer.isEmpty()) {
            errorMessage.setText("Please write your answer.");
        } else if (checkAnswer) {
            errorMessage.setText("Congratulations! " + playerName + " Let's move on to the Real Quiz");

            // Create a new button
            Button startButton = new Button("GO!");

            // Add an action event to the button
            startButton.setOnAction(event -> {
                try {
                    // Get a reference to the button's stage
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    // Load the FXML file for the new scene
                    FXMLLoader loader = new FXMLLoader();
                    VBox root = loader.load(new FileInputStream("GUI/mainpage.fxml"));

                    // Create a new scene with the loaded FXML file as the root
                    Scene scene = new Scene(root);

                    // Set the controller for the FXMLLoader instance
                    QuizController quizController = loader.getController();
                    quizController.setPlayerName(playerName);
                    quizController.setWelcomeMainText("Welcome to the Insecurity Quiz!");
                    quizController.setPlayerNameText("Hey, " + playerName + "Are you ready to begin?");

                    // Set the new scene to the application stage
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Add the button to the layout
            cipherVBox.getChildren().add(startButton);
        } else {
            errorMessage.setText("INCORRECT! Try Again");
        }
    }
    /**
     * Returns the player's name.
     *
     * @return the player's name
     */

    //for ciphertext
    @FXML
    private void submitButtonEvent(ActionEvent event) throws Exception {
        setCipherAnswer(plainTextField.getText());
        validateCipher(cipherAnswer);
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

