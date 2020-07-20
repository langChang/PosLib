package com.nhsoft.poslib.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;


public class XmlParser {

    public static String xml2json(String response) {
        JSONObject jsonObj = null;
        try {
            jsonObj = XML.toJSONObject(response);
        } catch (JSONException e) {
            Log.e("JSON exception", e.getMessage());
            e.printStackTrace();
        }
        return jsonObj == null ? "" : jsonObj.toString();
    }

    public static String xml2jsonString(String response) {
        if(response == null)return null;
        JSONObject jsonObj = null;
        try {
            jsonObj = XML.toJSONObject(response);
        } catch (JSONException e) {
            Log.e("JSON exception", e.getMessage());
            e.printStackTrace();
        }
        return jsonObj == null ? "" : jsonObj.toString();
    }
}
