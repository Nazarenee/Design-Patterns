package base;

import base.Device;

public abstract class BaseDevice implements Device {
    private String name;

    public BaseDevice(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
