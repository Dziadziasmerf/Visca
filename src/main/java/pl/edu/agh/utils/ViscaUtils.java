package pl.edu.agh.utils;

import jssc.SerialPort;
import jssc.SerialPortException;
import pl.edu.agh.kis.visca.ViscaResponseReader;
import pl.edu.agh.kis.visca.cmd.*;
import pl.edu.agh.model.Command;

public class ViscaUtils {

    private static String PORT = "COM11";

    public static SerialPort openSerialPort() throws SerialPortException {
        SerialPort serialPort = new SerialPort(ViscaUtils.PORT);

        serialPort.openPort();
        serialPort.setParams(9600, 8, 1, 0);

        return serialPort;
    }


    public static String executeCommand(Command command, SerialPort serialPort) throws UnrecognizedCommand, CommandExecutionException {

        pl.edu.agh.kis.visca.cmd.ViscaCommand viscaCommand = new pl.edu.agh.kis.visca.cmd.ViscaCommand();
        viscaCommand.commandData = getCommandData(command);
        viscaCommand.sourceAdr = 0;
        viscaCommand.destinationAdr = 1;

        byte[] commandData = viscaCommand.getCommandData();

        try {
            serialPort.writeBytes(commandData);

        } catch (SerialPortException e) {
            throw new CommandExecutionException("Exception during writing bytes", e);
        }

        try {
            return byteArrayToString(ViscaResponseReader.readResponse(serialPort));
        } catch (ViscaResponseReader.TimeoutException e) {
            throw new CommandExecutionException("Timeout exception",e);
        } catch (SerialPortException e) {
            throw new CommandExecutionException("Connection problems",e);
        }

    }

    private static byte[] getCommandData(Command command) throws UnrecognizedCommand {

        byte[] commandData;

        switch (command.getCommand()) {
            case HOME:
                commandData = new PanTiltHomeCmd().createCommandData();
                break;
            case MOVE_UP:
                commandData = new PanTiltUpCmd().createCommandData();
                commandData[3] = (byte) command.getParam();
                break;
            case MOVE_DOWN:
                commandData = new PanTiltDownCmd().createCommandData();
                commandData[3] = (byte) command.getParam();
                break;
            case MOVE_LEFT:
                commandData = new PanTiltLeftCmd().createCommandData();
                commandData[3] = (byte) command.getParam();
                break;
            case MOVE_RIGHT:
                commandData = new PanTiltRightCmd().createCommandData();
                commandData[3] = (byte) command.getParam();
                break;
            case ZOOM_TELE:
                commandData = new ZoomTeleStdCmd().createCommandData();
                break;
            case ZOOM_WIDE:
                commandData = new ZoomWideStdCmd().createCommandData();
                break;
            default:
                throw new UnrecognizedCommand("Unrecognized command: " + command.getCommand());
        }

        return commandData;
    }


    private static String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {
            sb.append(String.format("%02X ", new Object[]{b}));
        }

        return sb.toString();
    }
}
