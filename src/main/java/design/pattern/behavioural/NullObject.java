package design.pattern.behavioural;

import common.helper.storage.FileStorage;
import common.helper.storage.Storage;

/**
 * {@link NullObject} is used to represent absence of real object as a do nothing object
 * */
public interface NullObject {}


class NullStorageObject implements NullObject, Storage {

    @Override
    public boolean save(String fileName, String contents) {
        return false;
    }

    public static void main(String[] args) {
        boolean isFileSaved = false;
        String contents = "Hello world";

        FileStorage fileStorage = new FileStorage();
        isFileSaved = fileStorage.save("hello.txt", contents);
        System.out.println("File saved: " + isFileSaved);

        NullStorageObject nullStorageObject = new NullStorageObject();
        isFileSaved = nullStorageObject.save("hello.txt", contents);
        System.out.println("File saved: " + isFileSaved);
    }
}