public class DeviceFactory {
    public static Device createDevice(String type) {
        switch(type.toLowerCase()) {
            case "light":
                return new Light();
            case "logginglight":
                return new LoggingDevice(new Light());
            default:
                throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}
