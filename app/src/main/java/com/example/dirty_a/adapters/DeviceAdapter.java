package com.example.dirty_a.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dirty_a.R;
import com.example.dirty_a.model.Device;

import java.util.List;

public class DeviceAdapter extends ArrayAdapter<Device> {

    private LayoutInflater layoutInflater;
    private List<Device> devices;

    public DeviceAdapter(@NonNull Context context, @NonNull List<Device> objects) {
        super(context, R.layout.list_item_device, objects);

        layoutInflater = LayoutInflater.from(context);
        devices = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_device, parent, false);
        }

        Device device = devices.get(position);

        ImageView image = convertView.findViewById(R.id.deviceImage);
        if (device.getName().equals("Laser")) {
            image.setImageResource(R.drawable.ic_laser);
        }

        TextView name = convertView.findViewById(R.id.deviceNameText);
        name.setText(device.getName());

        return convertView;
    }
}
