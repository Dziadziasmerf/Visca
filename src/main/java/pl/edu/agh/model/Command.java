package pl.edu.agh.model;

public class Command {

    private ViscaCommand command;
    private int param;

    public Command() {
    }

    public Command(ViscaCommand command, int param) {
        this.command = command;
        this.param = param;
    }

    public ViscaCommand getCommand() {
        return command;
    }

    public void setCommand(ViscaCommand command) {
        this.command = command;
    }

    public int getParam() {
        return param;
    }

    public void setParam(int param) {
        this.param = param;
    }
}
