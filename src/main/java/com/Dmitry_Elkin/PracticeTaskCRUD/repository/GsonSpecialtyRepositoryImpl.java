package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Specialty;
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

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {
    final Class<Specialty> typeParameterClass = Specialty.class;
//    private static final String fileName = "specialty.json";
    private final String fileName;
    private final String tmpFileName;
    private final Path file;
    private final Path tmpFile;

    private final Gson gson = new GsonBuilder()
//            .setPrettyPrinting() //formats json-file to well done form
            .create();

    public GsonSpecialtyRepositoryImpl() {
        this.fileName = typeParameterClass.getName().toLowerCase() + ".json";
        this.tmpFileName = typeParameterClass.getName().toLowerCase() + ".tmp";
        this.file = Paths.get(fileName);
        this.tmpFile = Path.of(tmpFileName);
    }

    @Override
    public List<Specialty> getAll() {
        List<Specialty> list = new LinkedList<>();
        try {
            List<String> lines = Files.readAllLines(file);
            for (String jsonStr : lines) {
                Specialty item = new Gson().fromJson(jsonStr, Specialty.class);
                list.add(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public Specialty getById(Long id) {
        if (!Files.exists(file)) {
            System.out.println("The db-file is absent!");
            return null;
        }
        String jsonStr;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                jsonStr = sc.nextLine();
                Specialty item = new Gson().fromJson(jsonStr, Specialty.class);
                if (item.getId() == id) {
                    return item;
                }
            }
        } catch (IOException e) {
            System.out.println("oops! some io exception was occurred "+e.getMessage());
//            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void addOrUpdate(Specialty item) {
        //*** add ***
        if (item.getId() <= 0) {
            item.setNewId();
            add(item);
        }

        //*** update ***
        update(item);
    }


    public void add(Specialty item){
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

    public void update(Specialty item){
        try(
                BufferedReader in = new BufferedReader(new FileReader(fileName));
                BufferedWriter out = new BufferedWriter(new FileWriter(tmpFileName));
                )
        {
            String jsonStr;
            Specialty updatingItem;
            while((jsonStr=in.readLine())!=null)  {
                updatingItem = new Gson().fromJson(jsonStr, Specialty.class);

                if (updatingItem.getId() == item.getId()) {
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
    public void delete(Specialty item) {
        item.setDeleted();
        update(item);
    }

    @Override
    public void unDelete(Specialty item) {
        item.setUnDeleted();
        update(item);
    }

}
