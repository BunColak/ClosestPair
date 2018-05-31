import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClosestPair {
    public static void main(String[] args) throws IOException {

        String filepath = args[0];
        double[][] points = getPoints(filepath);

        printToFile(findClosest(points));
    }

    /*
    Parses the file and transfers points into a 2D double array
     */
    public static double[][] getPoints(String filepath) throws IOException {
        // Get how many points there is
        Path path = Paths.get(filepath);
        int lineCount = (int) Files.lines(path).count();

        // Read the file
        BufferedReader in = new BufferedReader(new FileReader(filepath));

        // Go through all points
        String point = in.readLine();
        String[] point_indices = point.split("\\s");

        // Since it is not necessary to have x by 2 we read one line to see matrix size
        double[][] points = new double[lineCount][point_indices.length];

        // Set the initials
        for (int i = 0; i < point_indices.length; i++) {
            points[0][i] = Double.valueOf(point_indices[i]);
        }

        for (int i = 1; i < lineCount; i++) {
            // Go through all points
            point = in.readLine();
            point_indices = point.split("\\s");

            // Set the indices
            for (int j = 0; j < point_indices.length; j++) {
                points[i][j] = Double.valueOf(point_indices[j]);
            }
        }
        return points;
    }

    /*
    Finds the closest pair
     */
    public static String findClosest(double[][] points) {
        double min_distance = -1;
        double distance;
        String min_pair = "Error";
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                distance = euclidianDistance(points[i], points[j], points[i].length);
                if (distance < min_distance || min_distance == -1) {
                    min_distance = distance;
                    min_pair = printPair(points[i], points[j], points[i].length, i, j);
                }
            }
        }
        return min_pair;
    }

    /*
    Helper method, calculates the euclidian distance between two double arrays
     */
    public static double euclidianDistance(double[] point1, double[] point2, int dimension) {
        double distance = 0;
        for (int i = 0; i < dimension; i++) {
            distance += Math.pow(point1[i] - point2[i], 2);
        }

        return Math.sqrt(distance);
    }

    /*
    Helper method, constructs the String to be printed
     */
    public static String printPair(double[] point1, double[] point2, int dimension, int index1, int index2) {
        String pair = (index1 + 1) + ":";
        for (int i = 0; i < dimension; i++) {
            if (i != dimension-1)
                pair += Math.round(point1[i]) + "\t";
            else
                pair += Math.round(point1[i]);
        }

        pair += "\n" + (index2 + 1) + ":";

        for (int i = 0; i < dimension; i++) {
            if (i != dimension-1)
                pair += Math.round(point2[i]) + "\t";
            else
                pair += Math.round(point2[i]);        }
        return pair;

    }

    public static void printToFile(String text) throws IOException {
        Path path = Paths.get("output.txt");
        byte[] strToBytes = text.getBytes();

        Files.write(path, strToBytes);
    }
}
