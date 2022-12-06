package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Specialty;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class GsonSpecialtyRepositoryImplTest {
    Path file = Paths.get("specialty.json");
    Specialty item1 = new Specialty("developer");
    Specialty item2 = new Specialty("qa");
    GenericRepositoryMyImpl<Specialty> gsonRepository = new GenericRepositoryMyImpl<>(Specialty.class);


    @Test
    void addOrUpdate() {

        System.out.println("****** add **********");
        gsonRepository.addOrUpdate(item1);
        gsonRepository.addOrUpdate(item2);

        try {
            List<String> list = Files.readAllLines(file);
            list.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n"+"***** update now ********");

        item2.setName(item2.getName()+" updated");
        item2.setId(2);
        gsonRepository.addOrUpdate(item2);

        try {
            List<String> list = Files.readAllLines(file);
            list.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void getAll() {
        List<Specialty> list = gsonRepository.getAll();
        list.forEach(System.out::println);
        item1.setId(1);
        item2.setId(2);
        item2.setName(item2.getName()+" updated");

        assertThat(list)
                .containsAll(List.of(item1, item2));

    }


}