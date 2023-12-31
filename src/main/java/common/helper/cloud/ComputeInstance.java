package common.helper.cloud;

public class ComputeInstance implements Instance {

    private Capacity capacity;
    private CloudStorage storage;

    public ComputeInstance(Capacity capacity) {
        this.capacity = capacity;
    }

    @Override
    public void start() {
        System.out.println("Google compute instance started");
    }

    @Override
    public void stop() {
        System.out.println("Google compute instance stopped");
    }

    @Override
    public void attach(Storage storage) {
        this.storage = (CloudStorage) storage;

        System.out.println("Google cloud storage attached to google compute instance");
    }
}
