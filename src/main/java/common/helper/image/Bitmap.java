package common.helper.image;

import common.helper.unit.Point3D;

public class Bitmap implements Image {
    private final String fileName;
    private Point3D point3D;

    public Bitmap(String fileName) {
        System.out.println("Loading the bitmap image from file : " + fileName);
        this.fileName = fileName;
    }

    @Override
    public void reset() {
        point3D = Point3D.getStartingPoint();
    }

    @Override
    public void setPoint3D(Point3D point3D) {
        this.point3D = point3D;
    }

    @Override
    public Point3D getPoint3D() {
        return this.point3D;
    }

    @Override
    public void render() {
        System.out.println("Rendering " + this.fileName + " @ " + this.point3D);
    }

    @Override
    public String toString() {
        return fileName + "@" + point3D;
    }
}
