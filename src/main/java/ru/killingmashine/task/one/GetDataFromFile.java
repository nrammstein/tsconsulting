package ru.killingmashine.task.one;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GetDataFromFile implements Data {
    public GetDataFromFile(String fileName){
     file = new File(fileName);
    }

     private File file;
     private ArrayList data = new ArrayList<String>();
     private FileReader fileReader;
     private BufferedReader bufferedReader;

    public List getData() {
        try {
            fileReader= new FileReader(file);
            bufferedReader=new BufferedReader(fileReader);
            String line;
            while((line=bufferedReader.readLine()) != null){
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Create file: " + file.getName()+ "- message: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Read file: " + file.getName() + "- message:" + e.getMessage());
        }


        return data;
    }
}
