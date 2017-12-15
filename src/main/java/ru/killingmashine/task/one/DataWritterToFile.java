package ru.killingmashine.task.one;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class DataWritterToFile implements DataWritter {
    private String fileName;

    public DataWritterToFile(String fileName) {
        this.fileName = fileName;
    }

    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    private File file;

    public void clearFile(String fileName){
        file = new File(fileName);
        if (file.exists()){
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Ошибка создания файла - " + e.getMessage());
            }
        }
    }
    public void writeData(List<String> list) {
        Iterator<String> iterator = list.iterator();
        try {
            fileWriter=new FileWriter(new File(fileName),true);
            bufferedWriter=new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
        String line;
        while (iterator.hasNext()){
            line=iterator.next();
            try {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл: " + e.getMessage());
            }
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия bufferedWriter: " + e.getMessage());
        }
    }
}
