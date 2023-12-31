package common.helper.cloud;

public class CloudStorage implements Storage {

    private Size size;

    public CloudStorage(Size size) {
        this.size = size;
    }
    @Override
    public String getId() {
        return "GCP" + "_" + "Cloud" + "_" + size.toString().toUpperCase();
    }
}
