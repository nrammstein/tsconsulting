package ru.killingmashine.task.one;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class DataLoaderFromFile implements DataLoader {
    private String fileName;

    public DataLoaderFromFile(String filename) {
        this.fileName = filename;
    }

     private File file;
     private FileReader fileReader;
     private BufferedReader bufferedReader;
     private Departament departament;
     private Set<Departament> departamentList = new HashSet<Departament>();

    public Set<Departament> getData() {
        try {
            file = new File(fileName);
            fileReader= new FileReader(file);
            bufferedReader=new BufferedReader(fileReader);
            String line;
            String [] masPeople;
            while((line=bufferedReader.readLine()) != null){
                boolean flagDepartamentInSet=false;
                masPeople = line.split(";");
                String departamentNameInFile = masPeople[0];
                String peopleNameInFile = masPeople[1];
                BigDecimal peopleSalaryInFile = new BigDecimal(masPeople[2]);
                if (departamentList.isEmpty()){
                    departament=new Departament(departamentNameInFile);
                    departament.addNewPeopleInDepartament(new People(peopleNameInFile,peopleSalaryInFile));
                    departamentList.add(departament);
                    continue;
                }
                Iterator<Departament> iterator = departamentList.iterator();
                while (iterator.hasNext()){
                    departament=iterator.next();
                    String departamentName=departament.getDepartamentName();
                    if (departamentName.equals(departamentNameInFile)){
                        departament.addNewPeopleInDepartament(new People(peopleNameInFile,peopleSalaryInFile));
                        flagDepartamentInSet=true;
                    }
                }
                if(!flagDepartamentInSet){
                    departament=new Departament(departamentNameInFile);
                    departament.addNewPeopleInDepartament(new People(peopleNameInFile,peopleSalaryInFile));
                    departamentList.add(departament);
                }
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
        return departamentList;
    }
}
