package org.insecurity_quiz.Quiz_Data_Management;


import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.IOException;

public class QuestionLoader {
    /*
    Public facing methods
     */

    public QuestionLoader(String questionFile) throws IOException {
        ColumnType[] types = {ColumnType.STRING, ColumnType.STRING,ColumnType.STRING, ColumnType.STRING, ColumnType.STRING};
        CsvReadOptions.Builder builder = CsvReadOptions.builder(questionFile).columnTypes(types);
        CsvReadOptions options = builder.build();

        Table questionData = Table.read().csv(options);
    }

    /*
    Internal methods
     */
}
