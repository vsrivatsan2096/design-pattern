package common.helper.cloud;

public interface Instance {
    enum Capacity{ MICRO, SMALL, MEDIUM, LARGE };

    void start();

    void stop();

    void attach(Storage storage);
}
