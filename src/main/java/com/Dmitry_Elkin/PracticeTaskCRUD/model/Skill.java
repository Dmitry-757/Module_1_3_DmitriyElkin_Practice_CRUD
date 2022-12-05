package com.Dmitry_Elkin.PracticeTaskCRUD.model;

public class Skill {
    private static volatile long lastId;

    private long id;
    private String Name;

    public Skill(long id, String name) {
        this.id = id;
        Name = name;
    }

    public Skill(String name) {
        Name = name;
    }

    public static long getLastId() {
        return lastId;
    }

    public static void setLastId(long lastId) {
        Skill.lastId = lastId;
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
