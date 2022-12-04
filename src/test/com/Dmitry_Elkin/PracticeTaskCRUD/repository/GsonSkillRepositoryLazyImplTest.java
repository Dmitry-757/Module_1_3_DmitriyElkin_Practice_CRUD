package com.Dmitry_Elkin.PracticeTaskCRUD.repository;


import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;

class GsonSkillRepositoryLazyImplTest {



    @Test
    void addOrUpdate() {
        Skill skill1 = new Skill(1, "Linear algebra");
        Skill skill2 = new Skill(2, "java core");

        GsonSkillRepositoryLazyImpl gsonSkillRepositoryLazy = new GsonSkillRepositoryLazyImpl();
        gsonSkillRepositoryLazy.addOrUpdate(skill1);
        gsonSkillRepositoryLazy.addOrUpdate(skill2);
        HashMap<Long, Skill> skillSet = gsonSkillRepositoryLazy.getSkillSet();
        skillSet.forEach((key, value) -> System.out.println(key + " " + value.getName()));
        assertThat(skillSet)
                .containsValue(skill1)
                .containsValue(skill2);
    }


}