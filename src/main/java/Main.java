public class Main {
    public static void main(String[] args) {
        Device light = DeviceFactory.createDevice("logginglight");
        Device fan = DeviceFactory.createDevice("light");
        Device light1 = DeviceFactory.createDevice("light");


        Room livingRoom = new Room();
        livingRoom.addDevice(light);
        livingRoom.addDevice(fan);

        Room toilet = new Room();
        toilet.addDevice(light1);
        toilet.addDevice(fan);

        Command turnOnLivingRoom = new TurnOnCommand(livingRoom);
        Command turnOffToilet = new TurnOffCommand(toilet);
        RemoteControl remote = new RemoteControl();
        remote.setCommand(turnOnLivingRoom);
        remote.excecute();
        remote.setCommand(turnOffToilet);
        remote.excecute();
    }
}
