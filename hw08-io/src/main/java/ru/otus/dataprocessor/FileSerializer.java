package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        Gson generateJson = new Gson();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            generateJson.toJson(data, bufferedWriter);
            bufferedWriter.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}