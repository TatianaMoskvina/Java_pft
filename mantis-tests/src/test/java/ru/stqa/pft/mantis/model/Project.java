package ru.stqa.pft.mantis.model;

public class Project {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public Project SetId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project SetName(String name) {
        this.name = name;
        return this;
    }
}
