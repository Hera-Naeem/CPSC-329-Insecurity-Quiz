package org.insecurity_quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.insecurity_quiz.Quiz_Data_Management.Question;
import org.insecurity_quiz.Quiz_Data_Management.QuestionLoader;
import org.insecurity_quiz.Quiz_Data_Management.Question_Types.MultipleChoiceQuestion;
import org.insecurity_quiz.Quiz_Data_Management.Question_Types.NumericalQuestion;
import org.insecurity_quiz.Quiz_Data_Management.Question_Types.ShortAnswerQuestion;


import java.io.FileInputStream;
import java.io.IOException;


public class QuestionController {
    @FXML
    public VBox quizBox;

    @FXML
    private Label titleLabel;
    @FXML
    private Label quesIndexLabel;
    @FXML
    private Label quesTextLabel;
    @FXML
    private VBox questionBox;
    @FXML
    private Button nextButton;
    @FXML
    private ProgressBar progressTracker;
    private Stage applicationStage;
    private int score;

    private int totalQuestions;

    private int quesIndex;

    private static final int NUM_QUESTIONS = 20;

    private QuestionLoader questionLoader;
    private Question currentQuestion;

    private Question[] questions;

    private String questionType;

    //private String playerName;
    private String indexText;

    //for multiple choice questions
    private String questionText;
    private RadioButton optionButton1;
    private RadioButton optionButton2;
    private RadioButton optionButton3;
    private RadioButton optionButton4;

    private TextField answerField;


    //for numerical & short answer questions
    private String correctAnswer;
    private String userAnswer;

    private boolean isCorrect;

    private String playerName;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setCurrentScore(int correct) {
        this.score = correct;
    }

    public void setQuestionIndex(int index) {
        this.quesIndex = index;
    }

    public void setTotalQuestions(int total) {
        this.totalQuestions = total;
    }

    public void setProgressTracker(int track) {
        progressTracker.setProgress((double) track / 20);
    }

