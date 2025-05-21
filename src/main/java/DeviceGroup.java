public interface DeviceGroup {
    void addDevice(Device device);
    void removeDevice(Device device);
    void turnAllOn();
    void turnAllOff();
    String getName();
}
