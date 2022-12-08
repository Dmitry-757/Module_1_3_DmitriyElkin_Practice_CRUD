package com.Dmitry_Elkin.PracticeTaskCRUD.NonTechTask.myController;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import com.Dmitry_Elkin.PracticeTaskCRUD.repository.RepositoryFactory;

public class MySkillController extends MyGenericController<Skill> {
    public MySkillController() {
        super(Skill.class, RepositoryFactory.getSkillRepository());
    }
}
