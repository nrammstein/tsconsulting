package ru.killingmashine.task.one;

import java.math.BigDecimal;

public class People {
    private String name;
    private BigDecimal salary;

    public People(String name, BigDecimal salary){
        this.name=name;
        this.salary=salary;
    }

    public String getName(){
        return name;
    }

    public BigDecimal getSalary(){
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s;%s", name,salary);
    }
}
