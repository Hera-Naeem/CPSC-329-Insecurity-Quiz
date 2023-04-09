
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

public class NumericalController {
    @FXML
    private Button checkAnswerButton;
    @FXML
    private VBox root;

    @FXML
    private Button backButton;
    @FXML
    private Label quesIndexLabel;

        @FXML
        private Label quesLabel;

        @FXML
        private TextField answerField;

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
                questionLoader = new QuestionLoader("Numerical.csv");
                currentQuestion = questionLoader.getRandomQuestions(1)[0];

                quesIndexLabel.setText(String.valueOf(counter));
                quesLabel.setText(currentQuestion.getQuestion());
            } catch (IOException e) {
                showErrorDialog("Error loading questions from file.");
                Platform.exit();
            }
        }

    @FXML
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
        if (answerField.getText().isEmpty()) {
            showErrorDialog("Please enter your answer!");
            return;
        }

        try {
            double userAnswer = Double.parseDouble(answerField.getText());
            double correctAnswer = Double.parseDouble(currentQuestion.getAnswer());

            if (userAnswer == correctAnswer) {
                correct++;
                showAnswerMessage(true);
                checkAnswerButton.setText("Next Question");
                checkAnswerButton.setOnAction(event2 -> loadNextQuestion());
            } else {
                wrong++;
                showErrorDialog("Wrong answer!");
            }

        } catch (NumberFormatException e) {
            showErrorDialog("Please enter a valid number!");
        }
    }

    private void loadNextQuestion() {
    }

    private void showAnswerMessage(boolean isCorrect) {
        String message = isCorrect ? "Correct!" : "Incorrect!";
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

    @FXML
    public void backBtnEvent(ActionEvent actionEvent) {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.close();
    }
}
