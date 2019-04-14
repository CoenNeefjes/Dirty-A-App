package com.example.dirty_a.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dirty_a.R;
import com.example.dirty_a.dataproviders.DeviceDataProvider;
import com.example.dirty_a.model.Device;
import com.example.dirty_a.model.RGBDevice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.priyesh.chroma.ChromaDialog;
import me.priyesh.chroma.ColorMode;

public class RGBDeviceDetailFragment extends Fragment {

    // UI elements
    private TextView nameText;
    private Button onOffButton, changeColorButton;
    private ImageView colorPreview;

    // Data
    private List<RGBDevice> rgbDevices;
    private long device_id;
    private int currentColorInt = -16777216;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("RGB Device");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        device_id = getArguments().getLong("device_id");
        return inflater.inflate(R.layout.fragment_rgb_device_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set UI elements
        nameText = getActivity().findViewById(R.id.rgb_device_detail_name);
        onOffButton = getActivity().findViewById(R.id.rgb_device_turn_on_off_btn);
        changeColorButton = getActivity().findViewById(R.id.rgb_device_change_color_btn);
        colorPreview = getActivity().findViewById(R.id.rgb_device_color_image);

        getDevice();
        setUIData();

        changeColorButton.setOnClickListener(v -> new ChromaDialog.Builder()
                .initialColor(currentColorInt)
                .colorMode(ColorMode.ARGB)
                .onColorSelected(color -> setNewColor(color))
                .create()
                .show(getActivity().getSupportFragmentManager(), "ColorPickerDialog"));
    }

    private void setNewColor(int colorInt) {
        System.out.println("Selected color int: " + colorInt);
        currentColorInt = colorInt;

        int red = Color.red(colorInt);
        int green = Color.green(colorInt);
        int blue = Color.blue(colorInt);
        int alpha = Color.alpha(colorInt);
        System.out.println("r=" + red + ", g=" + green + ", b=" + blue + ", a=" + alpha);

        String hexColor = Integer.toHexString(colorInt).toUpperCase();
        System.out.println("New color in hex:" + hexColor);
        colorPreview.setColorFilter(colorInt);

        for (RGBDevice rgbDevice : rgbDevices) {
            rgbDevice.changeColor(getContext(), Arrays.asList(red, green, blue));
        }
    }

    private void getDevice() {
        // 0 is the id for all the rgb devices combined
        if (device_id == 0) {
            rgbDevices = DeviceDataProvider.getInstance().getRGBDevices();
            System.out.println("Controlling " + rgbDevices.size() + " devices in the RGB Service fragment");
        } else {
            Device device = DeviceDataProvider.getInstance().getDeviceById(device_id);
            if (device instanceof RGBDevice) {
                rgbDevices = new ArrayList<>();
                rgbDevices.add((RGBDevice) device);
            } else {
                //TODO: throw error that device is not of class RGBDevice
            }
        }
    }

    private void setUIData() {
        if (device_id == 0) {
            nameText.setText("All RGB Devices");
        } else {
            nameText.setText(rgbDevices.get(0).getName());
        }

        //TODO: request the state of the device and set button accordingly
        onOffButton.setEnabled(false);

        //TODO: request the state of the device to set the image color preview
    }
}
