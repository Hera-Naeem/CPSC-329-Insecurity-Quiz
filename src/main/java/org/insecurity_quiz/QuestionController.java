package org.insecurity_quiz;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.insecurity_quiz.Quiz_Data_Management.Question;
import org.insecurity_quiz.Quiz_Data_Management.QuestionLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    private TextField answerField;


    //for numerical & short answer questions
    private String correctAnswer;
    private String userAnswer;


    @FXML
    public void initialize() throws IOException {
        //setPlayerName(playerName);
        setTotalQuestions(totalQuestions);
        setQuestionIndex(0);
        setCurrentScore(score);
        questionLoader = new QuestionLoader("QuizQuestions.csv");
        loadQuestions(quesIndex);
    }

    private void loadQuestions(int quesIndex) throws IOException {
        // Load questions from Quiz Data management
        //setTotalQuestions(totalQuestions);
        questions = questionLoader.getRandomQuestions(20);
        currentQuestion = questions[quesIndex];

        if(currentQuestion != null){
            this.questionType = currentQuestion.getQuestionType().toString();
            System.out.println("Current question type: " + questionType);

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

            } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.SelectCorrect) {
                // Do something for select correct questions
                CheckBox optionBox1 = new CheckBox(currentQuestion.getOptions()[0]);
                CheckBox optionBox2 = new CheckBox(currentQuestion.getOptions()[1]);
                CheckBox optionBox3 = new CheckBox(currentQuestion.getOptions()[2]);
                CheckBox optionBox4 = new CheckBox(currentQuestion.getOptions()[3]);

                //add checkboxes to question box
                questionBox.getChildren().clear();
                questionBox.getChildren().addAll(optionBox1, optionBox2, optionBox3, optionBox4);

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


    public void setCurrentScore(int correct) {
        this.score = correct;
    }

    public void setQuestionIndex(int index) {
        this.quesIndex = index;
    }


    public void setTotalQuestions(int total) {
        this.totalQuestions = total;
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
        } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.SelectCorrect) {
            CheckBox optionBox1 = (CheckBox) questionBox.getChildren().get(0);
            CheckBox optionBox2 = (CheckBox) questionBox.getChildren().get(1);
            CheckBox optionBox3 = (CheckBox) questionBox.getChildren().get(2);
            CheckBox optionBox4 = (CheckBox) questionBox.getChildren().get(3);
            StringBuilder userAnswerBuilder = new StringBuilder();
            if (optionBox1.isSelected()) {
                userAnswerBuilder.append("A");
            }
            if (optionBox2.isSelected()) {
                userAnswerBuilder.append("B");
            }
            if (optionBox3.isSelected()) {
                userAnswerBuilder.append("C");
            }
            if (optionBox4.isSelected()) {
                userAnswerBuilder.append("D");
            }
            String userAnswer = userAnswerBuilder.toString();
            return userAnswer.isEmpty() ? null : userAnswer;
        } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.Numerical) {
            TextField answerField = (TextField) questionBox.getChildren().get(0);
            return answerField.getText();
        } else {
            return null; // unknown question type
        }
    }
    public void submitEvent(ActionEvent actionEvent) {

        // Get the selected answer based on the question type
        if (currentQuestion.getQuestionType() == Question.QuestionTypes.MultipleChoice) {
            RadioButton selectedButton = (RadioButton) questionBox.getChildren().stream()
                    .filter(node -> node instanceof RadioButton && ((RadioButton) node).isSelected())
                    .findFirst()
                    .orElse(null);
            if (selectedButton != null) {
                this.userAnswer = getUserAnswer();
            }
        } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.ShortAnswer) {
            TextField answerField = (TextField) questionBox.getChildren().get(0);
            userAnswer = answerField.getText();
        } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.SelectCorrect) {
            List<CheckBox> selectedBoxes = questionBox.getChildren().stream()
                    .filter(node -> node instanceof CheckBox && ((CheckBox) node).isSelected())
                    .map(node -> (CheckBox) node)
                    .collect(Collectors.toList());
            userAnswer = "";
            for (CheckBox box : selectedBoxes) {
                this.userAnswer = getUserAnswer();
            }
            if (userAnswer.length() > 0) {
                userAnswer = userAnswer.substring(0, userAnswer.length() - 1); // remove the last comma
            }
        } else if (currentQuestion.getQuestionType() == Question.QuestionTypes.Numerical) {
            TextField answerField = (TextField) questionBox.getChildren().get(0);
            this.userAnswer = getUserAnswer();
        }

        // Check if an answer is selected
        if (userAnswer == null || userAnswer.isEmpty()) {
            showErrorDialog("Please select an answer.");
            return;
        }

        // Check if the selected answer is correct
        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            setCurrentScore(score + 1);

            // Show an alert to indicate that the answer is correct
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct Answer");
            alert.setHeaderText(null);
            alert.setContentText("Congratulations! Your answer is correct.");
            alert.showAndWait();
        }

        // Check if this is the last question
        if (quesIndex == questions.length - 1) {
            // Change the text of the submit button to "Finish"
            submitButton.setText("Finish");
            submitButton.setOnAction(event -> {
                try {
                    loadNextQuestion();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            // Change the text of the submit button to "Next"
            submitButton.setText("Next");
            submitButton.setOnAction(event -> {
                try {
                    loadNextQuestion();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void loadNextQuestion() throws IOException {
        // Increment the question index
        setQuestionIndex(quesIndex + 1);

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
            quizController.setScore(this.score);
            quizController.setQuesIndex(this.quesIndex);


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
