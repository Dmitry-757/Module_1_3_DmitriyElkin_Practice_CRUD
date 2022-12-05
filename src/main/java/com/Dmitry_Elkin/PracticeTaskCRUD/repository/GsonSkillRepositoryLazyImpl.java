package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import com.Dmitry_Elkin.PracticeTaskCRUD.service.SkillService;
import java.util.HashMap;
import java.util.List;

public class GsonSkillRepositoryLazyImpl implements SkillRepository{

    public HashMap<Long, Skill> getSkillSet() {
        return skillSet;
    }

    private final HashMap<Long, Skill> skillSet;

    //при старте считываем данные из бд в skillSet что бы в дальнейшем дергать данные из skillSet, а не обращаться к бд каждый раз
    public GsonSkillRepositoryLazyImpl() {
        skillSet = SkillService.getItemsFromDB();
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
    public boolean addOrUpdate(Skill item) {
        long id = item.getId();
        if(id <= 0) { //add
            id = Skill.getLastId()+1;
            Skill.setLastId(id);
            item.setId(id);
        }
        skillSet.put(item.getId(), item);
        SkillService.updateBD(skillSet);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if (skillSet.remove(id)!=null){
            return true;
        }
        return false;
    }
}
