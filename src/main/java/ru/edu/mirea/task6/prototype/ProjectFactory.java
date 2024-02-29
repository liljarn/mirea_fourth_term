package ru.edu.mirea.task6.prototype;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectFactory {
    Project project;

    Project cloneProject() {
        return (Project) project.copy();
    }
}
