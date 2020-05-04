import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import models.InputCsvLine;
import models.OutputCsvLine;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Parser {

    public static void main(String[] args) throws Exception {
//        if(args.length < 2)
//            throw new Exception("You could define two arguments");

        char SEPARATOR = ';';

        String spendsFile = "D:\\z\\spend.csv", debsFile = "D:\\z\\debts.csv", incomesFile = "D:\\z\\incomes.csv", transfersFile = "D:\\z\\transfers.csv";

        List<OutputCsvLine> debts = new ArrayList<>(), spends = new ArrayList<>(), incomes = new ArrayList<>(), tranfers = new ArrayList<>();



        var inputFilename = "D:\\d.csv" ; // args[0];
//        var outputDir = args[1];

        var parser = new CSVParserBuilder().build();


        try (FileInputStream fis = new FileInputStream(inputFilename) )
        {
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            CsvToBean<InputCsvLine> reader = new CsvToBeanBuilder<InputCsvLine>(isr).withType(InputCsvLine.class).withSkipLines(3).withSeparator(SEPARATOR).build();

            reader.stream().parallel().forEach(input -> {
                if (input.getSourceName().equalsIgnoreCase("Долг") || input.getSourceName().equalsIgnoreCase("Долги Евро")
                ) {
                    OutputCsvLine output = new OutputCsvLine(input);
                    output.setSourceName("Долги");
                    debts.add(new OutputCsvLine(input));
                } else {
                if (input.getDestination().equalsIgnoreCase("Долг") || input.getDestination().equalsIgnoreCase("Долги Евро")
                ) {
                    OutputCsvLine output = new OutputCsvLine(input);
                    output.setDestination("Долги");
                    debts.add(new OutputCsvLine(input));
                } else {
                    if (input.getSourceName().equalsIgnoreCase("Зарплата") || input.getSourceName().equalsIgnoreCase("Левак") || input.getSourceName().equalsIgnoreCase("Проценты")) {
                        incomes.add(new OutputCsvLine(input));
                    } else {
                        if (input.getOperationType().equalsIgnoreCase("Расход")) {
                            spends.add(new OutputCsvLine(input));
                        } else {
                            tranfers.add(new OutputCsvLine(input));
                        }
                    }
                }
            }
            });

            writeCsv(debsFile, debts);
            writeCsv(incomesFile, incomes);
            writeCsv(spendsFile, spends);
            writeCsv(transfersFile, tranfers);


        }

    }

    private static void writeCsv(String filename, List<OutputCsvLine> beans) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try(Writer writer = new FileWriter(filename)) {
            StatefulBeanToCsv<OutputCsvLine> debsBeanToCsv = new StatefulBeanToCsvBuilder<OutputCsvLine>(writer).build();
            debsBeanToCsv.write(beans);
        }
    }
}
