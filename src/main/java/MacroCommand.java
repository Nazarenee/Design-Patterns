import java.util.ArrayList;
import java.util.List;

public class MacroCommand implements Command{
    private List<Command> commands = new ArrayList<>();
    private String name;

    public MacroCommand(String name){
        this.name = name;
    }


    public void addCommand(Command command){
        commands.add(command);
    }

    @Override
    public void execute() {
        System.out.println("Executing" + name);
        commands.forEach(Command::execute);
    }

    @Override
    public String toString() {
        return "MacroCommand: " + name;
    }
}
