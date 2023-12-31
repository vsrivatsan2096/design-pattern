package design.pattern.creational;

import common.helper.cloud.*;
import common.helper.cloud.Instance.Capacity;
import common.helper.cloud.Storage.Size;

/**
 * {@link AbstractFactory} is used when we need to isolate the object creation with group of dependant objects
 * */
public interface AbstractFactory {}

abstract class ResourceAbstractFactory implements AbstractFactory {
    public abstract Instance createInstance(Capacity capacity);
    public abstract Storage createStorage(Size size);

    private static void createCloudServer(ResourceAbstractFactory resourceAbstractFactory,
                                          Capacity capacity, Size size) {
        Instance instance = resourceAbstractFactory.createInstance(capacity);
        Storage storage = resourceAbstractFactory.createStorage(size);

        instance.attach(storage);

        instance.start();
        instance.stop();
    }

    public static void main(String[] args) {
        createCloudServer(new AWSResourceFactory(), Capacity.MEDIUM, Size.FOUR_TERABYTES);
        createCloudServer(new GCPResourceFactory(), Capacity.LARGE, Size.EIGHT_TERABYTE);
    }
}

class GCPResourceFactory extends ResourceAbstractFactory {

    @Override
    public Instance createInstance(Capacity capacity) {
        return new ComputeInstance(capacity);
    }

    @Override
    public Storage createStorage(Size size) {
        return new CloudStorage(size);
    }
}

class AWSResourceFactory extends ResourceAbstractFactory {

    @Override
    public Instance createInstance(Capacity capacity) {
        return new EC2Instance(capacity);
    }

    @Override
    public Storage createStorage(Size size) {
        return new S3Storage(size);
    }
}