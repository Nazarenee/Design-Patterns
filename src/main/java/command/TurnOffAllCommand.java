package command;

import base.DeviceGroup;
import command.Command;

public class TurnOffAllCommand implements Command {

    DeviceGroup deviceGroup;

    public TurnOffAllCommand(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }
    @Override
    public void execute() {
        deviceGroup.turnAllOff();
    }
}
