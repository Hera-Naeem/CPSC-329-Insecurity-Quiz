package org.insecurity_quiz.Quiz_Data_Management;


import org.insecurity_quiz.Quiz_Data_Management.Question_Types.MultipleChoiceQuestion;
import org.insecurity_quiz.Quiz_Data_Management.Question_Types.NumericalQuestion;
import org.insecurity_quiz.Quiz_Data_Management.Question_Types.SelectCorrectQuestion;
import org.insecurity_quiz.Quiz_Data_Management.Question_Types.ShortAnswerQuestion;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class QuestionLoader {
    static final String TYPE_COLUMN = "Type";
    static final String QUESTION_COLUMN = "Question";

    static final String QUESTION_OPTIONS = "Options";
    static final String QUESTION_HINT_COLUMN = "Question Hint";
    static final String ANSWER_COLUMN = "Answer";
    static final String ANSWER_FOLLOWUP_COLUMN = "Answer Followup";

    Table questionData;

    /*
    Public facing methods
     */

    public QuestionLoader(String questionFile) throws IOException {
        ColumnType[] types = {ColumnType.STRING, ColumnType.STRING,ColumnType.STRING, ColumnType.STRING, ColumnType.STRING, ColumnType.STRING};
        CsvReadOptions.Builder builder = CsvReadOptions.builder(questionFile).columnTypes(types);
        CsvReadOptions options = builder.build();

        questionData = Table.read().csv(options);
    }

    public int getNumQuestions() {
        return questionData.rowCount();
    }

    //Current behavior: If numQuestions > numRows, return all the questions, not more.
    public Question[] getRandomQuestions(int numQuestions) {
        int numRows = getNumQuestions();
        int[] shuffledRows = randomRowIndexes(numRows);
        numQuestions = min(numRows, numQuestions);

        Question[] questions = new Question[numQuestions];

        for (int i = 0; i < numQuestions; i++) {
            questions[i] = makeQuestion(shuffledRows[i]);
        }
        return questions;
    }

    /*
    Internal methods
     */
    //Todo: This should be refactored into a Question class, as this is a factory method for the questions.
    protected static Question CreateQuestion(String type, String question, String options, String questionHint, String answer, String answerFollowup) {
        Question questionObj = null;
        if (type.equals(Question.QuestionTypes.MultipleChoice.toString())) {
            questionObj = new MultipleChoiceQuestion(question, options, questionHint, answer, answerFollowup);
        }
        else if (type.equals(Question.QuestionTypes.Numerical.toString())) {
            questionObj = new NumericalQuestion(question, questionHint, answer, answerFollowup);
        }
        else if (type.equals(Question.QuestionTypes.SelectCorrect.toString())) {
            questionObj = new SelectCorrectQuestion(question, options, questionHint, answer, answerFollowup);
        }
        else if (type.equals(Question.QuestionTypes.ShortAnswer.toString())) {
            questionObj = new ShortAnswerQuestion(question, questionHint, answer, answerFollowup);
        }

        return questionObj;
    }
    //Given an index, makes a Question object from the data in row i. Does not validate, should be done by invoking method.
    protected Question makeQuestion(int i) {
        String type = questionData.stringColumn(TYPE_COLUMN).get(i);
        String question = questionData.stringColumn(QUESTION_COLUMN).get(i);
        String options = questionData.stringColumn(QUESTION_OPTIONS).get(i);
        String questionHint = questionData.stringColumn(QUESTION_HINT_COLUMN).get(i);
        String answer = questionData.stringColumn(ANSWER_COLUMN).get(i);
        String answerFollowup = questionData.stringColumn(ANSWER_FOLLOWUP_COLUMN).get(i);

        return CreateQuestion(type, question, options, questionHint, answer, answerFollowup);
    }
    //Given a number of rows, returns an array containing the numbers from zero to that number, shuffled.
    //Todo: Refactor to a utility class?
    protected int[] randomRowIndexes(int numRows) {
        int[] numArray = new int[numRows];
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = i;
        }
        Collections.shuffle(Arrays.asList(numArray));

        return numArray;
    }
}
