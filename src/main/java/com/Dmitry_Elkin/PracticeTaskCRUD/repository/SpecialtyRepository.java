package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Specialty;

import java.util.List;

public interface SpecialtyRepository extends GenericRepository<Specialty, Long> {
    @Override
    void addOrUpdate(Specialty item);


    @Override
    List<Specialty> getAll();

    @Override
    Specialty getById(Long id);

    @Override
    void delete(Specialty item);
}
