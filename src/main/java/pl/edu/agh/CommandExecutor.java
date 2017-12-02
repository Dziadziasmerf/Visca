package pl.edu.agh;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.stereotype.Component;
import pl.edu.agh.model.Command;
import pl.edu.agh.utils.CommandExecutionException;
import pl.edu.agh.utils.UnrecognizedCommand;
import pl.edu.agh.utils.ViscaUtils;

@Component
public class CommandExecutor {

    private SerialPort serialPort;


    public void openPort() {
        try {
            this.serialPort = ViscaUtils.openSerialPort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public String executeCommand(Command command) throws CommandExecutionException, UnrecognizedCommand {
        return ViscaUtils.executeCommand(command,this.serialPort);
    }
}
