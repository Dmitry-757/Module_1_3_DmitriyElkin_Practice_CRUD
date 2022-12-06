package com.Dmitry_Elkin.PracticeTaskCRUD.myRepository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import com.Dmitry_Elkin.PracticeTaskCRUD.repository.SkillRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class GsonSkillRepositoryLazyImpl implements SkillRepository {
    public HashMap<Long, Skill> getSkillSet() {
        return skillSet;
    }

    private final HashMap<Long, Skill> skillSet;

    private static final Path file = Paths.get("skillsHM.json");
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting() //formats json-file to well done form
            .create();

    //при старте считываем данные из бд в skillSet что бы в дальнейшем дергать данные из skillSet, а не обращаться к бд каждый раз
    public GsonSkillRepositoryLazyImpl() {
        skillSet = getItemsFromDB();
    }

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



    @Override
    public List<Skill> getAll() {
        return skillSet.values().stream().toList();
    }

    @Override
    public Skill getById(Long id) {
        return skillSet.get(id);
    }

    @Override
    public void addOrUpdate(Skill item) {
        if(item.getId() <= 0) { //add
            item.setNewId();
        }
        skillSet.put(item.getId(), item);
        updateBD(skillSet);
    }

    @Override
    public void delete(Skill item) {
        item.setDeleted();
        addOrUpdate(item);
    }

    @Override
    public void unDelete(Skill item) {
        item.setUnDeleted();
        addOrUpdate(item);
    }

}
