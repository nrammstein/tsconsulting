package ru.killingmashine.task.one;

import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {
        File file = new File("src\\main\\resources\\Departaments.txt");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedreader = new BufferedReader(reader);
        String line;
        while ((line=bufferedreader.readLine()) != null){
            System.out.println(line);
        }
    }
}
