package com.example.dirty_a.model;

import android.content.Context;

import com.android.volley.Request;
import com.example.dirty_a.callbacks.JsonObjectCallback;
import com.example.dirty_a.settings.ApiSettings;
import com.example.dirty_a.volley.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Laser extends Device {

    public Laser(long id, String name, List<Integer> channels) {
        super(id, name, channels);
    }

    public void setChannels(Context context, List<Integer> values) {
        if (values.size() != channels.size()) {
            //TODO: throw better exception
            throw new RuntimeException("Laser setChannels: too many or too few channels given as argument");
        }

        RequestHandler.standardJsonObjectRequest(context, Request.Method.GET, ApiSettings.BASE_URL + "get_dmx?u=" + ApiSettings.UNIVERSE, null, new JsonObjectCallback() {
            @Override
            public void processFinished(JSONObject response) {
                System.out.println("Get response: " + response);

                String updatedChannels = updateChannels(getChannelsFromResponse(response), values);

                System.out.println("Sending: " + updatedChannels);

                Map<String, String> params = new HashMap<>();
                params.put("u", "1");
                params.put("d", updatedChannels.substring(1, updatedChannels.length()-1));

                RequestHandler.customStringRequest(context, Request.Method.POST, ApiSettings.BASE_URL + "set_dmx", params, null);
            }
        });
    }

    private List<Integer> getChannelsFromResponse(JSONObject response) {
        List<Integer> result = new ArrayList<>();
        try {
            // Get the JSON array from the response
            JSONArray channels = response.getJSONArray("dmx");

            // Get a list of Integers from the array
            if (channels != null) {
                int len = channels.length();
                for (int i = 0; i < len; i++) {
                    result.add((Integer) channels.get(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String updateChannels(List<Integer> channels, List<Integer> newValues) {

        // Update the list of channels with the rgbValues
        for (int i=0; i<newValues.size(); i++) {
            channels.set(getChannels().get(i), newValues.get(i));
        }

        return channels.toString();
    }
}
