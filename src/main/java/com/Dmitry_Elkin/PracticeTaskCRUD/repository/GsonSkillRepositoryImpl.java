package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import java.util.List;

public class GsonSkillRepositoryImpl implements SkillRepository{


    @Override
    public List<Skill> getAll() {
        return null;
    }

    @Override
    public Skill getById(Long id) {
        return null;
    }

    @Override
    public boolean addOrUpdate(Skill item) {
        long id = item.getId();
        if(id <= 0) { //add
            id = Skill.getLastId()+1;
            Skill.setLastId(id);
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
         return true;
    }
}
