package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Developer;
import com.Dmitry_Elkin.PracticeTaskCRUD.model.Skill;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {
//    private static final String fileName = "skills.json";
//    private static final String tmpFileName = "skills.tmp";
//    private static final Path file = Paths.get(fileName);
//    private static final Path tmpFile = Path.of(tmpFileName);

    final Class<Developer> typeParameterClass = Developer.class;
    //    private static final String fileName = "developer.json";
    private final String fileName;
    private final String tmpFileName;
    private final Path file;
    private final Path tmpFile;

    private final Gson gson = new GsonBuilder()
//            .setPrettyPrinting() //formats json-file to well done form
            .create();

    public GsonDeveloperRepositoryImpl() {
        this.fileName = typeParameterClass.getName().toLowerCase() + ".json";
        this.tmpFileName = typeParameterClass.getName().toLowerCase() + ".tmp";
        this.file = Paths.get(fileName);
        this.tmpFile = Path.of(tmpFileName);
    }


    @Override
    public List<Developer> getAll() {
        List<Developer> list = new LinkedList<>();
        try {
            List<String> lines = Files.readAllLines(file);
            for (String jsonStr : lines) {
                Developer item = new Gson().fromJson(jsonStr, Developer.class);
//                System.out.println(skill);
                list.add(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Developer getById(Long id) {
        if (!Files.exists(file)) {
            System.out.println("The db file "+fileName+" is absent!");
            return null;
        }
        String jsonStr;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                jsonStr = sc.nextLine();
                Developer item = new Gson().fromJson(jsonStr, Developer.class);
                if (item.getId() == id) {
                    return item;
                }
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("oops! some io exception was occurred "+e.getMessage());
        }

        return null;
    }

    @Override
    public void addOrUpdate(Developer item) {
        //*** add ***
        if (item.getId() <= 0) {
            item.setNewId();
            add(item);
        }

        //*** update ***
        update(item);
    }


    public void add(Developer item){
        try {
            if (Files.exists(file)) {
                Files.write(file, List.of(gson.toJson(item)), StandardOpenOption.APPEND);
            } else {
                Files.write(file, List.of(gson.toJson(item)), StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("oops, IO exception was occurred (( " + e.getMessage());
        }
    }

    public void update(Developer item){
        try(
                BufferedReader in = new BufferedReader(new FileReader(fileName));
                BufferedWriter out = new BufferedWriter(new FileWriter(tmpFileName));
                )
        {
            String jsonStr;
            Skill skill;
            while((jsonStr=in.readLine())!=null)  {
                skill = new Gson().fromJson(jsonStr, Skill.class);

                if (skill.getId() == item.getId()) {
                    //line = line.replace(stringToReplace, replaceWith);
                    jsonStr = gson.toJson(item);
                }

                out.write(jsonStr);
                out.newLine();
            }
        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
            System.out.println("oops! File not found! "+e.getMessage());
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("oops! some IO exception : "+e.getMessage());
        }
        try {
//            Files.copy(tmpFile, file, REPLACE_EXISTING);
//            Files.delete(tmpFile);
            Files.move(tmpFile, file, REPLACE_EXISTING);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("oops! some IO exception : "+e.getMessage());
        }

    }

    @Override
    public void delete(Developer item) {
        item.setDeleted();
        update(item);
    }

    public void unDelete(Developer item) {
        item.setUnDeleted();
        update(item);
    }



}