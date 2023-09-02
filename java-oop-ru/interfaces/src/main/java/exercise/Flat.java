package exercise;

import java.util.Locale;

// BEGIN
public class Flat implements Home {

    private double area;
    private double balconyArea;
    private  int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home another) {
        var anotherArea = another.getArea();
        return Double.compare(getArea(), anotherArea);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "Квартира площадью %.1f метров на %d этаже", area + balconyArea, floor);
    }
}
// END
