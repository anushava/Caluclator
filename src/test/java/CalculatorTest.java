import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalculatorTest {
    static final String PATH = "src/test/data/";

    public static Map<String, String> getDataFromFile(String fileName) {
        //Initialize the map for holding the prefix as key and price as value
        Map<String, String> expressionsMap = new HashMap<>();
        try {
            //Read the operator file
            BufferedReader operator_reader = new BufferedReader(new FileReader(fileName));
            String line;
            //Loop the file lines until not null
            while ((line = operator_reader.readLine()) != null) {
                //Get the values separated by comma and put it to string array
                String[] parts = line.split(",", 2);
                //validate if array have 2 element
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    //Store the key value pair in to map
                    expressionsMap.put(key, value);
                } else {
                    System.out.println("ignoring line: " + line);
                }
            }
            // Close the file stream
            operator_reader.close();
        } catch (IOException e) {
            System.out.println("ERROR : " + e);
        }
        return expressionsMap;
    }


    @Test
    public void calculateIntegerExpressionTest() throws Exception {
        Calculator calculator = new Calculator();
        Map<String, String> expressionsMap = new HashMap<>();
        expressionsMap = getDataFromFile(PATH + "sample_data_1.csv");
        expressionsMap.forEach((key, value) -> {
            try {
                System.out.println("Test with expression : " + key);
                System.out.println("Expected Answer : " + Math.round(Double.parseDouble(value)));
                System.out.println("Actual Answer : " + Math.round(calculator.compute(key)));
                Assertions.assertEquals(Math.round(Double.parseDouble(value)), Math.round(calculator.compute(key)));
                System.out.println("\n");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void calculateWithAllOperatorsTest() throws Exception {
        Calculator calculator = new Calculator();
        Map<String, String> expressionsMap = new HashMap<>();
        expressionsMap = getDataFromFile(PATH + "sample_data_2.csv");
        expressionsMap.forEach((key, value) -> {
            try {
                System.out.println("Test with expression : " + key);
                System.out.println("Expected Answer : " + Math.round(Double.parseDouble(value)));
                System.out.println("Actual Answer : " + Math.round(calculator.compute(key)));
                Assertions.assertEquals(Math.round(Double.parseDouble(value)), Math.round(calculator.compute(key)));
                System.out.println("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void calculateWithPositiveAndNegativeIntsTest() throws Exception {
        Calculator calculator = new Calculator();
        Map<String, String> expressionsMap = new HashMap<>();
        expressionsMap = getDataFromFile(PATH + "sample_data_3.csv");
        expressionsMap.forEach((key, value) -> {
            try {
                System.out.println("Test with expression : " + key);
                System.out.println("Expected Answer : " + Math.round(Double.parseDouble(value)));
                System.out.println("Actual Answer : " + Math.round(calculator.compute(key)));
                Assertions.assertEquals(Math.round(Double.parseDouble(value)), Math.round(calculator.compute(key)));
                System.out.println("\n");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
