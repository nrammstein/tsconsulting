package ru.killingmashine.task.one;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PeopleHandler {
    private Set<Departament> departamentSet;
    private List<People> peopleList;
    private HashMap<String, BigDecimal> avgDepartamentsSalaryMap = new HashMap<String, BigDecimal>();
    private List<String> listForWritter =new ArrayList<String>();
    private Departament departament;
    private List<String> departamentSetAsList =new ArrayList<String>();
    private People people;

    public PeopleHandler(Set<Departament> departamentSet)
    {
        this.departamentSet=departamentSet;
    }

    private void printMap(HashMap<String,BigDecimal> hashMap){
        Iterator<Map.Entry<String, BigDecimal>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, BigDecimal> pair = iterator.next();
            System.out.println("Департамент: " + pair.getKey() + " - Средняя зп: " + pair.getValue());
            listForWritter.add("Департамент: " + pair.getKey() + " - Средняя зп: " + pair.getValue());
        }
        System.out.println();
    }

    private void viewDepartamentSetAsStringList(){
        Iterator<Departament> iterator = departamentSet.iterator();
        departamentSetAsList.clear();
        while (iterator.hasNext()){
            departament=iterator.next();
            peopleList=departament.getPeopleInDepartament();
            for (int i = 0; i < peopleList.size(); i++) {
                people=peopleList.get(i);
                departamentSetAsList.add(departament.getDepartamentName()+";"+people.getName()+";"+people.getSalary());
            }
        }
    }

    private void calculationAvgSalaryInDepartament(List<String> listDepartamentsAndPeople){
        String departamentName;
        Set<String> departamentsNamesInSetList= new HashSet<String>();
        for (int i = 0; i < listDepartamentsAndPeople.size(); i++) {
            departamentsNamesInSetList.add(listDepartamentsAndPeople.get(i).split(";")[0]);
        }
        Iterator<String> iterator = departamentsNamesInSetList.iterator();
        while (iterator.hasNext()) {
            departamentName=iterator.next();
            BigDecimal sumSalaryDepartament=new BigDecimal(0);
            BigDecimal avgSalaryDepartament= new BigDecimal(0);
            int countPeopleInDepertament=0;
            for (int j = 0; j < listDepartamentsAndPeople.size(); j++) {
                if (departamentName.equals(listDepartamentsAndPeople.get(j).split(";")[0])){
                    sumSalaryDepartament=sumSalaryDepartament.add(new BigDecimal(listDepartamentsAndPeople.get(j).split(";")[2]));
                    countPeopleInDepertament++;
                }
            }
            avgSalaryDepartament=sumSalaryDepartament.divide(new BigDecimal(countPeopleInDepertament),2,RoundingMode.HALF_EVEN);
            avgDepartamentsSalaryMap.put(departamentName,avgSalaryDepartament);
        }
    }

    public List<String> changePeopleInDepartament(){
        if (departamentSetAsList.isEmpty()) viewDepartamentSetAsStringList();
        for (String s :
                departamentSetAsList) {
            System.out.println(s);
            listForWritter.add(s);
        }
        calculationAvgSalaryInDepartament(departamentSetAsList);
        System.out.println();
        printMap(avgDepartamentsSalaryMap);

        Set<String> departamentsNamesInSetList= new HashSet<String>();
        for (int i = 0; i < departamentSetAsList.size(); i++) {
            departamentsNamesInSetList.add(departamentSetAsList.get(i).split(";")[0]);
        }
        System.out.println("\nВарианты перевода: \n");
        listForWritter.add("Варианты перевода: ");
        for (int i = 0; i < departamentSetAsList.size(); i++) {
            Iterator<String> iterator = departamentsNamesInSetList.iterator();
            String oldDepName=departamentSetAsList.get(i).split(";")[0];
            while (iterator.hasNext()) {
                String newDepName=iterator.next();
                if(!oldDepName.equals(newDepName)){
                    BigDecimal oldSalaryDepartamentBeforeChange = avgDepartamentsSalaryMap.get(oldDepName);
                    BigDecimal newSalaryDepartamentBeforeChange = avgDepartamentsSalaryMap.get(newDepName);
                    departamentSetAsList.set(i,departamentSetAsList.get(i).replaceFirst(oldDepName,newDepName));
                    calculationAvgSalaryInDepartament(departamentSetAsList);
                    BigDecimal oldSalaryDepartamentAfterChange = avgDepartamentsSalaryMap.get(oldDepName);
                    BigDecimal newSalaryDepartamentAfterChange = avgDepartamentsSalaryMap.get(newDepName);

                    if((oldSalaryDepartamentBeforeChange.compareTo(oldSalaryDepartamentAfterChange))==-1 && (newSalaryDepartamentBeforeChange.compareTo(newSalaryDepartamentAfterChange))==-1){
                        System.out.printf("Переводим сотрудника %s, из отдела %s, в отдел %s. %n Средняя зп отдела до перевода: %s. %n Средняя зп отдела после перевода: %s%n " +
                                "Изменение зп отдела куда перевели сотрудника, до перевода: %s, после перевода: %s%n%n ",
                                departamentSetAsList.get(i).split(";")[1],oldDepName,newDepName,oldSalaryDepartamentBeforeChange,
                                oldSalaryDepartamentAfterChange,newSalaryDepartamentBeforeChange,newSalaryDepartamentAfterChange);
                        listForWritter.add(String.format("Переводим сотрудника %s, из отдела %s, в отдел %s. %n Средняя зп отдела до перевода: %s. %n Средняя зп отдела после перевода: %s%n " +
                                        "Изменение зп отдела куда перевели сотрудника, до перевода: %s, после перевода: %s%n%n ",
                                departamentSetAsList.get(i).split(";")[1],oldDepName,newDepName,oldSalaryDepartamentBeforeChange,
                                oldSalaryDepartamentAfterChange,newSalaryDepartamentBeforeChange,newSalaryDepartamentAfterChange));
                    }
                    departamentSetAsList.set(i,departamentSetAsList.get(i).replaceFirst(newDepName,oldDepName));
                    calculationAvgSalaryInDepartament(departamentSetAsList);
                }
            }
        }
    return listForWritter;
    }
}
