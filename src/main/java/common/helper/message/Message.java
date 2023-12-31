package common.helper.message;

import common.helper.entity.Entity;

public abstract class Message implements Entity {
    protected String message;

    public Message() {}

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public abstract void reloadMessage();
}
