package common.helper.message;

import common.helper.transformers.XMLTransformer;
import org.w3c.dom.Document;

public class XMLMessage extends Message {

    private final Document xmlMessage;

    public XMLMessage(String message) {
        super(message);
        this.xmlMessage = XMLTransformer.loadXML(message);
    }

    @Override
    public void reloadMessage() {
        super.message = Document.class.getName() + ":" + "\n" + XMLTransformer.parseXML(xmlMessage);
    }

    public Document getJSONMessage() {
        return this.xmlMessage;
    }
    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }
}
