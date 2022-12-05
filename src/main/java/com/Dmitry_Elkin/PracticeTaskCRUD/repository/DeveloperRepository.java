package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Developer;

import java.util.List;

public interface DeveloperRepository extends GenericRepository<Developer, Long>{
    @Override
    boolean addOrUpdate(Developer item);

    @Override
    List<Developer> getAll();

    @Override
    Developer getById(Long id);

    @Override
    boolean delete(Developer item);
}
