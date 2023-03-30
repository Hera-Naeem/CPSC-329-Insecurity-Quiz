package org.insecurity_quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CorrectAnsWindowController {

    @FXML
    private Label quesIndexLabel;

    @FXML
    private Label quesLabel;

    @FXML
    private Button nextQuestionButton;
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


    }

    @FXML
    public void getHintEvent(ActionEvent actionEvent) {
        // TODO: display a hint for the current question
    }

}
