package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController extends HelloApplication{

    //this section is for the types of questions window
    @FXML
    private Button start_button;

    @FXML
    private Label welcomeText;

    @FXML
    void QuestionTypeWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("questions.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 280);
        Stage stage = new Stage();
        stage.setTitle("The Insecurity Quiz");
        stage.setScene(scene);
        stage.show();
    }

    //this section is for each separate type of question windows
    @FXML
    private Button correctAnswer;

    @FXML
    private Button mcq;

    @FXML
    private Button numerical;

    @FXML
    private Button shortAnswer;

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
