import java.util.Stack;

public class RemoteControl {
    private Command command;
    private Stack<Command> commandHistory = new Stack<>();

    public void setCommand(Command command){
        this.command = command;
    }

    public void execute(){
        if(command!=null){
            command.execute();
            commandHistory.push(command);
        }
    }

    public void undoLastCommand(){
        if(!commandHistory.isEmpty()){
            Command command = commandHistory.pop();
            System.out.println("Undoing last command" + command);
        }
    }
}
