package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController extends HelloApplication{

    //THIS SECTION IS FOR THE INTRO WINDOW
    @FXML
    private Label credits;

    @FXML
    private Button instructions;

    @FXML
    private Button start_button;

    @FXML
    private Label title;

    @FXML
    void CipherWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cipher.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 530, 316);
        Stage stage = new Stage();
        stage.setTitle("Caesar Cipher Challenge!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Instructions(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText("Instructions");
        alert.setContentText("1) In order to start the game, try to decrypt the ciphertext once you click Start" + "\n" +
                "2) Once you are decrypt the message, pick the type of question you would like to quiz yourself on" + "\n" +
                "3) Good luck! And Enjoy!");
        alert.show();
    }

    //THIS SECTION IS FOR THE CIPHER TEXT WINDOW
    @FXML
    private Label ciphertext;

    @FXML
    private Label decryptQue;

    @FXML
    private Label key;

    @FXML
    private Label plainTextAns;

    @FXML
    private TextField plaintextfield;

    @FXML
    private Button startQuiz;

    @FXML
    void QuestionTypeWindow(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("questions.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 280);
        Stage stage = new Stage();
        stage.setTitle("Question Types");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handlePlainTextButtonAction(ActionEvent event) {
        String inputText = plaintextfield.getText();
        String expectedText = "Janet loves frogs";

        Alert alert;

        if (inputText.equals(expectedText)) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Congratulations! You got it!");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("INCORRECT!");
        }
        alert.show();
    }

    //THIS SECTION IS FOR THE WINDOW WITH THE DIFFERENT TYPES OF QUESTIONS
    @FXML
    private Button correctAnswer;

    @FXML
    private Button mcq;

    @FXML
    private Button numerical;

    @FXML
    private Button shortAnswer;

    @FXML
    private Button back;

    @FXML
    void CorrectAnsWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("correctanswer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 485, 307);
        Stage stage = new Stage();
        stage.setTitle("The Insecurity Quiz");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void multipleChoiceWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("multiplechoice.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 376, 380);
        Stage stage = new Stage();
        stage.setTitle("The Insecurity Quiz");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void numericalWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("numerical.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 509, 277);
        Stage stage = new Stage();
        stage.setTitle("The Insecurity Quiz");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void shortAnsWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("shortanswer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 513, 291);
        Stage stage = new Stage();
        stage.setTitle("The Insecurity Quiz");
        stage.setScene(scene);
        stage.show();
    }
}
