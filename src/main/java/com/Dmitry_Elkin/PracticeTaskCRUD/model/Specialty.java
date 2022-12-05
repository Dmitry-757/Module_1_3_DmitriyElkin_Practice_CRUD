package com.Dmitry_Elkin.PracticeTaskCRUD.model;

public class Specialty {

    private static volatile long lastId;

    private long id;
    private String Name;
    private Status status;

    public Specialty(long id, String name) {
        this.id = id;
        Name = name;
        status = Status.ACTIVE;
    }

    public Specialty(String name) {
        Name = name;
        status = Status.ACTIVE;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
