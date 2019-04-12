package com.example.dirty_a.dataproviders;

import com.example.dirty_a.model.Device;

import java.util.List;

public class DeviceDataProvider {

    private static final DeviceDataProvider deviceDataProvider = new DeviceDataProvider();

    private List<Device> devices;

    private DeviceDataProvider() {}

    public static DeviceDataProvider getInstance() {
        return deviceDataProvider;
    }
}
