package org.insecurity_quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CorrectAnsWindowController {

    @FXML
    private Button backButton;
    @FXML
    private Label quesIndexLabel;

    @FXML
    private Label quesLabel;

    @FXML
    private Button nextQuestionButton;


    @FXML
    private CheckBox optn1Box, optn2Box, optn3Box, optn4Box;

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

            optn1Box.setText("Option 1");
            optn2Box.setText("Option 2");
            optn3Box.setText("Option 3");
            optn4Box.setText("Option 4");
        }
    }

    @FXML
    public void nextQuesEvent(ActionEvent event) {


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

    public void checkedOneEvent(ActionEvent actionEvent) {
    }

    public void checkedTwoEvent(ActionEvent actionEvent) {
    }

    public void checkedThreeEvent(ActionEvent actionEvent) {
    }

    public void checkedFourEvent(ActionEvent actionEvent) {
    }

    public void backBtnEvent(ActionEvent actionEvent) {
    }
}
