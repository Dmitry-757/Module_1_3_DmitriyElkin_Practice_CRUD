package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;

import java.util.List;

public interface SkillRepository extends GenericRepository<Skill, Long>{
    @Override
    boolean addOrUpdate(Skill item);

    @Override
    List<Skill> getAll();

    @Override
    Skill getById(Long id);

    @Override
    boolean delete(Skill item);
}
