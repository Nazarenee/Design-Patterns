package devices;

import base.Device;
import base.DeviceGroup;

import java.util.ArrayList;
import java.util.List;

public class Room implements DeviceGroup {
    private String name;
    private List<Device> devices = new ArrayList<>();

    public Room(String name){
        this.name = name;
    }

    public void addDevice(Device device){
        devices.add(device);
    }


    @Override
    public void removeDevice(Device device) {
        devices.remove(device);
    }

    @Override
    public void turnAllOn() {
        System.out.println("Turning on all devices in " + name);
        devices.forEach(Device::turnOn);
    }

    @Override
    public void turnAllOff() {
        System.out.println("Turning off all devices in " + name);
        devices.forEach(Device::turnOff);
    }

    @Override
    public String getName() {
        return name;
    }

    public List<Device> getDevices() {
        return devices;
    }

    @Override
    public String toString() {
        return getName();
    }
}
