package ru.killingmashine.task.one;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PeopleHandler {
    private Set<Departament> departamentSet;

    public PeopleHandler(Set<Departament> departamentSet)
    {
        this.departamentSet=departamentSet;
    }

    public void printVariablesChangePeopleInDepartament(){
        Iterator<Departament> iterator = departamentSet.iterator();
//        цикл по департаментам
        while (iterator.hasNext()){
            Departament departament = iterator.next();
            List<People> peopleList = departament.getPeopleInDepartament();
            BigDecimal sumSalaryInDepartament = new BigDecimal(0);
            BigDecimal avgSalaryInDepartament;
//            цикл по люд€м в конкретном департаменте, считаем среднюю зп
            for (int i = 0; i <peopleList.size() ; i++) {
                People people = peopleList.get(i);
                sumSalaryInDepartament=sumSalaryInDepartament.add(people.getSalary());
            }
            avgSalaryInDepartament=sumSalaryInDepartament.divide(new BigDecimal(peopleList.size()),2,RoundingMode.HALF_EVEN);

//            рассматриваем возможности перевода людей из этого департамента
//            провер€ем будет ли средн€€ зп отдела больше при переводе сотрудника
//            еще раз цикл по сотрудникам

            for (int i = 0; i < peopleList.size(); i++) {
                People people = peopleList.get(i);
                BigDecimal sumSalaryInDepartamentNew=new BigDecimal(0);
                BigDecimal avgSalaryInDepartamentNew;
                sumSalaryInDepartamentNew=sumSalaryInDepartament.subtract(people.getSalary());
                avgSalaryInDepartamentNew=sumSalaryInDepartamentNew.divide(new BigDecimal(peopleList.size()-1),2,RoundingMode.HALF_EVEN);
                if ((avgSalaryInDepartament.compareTo(avgSalaryInDepartamentNew)) == -1){
//                    если мы тут, значит перевод сотрудника увеличил среднюю зп в отделе, дальше ищем куда его можно перевести
                    Iterator<Departament> iteratorSecond = departamentSet.iterator();
//                    присматриваем новый отдел дл€ нашего сотрудника
                    while (iteratorSecond.hasNext()){
                        Departament departamentSecond = iteratorSecond.next();
//                        провер€ем чтобы департамент был другой
                        if (!departament.getDepartamentName().equals(departamentSecond.getDepartamentName())){
//                            суда попадаем если имена отделов не совпадают, дальше считаем среднюю зп отдела куда переводим
                            List<People> listPeopleSecond = departamentSecond.getPeopleInDepartament();
                            BigDecimal sumSalaryInNewDepartament=new BigDecimal(0);
                            BigDecimal avgSalaryInNewDepartament;
                            for (int j = 0; j < listPeopleSecond.size(); j++) {
                                People peopleSecond = listPeopleSecond.get(j);
                                sumSalaryInNewDepartament=sumSalaryInNewDepartament.add(peopleSecond.getSalary());
                            }
                            avgSalaryInNewDepartament=sumSalaryInNewDepartament.divide(new BigDecimal(listPeopleSecond.size()),2,RoundingMode.HALF_EVEN);
//                            тут высчитываем среднюю зп нового отдела после предпологаемого перевода сотрудника
                            BigDecimal sumSalaryInNewDepartamentSecond = sumSalaryInNewDepartament.add(people.getSalary());
                            BigDecimal avgSalaryInNewDepartamentSecond = sumSalaryInNewDepartamentSecond.divide(new BigDecimal(listPeopleSecond.size()+1),2,RoundingMode.HALF_EVEN);
//                            сравниваем среднюю зп после перевода
                            if ((avgSalaryInNewDepartament.compareTo(avgSalaryInNewDepartamentSecond))==-1){
//                                если мы тут, перевод сотрудника в этот отдел нас удовлетвор€ет
                                System.out.printf("¬озможен перевод сотрудника %s из отдела - %s в отдел - %s%n  —редн€€ зп старого отдела до перевода - %s%n" +
                                        "—редн€€ зп старого отдела после перевода - %s%n—редн€€ зп нового отдела до перевода - %s%n—редн€€ зп нового отдела после перевода - %s%n%n",
                                        people.getName(),departament.getDepartamentName(),departamentSecond.getDepartamentName(),avgSalaryInDepartament,avgSalaryInDepartamentNew,
                                        avgSalaryInNewDepartament,avgSalaryInNewDepartamentSecond);
                            }
                        }
                    }
                }
            }
        }


    }

}
