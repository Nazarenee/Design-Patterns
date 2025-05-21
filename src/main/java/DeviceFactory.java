public class DeviceFactory {
    public static Device createDevice(String type, String name) {
        switch(type.toLowerCase()) {
            case "light":
                return new Light(name);
            case "logginglight":
                return new LoggingDevice(new Light(name));
            default:
                throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}
