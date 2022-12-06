package com.Dmitry_Elkin.PracticeTaskCRUD.model;

//содержит методы необходимые для GenericRepositoryMyImpl - без них java не может узнать что у дженерика есть эти методы
public interface BaseModelsMethsI {
    long getId();
    void setNewId();
    void setDeleted();
    void setUnDeleted();

}
