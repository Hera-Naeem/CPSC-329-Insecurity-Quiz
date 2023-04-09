package org.insecurity_quiz;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.insecurity_quiz.Quiz_Data_Management.QuestionLoader;
import org.insecurity_quiz.Quiz_Data_Management.Question;

import java.io.IOException;

public class MultipleChoiceController {

    @FXML
    private Button checkAnswerButton;
    @FXML
    private RadioButton optionBtn1;
    @FXML
    private RadioButton optionBtn2;
    @FXML
    private RadioButton optionBtn3;
    @FXML
    private RadioButton optionBtn4;
    @FXML
    private Button backButton;

    @FXML
    private VBox root;

    @FXML
    private Label quesIndexLabel;

    @FXML
    private Label quesLabel;

    private QuestionLoader questionLoader;
    private Question currentQuestion;

    @FXML
    public void initialize() {
        loadQuestions();
    }


    private void loadQuestions() {
        try {
            questionLoader = new QuestionLoader("MultipleChoiceFile.csv");
            currentQuestion = questionLoader.getRandomQuestions(1)[0];

            // Set the question text to the question label in the UI
            quesLabel.setText(currentQuestion.getQuestion());

            // Create radio buttons for the answer options
            optionBtn1.setText(currentQuestion.getOptions()[0]);
            optionBtn2.setText(currentQuestion.getOptions()[1]);
            optionBtn3.setText(currentQuestion.getOptions()[2]);
            optionBtn4.setText(currentQuestion.getOptions()[3]);
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
        // Check if the user has selected an answer
        if (optionBtn1.isSelected() || optionBtn2.isSelected() || optionBtn3.isSelected() || optionBtn4.isSelected()) {

            // Check if the user's answer is correct
            String userAnswer = "";
            if (optionBtn1.isSelected()) {
                userAnswer = "A";
            } else if (optionBtn2.isSelected()) {
                userAnswer = "B";
            } else if (optionBtn3.isSelected()) {
                userAnswer = "C";
            } else if (optionBtn4.isSelected()) {
                userAnswer = "D";
            }
            boolean isCorrect = currentQuestion.validateAnswer(userAnswer);
            showAnswerMessage(isCorrect);

            // Clear the selected answer
            ToggleGroup toggleGroup = new ToggleGroup();
            optionBtn1.setToggleGroup(toggleGroup);
            optionBtn2.setToggleGroup(toggleGroup);
            optionBtn3.setToggleGroup(toggleGroup);
            optionBtn4.setToggleGroup(toggleGroup);

            // Load the next question
            currentQuestion = questionLoader.getRandomQuestions(1)[0];
            quesLabel.setText(currentQuestion.getQuestion());
            optionBtn1.setText(currentQuestion.getOptions()[0]);
            optionBtn2.setText(currentQuestion.getOptions()[1]);
            optionBtn3.setText(currentQuestion.getOptions()[2]);
            optionBtn4.setText(currentQuestion.getOptions()[3]);
        } else {
            showErrorDialog("Please select an answer before proceeding.");
        }

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

    private void loadNextQuestion() {

    }

    public void optionOneSelected(ActionEvent actionEvent) {
        optionBtn1.setSelected(true);
        optionBtn2.setSelected(false);
        optionBtn3.setSelected(false);
        optionBtn4.setSelected(false);
    }

    public void optionTwoSelected(ActionEvent actionEvent) {
        optionBtn1.setSelected(false);
        optionBtn2.setSelected(true);
        optionBtn3.setSelected(false);
        optionBtn4.setSelected(false);
    }

    public void optionThreeSelected(ActionEvent actionEvent) {
        optionBtn1.setSelected(false);
        optionBtn2.setSelected(false);
        optionBtn3.setSelected(true);
        optionBtn4.setSelected(false);
    }

    public void optionFourSelected(ActionEvent actionEvent) {
        optionBtn1.setSelected(false);
        optionBtn2.setSelected(false);
        optionBtn3.setSelected(false);
        optionBtn4.setSelected(true);
    }

    public void backBtnEvent(ActionEvent actionEvent) {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.close();
    }
}