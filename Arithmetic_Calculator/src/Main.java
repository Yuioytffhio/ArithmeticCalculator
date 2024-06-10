import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Main class for evaluating arithmetic expressions from an input file and writing the results to an output file
 */
public class Main {
    public static void main(String[] args) {
        String inputFilePath = "src/InputFile.txt";
        String outputFilePath = "src/out.txt";

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
        ) {
            // Read each line from the input file
            String expression;
            while ((expression = reader.readLine()) != null) {
                // Evaluate the expression and write the result to the output file
                int result = ArithmeticCalculator.arithmeticCalculator(expression);
                writer.write(expression + ", Result: " + result + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}