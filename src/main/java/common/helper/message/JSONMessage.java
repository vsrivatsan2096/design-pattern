package common.helper.message;

import org.json.JSONObject;

public class JSONMessage extends Message {

    private final JSONObject jsonMessage;

    public JSONMessage(String message) {
        super(message);
        this.jsonMessage = new JSONObject(message);
    }

    public JSONObject getJSONMessage() {
        return this.jsonMessage;
    }

    @Override
    public void reloadMessage() {
        super.message = JSONObject.class.getName() + ":" + "\n" + this.jsonMessage.toString();
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }
}
