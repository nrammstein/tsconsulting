package ru.killingmashine.task.one;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Just do it %n");
        String writeFileName="target\\DepartamentsResult.txt";
        DataLoaderFromFile dataLoaderFromFile = new DataLoaderFromFile("src\\main\\resources\\Departaments.txt");
        DataWritterToFile dataWritterToFile = new DataWritterToFile(writeFileName);
        dataWritterToFile.clearFile(writeFileName);
//        DataLoaderFromFile dataLoaderFromFile = new DataLoaderFromFile(args[0]);
        PeopleHandler peopleHandler=new PeopleHandler(dataLoaderFromFile.getData());
        dataWritterToFile.writeData(peopleHandler.salaryAvgInDepartament());
        dataWritterToFile.writeData(peopleHandler.peoplesInDepartament());

//        double d;
//        d=peopleHandler.salaryAvgInDepartament("Бухгалтерия");
//        System.out.println(d);
//        System.out.println();
        //peopleHandler.salaryAvgInDepartament();
//        System.out.println();
//        peopleHandler.peoplesInDepartament();

//        System.out.println();
        dataWritterToFile.writeData(peopleHandler.ChangePeopleInDepartament());
    }
}
