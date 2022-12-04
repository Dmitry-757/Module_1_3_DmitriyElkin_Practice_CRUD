package com.Dmitry_Elkin.PracticeTaskCRUD;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import com.Dmitry_Elkin.PracticeTaskCRUD.repository.GsonSkillRepositoryLazyImpl;

import java.util.Arrays;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {

        Skill skill1 = new Skill(1, "Linear algebra");
        Skill skill2 = new Skill(2, "java core");




        GsonSkillRepositoryLazyImpl gsonSkillRepositoryLazy = new GsonSkillRepositoryLazyImpl();
        gsonSkillRepositoryLazy.addOrUpdate(skill1);
        gsonSkillRepositoryLazy.addOrUpdate(skill2);
        HashMap<Long, Skill> skillSet = gsonSkillRepositoryLazy.getSkillSet();
        skillSet.forEach((key, value) -> System.out.println(key + " " + value.getName()));

    }
}
