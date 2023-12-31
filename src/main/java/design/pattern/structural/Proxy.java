package design.pattern.structural;

import common.helper.image.Bitmap;
import common.helper.image.Image;
import common.helper.unit.Point3D;

/**
 * {@link Proxy} is used to act as a stand-in instance for the real instance.
 * */
public interface Proxy {}

class BitmapImageStaticProxy implements Image, Proxy {

    private Bitmap bitmapImage;

    private Point3D point3D;

    private final String name;

    public BitmapImageStaticProxy(String name) {
        System.out.println("Creating proxy for bitmap image");
        this.name = name;
    }

    @Override
    public void reset() {
        if (bitmapImage == null) {
            bitmapImage = new Bitmap(name);
            bitmapImage.setPoint3D(point3D);
        }

        bitmapImage.reset();
    }

    @Override
    public void setPoint3D(Point3D point3D) {
        if (bitmapImage != null) {
            bitmapImage.setPoint3D(point3D);
        }

        this.point3D = point3D;
    }

    @Override
    public Point3D getPoint3D() {
        if (bitmapImage != null) {
            return bitmapImage.getPoint3D();
        }

        return point3D;
    }

    @Override
    public void render() {
        if (bitmapImage == null) {
            bitmapImage = new Bitmap(name);
            bitmapImage.setPoint3D(point3D);
        }

        bitmapImage.render();
    }
}

class ImageFactory {
    public static Image getImage(String imageType, String name) {
        if (imageType.equals("Bitmap")) {
            return new BitmapImageStaticProxy(name);
        }

        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        Image image = ImageFactory.getImage("Bitmap", "profile-photo.png");

        image.setPoint3D(new Point3D(1, 5, -4));
        image.render();

        image.reset();

        image.render();
    }
}
