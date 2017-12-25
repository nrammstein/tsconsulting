package ru.killingmashine.task.one;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class test {

    public static void main(String[] args) throws IOException {

        int i1 = new Integer(10);
        Integer i2 = new Integer(10);

        Double d1 = new Double(10d);
        Double d2 = 10d;


        System.out.println(i1 == i2);
        System.out.println(d1 == d2);
    }
}
