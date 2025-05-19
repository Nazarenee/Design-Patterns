import java.util.ArrayList;
import java.util.List;

public class Room implements Device{
    private List<Device> devices = new ArrayList<>();

    public void addDevice(Device device){
        devices.add(device);
    }


    @Override
    public void turnOn() {
        devices.forEach(Device::turnOn);
    }

    @Override
    public void turnOff() {
        devices.forEach(Device::turnOff);
    }
}
