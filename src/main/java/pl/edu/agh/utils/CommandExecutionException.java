package pl.edu.agh.utils;

public class CommandExecutionException extends Exception{

    public CommandExecutionException(String s) {
        super(s);
    }

    public CommandExecutionException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
