package ru.killingmashine.task.one;


import java.util.*;

public class PeopleHandler {
    private List<People> peopleList;
    private Set<String> departamentNames=new HashSet<String>();
    private HashMap<String, Double> avgDepartamentsSalaryMap = new HashMap<String, Double>();
    private List<String> listForWritter;

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

    public List<String> salaryAvgInDepartament(){
        listForWritter= new ArrayList<String>();
//        inicialize departamentsNames - hashset
        if (departamentNames.size()==0) {
            departamentsNamesHashSet();
        }
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
            listForWritter.add(String.format("Департамент - %s, средняя зп - %.1f %n", departament,avgSalaryInDepartament));
            System.out.printf("Департамент - %s, средняя зп - %.1f %n", departament,avgSalaryInDepartament);
        }
        return listForWritter;
    }

    private void calculationAvgSalaryInDepartamentHashMap(List<People> listPeople){
        if (departamentNames.size()==0) {
            departamentsNamesHashSet();
        }
        Iterator<String> iterator=departamentNames.iterator();
        String departament;
        while (iterator.hasNext()){
            int sumSalaryDepartament=0;
            int countPeopleInDepartament=0;
            double avgSalaryInDepartament=0.0;
            departament=iterator.next();
            for (int j = 0; j < listPeople.size(); j++) {
                if(departament.equals(listPeople.get(j).getDepartament().getDepartamentName())){
                    sumSalaryDepartament+=listPeople.get(j).getSalary();
                    countPeopleInDepartament++;
                }
            }
            try {
                avgSalaryInDepartament=sumSalaryDepartament/countPeopleInDepartament;
            } catch (ArithmeticException e) {
                System.out.println("Нет сотрудников в департаменте ");
            }
            avgDepartamentsSalaryMap.put(departament,avgSalaryInDepartament);
        }
    }

    public List<String> ChangePeopleInDepartament(){
        listForWritter = new ArrayList<String>();
        String oldDepartament;
        String newDepartament;
        double oldSalaryDepartament;
        double newSalaryDepartament;
        int counNumberChange=0;
        People people;

//        inicialize departamentNames
        calculationAvgSalaryInDepartamentHashMap(peopleList);
        if (departamentNames.size()==0) {
            departamentsNamesHashSet();
        }
//        System.out.println("Начальная мапа");
//        printMap(avgDepartamentsSalaryMap);

        for (int i = 0; i < peopleList.size(); i++) {
            people=peopleList.get(i);
            oldDepartament=people.getDepartament().getDepartamentName();
            oldSalaryDepartament=avgDepartamentsSalaryMap.get(oldDepartament);
            Iterator<String> iterator = departamentNames.iterator();
            while (iterator.hasNext()){
                newDepartament=iterator.next();
                if (! oldDepartament.equals(newDepartament)){
                    newSalaryDepartament=avgDepartamentsSalaryMap.get(newDepartament);
//                    System.out.println(people.toString());
                    people.getDepartament().setDepartamentName(newDepartament);
//                    System.out.println(people.toString());
                    calculationAvgSalaryInDepartamentHashMap(peopleList);
                    if (oldSalaryDepartament<avgDepartamentsSalaryMap.get(oldDepartament) && newSalaryDepartament<avgDepartamentsSalaryMap.get(newDepartament)){
                        counNumberChange++;
                        listForWritter.add(("Вариант перемещения № " + counNumberChange ));
                        listForWritter.add(people.toString());
                        System.out.println("Вариант перемещения № " + counNumberChange);
                        System.out.println(people.toString() + "    Состоит в " + oldDepartament + " отделе");
                        printMap(avgDepartamentsSalaryMap);
                    }
                    people.getDepartament().setDepartamentName(oldDepartament);
//                    System.out.println(people.toString());
                }
            }
        }
        return listForWritter;
    }
    private void printMap(HashMap<String,Double> hashMap){
        Iterator<Map.Entry<String, Double>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Double> pair = iterator.next();
            System.out.println("Key: " + pair.getKey() + " - Value: " + pair.getValue());
        }
        System.out.println("end print map");
    }

    public List<String> peoplesInDepartament(){
        listForWritter=new ArrayList<String>();
//        inicialize departamentNames - hashset
        if (departamentNames.size()==0) {
            departamentsNamesHashSet();
        }
        Iterator<String> iterator = departamentNames.iterator();
        String departamentsName;
        while (iterator.hasNext()) {
            departamentsName=iterator.next();
            for (int i = 0; i < peopleList.size(); i++) {
                if (departamentsName.equals(peopleList.get(i).getDepartament().getDepartamentName())){
                    System.out.printf("%s       %s      %d%n",departamentsName, peopleList.get(i).getName(), peopleList.get(i).getSalary());
                    listForWritter.add(String.format("%s       %s      %d%n",departamentsName, peopleList.get(i).getName(), peopleList.get(i).getSalary()));
                }
            }
            System.out.println();
        }
        return listForWritter;
    }

}
