package common.helper.cloud;

public class EC2Instance implements Instance {

    private Capacity capacity;
    private S3Storage storage;

    public EC2Instance(Capacity capacity) {
        this.capacity = capacity;
    }

    @Override
    public void start() {
        System.out.println("Amazon EC2 instance started");
    }

    @Override
    public void stop() {
        System.out.println("Amazon EC2 instance stopped");
    }

    @Override
    public void attach(Storage storage) {
        this.storage = (S3Storage) storage;

        System.out.println("Amazon S3 storage attached to amazon EC2 instance");
    }
}
