package design.pattern.creational;

import common.helper.message.JSONMessage;
import common.helper.message.Message;
import common.helper.message.XMLMessage;

/**
 * {@link FactoryMethod} will have individual factory method classes
 *           {@link XMLMessageMessageFactoryMethod}, {@link JSONMessageMessageFactoryMethod}
 *           for every instances needed {@link XMLMessage}, {@link JSONMessage} and we cannot
 *           go with {@link SimpleFactory} since the {@link Message} might be extended by different
 *           concrete classes in the future
 */
public interface FactoryMethod {}

/**
 * More over base factory method {@link MessageFactoryMethod} will contain the method
 *           {@code MessageFactoryMethod.getMessage} which will internally call the abstract
 *           method {@code MessageFactoryMethod.createMessage} which will be handled by the
 *           individual factory method classes
 */
abstract class MessageFactoryMethod implements FactoryMethod {
    public Message getMessage(String messageStr) {
        Message message = this.createMessage(messageStr);

        message.reloadMessage();

        return message;
    }

    public abstract Message createMessage(String messageStr);

    public static void main(String[] args) {
        System.out.println(new XMLMessageMessageFactoryMethod().getMessage("<name>Srivatsan</name>").getMessage());

        System.out.println(new JSONMessageMessageFactoryMethod().getMessage("{\"name\": \"Srivatsan\"}").getMessage());
    }
}

class XMLMessageMessageFactoryMethod extends MessageFactoryMethod {

    @Override
    public Message createMessage(String messageStr) {
        return new XMLMessage(messageStr);
    }
}

class JSONMessageMessageFactoryMethod extends MessageFactoryMethod {

    @Override
    public Message createMessage(String messageStr) {
        return new JSONMessage(messageStr);
    }
}