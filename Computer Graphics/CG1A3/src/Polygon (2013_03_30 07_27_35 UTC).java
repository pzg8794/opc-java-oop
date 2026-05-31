//import java.awt.Point;
//
///*************************************************************************
// *  Compilation:  javac Polygon.java
// *  Execution:    java Polygon
// *
// *  An immutable datat type for polygons, possibly intersecting.
// *
// *  Centroid calculation assumes polygon is nonempty (o/w area = 0)
// *
// *************************************************************************/
//
//public class Polygon { 
//    private final int N;            // number of points in the polygon
//    private final Point[] points;   // the points, setting p[0] = p[N]
//   
//    // defensive copy
//    public Polygon(Point[] points) {
//        N = points.length;
//        this.points = new Point[N+1];
//        for (int i = 0; i < N; i++)
//            this.points[i] = points[i];
//        this.points[N] = points[0];
//    }
//
//    // read from input stream
//    public Polygon(In in) {
//        N = in.readInt();
//        points = new Point[N+1];
//        for (int i = 0; i < N; i++)
//           points[i] = new Point(in);
//        points[N] = points[0];
//    }
//
//
//    // return size
//    public int size() { return N; }
//
//    // draw outline of polygon
//    public void draw() {
//        double[] x = new double[N+1];
//        double[] y = new double[N+1];
//        for (int i = 0; i <= N; i++) x[i] = points[i].x();
//        for (int i = 0; i <= N; i++) y[i] = points[i].y();
//        StdDraw.polygon(x, y);
//    }
//
//    // filled polygon
//    public void fill() {
//        double[] x = new double[N+1];
//        double[] y = new double[N+1];
//        for (int i = 0; i <= N; i++) x[i] = points[i].x();
//        for (int i = 0; i <= N; i++) y[i] = points[i].y();
//        StdDraw.filledPolygon(x, y);
//    }
//
//    // return the bounding box
//    public BoundingBox box() {
//        return new BoundingBox(points);
//    }
//
//    // return a random point inside the polygon
//    public Point sample() {
//        BoundingBox box = this.box();
//        Point p;
//        do {
//            p = box.sample();
//        } while (!this.contains(p));
//        return p;
//    }
//
//    // return area of polygon
//    public double area() { return Math.abs(signedArea()); }
//
//    // return signed area of polygon
//    public double signedArea() {
//        double sum = 0.0;
//        for (int i = 0; i < N; i++) {
//            sum = sum + (points[i].x() * points[i+1].y()) - (points[i].y() * points[i+1].x());
//        }
//        return 0.5 * sum;
//    }
//
//    // are vertices in counterclockwise order?
//    // assumes polygon does not intersect itself
//    public boolean isCCW() {
//        return signedArea() > 0;
//    }
//
//    // return the centroid of the polygon
//    public Point centroid() {
//        double cx = 0.0, cy = 0.0;
//        for (int i = 0; i < N; i++) {
//            cx = cx + (points[i].x() + points[i+1].x()) * (points[i].y() * points[i+1].x() - points[i].x() * points[i+1].y());
//            cy = cy + (points[i].y() + points[i+1].y()) * (points[i].y() * points[i+1].x() - points[i].x() * points[i+1].y());
//        }
//        cx /= (6 * area());
//        cy /= (6 * area());
//        return new Point(cx, cy);
//    }
//
//
//    // does this Polygon contain the point p?
//    // if p is on boundary then 0 or 1 is returned, and p is in exactly one point of every partition of plane
//    // Reference: http://exaflop.org/docs/cgafaq/cga2.html
//    public boolean contains(Point p0) {
//        int crossings = 0;
//        for (int i = 0; i < N; i++) {
//            double slope = (points[i+1].x() - points[i].x())  / (points[i+1].y() - points[i].y());
//            boolean cond1 = (points[i].y() <= p0.y()) && (p0.y() < points[i+1].y());
//            boolean cond2 = (points[i+1].y() <= p0.y()) && (p0.y() < points[i].y());
//            boolean cond3 = p0.x() <  slope * (p0.y() - points[i].y()) + points[i].x();
//            if ((cond1 || cond2) && cond3) crossings++;
//        }
//        return (crossings % 2 != 0);
//    }
//
//    // return string representation of this polygon
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        s.append(N + "\n");
//        for (int i = 0; i < N; i++)
//            s.append(points[i] + "\n");
//        return s.toString();
//    }
//
//
//    // test client
//    public static void main(String[] args) {
//        int N = Integer.parseInt(args[0]);
//
//        // read polygon from stdin
//        In in = new In();
//        Polygon poly = new Polygon(in);
//        StdOut.println(poly);
//        StdOut.println(poly.box());
//        StdOut.println("area       = " + poly.area());
//        StdOut.println("centroid   = " + poly.centroid());
//
//        StdDraw.setXscale(-106.644936, -93.507957);
//        StdDraw.setYscale( +25.839756, +36.500549);
//
//        StdDraw.setPenColor(StdDraw.GRAY);
//        poly.fill();
//
//        // generate N random points in the polygon
//        for (int i = 0; i < N; i++) {
//            Point p = poly.sample();
//            if (StdRandom.bernoulli(.3)) StdDraw.setPenColor(StdDraw.WHITE);
//            else                         StdDraw.setPenColor(StdDraw.BLUE);
//            p.draw();
//        }
//    }
//}