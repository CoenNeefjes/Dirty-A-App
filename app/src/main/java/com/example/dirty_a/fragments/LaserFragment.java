package com.example.dirty_a.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.example.dirty_a.R;
import com.example.dirty_a.callbacks.JsonObjectCallback;
import com.example.dirty_a.dataproviders.DeviceDataProvider;
import com.example.dirty_a.model.Laser;
import com.example.dirty_a.settings.ApiSettings;
import com.example.dirty_a.volley.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LaserFragment extends Fragment {

    // UI elements
    private TextView nameText;
    private SeekBar seekBar1, seekBar2, seekBar3, seekBar4, seekBar5, seekBar6, seekBar7, seekBar8;
    private List<SeekBar> seekBars;

    // Data
    private long device_id;
    private Laser laser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Laser");
        device_id = getArguments().getLong("device_id");
        return inflater.inflate(R.layout.fragment_laser_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set UI elements
        nameText = getActivity().findViewById(R.id.laser_device_detail_name);
        seekBar1 = getActivity().findViewById(R.id.seekBar1);
        seekBar2 = getActivity().findViewById(R.id.seekBar2);
        seekBar3 = getActivity().findViewById(R.id.seekBar3);
        seekBar4 = getActivity().findViewById(R.id.seekBar4);
        seekBar5 = getActivity().findViewById(R.id.seekBar5);
        seekBar6 = getActivity().findViewById(R.id.seekBar6);
        seekBar7 = getActivity().findViewById(R.id.seekBar7);
        seekBar8 = getActivity().findViewById(R.id.seekBar8);

        // Add all seekBars to a List for easy editing
        seekBars = new ArrayList<>(Arrays.asList(seekBar1, seekBar2, seekBar3, seekBar4, seekBar5, seekBar6, seekBar7, seekBar8));

        // Set the same listener for each seekBar
        seekBars.forEach(seekBar -> seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                changeSettings();
            }
        }));

        // Get the device
        this.laser = (Laser) DeviceDataProvider.getInstance().getDeviceById(device_id);

        if (laser.getChannels().size() != seekBars.size()) {
            throw new RuntimeException("Number of channels of the laser is not equal to the amout of sliders on the fragment");
        }

        setUIData();
    }

    private void setUIData() {
        // Set the device name
        nameText.setText(laser.getName());

        // Set Max value of all seekBars to 255
        seekBars.forEach(seekBar -> seekBar.setMax(255));

        RequestHandler.standardJsonObjectRequest(getContext(), Request.Method.GET, ApiSettings.BASE_URL + "get_dmx?u=" + ApiSettings.UNIVERSE, null, new JsonObjectCallback() {
            @Override
            public void processFinished(JSONObject response) {
                System.out.println("Get response: " + response);

                List<Integer> channelList = getChannelsFromResponse(response);
                List<Integer> deviceChannels = laser.getChannels();

                // Set all the seekBars to the right value
                for (int i = 0; i < seekBars.size(); i++) {
                    seekBars.get(i).setProgress(channelList.get(deviceChannels.get(i)));
                }
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

    private void changeSettings() {
        List<Integer> values = new ArrayList<>();
        seekBars.forEach(seekBar -> values.add(seekBar.getProgress()));
        System.out.println("Current values: " + values.toString());
        laser.setChannels(getContext(), values);
    }
}
