package design.pattern.behavioural;

import common.helper.cloud.EC2Instance;
import common.helper.cloud.Instance;
import common.helper.cloud.Instance.Capacity;
import common.helper.cloud.S3Storage;
import common.helper.cloud.Storage;
import common.helper.cloud.Storage.Size;

/**
 * {@link Command} will be used to replace a method with an object and parameters by the object fields so that we can
 *                  handle them in after some time or make another thread execute it.
 * */
public interface Command {
    void execute();
}

class AWSResourceBL {
    public Instance createInstance(Capacity capacity) {
        Instance instance = new EC2Instance(capacity);
        instance.start();

        return instance;
    }

    public void attachStorage(Instance instance, Size size) {
        Storage storage = new S3Storage(size);

        instance.attach(storage);
    }
}

class CreateAWSResource implements Command {
    private final Capacity instanceCapacity;
    private final Size storageSize;
    private final AWSResourceBL awsResourceBL;

    public CreateAWSResource(Capacity instanceCapacity, Size storageSize, AWSResourceBL awsResourceBL) {
        this.instanceCapacity = instanceCapacity;
        this.storageSize = storageSize;
        this.awsResourceBL = awsResourceBL;
    }

    @Override
    public void execute() {
        Instance instance = awsResourceBL.createInstance(instanceCapacity);
        awsResourceBL.attachStorage(instance, storageSize);
    }

    public static void main(String[] args) {
        AWSResourceBL resourceBL = new AWSResourceBL();

        Command createAWSResource = new CreateAWSResource(Capacity.MICRO, Size.TWO_TERABYTES, resourceBL);

        // Above instance of command can be executed in different thread also
        createAWSResource.execute();
    }
}