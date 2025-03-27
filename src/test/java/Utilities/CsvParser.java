package Utilities;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParser {
    public static List<Map<String, String>> parseCsv(String csvPath) throws IOException {

        // Arraylist
        // row converted to column:value

        List<Map<String, String>> records = new ArrayList<>();
        try (FileReader reader = new FileReader(csvPath);

             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : csvParser) {

                Map<String, String> row = new HashMap<>();
                record.toMap().forEach(row::put);
                records.add(row);
            }
        }
        return records;
    }
}
