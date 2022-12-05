package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GsonSkillRepositoryImpl implements SkillRepository {
    private static final Path file = Paths.get("skills.json");
    private static final Gson gson = new GsonBuilder()
//            .setPrettyPrinting() //formats json-file to well done form
            .create();


    @Override
    public List<Skill> getAll() {
        List<Skill> skillList = new LinkedList<>();
        try {
            List<String> lines = Files.readAllLines(file);
            for (String jsonStr : lines) {
                Skill skill = new Gson().fromJson(jsonStr, Skill.class);
//                System.out.println(skill);
                skillList.add(skill);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return skillList;
    }

    @Override
    public Skill getById(Long id) {
        if (!Files.exists(file)) {
            System.out.println("The file 'skill.json' is absent!");
            return null;
        }
        String jsonStr;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                jsonStr = sc.nextLine();
                Skill skill = new Gson().fromJson(jsonStr, Skill.class);
                if (skill.getId() == id) {
                    return skill;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean addOrUpdate(Skill item) {
        if (item.getId() <= 0) { //add
            item.setNewId();
        }

        try {
            if (Files.exists(file)) {
                Files.write(file, List.of(gson.toJson(item)), StandardOpenOption.APPEND);
            } else {
                Files.write(file, List.of(gson.toJson(item)), StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("oops, IO exception was occurred (( " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Long id) {
        return true;
    }
}
