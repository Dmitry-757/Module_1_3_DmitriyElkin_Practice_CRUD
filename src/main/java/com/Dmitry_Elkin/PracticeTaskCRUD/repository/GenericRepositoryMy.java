package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import java.util.List;

public interface GenericRepositoryMy<T,ID> {
    List<T> getAll();
    T getById(ID id);
    void addOrUpdate(T item);
    void delete(T item);
    void unDelete(T item);
}
