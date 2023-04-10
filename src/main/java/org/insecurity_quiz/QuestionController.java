package org.insecurity_quiz;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.insecurity_quiz.Quiz_Data_Management.Question;
import org.insecurity_quiz.Quiz_Data_Management.QuestionLoader;

import java.io.FileInputStream;
import java.io.IOException;


public class QuestionController {
    @FXML
    private Label titleLabel;
    @FXML
    private Label quesIndexLabel;
    @FXML
    private Label quesTextLabel;
    @FXML
    private VBox questionBox;
    @FXML
    private Button submitButton;
    @FXML
    private Button mainMenuButton;
    @FXML
    private ProgressBar progressTracker;
    private Stage applicationStage;
    private int score;
    private int counter = -1;

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
        questionLoader = new QuestionLoader("QuizQuestions.csv");
        loadQuestions(quesIndex);
        setProgressTracker(quesIndex+1);
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

                //add radio buttons to question box
                questionBox.getChildren().clear();
                questionBox.getChildren().addAll(optionButton1, optionButton2, optionButton3, optionButton4);
                optionButton1.setToggleGroup(optionGroup);
                optionButton2.setToggleGroup(optionGroup);
                optionButton3.setToggleGroup(optionGroup);
                optionButton4.setToggleGroup(optionGroup);

            } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.ShortAnswer) {
                // Do something for short answer questions
                TextField answerField = new TextField();
                questionBox.getChildren().clear();
                questionBox.getChildren().add(answerField);

            } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.Numerical) {
                // Do something for numerical questions
                TextField answerField = new TextField();
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

    private void showErrorDialog(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText(s);

        alert.showAndWait();
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
    @FXML
    public void submitEvent(ActionEvent actionEvent) {

        answerValidation();

        // Check if this is the last question
        if (quesIndex == 19) {
            // Change the text of the submit button to "Finish"
            //submitButton.setText("Finish");
            submitButton.setOnAction(event -> {
                try {
                    // Get a reference to the button's stage
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

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


    public void answerValidation(){

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
            userAnswer = answerField.getText();
            isCorrect = userAnswer.equals(correctAnswer);

            if (!isCorrect) {
                // Create a new Alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Incorrect Answer");
                alert.setHeaderText("Did you know?");
                String correctAnswer = currentQuestion.getAnswer();
                alert.setContentText("Correct Answer:\n" + correctAnswer);
                Text text = new Text(correctAnswer);
                text.wrappingWidthProperty().bind(alert.getDialogPane().widthProperty().subtract(50));
                alert.getDialogPane().setContent(text);
                alert.showAndWait();
            } else {
                isCorrect = true;
            }
        }else if (currentQuestion.getQuestionType() == Question.QuestionTypes.Numerical) {
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

            /*

             *



            // Show an alert to indicate that the answer is correct
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct Answer");
            alert.setHeaderText(null);
            alert.setContentText("Congratulations! Your answer is correct.");
            alert.showAndWait();
        } else {
            // Show an alert to indicate that the answer is incorrect
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Answer");
            alert.setHeaderText(null);
            alert.setContentText("Better luck next time!");
            alert.showAndWait();
        }

             **/




    }

    private void loadNextQuestion() throws IOException {
        // Increment the question index
        setQuestionIndex(quesIndex + 1);
        setProgressTracker(quesIndex+1);

        // Reset the submit button's text and event handler
        //submitButton.setText("Submit");
        //submitButton.setOnAction(this::submitEvent);

        // Load the next question
        if (quesIndex < questions.length) {
            loadQuestions(quesIndex);
        }
    }


    @FXML
    public void mainMenuEvent(ActionEvent event) {
        try {
            // Get a reference to the button's stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Load the FXML file for the new scene
            FXMLLoader loader = new FXMLLoader();
            VBox root = loader.load(new FileInputStream("GUI/mainpage.fxml"));

            // Create a new scene with the loaded FXML file as the root
            Scene scene = new Scene(root);

            // Set the controller for the FXMLLoader instance
            QuizController quizController = loader.getController();
            quizController.setPlayerName(playerName);
            quizController.setScore(this.score);
            quizController.setQuesIndex(this.quesIndex);
            quizController.setPlayerNameText("Player Name: " + playerName);

            // Set the new scene to the application stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
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
