package ru.killingmashine.task.one;

public class People {
    private String name;
    private int salary;
    private Departament departament;

    public People(String name, int salary, Departament departament){
        this.name=name;
        this.salary=salary;
        this.departament=departament;
    }
    public String getName(){
        return name;
    }

    public int getSalary(){
        return salary;
    }

    public Departament getDepartament() {
        return departament;
    }

    @Override
    public String toString() {
        return String.format("%s;%d;%s", name,salary,departament.getDepartamentName());
    }
}
