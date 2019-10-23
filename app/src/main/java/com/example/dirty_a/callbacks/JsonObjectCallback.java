package com.example.dirty_a.callbacks;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface JsonObjectCallback {

    void processFinished(JSONObject response);

    void processFailed(VolleyError error);

}
