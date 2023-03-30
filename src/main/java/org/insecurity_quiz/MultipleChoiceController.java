package org.insecurity_quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
        // Create a new window
        Stage hintStage = new Stage();
        hintStage.setTitle("Hint");

        // Create a label to display the hint
        Label hintLabel = new Label("Here's a hint for the current question!");

        // Create a button to close the window
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> hintStage.close());

        // Create a layout for the window
        VBox layout = new VBox(10);
        layout.getChildren().addAll(hintLabel, closeButton);
        layout.setAlignment(Pos.CENTER);

        // Set the layout and show the window
        hintStage.setScene(new Scene(layout));
        hintStage.show();
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