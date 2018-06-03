public class Point {
    private int dimension;
    private double[] coordinates;
    private int index;

    public Point(double[] coordinates, int index) {
        this.coordinates = coordinates;
        this.index = index;
        dimension = coordinates.length;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public int getDimension() {
        return dimension;
    }

    public int getIndex() {
        return index;
    }

    public double findDistance(Point point2) {
        double distance = 0;
        for (int i = 0; i < dimension; i++) {
            distance += Math.pow(coordinates[i] - point2.getCoordinates()[i], 2);
        }

        return Math.sqrt(distance);
    }
}
