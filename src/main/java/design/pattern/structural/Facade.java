package design.pattern.structural;

import common.helper.cloud.CloudStorage;
import common.helper.cloud.ComputeInstance;
import common.helper.cloud.Instance;
import common.helper.cloud.Storage;

/**
 * {@link Facade} is to create a simple use-case interface which can be directly used by the user without knowing/implementing
 *          all related classes needed for that use-case by the user.
 * */
public interface Facade {}

class GCPInstanceFacade implements Facade {
    public Instance createDefaultGCPInstance() {
        Storage storage = new CloudStorage(Storage.Size.FOUR_TERABYTES);

        Instance instance = new ComputeInstance(Instance.Capacity.MEDIUM);
        instance.attach(storage);

        instance.start();

        return instance;
    }

    public static void main(String[] args) {
        GCPInstanceFacade gcpInstanceFacade = new GCPInstanceFacade();

        Instance instance = gcpInstanceFacade.createDefaultGCPInstance();

        System.out.println(instance);
    }
}
