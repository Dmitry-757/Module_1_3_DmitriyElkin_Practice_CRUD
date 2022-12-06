package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;

import java.util.List;

public interface SkillRepository extends GenericRepository<Skill, Long>{
    @Override
    void addOrUpdate(Skill item);

    @Override
    List<Skill> getAll();

    @Override
    Skill getById(Long id);

    @Override
    void delete(Skill item);

    @Override
    void unDelete(Skill item);
}
