package design.pattern.structural;

import common.helper.file.File;

import java.util.LinkedList;
import java.util.List;

/**
 * {@link Composite} is used to handle the parent node and leaf node as same for the client
 * */
public interface Composite {}

class TextFile extends File {
    private final Double size;

    public TextFile(String name, Double size) {
        super(name);
        this.size = size;
    }

    public Double getSize() {
        return this.size;
    }

    @Override
    public void ls() {
        System.out.println(getName() + "    " + getSize() + " Bytes");
    }
}

class Folder extends File implements Composite {
    private final List<File> files;

    public Folder(String name) {
        super(name);
        this.files = new LinkedList<>();
    }

    @Override
    public void ls() {
        System.out.println(this.getName());
        this.files.forEach(File::ls);
    }

    public void addFile(File file) {
        this.files.add(file);
    }

    public void removeFile(File file) {
        this.files.remove(file);
    }

    public static void main(String[] args) {
        TextFile textFile0 = new TextFile("file0-0", 0.0);
        TextFile textFile1 = new TextFile("file0-1", 1000.0);
        TextFile textFile2 = new TextFile("file0-2", 2000.0);

        TextFile textFile3 = new TextFile("file1-0", 0.0);
        TextFile textFile4 = new TextFile("file1-1", 1000.0);

        TextFile textFile5 = new TextFile("file2-0", 2000.0);

        Folder folder0 = new Folder("folder0");
        folder0.addFile(textFile0);
        folder0.addFile(textFile1);
        folder0.addFile(textFile2);

        Folder folder1 = new Folder("folder1");
        folder1.addFile(textFile3);
        folder1.addFile(textFile4);

        Folder folder2 = new Folder("folder2");
        folder2.addFile(textFile5);

        Folder rootFolder = new Folder("root");
        rootFolder.addFile(folder0);
        rootFolder.addFile(folder1);
        rootFolder.addFile(folder2);

        rootFolder.ls();

        System.out.println("------------------------------------------------------------");

        folder0.ls();

        System.out.println("------------------------------------------------------------");

        textFile0.ls();
    }
}
