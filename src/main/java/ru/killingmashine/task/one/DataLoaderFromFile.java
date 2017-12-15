package ru.killingmashine.task.one;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataLoaderFromFile implements DataLoader {
    private String fileName;

    public DataLoaderFromFile(String filename) {
        this.fileName = filename;
    }

     private File file;
     private FileReader fileReader;
     private BufferedReader bufferedReader;
     private People people;
     private Departament peopleDepartament;
     private List peopleList = new ArrayList<People>();

    public List<People> getData() {
        try {
            file = new File(fileName);
            fileReader= new FileReader(file);
            bufferedReader=new BufferedReader(fileReader);
            String line;
            String [] masPeople;
            while((line=bufferedReader.readLine()) != null){
                masPeople = line.split(";");
                peopleDepartament=new Departament(masPeople[0]);
                String peopleName = masPeople[1];
                int peopleSalary = Integer.parseInt(masPeople[2]);
                people=new People(peopleName,peopleSalary,peopleDepartament);
                peopleList.add(people);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Create file: " + file.getName()+ "- message: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Read file: " + file.getName() + "- message:" + e.getMessage());
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия bufferedReader: " + e.getMessage());
        }
        return peopleList;
    }
}
