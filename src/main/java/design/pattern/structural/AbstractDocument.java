package design.pattern.structural;

import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * {@link AbstractDocument} aims to provide a consistent way to handle hierarchical and tree-like data structure
 *          without losing the type safety of the language
 * */
public interface AbstractDocument {

    void put(FileProperties key, Object value);

    Object get(FileProperties key);

    List<AbstractDocument> children(FileProperties key);
}

enum FileProperties {DIMENSION, SECONDS, FILES};

interface HasDimension extends AbstractDocument {
    default Integer getDimension() {
        return (Integer) get(FileProperties.DIMENSION);
    }
}

interface HasTime extends AbstractDocument {
    default Integer getSeconds() {
        return (Integer) get(FileProperties.SECONDS);
    }
}

interface HasFiles extends AbstractDocument {
    default List<AbstractDocument> getFiles() {
        return children(FileProperties.FILES);
    }
}

abstract class File implements AbstractDocument {

    private final Map<FileProperties, Object> properties;

    public File(Map<FileProperties, Object> properties) {
        Objects.requireNonNull(properties, "properties is null");
        this.properties = properties;
    }

    @Override
    public void put(FileProperties key, Object value) {
        properties.put(key, value);
    }

    @Override
    public Object get(FileProperties key) {
        return properties.get(key);
    }

    @Override
    public List<AbstractDocument> children(FileProperties key) {
        return (List<AbstractDocument>) properties.get(key);
    }

    public static void main(String[] args) {
        JPEGImageFile jpegImageFile1 = new JPEGImageFile(Map.of(
                FileProperties.DIMENSION, 1920 * 1080
        ));

        MP4VideoFile mp4VideoFile1 = new MP4VideoFile(Map.of(
                FileProperties.DIMENSION, 1366 * 768,
                FileProperties.SECONDS, 5 * 60
        ));

        FolderFile folderFile1 = new FolderFile(Map.of(
                FileProperties.FILES, List.of(
                        jpegImageFile1,
                        mp4VideoFile1
                )
        ));

        MP4VideoFile mp4VideoFile2 = new MP4VideoFile(Map.of(
                FileProperties.DIMENSION, 1920 * 1080,
                FileProperties.SECONDS, 10 * 60
        ));

        FolderFile folderFile2 = new FolderFile(Map.of(
                FileProperties.FILES, List.of(
                        mp4VideoFile2
                )
        ));

        FolderFile folderFile3 = new FolderFile(Map.of(
                FileProperties.FILES, List.of(
                        folderFile1,
                        folderFile2
                )
        ));

        System.out.println(folderFile3);
    }
}

class JPEGImageFile extends File implements HasDimension {

    public JPEGImageFile(Map<FileProperties, Object> properties) {
        super(properties);
    }

    @Override
    public String toString() {
        return "JPEG File - " + getDimension() + " KB";
    }
}

class MP4VideoFile extends File implements HasTime, HasDimension {

    public MP4VideoFile(Map<FileProperties, Object> properties) {
        super(properties);
    }

    @Override
    public String toString() {
        return "MP4 File - " + getDimension() + " KB" + " and " + getSeconds() + " seconds";
    }
}

class FolderFile extends File implements HasFiles {

    public FolderFile(Map<FileProperties, Object> properties) {
        super(properties);
    }

    @Override
    public String toString() {
        return "Folder - " + children(FileProperties.FILES).toString();
    }
}