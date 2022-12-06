package com.Dmitry_Elkin.PracticeTaskCRUD.model;

//содержит методы необходимые для MyGenericRepositoryImpl - без них java не может узнать что у дженерика есть эти методы
public interface BaseModelsMethsI {
    long getId();
    void setNewId();
    void setDeleted();
    void setUnDeleted();

}
