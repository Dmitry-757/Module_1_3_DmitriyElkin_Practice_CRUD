package com.Dmitry_Elkin.PracticeTaskCRUD.controller;


import com.Dmitry_Elkin.PracticeTaskCRUD.AppMain;
import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import com.Dmitry_Elkin.PracticeTaskCRUD.myRepository.GsonSkillRepositoryLazyImpl;
import com.Dmitry_Elkin.PracticeTaskCRUD.repository.RepositoryFactory;

import java.util.Scanner;

public class MainController {
    public static Scanner sc = new Scanner(System.in);

    public void upLevelMenu() {
        System.out.println("1 - work with Skills, 2 - work with Specialties, 3 - work with Developers, 0 - exit");
        if (sc.hasNextInt()) {
            int choice=sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> SkillController.menu();
                case 11 -> {
                    (new Controller<Skill>(Skill.class, RepositoryFactory.getSkillRepository())).menu();
                }
//                case 2 -> SpecialtyController.menu();
//                case 3 -> DeveloperController.menu();
                case 0 -> AppMain.terminate = true;
                default -> System.out.println("Wrong input!");
            }
        }
    }

}
