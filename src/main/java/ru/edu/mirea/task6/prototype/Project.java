package ru.edu.mirea.task6.prototype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Project implements Copyable {
    private int id;
    private String projectName;
    private String sourceCode;

    @Override
    public Copyable copy() {
        return new Project(id, projectName, sourceCode);
    }
}
