package common.helper.unit;

public class Point3D {
    public int x;
    public int y;
    public int z;

    private Point3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Point3D getStartingPoint() {
        return new Point3D();
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }
}