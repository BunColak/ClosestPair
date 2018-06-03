import java.util.Comparator;

public class PointComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        if (o1.getCoordinates()[0] < o2.getCoordinates()[0])
            return 1;
        else
            return 0;
    }
}
