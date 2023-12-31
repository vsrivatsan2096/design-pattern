package design.pattern.creational;

import common.helper.image.Bitmap;
import common.helper.image.Image;
import common.helper.unit.Point3D;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

/**
 * {@link ObjectPool} is used when the object creation is costly, so we have already created
 *          a pool of objects which can be reused
 * */
public interface ObjectPool {}

class ImageObjectPool<T extends Image> implements ObjectPool {
    private final BlockingQueue<T> availableObjects;

    public ImageObjectPool(Supplier<T> supplier, int counts) {
        availableObjects = new LinkedBlockingQueue<>();

        this.initializeObjectPools(supplier, counts);
    }

    private void initializeObjectPools(Supplier<T> supplier, int counts) {
        for (int i = 0; i < counts; i ++) {
            availableObjects.add(supplier.get());
        }
    }

    public T get() {
        try {
            return availableObjects.take();
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        return null;
    }

    public void release(T image) {
        image.reset();

        this.availableObjects.add(image);
    }
}

class BitmapClient {
    private static ImageObjectPool<Bitmap> bitmapImageObjectPool = new ImageObjectPool<>(()-> new Bitmap("/temp/test.bmp"), 2);

    public static void main(String[] args) {
        System.out.println("Started");

        Bitmap bitmap1 = bitmapImageObjectPool.get();
        Bitmap bitmap2 = bitmapImageObjectPool.get();

        bitmap1.setPoint3D(new Point3D(1, 1, 1));
        bitmap2.setPoint3D(new Point3D(2, 2, 2));

        System.out.println(bitmap1);
        System.out.println(bitmap2);

        bitmapImageObjectPool.release(bitmap1);

        Bitmap bitmap3 = bitmapImageObjectPool.get();

        System.out.println(bitmap3);

        bitmapImageObjectPool.release(bitmap2);
        bitmapImageObjectPool.release(bitmap3);

        System.out.println("Stopped");
    }
}