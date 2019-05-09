package com.example.dirty_a.dataproviders;

import com.example.dirty_a.model.Device;
import com.example.dirty_a.model.Laser;
import com.example.dirty_a.model.RGBDevice;

import java.util.ArrayList;
import java.util.Arrays;
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
//        devicesToAdd.add(new RGBDevice(0, "All_lamps", Arrays.asList(-1, -1, -1)));
        devicesToAdd.add(new RGBDevice(1, "Bar hemel", Arrays.asList(0,1,2)));
        devicesToAdd.add(new RGBDevice(2, "Midden woonkamer", Arrays.asList(3,4,5)));
        devicesToAdd.add(new Laser(3, "Laser", Arrays.asList(30, 31, 32, 33, 34, 35, 36, 37)));
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
        //TODO: throw better exception
        throw new RuntimeException("Device with id " + id + " could not be found");
    }

    public List<RGBDevice> getRGBDevices() {
        List<RGBDevice> rgbDevices = new ArrayList<>();
        for (Device device : devices) {
            if (device instanceof RGBDevice && device.getId() != 0) {
                rgbDevices.add((RGBDevice) device);
            }
        }
        return rgbDevices;
    }
}
