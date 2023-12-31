package design.pattern.structural;

import common.helper.message.JSONMessage;
import common.helper.message.Message;

import java.util.Base64;

/**
 * {@link Decorator} is used to add a simple functionality to existing classes without changing the total behavior
 * */
public interface Decorator {}

class Base64EncoderDecoratorMessage extends Message implements Decorator {
    private final Message message;
    public Base64EncoderDecoratorMessage(Message message) {
        this.message = message;
    }

    @Override
    public String getEntityName() {
        return this.message.getEntityName();
    }

    @Override
    public void reloadMessage() {
        this.message.reloadMessage();
    }

    @Override
    public String getMessage() {
        return Base64.getEncoder().encodeToString(this.message.getMessage().getBytes());
    }
}

class Base64DecoderDecoratorMessage extends Message implements Decorator {
    private final Message message;
    public Base64DecoderDecoratorMessage(Message message) {
        this.message = message;
    }

    @Override
    public String getEntityName() {
        return this.message.getEntityName();
    }

    @Override
    public void reloadMessage() {
        this.message.reloadMessage();
    }

    @Override
    public String getMessage() {
        return new String(Base64.getDecoder().decode(this.message.getMessage()));
    }

    public static void main(String[] args) {
        Message message = new JSONMessage("{\"test\": \"message\"}");

        System.out.println(message.getMessage());

        Base64EncoderDecoratorMessage base64Message = new Base64EncoderDecoratorMessage(message);

        System.out.println(base64Message.getMessage());

        Base64DecoderDecoratorMessage decodedMessage = new Base64DecoderDecoratorMessage(base64Message);

        System.out.println(decodedMessage.getMessage());
    }
}


