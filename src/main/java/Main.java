import base.Device;
import command.*;
import devices.Room;
import factory.DeviceFactory;

public class Main {
    public static void main(String[] args) {
        Room livingRoom = new Room("Living devices.Room");
        Room kitchen = new Room("Kitchen");

        Device livingRoomLight = DeviceFactory.createDevice("light", "Main devices.Light");
        Device accentLight = DeviceFactory.createDevice("light", "Accent devices.Light");

        Device kitchenLight = DeviceFactory.createDevice("logginglight", "Kitchen devices.Light");


        livingRoom.addDevice(livingRoomLight);
        livingRoom.addDevice(accentLight);

        kitchen.addDevice(kitchenLight);

        Command turnOnLivingRoom = new TurnOnAllCommand(livingRoom);
        Command turnOffLivingRoom = new TurnOffAllCommand(livingRoom);
        Command turnOnKitchen = new TurnOnAllCommand(kitchen);
        Command turnOffKitchen = new TurnOffAllCommand(kitchen);


        MacroCommand eveningMode = new MacroCommand("Evening Mode");
        eveningMode.addCommand(new TurnOnCommand(accentLight));
        eveningMode.addCommand(new TurnOffCommand(livingRoomLight));
        eveningMode.addCommand(new TurnOffCommand(kitchenLight));

        RemoteControl remote = new RemoteControl();

        System.out.println("\n--- Basic devices.Room Control ---");
        remote.setCommand(turnOnLivingRoom);
        remote.execute();

        System.out.println("\n--- Individual base.Device Control ---");
        remote.setCommand(turnOnKitchen);
        remote.execute();

        System.out.println("\n--- Macro command.Command Example ---");
        remote.setCommand(eveningMode);
        remote.execute();

        System.out.println("\n--- Turning Everything Off ---");
        remote.setCommand(turnOffLivingRoom);
        remote.execute();
        remote.setCommand(turnOffKitchen);
        remote.execute();
    }
}
