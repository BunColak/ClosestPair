import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    @Test
    void doesNotReadEmptyFiles() {
        String filepath = "TestFiles/empty_file.txt";
        assertEquals(-1, ClosestPair.validateInput(filepath));
    }

    @Test
    void realizesNonExistentFile() {
        String filepath = "there_is_no_such_file.txt";
        assertEquals(-2, ClosestPair.validateInput(filepath));
    }

    @Test
    void sampleOutputsAreCorrect() throws IOException {
        // Sample I/O 1
        String[] args = {"TestFiles/sample_input_2_8.tsv"};
        ClosestPair.main(args);

        String sampleOutput = "TestFiles/sample_output_2_8.txt";
        BufferedReader inActual = new BufferedReader(new FileReader("TestFiles/sample_input_2_8_output.txt"));
        BufferedReader inExpected = new BufferedReader(new FileReader(sampleOutput));

        String pointActual = inActual.readLine();
        String pointExpected = inExpected.readLine();

        boolean error = false;
        while (pointExpected != null) {
            if (!pointActual.equals(pointExpected))
                error = true;

            pointExpected = inExpected.readLine();
            pointActual = inActual.readLine();
        }

        assertFalse(error);

        // Sample I/O 2
        args = new String[]{"TestFiles/sample_input_3_1000.tsv"};
        ClosestPair.main(args);

        sampleOutput = "TestFiles/sample_output_3_1000.txt";
        inActual = new BufferedReader(new FileReader("TestFiles/sample_input_3_1000_output.txt"));
        inExpected = new BufferedReader(new FileReader(sampleOutput));

        pointActual = inActual.readLine();
        pointExpected = inExpected.readLine();

        error = false;
        while (pointExpected != null) {
            if (!pointActual.equals(pointExpected))
                error = true;

            pointExpected = inExpected.readLine();
            pointActual = inActual.readLine();
        }

        assertFalse(error);

        // Sample I/O 4
        args = new String[]{"TestFiles/sample_input_10_100.tsv"};
        ClosestPair.main(args);

        sampleOutput = "TestFiles/sample_output_10_100.txt";
        inActual = new BufferedReader(new FileReader("TestFiles/sample_input_10_100_output.txt"));
        inExpected = new BufferedReader(new FileReader(sampleOutput));

        pointActual = inActual.readLine();
        pointExpected = inExpected.readLine();

        error = false;
        while (pointExpected != null) {
            if (!pointActual.equals(pointExpected))
                error = true;

            pointExpected = inExpected.readLine();
            pointActual = inActual.readLine();
        }

        assertFalse(error);

        args = new String[]{"TestFiles/another_test.txt"};
        ClosestPair.main(args);

        sampleOutput = "TestFiles/another_test_output_1.txt";
        inActual = new BufferedReader(new FileReader("TestFiles/another_test_output.txt"));
        inExpected = new BufferedReader(new FileReader(sampleOutput));

        pointActual = inActual.readLine();
        pointExpected = inExpected.readLine();

        error = false;
        while (pointExpected != null) {
            if (!pointActual.equals(pointExpected))
                error = true;

            pointExpected = inExpected.readLine();
            pointActual = inActual.readLine();
        }

        assertFalse(error);
    }

}