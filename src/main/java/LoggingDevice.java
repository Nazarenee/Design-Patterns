public class LoggingDevice extends DeviceDecorator {
    public LoggingDevice(Device device) {
        super(device);
    }

    public void turnOn() {
        System.out.println("Turning on device " + device.getClass());
        device.turnOn();
    }

    public void turnOff() {
        System.out.println("Turning off device " + device.getClass());
        device.turnOff();
    }
}
