package pl.edu.agh.model;

import pl.edu.agh.utils.UnrecognizedCommand;

public enum ViscaCommand {

    MOVE_LEFT("Move left"),
    MOVE_RIGHT("Move right"),
    MOVE_UP("Move up"),
    MOVE_DOWN("Move down"),
    HOME("Home"),
    ZOOM_WIDE("Zoom wide"),
    ZOOM_TELE("Zoom tele");


    private String command;

    ViscaCommand(String command) {
        this.command = command;
    }

    public static ViscaCommand getViscaCommand(String command) throws UnrecognizedCommand {
        for(ViscaCommand viscaCommand: ViscaCommand.values()) {
            if(command.trim().equalsIgnoreCase(viscaCommand.getCommand()))
                return viscaCommand;
        }
        throw new UnrecognizedCommand("Unrecognized command");
    }

    public String getCommand() {
        return command;
    }
}
