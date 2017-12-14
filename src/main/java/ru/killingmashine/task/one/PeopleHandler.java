package ru.killingmashine.task.one;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PeopleHandler {
    private List<People> peopleList;
    private Set<String> departamentNames=new HashSet<String>();

    public PeopleHandler(List<People> peopleList){
        this.peopleList=peopleList;
    }

    public double salaryAvgInDepartament(String departamentname){
        int sumSalaryDepartament=0;
        double avgSalaryDepartament=0;
        int countPeopleInDepartament=0;
        for (int i = 0; i < peopleList.size(); i++) {
            if (departamentname.equals(peopleList.get(i).getDepartament().getDepartamentName())){
                sumSalaryDepartament+=peopleList.get(i).getSalary();
                countPeopleInDepartament++;
            }
            if (i == (peopleList.size()-1)){
                try {
                    avgSalaryDepartament=sumSalaryDepartament/countPeopleInDepartament;
                } catch (ArithmeticException e) {
                    System.out.println("Не известный департамент или в нем нет сотрудников");
                }
            }
        }
        return avgSalaryDepartament;
    }

    private void departamentsNamesHashSet(){
        for (int i = 0; i < peopleList.size(); i++) {
            departamentNames.add(peopleList.get(i).getDepartament().getDepartamentName());
        }
    }

    public void salaryAvgInDepartament(){
        departamentsNamesHashSet();
        Iterator<String> iterator=departamentNames.iterator();
        String departament;
        while (iterator.hasNext()){
            int sumSalaryDepartament=0;
            int countPeopleInDepartament=0;
            double avgSalaryInDepartament=0.0;
            departament=iterator.next();
            for (int j = 0; j < peopleList.size(); j++) {
                if(departament.equals(peopleList.get(j).getDepartament().getDepartamentName())){
                    sumSalaryDepartament+=peopleList.get(j).getSalary();
                    countPeopleInDepartament++;
                }
            }
            try {
                avgSalaryInDepartament=sumSalaryDepartament/countPeopleInDepartament;
            } catch (ArithmeticException e) {
                System.out.println("Нет сотрудников в департаменте ");
            }
            System.out.printf("Департамент - %s, средняя зп - %.1f %n", departament,avgSalaryInDepartament);
        }
    }

    public void ChangePeopleInDepartament(){}

    public void peoplesInDepartament(){
        departamentsNamesHashSet();
        Iterator<String> iterator = departamentNames.iterator();
        String departamentsName;
        while (iterator.hasNext()) {
            departamentsName=iterator.next();
            for (int i = 0; i < peopleList.size(); i++) {
                if (departamentsName.equals(peopleList.get(i).getDepartament().getDepartamentName())){
                    System.out.printf("%s       %s      %d%n",departamentsName, peopleList.get(i).getName(), peopleList.get(i).getSalary());
                }
            }
            System.out.println();
        }
    }

}
