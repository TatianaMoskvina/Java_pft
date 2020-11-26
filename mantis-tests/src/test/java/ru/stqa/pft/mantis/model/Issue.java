package ru.stqa.pft.mantis.model;

public class Issue {

    private int id;
    private String summary;
    private String description;
    private Project project;

    public int getId() {
        return id;
    }

    public Issue SetId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue SetSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue SetDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() { return project; }

    public Issue SetProject(Project project) {
        this.project = project;
        return this;
    }
}
