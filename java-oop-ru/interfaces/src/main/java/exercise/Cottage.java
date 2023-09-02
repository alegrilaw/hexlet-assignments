package exercise;

import java.util.Locale;

// BEGIN
public class Cottage implements Home {

    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        var anotherArea = another.getArea();
        return Double.compare(getArea(), anotherArea);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%d этажный коттедж площадью %.1f метров", floorCount, area);
    }
}
// END
