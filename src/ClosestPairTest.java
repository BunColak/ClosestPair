import com.sun.tools.javac.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    @Test
    void readsFiles() throws IOException {
        String filepath = "sample_input_2_8.tsv";
        assertNotNull(ClosestPair.getPoints(filepath));
    }

    @Test
    void calculateDistance() {
        double[] point1 = {0, 0};
        double[] point2 = {3, 4};

        assertEquals(5, ClosestPair.euclidianDistance(point1, point2, 2));
    }

    @Test
    void printedFileIsCorrect() throws IOException {
        // Sample I/O 1
        String[] args = {"sample_input_2_8.tsv"};
        ClosestPair.main(args);

        String sampleOutput = "sample_output_2_8.txt";
        BufferedReader inActual = new BufferedReader(new FileReader("output.txt"));
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
        args = new String[]{"sample_input_3_1000.tsv"};
        ClosestPair.main(args);

        sampleOutput = "sample_output_3_1000.txt";
        inActual = new BufferedReader(new FileReader("output.txt"));
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


        // Sample I/O 3
        args = new String[]{"sample_input_4_4.tsv"};
        ClosestPair.main(args);

        sampleOutput = "sample_output_4_4.txt";
        inActual = new BufferedReader(new FileReader("output.txt"));
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

        assertTrue(error);


        // Sample I/O 4
        args = new String[]{"sample_input_10_100.tsv"};
        ClosestPair.main(args);

        sampleOutput = "sample_output_10_100.txt";
        inActual = new BufferedReader(new FileReader("output.txt"));
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

        // Sample I/O 5
        args = new String[]{"sample_input_100_100.tsv"};
        ClosestPair.main(args);

        sampleOutput = "sample_output_100_100.txt";
        inActual = new BufferedReader(new FileReader("output.txt"));
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