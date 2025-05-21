package decorator;

import base.Device;

public abstract class DeviceDecorator implements Device {
    protected Device device;

    public DeviceDecorator(Device device) {
        this.device = device;
    }

    @Override
    public String getName() {
        return device.getName();
    }
}

