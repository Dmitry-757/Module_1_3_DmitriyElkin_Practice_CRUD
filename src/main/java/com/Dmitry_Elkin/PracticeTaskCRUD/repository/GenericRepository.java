package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import java.util.List;

public interface GenericRepository<T,ID> {
    boolean addOrUpdate(T item);
    List<T> getAll();
    T getById(ID id);
    boolean delete(ID id);
}
