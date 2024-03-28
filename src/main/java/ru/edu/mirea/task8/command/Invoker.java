package ru.edu.mirea.task8.command;

import lombok.Setter;

@Setter
public class Invoker {
    private Command command;

    public void executeCommand() {
        command.execute();
    }
}
