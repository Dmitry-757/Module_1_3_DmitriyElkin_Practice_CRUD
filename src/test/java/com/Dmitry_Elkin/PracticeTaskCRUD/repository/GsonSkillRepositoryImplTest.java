package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GsonSkillRepositoryImplTest {
    Path file = Paths.get("skills.json");
    Skill skill1 = new Skill("Linear algebra");
    Skill skill2 = new Skill("java EE");
    GsonSkillRepositoryImpl gsonSkillRepository = new GsonSkillRepositoryImpl();

    @Test
    void addOrUpdate() {

        gsonSkillRepository.addOrUpdate(skill1);
        gsonSkillRepository.addOrUpdate(skill2);

        try {
            List<String> list = Files.readAllLines(file);
            list.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAll() {
        List<Skill> list = gsonSkillRepository.getAll();
        list.forEach(System.out::println);
        skill1.setId(1);
        skill2.setId(2);
        assertThat(list)
                .containsAll(List.of(skill1, skill2));
    }

    @Test
    void getById() {
        skill1.setId(1);
        Skill skill = gsonSkillRepository.getById(1L);
        System.out.println(skill);
        assertThat(skill).isEqualTo(skill1);
    }

    @Test
    void delete() {
    }
}