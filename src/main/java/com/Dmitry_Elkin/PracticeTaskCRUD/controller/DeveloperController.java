package com.Dmitry_Elkin.PracticeTaskCRUD.controller;


import com.Dmitry_Elkin.PracticeTaskCRUD.model.Developer;
import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import com.Dmitry_Elkin.PracticeTaskCRUD.model.Specialty;
import com.Dmitry_Elkin.PracticeTaskCRUD.model.Status;
import com.Dmitry_Elkin.PracticeTaskCRUD.repository.DeveloperRepository;
import com.Dmitry_Elkin.PracticeTaskCRUD.repository.RepositoryFactory;
import com.Dmitry_Elkin.PracticeTaskCRUD.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.Dmitry_Elkin.PracticeTaskCRUD.controller.MainController.sc;

public class DeveloperController {

    private static final DeveloperRepository repository = RepositoryFactory.getDeveloperRepository();
    private static final SkillRepository skillRepository = RepositoryFactory.getSkillRepository();

    //************* menu ********************
    public static void menu() {
        boolean goBack = false;
        while (!goBack) {
            System.out.println("1 - New item, 2 - change item, 3 - Delete item, 4 - UnDelete item, " +
                    "5 - print all items, 6 - print Active items, 7 - print Deleted items, 0 - go back");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1 -> createNewItem();
                    case 2 -> changeItem();
                    case 3 -> deleteItem();
                    case 4 -> unDeleteItem();
                    case 5 -> printItems(null);
                    case 6 -> printItems(Status.ACTIVE);
                    case 7 -> printItems(Status.DELETED);
                    case 0 -> goBack = true;
                    default -> System.out.println("Wrong input!");
                }
            } else {
                System.out.println("wrong input... Please, use only digits!");
                sc.nextLine();
            }
        }
    }

    private static void createNewItem() {

        Pattern pattern = Pattern.compile("^[a-zA-Zа-яА-Я\s]*");
        System.out.println("Input name of item");

        String firstName = getStringParamFromConsole("first name");
        String lastName = getStringParamFromConsole("second name");
        List<Skill> skills = getListFromConsole();
        Specialty specialty = null;
//        Status status;
        //String firstName, String lastName, List<Skill> skills, Specialty specialty
        repository.addOrUpdate(new Developer(firstName, lastName, skills, specialty));
    }

    private static String getStringParamFromConsole(String parameterName) {
        Pattern pattern = Pattern.compile("^[a-zA-Zа-яА-Я\s]*");
        System.out.println("Input " + parameterName);
        String strParam;
        while (true) {
            String line = sc.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                strParam = matcher.group();
                System.out.println(parameterName + " is: " + strParam);
                return strParam;
            } else {
                System.out.println("wrong input... Please, try again!");
            }
        }
    }

    private static List<Skill> getListFromConsole() {

        String strParam;
        List<Skill> result = new ArrayList<>();
        System.out.println("Choose from items:");


//        SkillController.printItems(Status.ACTIVE);
//        if (repository.getAll(Status.ACTIVE).size() == 0){
//            System.out.println("There is no Non-deleted items");
//            return;
//        }

        while (true) {
            System.out.println("\ncurrent items:");
            for (Skill item : skillRepository.getAll(Status.ACTIVE)) {
                System.out.println(item.toString());
            }
            System.out.println("Input id of chosen item, or type 0 for end of choosing");
            if (sc.hasNextLong()) {
                long id = sc.nextLong();
                if (id == 0) break;

                sc.nextLine();
                Skill item = skillRepository.getById(id);
                if (item != null) {
                    System.out.println("choosing item is : " + item.toString());
                    result.add(item);
                } else
                    System.out.println("item by id `" + id + "` was not found...");
            } else {
                System.out.println("wrong input...");
            }
        }

        return result;
    }


    private static void changeItem() {
        Pattern pattern = Pattern.compile("^[a-zA-Zа-яА-Я\s]*");
        printItems(Status.ACTIVE);// do not to disturb the dead
        System.out.println("Input id of changing item");
        if (sc.hasNextLong()) {
            long id = sc.nextLong();
            sc.nextLine();
            System.out.println("your choice = " + id);
            Developer item = repository.getById(id);
            if (item != null) {
                System.out.println("editing item = " + item.toString());

                System.out.println("Input new name of item");
                //sc.nextLine();
                String line = sc.nextLine();
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String newName = matcher.group();
                    item.setName(newName);
                    repository.addOrUpdate(item);
                    System.out.println("new name of item = " + newName);
                }
            } else
                System.out.println("item by id `" + id + "` is not found");
        } else {
            System.out.println("wrong input...");
        }
    }

    public static void printItems(Status status) {
        System.out.println("current items:");
        for (Developer item : repository.getAll(status)) {
            System.out.println(item.toString());
        }
    }


    private static void deleteItem() {
        System.out.println("Choose item from:");
        printItems(Status.ACTIVE);
        if (repository.getAll(Status.ACTIVE).size() == 0) {
            System.out.println("There is no Non-deleted items");
            return;
        }

        System.out.println("Input id of deleting item");
        if (sc.hasNextLong()) {
            long id = sc.nextLong();
            sc.nextLine();
            Developer item = repository.getById(id);
            if (item != null) {
                System.out.println("deleting item is : " + item.toString());
                repository.delete(item);
            } else
                System.out.println("item by id `" + id + "` was not found...");
        } else {
            System.out.println("wrong input...");
        }
    }

    private static void unDeleteItem() {
        System.out.println("Choose item from:");
        printItems(Status.DELETED);
        if (repository.getAll(Status.DELETED).size() == 0) {
            System.out.println("There is no deleted items");
            return;
        }

        System.out.println("Input id of UnDeleting item");
        if (sc.hasNextLong()) {
            long id = sc.nextLong();
            sc.nextLine();
            Developer item = repository.getById(id);
            if (item != null) {
                System.out.println("UnDeleting item is : " + item.toString());
                repository.unDelete(item);
            } else
                System.out.println("item by id `" + id + "` was not found...");
        } else {
            System.out.println("wrong input...");
        }
    }

    //*****************************************************
}
