package ru.edu.mirea.task6;

import org.junit.jupiter.api.Test;
import ru.edu.mirea.task6.prototype.Project;
import ru.edu.mirea.task6.prototype.ProjectFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrototypeTest {
    @Test
    public void prototypeTest() {
        Project project = new Project(1, "first", "hello, world!");
        ProjectFactory projectFactory = new ProjectFactory(project);
        Project clone = projectFactory.cloneProject();
        assertEquals(clone.getId(), project.getId());
    }
}