    @FXML
    public void initialize() throws IOException {
        //setPlayerName(playerName);
        setTotalQuestions(totalQuestions);
        setQuestionIndex(0);
        setCurrentScore(score);
        questionLoader = new QuestionLoader("QuizQuestionsFinal.csv");
        loadQuestions(quesIndex);
        setProgressTracker(quesIndex+1);
    }
    @FXML
    public void nextButtonEvent(ActionEvent actionEvent) {

        answerValidation();

        // Check if this is the last question
        if (quesIndex == 19) {
            // Change the text of the submit button to "Finish"
            //submitButton.setText("Finish");
            nextButton.setOnAction(event -> {
                // Remove all children of quizBox other than the title
                quizBox.getChildren().removeIf(node -> !(node instanceof Label));

                // Create a label with the text "Great job! You've answered all the questions..."
                Label congratulationsLabel = new Label("Great job! You've answered all the questions!");
                congratulationsLabel.setAlignment(Pos.CENTER);
                congratulationsLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

                // Create a button with the label "View Results"
                Button viewResultsButton = new Button("View Results");
                viewResultsButton.setFont(Font.font("Segoe UI Black", FontWeight.BOLD, 25));
                viewResultsButton.setTextFill(javafx.scene.paint.Color.WHITE);
                viewResultsButton.setStyle("-fx-background-color: #6e3d6d; -fx-padding: 10px; -fx-background-radius: 5em;");
                viewResultsButton.setOnAction(event1 -> {
                    try {
                        // Get a reference to the button's stage
                        Stage stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();

                        // Load the FXML file for the new scene
                        FXMLLoader loader = new FXMLLoader();
                        VBox root = loader.load(new FileInputStream("GUI/results.fxml"));

                        // Create a new scene with the loaded FXML file as the root
                        Scene scene = new Scene(root);

                        // Set the controller for the FXMLLoader instance
                        ResultsController resultsController = loader.getController();
                        resultsController.setPlayerName(playerName);
                        resultsController.setScore(this.score);

                        // Set the new scene to the application stage
                        stage.setScene(scene);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                // Add the label and button to the quizBox
                quizBox.getChildren().addAll(congratulationsLabel, viewResultsButton);
            });
        } else {
            // Change the text of the submit button to "Next"
            //submitButton.setText("Next");
            //submitButton.setOnAction(event -> {
            try {
                loadNextQuestion();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }


    private void showErrorDialog(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText(s);

        alert.showAndWait();
    }

    private void loadQuestions(int quesIndex) throws IOException {
        // Load questions from Quiz Data management
        //setTotalQuestions(totalQuestions);
        questions = questionLoader.getRandomQuestions(20);
        currentQuestion = questions[quesIndex];

        if(currentQuestion != null){
            this.questionType = currentQuestion.getQuestionType().toString();
            System.out.println("Current question type: " + questionType);
            System.out.println(quesIndex);

            // Check the type of the current question
            if (currentQuestion.getQuestionType() == Question.QuestionTypes.MultipleChoice) {
                // Do something for multiple choice questions
                ToggleGroup optionGroup = new ToggleGroup();
                this.optionButton1 = new RadioButton(currentQuestion.getOptions()[0]);
                this.optionButton2 = new RadioButton(currentQuestion.getOptions()[1]);
                this.optionButton3 = new RadioButton(currentQuestion.getOptions()[2]);
                this.optionButton4 = new RadioButton(currentQuestion.getOptions()[3]);


                // set radio button font color and size
                String radioButtonStyle = "-fx-font-size: 14pt; -fx-text-fill: white;";
                optionButton1.setStyle(radioButtonStyle);
                optionButton2.setStyle(radioButtonStyle);
                optionButton3.setStyle(radioButtonStyle);
                optionButton4.setStyle(radioButtonStyle);

                //add radio buttons to question box
                questionBox.getChildren().clear();
                questionBox.getChildren().addAll(optionButton1, optionButton2, optionButton3, optionButton4);
                optionButton1.setToggleGroup(optionGroup);
                optionButton2.setToggleGroup(optionGroup);
                optionButton3.setToggleGroup(optionGroup);
                optionButton4.setToggleGroup(optionGroup);

            } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.ShortAnswer || currentQuestion.getQuestionType() == Question.QuestionTypes.Numerical) {
                // Do something for short answer questions
                this.answerField = new TextField();
                questionBox.getChildren().clear();
                questionBox.getChildren().add(answerField);

            } else {
                // Show error dialog for unknown question type
                showErrorDialog("Unknown question type");
                return;
            }
        } else {
            // Show error dialog for null current question
            showErrorDialog("Questions have not been loaded");
            return;
        }

        //labels
        this.questionText = currentQuestion.getQuestion();
        this.indexText = String.valueOf(quesIndex + 1);
        this.correctAnswer = currentQuestion.getAnswer();

        //update question and index
        quesTextLabel.setText(questionText);
        quesIndexLabel.setText(indexText);
    }

    public String getUserAnswer() {
        if (currentQuestion.getQuestionType() == Question.QuestionTypes.MultipleChoice) {
            if (optionButton1.isSelected()) {
                return "A";
            } else if (optionButton2.isSelected()) {
                return "B";
            } else if (optionButton3.isSelected()) {
                return "C";
            } else if (optionButton4.isSelected()) {
                return "D";
            } else {
                return null; // no answer selected
            }
        } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.ShortAnswer) {
            TextField answerField = (TextField) questionBox.getChildren().get(0);
            return answerField.getText();
        }  else if (currentQuestion.getQuestionType() == Question.QuestionTypes.Numerical) {
            TextField answerField = (TextField) questionBox.getChildren().get(0);
            return answerField.getText();
        } else {
            return null; // unknown question type
        }
    }

    public void answerValidation() {

        // Get the selected answer based on the question type
        if (currentQuestion.getQuestionType() == Question.QuestionTypes.MultipleChoice) {
            RadioButton selectedButton = (RadioButton) questionBox.getChildren().stream()
                    .filter(node -> node instanceof RadioButton && ((RadioButton) node).isSelected())
                    .findFirst()
                    .orElse(null);
            if (selectedButton != null) {
                this.userAnswer = getUserAnswer();
                isCorrect = userAnswer.equals(correctAnswer);
            }
        } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.ShortAnswer) {
            TextField answerField = (TextField) questionBox.getChildren().get(0);
            this.userAnswer = answerField.getText();
            isCorrect = userAnswer.equals(correctAnswer);

            // Create a new Alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Did you get this right?");
            alert.setHeaderText(currentQuestion.getQuestion());
            String correctAnswer = currentQuestion.getAnswer();
            alert.setContentText("Correct Answer:\n" + correctAnswer);
            Text text = new Text(correctAnswer);
            text.wrappingWidthProperty().bind(alert.getDialogPane().widthProperty().subtract(50));
            alert.getDialogPane().setContent(text);
            alert.showAndWait();
        } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.Numerical) {
            TextField answerField = (TextField) questionBox.getChildren().get(0);
            this.userAnswer = getUserAnswer();
            isCorrect = userAnswer.equals(correctAnswer);
        }

        // Check if an answer is selected
        if (userAnswer == null || userAnswer.isEmpty()) {
            showErrorDialog("Warning: you haven't answered the question");
            return;
        }

        // Check if the selected answer is correct
        if (isCorrect) {
            setCurrentScore(score + 1);
        }
    }

    private void loadNextQuestion() throws IOException {
        // Increment the question index
        setQuestionIndex(quesIndex + 1);
        setProgressTracker(quesIndex+1);

        // Load the next question
        if (quesIndex < questions.length) {
            loadQuestions(quesIndex);
        }
    }

    /**
     * The setApplicationStage method sets the application stage for this controller.
     *
     * @param applicationStage the stage for the application.
     */

    public void setApplicationStage(Stage applicationStage) {
        this.applicationStage = applicationStage;
    }

}
