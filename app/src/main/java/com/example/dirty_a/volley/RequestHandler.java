package com.example.dirty_a.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.dirty_a.callbacks.JsonObjectCallback;
import com.example.dirty_a.callbacks.StringCallback;
import com.example.dirty_a.helpers.AuthTokenHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestHandler {

    public static void customStringRequest(Context context, int method, String url, Map<String, String> params, final StringCallback stringCallback) {
        StringRequest stringRequest = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (stringCallback != null) {
                    stringCallback.processFinished(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                System.out.println(error.getMessage());
                for (StackTraceElement stackTraceElement: error.getStackTrace()) {
                    System.out.println(stackTraceElement.toString());
                }
            }
        }){

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (params == null) {
                    return super.getParams();
                }
                return params;
            }
        };

        // Add the request to the RequestQueue singleton class
        MyRequestQueue.getInstance(context.getApplicationContext()).addToRequestQueue(stringRequest);
    }

    public static void standardJsonObjectRequest(Context context, int method, String url, JSONObject params, final JsonObjectCallback callback){
        // Request a JSON response from the URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, url, params,
                response -> {
                    if (callback != null) {
                        callback.processFinished(response);
                    }
                },
                error -> {
                    if (callback != null) {
                        callback.processFailed(error);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                if (AuthTokenHelper.getAuthToken() != null) {
                    headers.put("Authentication", "Bearer " + AuthTokenHelper.getAuthToken());
                }
                return headers;
            }
        };
        // Add the request to the RequestQueue singleton class
        MyRequestQueue.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    public static void customJsonObjectRequest(Context context, int method, String url, final JSONObject params, final JSONObject body, final Map<String, String> headers, final JsonObjectCallback callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method,
                url, params,
                response -> {
                    if (callback != null) {
                        callback.processFinished(response);
                    }
                }, error -> {
            // Show error message
            System.out.println(error);
            System.out.println(error.getMessage());
            for (StackTraceElement stackTraceElement: error.getStackTrace()) {
                System.out.println(stackTraceElement.toString());
            }
        }
        ) {
            @Override
            public byte[] getBody() {
                if (body == null){
                    return super.getBody();
                }
                return body.toString().getBytes();
            }
//            @Override
//            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
//                if (params == null) {
//                    return super.getParams();
//                }
//                Map<String, String> finalParams = new HashMap<>();
//                // Add all the params
//                Set<String> keys = params.keySet();
//                for (String key : keys) {
//                    finalParams.put(key, params.get(key));
//                }
//                return finalParams;
//            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headers == null){
                    return super.getHeaders();
                }
                return headers;
            }
        };

        // Add the request to the RequestQueue singleton class
        MyRequestQueue.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

}
