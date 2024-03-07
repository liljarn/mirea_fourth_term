package ru.edu.mirea.task7.facade;

public class ComputerFacade {
    private final CPU cpu = new CPU();
    private final Memory memory = new Memory();
    private final HardDrive hardDrive = new HardDrive();

    public void startComputer() {
        cpu.start();
        memory.load();
        hardDrive.read();
        System.out.println("Computer is started");
    }

    public void shutdownComputer() {
        cpu.shutdown();
        memory.unload();
        hardDrive.write();
        System.out.println("Computer was off");
    }
}
