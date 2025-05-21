import java.time.LocalDateTime;

public class LoggingDevice extends DeviceDecorator {
    public LoggingDevice(Device device) {
        super(device);
    }

    public void turnOn() {
        System.out.println("Turning on device " + device.toString() + " Start time : " + LocalDateTime.now());
        device.turnOn();
    }

    public void turnOff() {
        System.out.println("Turning off device " + device.toString() +" End time : " + LocalDateTime.now());
        device.turnOff();
    }
}
