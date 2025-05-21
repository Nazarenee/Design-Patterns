package command;

import base.DeviceGroup;
import command.Command;

public class TurnOnAllCommand implements Command {
    private DeviceGroup deviceGroup;

    public TurnOnAllCommand(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }

    @Override
    public void execute() {
        deviceGroup.turnAllOn();
    }
}
