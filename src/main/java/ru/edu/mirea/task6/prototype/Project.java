package ru.edu.mirea.task6.prototype;

public class Project implements Copyable {
    private int id;
    private String projectName;
    private String sourceCode;

    public Project(int id, String projectName, String sourceCode) {
        this.id = id;
        this.projectName = projectName;
        this.sourceCode = sourceCode;
    }

    @Override
    public Copyable copy() {
        return new Project(id, projectName, sourceCode);
    }

    public int getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getSourceCode() {
        return sourceCode;
    }
}
