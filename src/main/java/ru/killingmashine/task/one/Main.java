package ru.killingmashine.task.one;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Just do it %n");
        String writeFileName="target\\DepartamentsResult.txt";
        DataLoader dataLoaderFromFile = new DataLoaderFromFile("src\\main\\resources\\Departaments.txt");
        DataWritter dataWritter = new DataWritterToFile(writeFileName);
        PeopleHandler peopleHandler = new PeopleHandler(dataLoaderFromFile.getData());
        dataWritter.clearFile(writeFileName);
        dataWritter.writeData(peopleHandler.changePeopleInDepartament());

    }
}
