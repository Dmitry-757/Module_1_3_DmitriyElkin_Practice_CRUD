package com.Dmitry_Elkin.PracticeTaskCRUD.controller;


import com.Dmitry_Elkin.PracticeTaskCRUD.AppMain;
import com.Dmitry_Elkin.PracticeTaskCRUD.NonTechTask.myController.MyDeveloperController;
import com.Dmitry_Elkin.PracticeTaskCRUD.NonTechTask.myController.MyGenericController;
import com.Dmitry_Elkin.PracticeTaskCRUD.NonTechTask.myController.MySkillController;
import com.Dmitry_Elkin.PracticeTaskCRUD.NonTechTask.myController.MySpecialtyController;
import com.Dmitry_Elkin.PracticeTaskCRUD.model.Developer;
import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import com.Dmitry_Elkin.PracticeTaskCRUD.model.Specialty;
import com.Dmitry_Elkin.PracticeTaskCRUD.repository.RepositoryFactory;

import java.util.Scanner;

public class MainController {
    public static Scanner sc = new Scanner(System.in);

    public void upLevelMenu() {
        System.out.println("1 - work with Skills, " +
                " 2 - work with Specialties, " +
                " 3 - work with Developers," +
                " 0 - exit");
        if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> SkillController.menu();
                case 11 -> {
                    (new MyGenericController<Skill>(Skill.class, RepositoryFactory.getSkillRepository())).menu();
                }
                case 111 -> {
                    (new MySkillController()).menu();
                }

                case 2 -> SpecialtyController.menu();
                case 22 -> {
                    (new MyGenericController<Specialty>(Specialty.class, RepositoryFactory.getSpecialtyRepository())).menu();
                }
                case 222 -> {
                    (new MySpecialtyController()).menu();
                }

                case 3 -> DeveloperController.menu();
                case 33 -> {
                    (new MyGenericController<Developer>(Developer.class, RepositoryFactory.getDeveloperRepository())).menu();
                }
                case 333 -> {
                    (new MyDeveloperController()).menu();
                }

                //                case 2 -> SpecialtyController.menu();
//                case 3 -> DeveloperController.menu();
                case 0 -> AppMain.terminate = true;
                default -> System.out.println("Wrong input!");
            }
        } else {
            System.out.println("wrong input... Please, use only digits!");
            sc.nextLine();
        }
    }

}
