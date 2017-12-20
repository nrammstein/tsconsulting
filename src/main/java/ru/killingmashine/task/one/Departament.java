package ru.killingmashine.task.one;

import java.util.ArrayList;
import java.util.List;

public class Departament {
    private String departamentName;
    private List<People> peopleList =null;

    public Departament(String departamentName)
    {
        this.departamentName=departamentName;
    }

    public String getDepartamentName(){
        return departamentName;
    }

    public void addNewPeopleInDepartament(People people){
        getPeopleInDepartament().add(people);
    }

    public List<People> getPeopleInDepartament(){
        if (peopleList == null) {
            peopleList=new ArrayList<People>();
        }
        return peopleList;
    }

    @Override
    public String toString() {
        return getDepartamentName();
    }
}
