package common.helper.cloud;

public class S3Storage implements Storage {

    private final Size size;

    public S3Storage(Size size) {
        this.size = size;
    }
    @Override
    public String getId() {
        return "AWS" + "_" + "S3" + "_" + size.toString().toUpperCase();
    }
}
