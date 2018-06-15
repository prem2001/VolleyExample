package com.example.r.volleyrequest;

import org.json.JSONException;
import org.json.JSONObject;

class Utilities {
    public static JSONObject getPaymentLinkJson() throws JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("can_id","11111");
        return jsonObject;
    }
}
