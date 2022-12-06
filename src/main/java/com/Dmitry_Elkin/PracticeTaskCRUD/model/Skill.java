package com.Dmitry_Elkin.PracticeTaskCRUD.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Skill {
    private static volatile long lastId;

    private long id;
    private String Name;
    private Status status;

    public Skill(long id, String name) {
        this.id = id;
        Name = name;
        status = Status.ACTIVE;
    }

    public Skill(String name) {
        Name = name;
        status = Status.ACTIVE;
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
    public void setNewId() {
        AtomicLong l = new AtomicLong(lastId);
        Skill.lastId = l.incrementAndGet();
        this.id = lastId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return id == skill.id && Name.equals(skill.Name) && status == skill.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name, status);
    }


    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", status=" + status +
                '}';
    }
}
