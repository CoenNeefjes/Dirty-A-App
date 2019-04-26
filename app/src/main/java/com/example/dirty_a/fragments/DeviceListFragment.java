package com.example.dirty_a.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dirty_a.R;
import com.example.dirty_a.adapters.DeviceAdapter;
import com.example.dirty_a.dataproviders.DeviceDataProvider;
import com.example.dirty_a.model.Device;

import java.util.List;

public class DeviceListFragment extends Fragment {

    private DeviceAdapter deviceAdapter;
    private ListView listView;
    private List<Device> devices;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Device List");
        return inflater.inflate(R.layout.fragment_device_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        devices = DeviceDataProvider.getInstance().getDevices();

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
        Bundle bundle = new Bundle();
        bundle.putLong("device_id", devices.get(index).getId());

        RGBDeviceDetailFragment nextFragment = new RGBDeviceDetailFragment();
        nextFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(((ViewGroup) (getView().getParent())).getId(), nextFragment, "DeviceListFragment")
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
