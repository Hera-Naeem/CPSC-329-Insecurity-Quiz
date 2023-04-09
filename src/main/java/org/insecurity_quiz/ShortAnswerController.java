package org.insecurity_quiz;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.insecurity_quiz.Quiz_Data_Management.Question;
import org.insecurity_quiz.Quiz_Data_Management.QuestionLoader;

import java.io.IOException;

public class ShortAnswerController {
    @FXML
    private VBox root;

    public TextField answerLabel;
    @FXML
    private Button backButton;
    @FXML
    private Label quesIndexLabel;

    @FXML
    private Label quesLabel;
    
    @FXML
    private Button checkAnswerButton;
    

    private QuestionLoader questionLoader;
    private Question currentQuestion;
    private int counter = 1;
    static int correct = 0;
    static int wrong = 0;


    @FXML
    private void initialize() {
        loadQuestions();
    }

    @FXML
    private void loadQuestions() {
        try {
            questionLoader = new QuestionLoader("ShortAnswer.csv");
            currentQuestion = questionLoader.getRandomQuestions(1)[0];

            quesIndexLabel.setText(String.valueOf(counter));
            quesLabel.setText(currentQuestion.getQuestion());
        } catch (IOException e) {
            showErrorDialog("Error loading questions from file.");
            Platform.exit();
        }
    }

    private void showErrorDialog(String s) {
        Stage errorStage = new Stage();
        errorStage.setTitle("Error");

        Label errorLabel = new Label(s);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> errorStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(errorLabel, closeButton);
        layout.setAlignment(Pos.CENTER);

        errorStage.setScene(new Scene(layout));
        errorStage.show();
    }

    @FXML
    public void checkAnswerEvent(ActionEvent event) {
        if (answerLabel.getText().isEmpty()) {
            showErrorDialog("Please enter your answer!");
            return;
        }

        String userAnswer = answerLabel.getText();
        String correctAnswer = currentQuestion.getAnswer();

        boolean isCorrect = userAnswer.equalsIgnoreCase(correctAnswer);
        showAnswerMessage(isCorrect, correctAnswer);
    }

    private void showAnswerMessage(boolean isCorrect, String correctAnswer) {
        String message;
        if (isCorrect) {
            message = "Correct!";
        } else {
            message = "The correct answer is: " + correctAnswer;
        }

        Label label = new Label(message);
        label.setPadding(new Insets(10));
        label.setStyle("-fx-font-size: 16");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            Stage stage = (Stage) label.getScene().getWindow();
            stage.close();
            checkAnswerButton.setText("Next Question");
            checkAnswerButton.setOnAction(event2 -> loadNextQuestion());
        });

        VBox layout = new VBox(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);

        Scene scene = new Scene(layout, 300, 150);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void loadNextQuestion() {
    }

    @FXML
    public void backBtnEvent(ActionEvent actionEvent) {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.close();
    }

}
