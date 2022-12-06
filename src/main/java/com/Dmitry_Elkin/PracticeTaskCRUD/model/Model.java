package com.Dmitry_Elkin.PracticeTaskCRUD.model;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Model {
    private static volatile long lastId;

    private long id;
    private String Name;
    private Status status;


    public static long getLastId() {
        return lastId;
    }

    public static void setLastId(long lastId) {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setNewId() {
    }

    public void setDeleted() {
        status = Status.DELETED;
    }

    public void setUnDeleted() {
        status = Status.ACTIVE;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
