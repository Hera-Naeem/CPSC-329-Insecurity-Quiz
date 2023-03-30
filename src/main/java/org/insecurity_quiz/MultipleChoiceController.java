package org.insecurity_quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.insecurity_quiz.Quiz_Data_Management.Question_Types.MultipleChoiceQuestion;

public class MultipleChoiceController {

    @FXML
    private Label quesIndexLabel;

    @FXML
    private Label quesLabel;

    @FXML
    private Button nextQuestionButton;

    @FXML
    private Button getHintButton;

    @FXML
    private Button mulButton1, mulButton2, mulButton3, mulButton4;

    private MultipleChoiceQuestion currentQuestion;

    int counter = 1;
    static int correct = 0;
    static int wrong = 0;

    @FXML
    private void initialize() {
        loadQuestions();
    }

    @FXML
    private void loadQuestions() {
        // TODO: load questions from Quiz_Data_Management
        // and display the first question


        if (counter == 1){
            quesIndexLabel.setText(String.valueOf(counter));
            quesLabel.setText("I am asking you a question.");
        }
    }

    @FXML
    public void nextQuesEvent(ActionEvent event) {
        // TODO: check if an answer has been selected
        // and display the next question
    }

    @FXML
    public void getHintEvent(ActionEvent actionEvent) {
        // TODO: display a hint for the current question
    }

    @FXML
    public void optionFourEvent(ActionEvent actionEvent) {
        // TODO: record the selected answer and check if it is correct
    }

    @FXML
    public void optionThreeEvent(ActionEvent actionEvent) {
        // TODO: record the selected answer and check if it is correct
    }

    @FXML
    public void optionTwoEvent(ActionEvent actionEvent) {
        // TODO: record the selected answer and check if it is correct
    }

    @FXML
    public void optionOneEvent(ActionEvent actionEvent) {
        // TODO: record the selected answer and check if it is correct
    }
}