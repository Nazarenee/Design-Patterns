public class Main {
    public static void main(String[] args) {
        Room livingRoom = new Room("Living Room");
        Room kitchen = new Room("Kitchen");

        Device livingRoomLight = DeviceFactory.createDevice("light", "Main Light");
        Device accentLight = DeviceFactory.createDevice("light", "Accent Light");

        Device kitchenLight = DeviceFactory.createDevice("logginglight", "Kitchen Light");


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

        System.out.println("\n--- Basic Room Control ---");
        remote.setCommand(turnOnLivingRoom);
        remote.execute();

        System.out.println("\n--- Individual Device Control ---");
        remote.setCommand(turnOnKitchen);
        remote.execute();

        System.out.println("\n--- Macro Command Example ---");
        remote.setCommand(eveningMode);
        remote.execute();

        System.out.println("\n--- Turning Everything Off ---");
        remote.setCommand(turnOffLivingRoom);
        remote.execute();
        remote.setCommand(turnOffKitchen);
        remote.execute();
    }
}
