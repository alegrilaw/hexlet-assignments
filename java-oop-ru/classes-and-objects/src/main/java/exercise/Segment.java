package exercise;

// BEGIN
public class Segment {

    private Point beginPoint;
    private Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public void setBeginPoint(Point beginPoint) {
        this.beginPoint = beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Point getMidPoint() {
        var midPointX = (beginPoint.getX() + endPoint.getX()) / 2;
        var midPointY = (beginPoint.getY() + endPoint.getY()) / 2;
        return new Point(midPointX, midPointY);
    }
}
// END
