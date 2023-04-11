package org.insecurity_quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class QuizController {

    @FXML
    private VBox mainPageVBox;
    @FXML
    private Label welcomeMainText;
    @FXML
    private Label playerNameText;
    @FXML
    private Label playerNameLabel;

    private String playerName;

    @FXML
    private VBox quizPage;

    @FXML
    private Button startQuizButton;


    private int numQuestionsAnswered = 0;
    private int score = 0;
    private int currentQuesIndex = 0;
    private static final int TOTAL_QUESTIONS = 3;
    private Stage applicationStage;

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public void setPlayerNameText(String playerText){
        playerNameText.setText(playerText);
    }

    public void setWelcomeMainText(String welcomeText){
        welcomeMainText.setText(welcomeText);
    }
    public void setScore(int score) {
        this.score = score;
        //scoreLabel.setText(score + "/" + TOTAL_QUESTIONS);
    }
    public void setQuesIndex(int quesIndex) {
        this.currentQuesIndex = quesIndex;
        //quizzesAttemptedLabel.setText(String.valueOf(currentQuesIndex));

    }

    @FXML
    private void initialize() {

    }

    private void showErrorDialog(String errorMessage) {
        // TODO: implement error dialog
        System.err.println(errorMessage);
    }


    @FXML
    private void startQuizEvent(ActionEvent event) throws IOException {
        // Get a reference to the button's stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Load the FXML file for the new scene
        FXMLLoader loader = new FXMLLoader();
        VBox root = loader.load(new FileInputStream("GUI/questions.fxml"));

        // Create a new scene with the loaded FXML file as the root
        Scene scene = new Scene(root);

        // Set the controller for the FXMLLoader instance
        QuestionController questionsController = loader.getController();
        questionsController.setApplicationStage(applicationStage);
        questionsController.setCurrentScore(score);
        questionsController.setPlayerName(playerName);
        questionsController.setQuestionIndex(currentQuesIndex);
        questionsController.setTotalQuestions(TOTAL_QUESTIONS);

        // Set the new scene to the application stage
        stage.setScene(scene);

    }

        /**
         * The setApplicationStage method sets the application stage for this controller.
         *
         * @param applicationStage the stage for the application.
         */
        public void setApplicationStage (Stage applicationStage){
            this.applicationStage = applicationStage;
        }



}
