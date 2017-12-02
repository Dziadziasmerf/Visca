package pl.edu.agh;

import jssc.SerialPort;
import jssc.SerialPortException;
import pl.edu.agh.model.Command;
import pl.edu.agh.model.ViscaCommand;
import pl.edu.agh.utils.CommandExecutionException;
import pl.edu.agh.utils.UnrecognizedCommand;
import pl.edu.agh.utils.ViscaUtils;

import java.util.Scanner;

public class CommandLineViscaApplication {

    public static void main(String[] args) {
        while(true) {
            try {
                SerialPort serialPort = ViscaUtils.openSerialPort();

                Command command = new Command();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Wybierz komende(Move left/Move right/Move up/Move down/Home/Zoom tele/Zoom wide):");
                String strCommand = scanner.nextLine();
                command.setCommand(ViscaCommand.getViscaCommand(strCommand));
                if(command.getCommand() != ViscaCommand.ZOOM_TELE
                        && command.getCommand() != ViscaCommand.ZOOM_WIDE
                        && command.getCommand() != ViscaCommand.HOME) {
                    try {
                        System.out.println("Podaj parametr (int): ");
                        int parameter = Integer.parseInt(scanner.nextLine());
                        command.setParam(parameter);
                    } catch (Exception e) {
                        System.out.println("Nieprawidłowa wartość parametru, przyjęto 0");
                        command.setParam(0);
                    }
                }

                String result = ViscaUtils.executeCommand(command,serialPort);
                System.out.println(result);

            } catch (SerialPortException e) {
                System.out.println("Couldn't open port");
                break;
            } catch (CommandExecutionException| UnrecognizedCommand e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
