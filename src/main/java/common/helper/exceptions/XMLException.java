package common.helper.exceptions;

public class XMLException extends RuntimeException {

    public XMLException(String message, Exception e) {
        super(message, e);
    }
}
