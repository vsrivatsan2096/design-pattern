package common.helper.file;

public abstract class File {
    private final String name;

    protected File(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    abstract public void ls();
}
