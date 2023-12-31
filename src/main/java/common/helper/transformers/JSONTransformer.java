package common.helper.transformers;

import org.json.JSONObject;

public class JSONTransformer {
    private JSONTransformer() {}

    public static JSONObject parseJSON(String jsonStr) {
        return new JSONObject(jsonStr);
    }
}
