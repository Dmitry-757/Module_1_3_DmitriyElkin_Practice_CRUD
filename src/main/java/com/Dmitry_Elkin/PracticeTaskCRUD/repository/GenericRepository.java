package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Status;

import java.util.List;

public interface GenericRepository<T,ID> {
    void addOrUpdate(T item);
    List<T> getAll(Status status);
    T getById(ID id);
    void delete(T item);
    void unDelete(T item);

}
