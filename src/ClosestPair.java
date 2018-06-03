import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClosestPair {
    static int lineCount = 0;
    static BufferedReader in;
    static Point[] points;
    static String min_pair;
    static double TOLERANCE = 0.0000000000000000000001;
    static double min_distance = 0;
    static int error_code = 0;

    public static void main(String[] args) throws IOException {

        String filepath = args[0];
        error_code = validateInput(filepath);
        switch (error_code) {

            case 0:
                System.out.println("File is not corrupt.\n");

                getPoints();
                System.out.println("Points have been acquired.\n");

                findClosestRL(points);

                String output_path = filepath.substring(0, filepath.length() - 4) + "_output.txt";
                printToFile(min_pair, output_path);
                System.out.println("Output file has been generated: " + output_path);
                break;
            case -1:
                System.out.println("File is empty!");
                System.exit(0);
                break;
            case -2:
                System.out.println("File not found or cannot be read.");
                System.exit(0);
                break;
            default:
                System.out.println("Unexpected Error!");
                System.exit(0);
                break;
        }

    }

    /*
    Input validation
     */
    public static int validateInput(String filepath) {
        System.out.println("Starting input validation...");
        Path path = Paths.get(filepath);
        try {
            lineCount = (int) Files.lines(path).count();
            in = new BufferedReader(new FileReader(filepath));
        } catch (IOException e) {
            return -2;
        }

        if (lineCount == 0) {
            return -1;
        }

        return 0;
    }

    /*
    Parses the file and transfers points into a 2D double array
     */
    public static Point[] getPoints() {
        points = new Point[lineCount];

        String point_raw = "";
        double[] point_coordinates = null;
        Scanner scanner;
        int dimension = 0;

        try {
            point_raw = in.readLine();
        } catch (IOException e) {
            System.out.println("Line could not be read.");
            e.printStackTrace();
            System.exit(0);
        }
        scanner = new Scanner(point_raw);
        scanner.useDelimiter("\\t");

        while (scanner.hasNext()) {
            dimension++;
            double[] temp_coordinates = point_coordinates;
            point_coordinates = new double[dimension];

            // Except the initial condition, copy existing array
            if (temp_coordinates != null)
                System.arraycopy(temp_coordinates, 0, point_coordinates, 0, dimension - 1);

            try {
                point_coordinates[dimension - 1] = Double.valueOf(scanner.next());
            } catch (IllegalArgumentException e) {
                error_code = -4;
                System.out.println("Unknown character in the input file. Exiting.");
                System.exit(0);
            }
        }

        try {
            points[0] = new Point(point_coordinates, 0);
        } catch (NullPointerException e) {
            System.out.println("Illegal expression in the input file!");
            e.printStackTrace();
            System.exit(0);
        }


        for (int i = 1; i < lineCount; i++) {
            try {
                point_raw = in.readLine();
            } catch (IOException e) {
                System.out.println("Line could not be read.");
                e.printStackTrace();
                System.exit(0);
            }
            scanner = new Scanner(point_raw);
            scanner.useDelimiter("\\t");
            point_coordinates = new double[dimension];
            for (int j = 0; j < dimension; j++) {
                try {
                    point_coordinates[j] = Double.valueOf(scanner.next());
                } catch (Exception e) {
                    System.out.println("Unknown or missing value in the input file. Exiting.");
                    System.exit(0);
                }
            }
            points[i] = new Point(point_coordinates, i);
        }
        Arrays.sort(points, new PointComparator());
        return points;
    }

    /*
    Finds the closest pair
     */
    public static double findClosestRL(Point[] points) {

        // Baseline
        if (points.length == 2) {
            if (points[0].findDistance(points[1]) < min_distance || min_distance == 0) {
                min_distance = points[0].findDistance(points[1]);
                min_pair = printPair(points[0], points[1]);
            }

            return points[0].findDistance(points[1]);
        } else if (points.length == 3) {
            double dmin = Math.min(points[0].findDistance(points[1]), points[0].findDistance(points[2]));
            dmin = Math.min(dmin, points[1].findDistance(points[2]));

            if (dmin < min_distance || min_distance == 0) {
                if (Math.abs(dmin - points[0].findDistance(points[1])) < TOLERANCE) {
                    min_pair = printPair(points[0], points[1]);
                } else if (Math.abs(dmin - points[0].findDistance(points[2])) < TOLERANCE) {
                    min_pair = printPair(points[0], points[2]);
                } else if (Math.abs(dmin - points[1].findDistance(points[2])) < TOLERANCE) {
                    min_pair = printPair(points[1], points[2]);
                }
                min_distance = dmin;
            }

            return dmin;
        }

        Point[] firstHalf;
        Point[] secondHalf;
        int array_length = points.length;

        if (array_length % 2 == 0) {
            firstHalf = new Point[array_length / 2];
            secondHalf = new Point[array_length / 2];
        } else {
            firstHalf = new Point[array_length / 2];
            secondHalf = new Point[array_length / 2 + 1];
        }


        for (int i = 0; i < firstHalf.length; i++) {
            firstHalf[i] = points[i];
        }

        for (int i = 0; i < secondHalf.length; i++) {
            secondHalf[i] = points[i + firstHalf.length];
        }

        double d2 = findClosestRL(secondHalf);
        double d1 = findClosestRL(firstHalf);
        double d = Math.min(d1, d2);


        for (Point point1 : firstHalf) {
            for (Point point2 : secondHalf) {
                d = Math.min(point1.findDistance(point2), d);
                if (Math.abs(d - point1.findDistance(point2)) < TOLERANCE) {
                    min_pair = printPair(point1, point2);
                }
            }
        }

        return d;
    }

    /*
    Helper method, constructs the String to be printed
     */
    public static String printPair(Point point1, Point point2) {
        String pair = (point1.getIndex() + 1) + ":";
        int dimension = point1.getDimension();
        for (int i = 0; i < dimension; i++) {
            if (i != dimension - 1)
                pair += Math.round(point1.getCoordinates()[i]) + "\t";
            else
                pair += Math.round(point1.getCoordinates()[i]);
        }

        pair += "\n" + (point2.getIndex() + 1) + ":";

        for (int i = 0; i < dimension; i++) {
            if (i != dimension - 1)
                pair += Math.round(point2.getCoordinates()[i]) + "\t";
            else
                pair += Math.round(point2.getCoordinates()[i]);
        }
        return pair;

    }

    public static void printToFile(String text, String filepath) throws IOException {
        Path path = Paths.get(filepath);
        byte[] strToBytes = text.getBytes();

        Files.write(path, strToBytes);
    }
}