package ru.killingmashine.task.one;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Just do it %n");
        GetDataFromFile getDataFromFile = new GetDataFromFile("src\\main\\resources\\Departaments.txt");
//        getDataFromFile.getData();
        ArrayList<String> list = (ArrayList<String>) getDataFromFile.getData();
        for (String s :
                list) {
            System.out.println(s);
        }
    }
}
