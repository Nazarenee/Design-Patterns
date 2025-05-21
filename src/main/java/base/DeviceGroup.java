package base;

import base.Device;

public interface DeviceGroup {
    void addDevice(Device device);
    void removeDevice(Device device);
    void turnAllOn();
    void turnAllOff();
    String getName();
}
