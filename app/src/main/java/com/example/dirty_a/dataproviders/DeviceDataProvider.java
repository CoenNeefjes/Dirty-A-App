package com.example.dirty_a.dataproviders;

import com.example.dirty_a.model.Device;
import com.example.dirty_a.model.RGBDevice;

import java.util.ArrayList;
import java.util.List;

public class DeviceDataProvider {

    private static final DeviceDataProvider deviceDataProvider = new DeviceDataProvider();

    private List<Device> devices;

    private DeviceDataProvider() {
        devices = new ArrayList<>();
        setTestData();
    }

    public static DeviceDataProvider getInstance() {
        return deviceDataProvider;
    }

    private void setTestData() {
        List<Device> devicesToAdd = new ArrayList<>();
        devicesToAdd.add(new RGBDevice(1, "RGB_lamp_1", new int[]{0, 1, 2}));
        devicesToAdd.add(new RGBDevice(2, "RGB_lamp_2", new int[]{3, 4, 5}));
        devices.addAll(devicesToAdd);
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Device getDeviceById(long id) {
        for (Device device: devices) {
            if (device.getId() == id) {
                return device;
            }
        }
        //TODO: throw exception
        return null;
    }

    public List<RGBDevice> getRGBDevices() {
        List<RGBDevice> rgbDevices = new ArrayList<>();
        for (Device device : devices) {
            if (device instanceof RGBDevice) {
                rgbDevices.add((RGBDevice) device);
            }
        }
        return rgbDevices;
    }
}
