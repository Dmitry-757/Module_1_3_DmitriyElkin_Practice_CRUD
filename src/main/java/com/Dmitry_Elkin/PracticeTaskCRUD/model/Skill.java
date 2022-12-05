package com.Dmitry_Elkin.PracticeTaskCRUD.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

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
    public void setNewId() {
        AtomicLong l = new AtomicLong(lastId);
        Skill.lastId = l.incrementAndGet();
        this.id = lastId;
    }


    public String getName() {
        return Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return id == skill.id && Name.equals(skill.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name);
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
