package ru.edu.mirea.task7.facade;

import org.junit.jupiter.api.Test;

public class FacadeTest {
    private final ComputerFacade computerFacade = new ComputerFacade();

    @Test
    public void patternFacadeTest() throws InterruptedException {
        computerFacade.startComputer();
        Thread.sleep(2000);
        computerFacade.shutdownComputer();
    }
}
