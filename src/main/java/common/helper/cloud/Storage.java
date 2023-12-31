package common.helper.cloud;

public interface Storage {
    enum Size{TWO_TERABYTES, FOUR_TERABYTES, EIGHT_TERABYTE};

    String getId();
}
