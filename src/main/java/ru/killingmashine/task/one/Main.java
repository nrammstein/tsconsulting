package ru.killingmashine.task.one;

public class Main {
    public static void main(String [] args) {
        System.out.printf("Just do it %n");
        String writeFileName="target\\DepartamentsResult.txt";
        DataLoader dataLoaderFromFile = new DataLoaderFromFile("src\\main\\resources\\Departaments.txt");
        PeopleHandler peopleHandler = new PeopleHandler(dataLoaderFromFile.getData());
//        peopleHandler.printVariablesChangePeopleInDepartament();
        peopleHandler.printManyPeopleVariablesChangeInDepartament();

    }
}
