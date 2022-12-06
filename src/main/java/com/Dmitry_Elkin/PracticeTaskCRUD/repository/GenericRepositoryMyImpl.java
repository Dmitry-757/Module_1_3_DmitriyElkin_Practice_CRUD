package com.Dmitry_Elkin.PracticeTaskCRUD.repository;

import com.Dmitry_Elkin.PracticeTaskCRUD.model.Model;
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

//public class GenericRepositoryMyImpl<T> implements SpecialtyRepository {
public class GenericRepositoryMyImpl<T extends Model> implements GenericRepositoryMy<T, Long> {
    final Class<T> typeParameterClass;
    private final String fileName;
    private final String tmpFileName;
    private final Path file;
    private final Path tmpFile;

    private static final Gson gson = new GsonBuilder()
//            .setPrettyPrinting() //formats json-file to well done form
            .create();

    public GenericRepositoryMyImpl(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
        this.fileName = typeParameterClass.getName().toLowerCase() + ".json";
        this.tmpFileName = typeParameterClass.getName().toLowerCase() + ".tmp";
        this.file = Paths.get(fileName);
        this.tmpFile = Path.of(tmpFileName);
    }


    @Override
    public List<T> getAll() {
        List<T> list = new LinkedList<>();
        try {
            List<String> lines = Files.readAllLines(file);
            for (String jsonStr : lines) {
                T item = (T) new Gson().fromJson(jsonStr, typeParameterClass);
                list.add(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public T getById(Long id) {
        if (!Files.exists(file)) {
            System.out.println("The db-file is absent!");
            return null;
        }
        String jsonStr;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                jsonStr = sc.nextLine();
                T item = new Gson().fromJson(jsonStr, typeParameterClass);
                if (item.getId() == id) {
                    return item;
                }
            }
        } catch (IOException e) {
            System.out.println("oops! some io exception was occurred "+e.getMessage());
        }

        return null;
    }

    @Override
    public void addOrUpdate(T item) {
        //*** add ***
        if (item.getId() <= 0) {
            item.setNewId();
            add(item);
        }

        //*** update ***
        update(item);
    }


    public void add(T item){
        try {
            if (Files.exists(file)) {
                Files.write(file, List.of(gson.toJson(item)), StandardOpenOption.APPEND);
            } else {
                Files.write(file, List.of(gson.toJson(item)), StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            System.out.println("oops, IO exception was occurred (( " + e.getMessage());
        }
    }

    public void update(T item){
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
                    jsonStr = gson.toJson(item);
                }

                out.write(jsonStr);
                out.newLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("oops! File not found! "+e.getMessage());
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("oops! some IO exception : "+e.getMessage());
        }
        try {
            Files.move(tmpFile, file, REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("oops! some IO exception : "+e.getMessage());
        }

    }

    @Override
    public void delete(T item) {
        item.setDeleted();
        update(item);
    }

    @Override
    public void unDelete(T item) {
        item.setUnDeleted();
        update(item);
    }

}
