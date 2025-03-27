package Tests;

import Utilities.ColumnRule;
import Utilities.CsvParser;
import Utilities.YamlParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class CsvValidationTest {

    private static final Logger logger = Logger.getLogger(CsvValidationTest.class.getName());


    @Test
    public void testCsvValidation() throws IOException {

//        System.out.println("Current directory: " + new File(".").getAbsolutePath());

        String csvPath = "src/test/java/Resources/data.csv";
        String yamlPath = "src/test/java/Resources/rules.yaml";

        Map<String, ColumnRule> rules = YamlParser.parseYaml(yamlPath);
        List<Map<String, String>> csvData = CsvParser.parseCsv(csvPath);

        boolean allValid = true;


        for (String column : rules.keySet()) {
            ColumnRule rule = rules.get(column);
            Set<String> uniqueValues = new HashSet<>();

            for (int rowIndex = 0; rowIndex < csvData.size(); rowIndex++) {
                Map<String, String> row = csvData.get(rowIndex);
                String value = row.get(column);

                // Check for NON-NULL
                if (rule.isNonNull() && (value == null || value.trim().isEmpty())) {
                    logger.severe("Row " + (rowIndex + 1) + " - Column '"+ column + "' must be NON-NULL");
                    allValid = false;
                }


                if (rule.isUnique() && !uniqueValues.add(value)) {
                    logger.severe("Row " + (rowIndex + 1) + " - Column'" + column + "' must be UNIQUE");
                    allValid = false;
                }


                if (rule.isBooleanType() && !("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value))) {
                    logger.severe("Row " + (rowIndex + 1) + " - Column '" + column + "' must beBOOLEAN (true/false)");
                    allValid = false;
                }

                // Check for ALLOWED_VALUES
                if (rule.getAllowedValues() != null && !rule.getAllowedValues().contains(value)) {
                    logger.severe("Row " + (rowIndex + 1) + "- Column '" + column + "' has invalid value: " + value);
                    allValid = false;
                }

                // FIXED_LENGTH check
                if (rule.getFixedLength() != null && value.length() != rule.getFixedLength()) {
                    logger.severe("Row " + (rowIndex + 1) + " - Column '" + column + "' must have length: " + rule.getFixedLength());
                    allValid = false;
                }
            }
        }

        Assert.assertTrue(allValid, "File validation failed");
    }
}
