package com.Dmitry_Elkin.PracticeTaskCRUD.service;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Deprecated
public class SkillService {
    private static final Path file = Paths.get("skillsHM.json");
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting() //formats json-file to well done form
            .create();
    public static HashMap<Long, Skill> getItemsFromDB(){
        if (Files.exists(file)) {
            String jsonStr;
            try {
                jsonStr = Files.readString(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Type targetClassType = new TypeToken<HashMap<Long, Skill>>() {
            }.getType();
            return new Gson().fromJson(jsonStr, targetClassType);
        };
        return new HashMap<Long, Skill>();

    }

    public static void updateBD(HashMap<Long, Skill> skillSet) {
        try {
            Files.writeString(file, gson.toJson(skillSet));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
