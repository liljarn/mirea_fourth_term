package ru.edu.mirea.task6;

import org.junit.jupiter.api.Test;
import ru.edu.mirea.task6.abstract_factory.Application;
import ru.edu.mirea.task6.abstract_factory.GUIFactory;
import ru.edu.mirea.task6.abstract_factory.MacOSFactory;
import ru.edu.mirea.task6.abstract_factory.WindowsFactory;

public class AbstractFactoryTest {
    @Test
    public void windowsFactoryTest() {
        GUIFactory guiFactory = new WindowsFactory();
        Application application = new Application(guiFactory);
        application.paint();
    }
    @Test
    public void macFactoryTest() {
        GUIFactory guiFactory = new MacOSFactory();
        Application application = new Application(guiFactory);
        application.paint();
    }

}
