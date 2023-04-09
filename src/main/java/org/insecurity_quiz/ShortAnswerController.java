package org.insecurity_quiz;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private Button nextQuestionButton;

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
            questionLoader = new QuestionLoader("src/main/java/org/insecurity_quiz/testFile.csv");
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
    public void nextQuesEvent(ActionEvent event) {
        if (answerLabel.getText().isEmpty()) {
            showErrorDialog("Please enter your answer!");
            return;
        }

        String userAnswer = answerLabel.getText();
        String correctAnswer = currentQuestion.getAnswer();

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            correct++;
            showErrorDialog("Correct answer!");
        } else {
            wrong++;
            showErrorDialog("Wrong answer!");
        }
    }
    @FXML
    public void backBtnEvent(ActionEvent actionEvent) {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.close();
    }
}
