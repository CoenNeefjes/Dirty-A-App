package com.example.dirty_a.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dirty_a.R;
import com.example.dirty_a.adapters.DeviceAdapter;
import com.example.dirty_a.model.Device;

import java.util.List;

public class DeviceListFragment extends Fragment {

    private DeviceAdapter deviceAdapter;
    private ListView listView;
    private List<Device> devices;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Afspeellijsten");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_device_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        devices = null;

        listView = getActivity().findViewById(R.id.listViewDevices);
        deviceAdapter = new DeviceAdapter(getActivity(), devices);
        listView.setAdapter(deviceAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewDevice(position);
            }
        });
    }

    private void viewDevice(int index) {

    }
}
